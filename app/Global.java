import java.util.Date;
import java.util.List;

import models.Member;
import models.User;
import models.VerificationDetails;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.libs.Yaml;

import com.avaje.ebean.Ebean;

public class Global extends GlobalSettings {
    @Override
    public void onStart(Application app) {
        // Check if the database is empty
        if (User.find.findRowCount() == 0) {
            Ebean.save((List<?>) Yaml.load("initial-data.yml"));
        }
        initializeVerificationDetails();
        initializeSecurityQuestions();
    }

    @SuppressWarnings("deprecation")
    private static void initializeVerificationDetails() {

        Member m = Member.find.byId(1);
        m.verificationDetails = new VerificationDetails(m, "Y", new Date(2013-1900, 03, 15), "N", null);
        m.save();
        m = Member.find.byId(2);
        m.verificationDetails = new VerificationDetails(m, "Y", new Date(2013-1900, 03, 15), "Y", new Date(2013, 12, 17));
        m.save();
        m = Member.find.byId(3);
        m.verificationDetails = new VerificationDetails(m, "N", null, "N", null);
        m.save();
        m = Member.find.byId(4);
        m.verificationDetails = new VerificationDetails(m, "F", new Date(2013-1900, 03, 15), "N", null);
        m.save();
        m = Member.find.byId(5);
        m.verificationDetails = new VerificationDetails(m, "N", null, "N", null);
        m.save();

        Logger.info("Verification Details: Initialized.");
    }

    private static void initializeSecurityQuestions() {

        // List<Member> members = Member.find.all();

        // for (Member member : members) {
        // if (member.securityQuestions == null ||
        // member.securityQuestions.size() == 0) {
        // member.saveNewSecurityQuestion();
        // }
        // }

        Logger.info("Security Questions: Initialized.");
    }
}
