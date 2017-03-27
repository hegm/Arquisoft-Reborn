package dispatchers;


import akka.dispatch.MessageDispatcher;
import play.libs.Akka;

/**
 * Created by he.gonzalez10 on 27/03/2017.
 */
public class AppExecutionContexts {

    public static MessageDispatcher jdbcDispatcher = Akka.system().dispatchers().lookup("context.jdbc-dispatcher");
}
