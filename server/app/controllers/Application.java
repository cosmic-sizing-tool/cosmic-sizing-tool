package controllers;

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
        return ok(mainPage.render());
    }

    public Result signup() {
        return ok(signup.render());
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
        member.password = "bob";
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
            session("email", loginForm.get().email);
            List<TeamMember> members = TeamMember.find.all();
            
            for(int i=0;i<members.size();i++){
                if(members.get(i).email.equals(loginForm.get().email)){
                    if(members.get(i).password.equals(loginForm.get().password)){
                        return ok(index.render("bienvenue: "+loginForm.get().email));
                    }else
                    {
                        return ok(index.render("Le mot de passe n'est pas le bon pour cet uttilisateur."));
                    }
                    
                }
                    
            }
            return ok(index.render("L'uttilisateur n'existe pas."));
            //return redirect(routes.Application.index());
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


