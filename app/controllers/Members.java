package controllers;

import static play.data.Form.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import models.ChallengeCode;
import models.Member;
import models.SecurityQuestion;
import models.User;
import play.data.Form;
import play.libs.WS;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import util.ChallengeCodeUtil;
import util.DateUtil;

@Security.Authenticated(Secured.class)
public class Members extends Controller {

    private final static String BASE_URL = "http://192.168.1.152:8080/sms-gw/ws";
    private final static String SUCCESS = "SUCCESS";

    public static Result validateMember(Integer id) {
        Member member = Member.find.byId(id);
        String challengeCode = ChallengeCodeUtil.generateChallenge();

        String URL = BASE_URL + "/" + member.person.phoneMobile + "/" + challengeCode;
        System.out.println("URL: " + URL);

        if ((WS.url(URL)
               .get().get()).getBody()
                            .equals(SUCCESS)) {
            ChallengeCode.saveChallengeCode(member, challengeCode);
            return ok("<div id=\"code-sent\">Sent.</div>");
        } else {
            return ok("<div id=\"code-notsent\">Sending failed. Try again.</div>");
        }
    }

    public static Result setWatchListFlag(Integer id) {
        Member member = Member.find.byId(id);

        if (member != null) {
            member.verificationDetails.verifiedFlag = "Y";
            member.verificationDetails.verifiedDate = DateUtil.today();
            member.verificationDetails.watchlistFlag = "Y";
            member.verificationDetails.watchlistDate = DateUtil.threeMonthsFromToday();
            member.update();
        }

        return ok("SUCCESS");
    }

    public static Result checkChallengeCode(Integer id) {
        Map<String, String[]> qp = request().queryString();
        String code = qp.get("code")[0];

        if (ChallengeCode.validateChallengeCode(id, code)) {
            Member member = Member.find.byId(id);
            member.verificationDetails.verifiedDate = DateUtil.today();
            member.verificationDetails.verifiedFlag = "Y";
            member.verificationDetails.watchlistFlag = "N";
            member.verificationDetails.watchlistDate = null;
            member.update();
            return ok(" <div id=\"member-verified\">Member verified and authorized to use card.</div> ");
        } else {
            Member member = Member.find.byId(id);
            member.verificationDetails.verifiedDate = DateUtil.threeMonthsFromToday();
            member.verificationDetails.verifiedFlag = "F";
            member.verificationDetails.watchlistFlag = "N";
            member.verificationDetails.watchlistDate = null;
            member.update();
            return ok(" <div id=\"code-wrong-three\">Wrong code. Please choose an option below.</div> ");
        }
    }
    
    public static Result searchMember() {
        Form<SearchMember> searchMemberForm = form(SearchMember.class).bindFromRequest();

        if (!searchMemberForm.hasErrors()) {
            List<Member> memberList = new ArrayList<Member>();

            Member result = Member.searchMemberById(searchMemberForm.get().searchId);
            if (result != null) {
                memberList.add(result);
                result.saveSearchAndVerificationDetails();

                return ok(views.html.member.render(User.find.byId(request().username()), result, form(SearchMember.class), getRandomQuestion(result),
                        form(ValidateChallengeCode.class)));
            } else {
                flash("error", "Member not found");
            }
        } else {
            flash("error", "Enter Member ID");
        }
        return redirect(routes.Application.dashboard());
    }

    public static Result viewMember(Integer member) {
        Member m = Member.find.byId(member);

        if (m.verificationDetails == null) {
            m.saveNewVerificationDetails();
        }
        if (m.securityQuestions == null || m.securityQuestions.size() == 0) {
            m.saveNewSecurityQuestion();
        }

        return ok(views.html.member.render(User.find.byId(request().username()), m, form(SearchMember.class), getRandomQuestion(m),
                form(ValidateChallengeCode.class)));
    }

    private static SecurityQuestion getRandomQuestion(Member member) {
        int randomIndex = (int) (Math.random() * (member.securityQuestions.size()));
        return member.securityQuestions.get(randomIndex);
    }

    public static class SearchMember {
        public Integer searchId;

        public String validate() {
            if (searchId == null) {
                return "Search ID is null";
            }
            return null;
        }

    }

    public static class ValidateChallengeCode {
        public Integer challengeCode;
        public Integer memberId;

        public String validate() {
            if (challengeCode == null) {
                return "Please Enter Challenge Code";
            }
            return null;
        }

    }

}
