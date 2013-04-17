package controllers;

import static play.data.Form.form;
import models.Member;
import models.SearchLog;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.dashboard;
import views.html.login;
import controllers.Members.SearchMember;

public class Application extends Controller {

    @Security.Authenticated(Secured.class)
    public static Result dashboard() {
        return ok(dashboard.render(Member.allMembers(), User.find.byId(request().username()), form(SearchMember.class), SearchLog.findLatestSearches()));
    }

    public static Result login() {
        return ok(login.render(form(Login.class)));
    }

    public static Result authenticate() {
        Form<Login> loginForm = form(Login.class).bindFromRequest();
        
        if (loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        } else {
            session().clear();
            session("username", loginForm.get().username);
            return redirect(routes.Application.dashboard());
        }
    }

    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(routes.Application.login());
    }

    public static class Login {

        public String username;
        public String password;

        public String validate() {
            if (User.authenticate(username, password) == null) {
                return "Wrong Username/Password. Please try again.";
            }
            return null;
        }
    }

}
