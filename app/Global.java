import static play.data.Form.form;

import java.util.Date;
import java.util.List;

import models.Member;
import models.User;
import models.VerificationDetails;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.libs.Yaml;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Http.RequestHeader;
import views.html.login;

import com.avaje.ebean.Ebean;

import controllers.Application.Login;

public class Global extends GlobalSettings {
    @Override
    public void onStart(Application app) {
        // Check if the database is empty
        if (User.find.findRowCount() == 0) {
            Ebean.save((List<?>) Yaml.load("initial-data.yml"));
        }
        initializeVerificationDetails();
    }

    @SuppressWarnings("deprecation")
    private static void initializeVerificationDetails() {

        Member m = Member.find.byId(1);
        m.verificationDetails = new VerificationDetails(m, "N", null, "N", null);
        m.update();
        m = Member.find.byId(2);
        m.verificationDetails = new VerificationDetails(m, "Y", new Date(2013 - 1900, 03, 15), "Y", new Date(2013 - 1900, 12, 17));
        m.update();
        m = Member.find.byId(3);
        m.verificationDetails = new VerificationDetails(m, "N", null, "N", null);
        m.update();
        m = Member.find.byId(4);
        m.verificationDetails = new VerificationDetails(m, "F", new Date(2013 - 1900, 03, 15), "N", null);
        m.update();
        m = Member.find.byId(5);
        m.verificationDetails = new VerificationDetails(m, "N", null, "N", null);
        m.update();

        Logger.info("Verification Details: Initialized.");
    }

    @Override
    public Result onHandlerNotFound(RequestHeader request) {
        return Controller.ok(login.render(form(Login.class)));
    }

}
