package models;

import Seguridad.CifradoSimetrico;
import Seguridad.HashMAC;
import Seguridad.Transformacion;
import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import javax.persistence.*;
import mediator.AlertMediator;
import mediator.IColleague;
import mediator.IMediator;

/**
 * Created by haes_ on 6/04/2017.
 */

@Entity
@Table(name="notificacionentity")
public class Notificacion extends Model implements IColleague {
    
    public AlertMediator mediator;
    
    public final static String DIRECCION = "localhost";
    public final static int PUERTO = 9000;
    
    private Socket socket;
    private SecretKey secretKey;
	
    private CifradoSimetrico simetrico;
    private HashMAC digest;
    
    public final static String ALG_SIM = "Blowfish";
    public final static String ALG_ASIM = "RSA";
    public final static String ALG_HMAC = "HMACSHA256";
    public final static String INICIAR = "INIT";
    public final static String FINALIZAR = "FIN";
    public final static String STATUS = "STATUS";
    public final static String OK = "OK";
    public final static String ERROR = "ERROR";
    public final static String ALGORITMOS = "ALGORITMOS";
    public final static String CERT_SERVIDOR = "CERTSRV";
    public final static String AUTENTICAR = "AUT";
    public final static String ESTADO_TUTELA = "STATTUTELA";
    public final static String INFORMACION = "INFO";
    public final static String RESULTADO = "RESULTADO";

    public static Finder<Long,Notificacion> FINDER = new Finder<>(Notificacion.class);

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private double presionSanguinea;

    private double frecuenciaCardiaca;

    private double nivelDeEstres;
    
    public List<String> mensajes = new ArrayList<String>();

    public Notificacion(){
        this.id=null;
        this.presionSanguinea=-1;
        this.frecuenciaCardiaca=-1;
        this.nivelDeEstres=-1;
        
       try
		{
			socket = new Socket(DIRECCION, PUERTO);
		}
		catch (Exception e)
		{
			System.out.println("Excepci?n del Socket: " + e.getMessage());
		}
    }
    
     public void run()
    {

		try
		{
			PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			boolean continuar = true;
			socketOut.println(INICIAR);
			
			while (continuar)
			{
				String msg = socketIn.readLine();
				verificarEstado(msg, continuar);
				
				socketOut.println(ALGORITMOS + ":" + ALG_SIM + ":" + ALG_ASIM + ":" + ALG_HMAC);
				
				msg = socketIn.readLine();
				verificarEstado(msg, continuar);
				
				socketOut.println(CERT_SERVIDOR);
				autenticarNotificacion(socketIn, socketOut);
				
				msg = socketIn.readLine();
//				System.out.println("Aut Certificado " + msg);
				verificarEstado(msg, continuar);
				msg = socketIn.readLine();
//				System.out.println("Aut Usuario " + msg);
				verificarEstado(msg, continuar);
				
				
				
				recibirInformacion(socketIn, socketOut);
				
				socketIn.close();
				socketOut.close();
				socket.close();
				
//				System.out.println("Finaliza la transacci?n");
				
			}
		}
		catch (Exception e)
		{
			String m = e.getMessage();
			
			if (m == "Stream closed")
			{
				
			}
			else
			{
				System.out.println("Error en la ejecuci?n: " + m);				
			}
		}
    }
     
     private void verificarEstado(String mensaje, boolean continuar) throws IOException
	{
		if (mensaje.split(":")[1] == OK)
		{
		}
		else if (mensaje.split(":")[1] == ERROR)
		{
			continuar = false;
		}
	}
    
    private void autenticarNotificacion(BufferedReader in, PrintWriter out)
	{
		try
		{
			X509Certificate cert = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(socket.getInputStream());
			KeyGenerator keygen = KeyGenerator.getInstance(ALG_SIM);
			secretKey = keygen.generateKey();
			
			Cipher cipher = Cipher.getInstance(ALG_ASIM);
			cipher.init(Cipher.ENCRYPT_MODE, cert.getPublicKey());
			byte[] cipheredKey = cipher.doFinal(secretKey.getEncoded());
			
			out.println(AUTENTICAR + ":" + Transformacion.transformar(cipheredKey));
		}
		catch (Exception e)
		{
			System.out.println("Excepci?n validando: " + e.getMessage());
		}
	}
    
    private void recibirInformacion(BufferedReader socketIn, PrintWriter socketOut)
	{
		try
		{
			String in = socketIn.readLine();
			String entrada[] = in.split(":");
			
			String resp = simetrico.descifrar(Transformacion.destransformar(entrada[1]));
			boolean verif = digest.verificar((Transformacion.destransformar(entrada[2])), resp);
			if (verif)
			{
				System.out.println("Estado de informacion: " + resp);
				socketOut.println(RESULTADO + ":" + OK + ":" + FINALIZAR);
			}
			
			else
			{
				System.out.println("Error en la informacion");
				socketOut.println(RESULTADO + ":" + ERROR + ":" + FINALIZAR);
			}
		}
		catch (Exception e)
		{
			System.out.println("Excepci?n finalizando: " + e.getMessage());
		}
	}

    public Notificacion(Long id) {
        this.id = id;
    }

    public Notificacion(double presionSanguinea, double frecuenciaCardiaca, double nivelDeEstres) {
        this.presionSanguinea = presionSanguinea;
        this.frecuenciaCardiaca = frecuenciaCardiaca;
        this.nivelDeEstres = nivelDeEstres;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPresionSanguinea() {
        return presionSanguinea;
    }

    public void setPresionSanguinea(double presionSanguinea) {
        this.presionSanguinea = presionSanguinea;
    }

    public double getFrecuenciaCardiaca() {
        return frecuenciaCardiaca;
    }

    public void setFrecuenciaCardiaca(double frecuenciaCardiaca) {
        this.frecuenciaCardiaca = frecuenciaCardiaca;
    }

    public double getNivelDeEstres() {
        return nivelDeEstres;
    }

    public void setNivelDeEstres(double nivelDeEstres) {
        this.nivelDeEstres = nivelDeEstres;
    }

    public static Notificacion bind(JsonNode j){
        double presion= j.findPath("presionSanguinea").asDouble();
        double frecuencia=j.findPath("frecuenciaSanguinea").asDouble();
        double estres=j.findPath("nivelDeEstres").asDouble();

        Notificacion not= new Notificacion(presion,frecuencia,estres);
        return not;
    }

    public void update(Notificacion nuevaNot){
        this.setFrecuenciaCardiaca(nuevaNot.getFrecuenciaCardiaca());
        this.setNivelDeEstres(nuevaNot.getNivelDeEstres());
        this.setPresionSanguinea(nuevaNot.getNivelDeEstres());
    }

    public void delete(){
        this.setFrecuenciaCardiaca(0.0);
        this.setPresionSanguinea(0.0);
        this.setNivelDeEstres(0.0);
    }

    public void setMensajes(List<String> mensajes) {
        this.mensajes = mensajes;
    }
    
    
    
    
    public void SendMessage(String message){
        
        
        mediator.Register(this);
        mediator.DistributeMessage(this, message);
        
    }

    public void ReceiveMessage(String message){
        
        mensajes.add(message);
        
    }
    
    
}
