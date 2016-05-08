package controllers;

import com.lambdaworks.crypto.SCrypt;
import com.lambdaworks.crypto.SCryptUtil;
import play.*;
import play.data.*;
import play.mvc.*;
import views.html.*;
import java.util.*;
import models.*;
import play.data.Form;
import static play.data.Form.*;

public class Application extends Controller {

    public Result index() {
        return ok(index.render("Your new application is ready."));
    }
    
    public Result counter() {
        return ok(counter.render());
    }

    public Result login() {
        return ok(
            login.render(form(Login.class))
        );
    }    
       
    public Result team(){

        flash("success", "message de succes");
        flash("error", "message d'erreur");
        TeamMember member = new TeamMember();

        //User member = new User();

        member.name = "bob";
        member.setPassword("bob");
        member.email = "bob@bob.com";
        member.save();

        List<User> members = User.find.all();
        return ok(team.render(members));
    }
    
    public Result authenticate() {
        Form<Login> loginForm = form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        } else {
            session().clear();

            TeamMember teamMember = TeamMember
                    .find
                    .where()
                        .eq("email", loginForm.get().email)
                        .findUnique();






            if (teamMember == null) {
                return unauthorized(index.render("L'utilisateur n'existe pas."));
            }

            if (SCryptUtil.check(loginForm.get().password, teamMember.getPasswordHash())) {
                session("uid", teamMember.id.toString());
                return redirect(routes.Application.index());
            }
            else {
                return unauthorized(index.render("Mauvais mot de passe. Please ask Hafedh <3"));
            }

        }
           
        
    }
    
    public static class Login {

        public String email;
        public String password;

        public String validate() {
            //List<TeamMember> members = TeamMember.find.all();
            //members.contains(TE);
            
           //if (User.authenticate(email, password) == null) {
            /// return "Invalid user or password";
            //}
            
            return null;
        }        
        
    }
}


