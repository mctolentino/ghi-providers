import java.util.List;

import models.Member;
import models.SearchLog;
import models.User;
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

    private static void initializeVerificationDetails() {

        List<SearchLog> logs = SearchLog.find.all();

        for (SearchLog log : logs) {
            if (log.member.verificationDetails == null) {
                log.member.saveNewVerificationDetails();
            }
        }

        Logger.info("Verification Details: Initialized.");
    }

    private static void initializeSecurityQuestions() {

        List<Member> members = Member.find.all();

        for (Member member : members) {
            if (member.securityQuestions == null || member.securityQuestions.size() == 0) {
                member.saveNewSecurityQuestion();
            }
        }

        Logger.info("Security Questions: Initialized.");
    }
}
