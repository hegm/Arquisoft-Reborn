import com.avaje.ebean.Model;
import play.*;
import play.libs.*;
import com.avaje.ebean.Ebean;
import models.*;
import java.util.*;

/**
 * Created by scvalencia606 on 8/10/15.
 */

public class Global extends GlobalSettings {
    @Override
    public void onStart(Application app) {
        UserInfoDB.addUserInfo("Dr Felipe", "yf.rivera10@uniandes.edu.co", "Yeltsin");
        UserInfoDB.addUserInfo("Dr Dalel", "e.dalel10@uniandes.edu.co", "Esteban");
        UserInfoDB.addUserInfo("Dr Nicolas", "jn.galvis10@uniandes.edu.co", "Juan");
        UserInfoDB.addUserInfo("Dr Haritold", "he.gonzalez10@uniandes.edu.co", "Esteban");
    }
}
