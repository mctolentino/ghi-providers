package controllers;

import static play.data.Form.form;

import java.util.ArrayList;
import java.util.List;

import models.Member;
import models.SecurityQuestion;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

@Security.Authenticated(Secured.class)
public class Members extends Controller {

    public static Result searchMember() {
        Form<SearchMember> searchMemberForm = form(SearchMember.class).bindFromRequest();

        if (!searchMemberForm.hasErrors()) {
            List<Member> memberList = new ArrayList<Member>();

            Member result = Member.searchMemberById(searchMemberForm.get().searchId);
            if (result != null) {
                memberList.add(result);
                result.saveSearchAndVerificationDetails();

                return ok(views.html.member.render(User.find.byId(request().username()), result, form(SearchMember.class), getRandomQuestion(result)));
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

        return ok(views.html.member.render(User.find.byId(request().username()), m, form(SearchMember.class), getRandomQuestion(m)));
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

}
