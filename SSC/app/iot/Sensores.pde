 /* Sensores de Medicos-Play
 * ------------------ 
 * Experimento para Arquitectura de Software 2017-1
 * Universidad de los Andes
 * 
 * el ejemplo lee entradas analogas de los sensores de presion sanguinea y frecuencia cardiaca,
 * así como una señal digital para medir el nivel de estres y la transmite por el puerto serial
 * como un string cada segundo.
 * 
 * creado el 14 de marzo de 2017 por Harold Gonzalez
 */
 #include <string.h>
 // Selecciona el pin de entrada analoga a leer del sensor de presion sanguinea.
 int presionPin = 24;
 // Seleciona el pin de entrada analoga a leer del sensor de frecuencia cardiaca
 int frecPin= 24;
 // Selecciona el pin de entrada analoga a leer del sensor de nivel de estres
 int estresPin=24;
 
 // variable para guardar el valor del sensor de presion sanguinea.
 double valorPres = 0;
 // variable para guardar el valor del sensor de frecuenca cardiaca
 int valorFrec= 0;
 // variable para guardar el valor del sensor de nivel de estres
 int valorEstres= 0;
 
 // variable para la unidad de presion sanguinea (mmHg).
 String presUnit = "mmHg";
 // variable para la unidad de fecuencia cardiaca (latidos/minuto).
 String frecUnit= "lat/min";
 // variable para la unidad de nivel de estres (nivel de estres).
 String estresUnit= "nivel de estres";
 
 // arreglo de Strings para envio final del dato del sensor de presion sanguinea.
 String presArray[2] = {"", ""};
 // arreglo de Strings para envio final del dato del sensor de frecuencia cardiaca.
 String frecArray[2]= {"",""};
 // arreglo de Strings para envio final del dato del sensor de nivel de estres.
 String estresArray[2]= {"",""};
 // variable temporal de conteo
 int i = 0;
 
 // preparacion del proceso
 void setup() { 
   // Abre puerto serial y lo configura a 9600 bps
     Serial.begin(9600);
     // se fija la unidad de trabajo del sensores de 
     //presion sanguinea, frecuencia cardiaca y nivel de estres.
     presArray[1] = String(presUnit);
     frecArray[1]= String (frecUnit);
     estresArray[1]= String(estresUnit);
 }
 
 // ejecuta el procesamiento del sensor
 void loop() {
   // lee el valor del sensor de presion sanguinea en Volts
   valorPres = analogRead(presionPin);
   // lee el valor del sensor de frecuencia cardiaca en Volts
   valorFrec= analogRead(frecPin);
   //lee el valor del sensor de nivel de estres en Volts
   valorEstres= digitalRead(estresPin);
   // Convierte el valor presion sanguinea a mmHg y de analogo a digital
   // DEPENDE DEL SENSOR, REVISAR DATASHEET
   valorPres = (3.9* valorPres * 100.0)/1024.0;
   // Convierte el valor de frecuencia cardiaca a lat/min de analgo a digital
   // DEPENDE DEL SENSOR, REVISAR DATASHEET
   valorFrec= (valorFrec*60.0)/350.0;
   // CODIGO DE ESTRES
    if(valorFrec >=70)
    { valorEstres=1;
    }
     else {
      valorEstres=0;}   
      
   // se transforma el dato double de presion sanguinea a un char
   presArray[0] = String(valorPres);
   // se transforma el dato int de frecuencia cardiaca a un char
   frecArray[0]= String(valorFrec);
   // CODIGO DE ESTRES
   estresArray[0]= String (valorEstres);
   
   // Envia los datos uno por uno del arreglo del sensor de presion sanguinea por puerto serial
   for (i=0; i<2; i++){
     // imprime el elemento del arreglo por el puerto serial
     Serial.print(presArray[i]);
     // espacio para diferenciar elementos en el arreglo
     if (i < 1){
       Serial.print("\t");      
     }
   }
   // final de la trama de datos
   Serial.println("\n");
   
   // Envia los datos un por uno del arreglo del sensor por puerto serial
   for (i=0; i<2; i++){
     // imprime el elemento del arreglo por el puerto serial
     Serial.print(frecArray[i]);
     // espacio para diferenciar elementos en el arreglo
     if (i < 1){
       Serial.print("\t");      
     }
   }
   // final de la trama de datos
   Serial.println("\n");
   
   //Imprime el valor del nivel de estres
   for (i=0; i<2; i++){
   Serial.print(estresArray[i]);
   if (i<1){
   Serial.print("\t");}}
   //final de la trama de datos
   Serial.println("\n");
   Serial.println("");
   
   // espera 1 segundo para repetir el procedimiento
   delay(1000);
  
 }
