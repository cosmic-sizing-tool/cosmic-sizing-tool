package controllers;

import com.lambdaworks.crypto.SCrypt;
import com.lambdaworks.crypto.SCryptUtil;
// Are you serious with these imports bro?
import play.*;
import play.data.*;
import play.mvc.*;
import views.html.*;
import java.util.*;
import models.*;
import play.data.Form;
import play.data.FormFactory;
import static play.data.Form.*;

import javax.inject.Inject;

public class Application extends Controller {

    @Inject
    FormFactory formFactory;

    public Result registerUser() {
      System.out.println("Inside registration method");
      // Get form from POST request
      Form<BasicUser> bUserForm = formFactory.form(BasicUser.class);
      BasicUser bUser = bUserForm.bindFromRequest().get();

      // Debug purposes
      System.out.println("Got user " + bUser + " from request");
      System.out.println("User info: ");
      System.out.println("Email: " + bUser.getEmail());
      System.out.println("Username: " + bUser.getUsername());
      System.out.println("Pass: " + bUser.getPassword());

      bUser.save();
      // Should probably return userPage... not my problem
      return ok(mainPage.render());
    }

    public Result index() {
      System.out.println("Rendering main page");
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
        member.setPassword("bob");
        member.email = "bob@bob.com";
        member.save();

        List<User> members = User.find.all();
        return ok(team.render(members));
    }

    public Result organisations(String idUser){
         Organisation o1 = new Organisation();
       o1.id = 1L ;
       o1.name = "UQAM";
     //	o1.save();
       Organisation o2 = new Organisation();
       o2.id = 12L ;
       o2.name = "McGill";
 //    	o2.save();
         Organisation o3 = new Organisation();
       o3.id = 3L ;
       o3.name = "Concordia";
     //	o3.save();

         User us1 = new User();
       us1.id = 2L;
       us1.alias = "us1";
       us1.name = "User1 name";
       us1.password = "User1 pass";
       us1.email = "User1 email";
       us1.organisations.add(Organisation.find.byId(1L));
       us1.organisations.add(Organisation.find.byId(12L));
     //	us1.save();
         us1 = User.find.byId(2L);

       User us2 =  new User();
       us2.id = 3L;
       us2.alias = "us2";
       us2.name = "User2 name";
       us2.password = "User2 pass";
       us2.email = "User2 email";
       us2.organisations.add(Organisation.find.byId(3L));
     //	us2.save();
         us2 = User.find.byId(3L);

         User realUser = User.find.byId(Long.valueOf(idUser).longValue());

         //List<Organisation> orga = Organisation.find.all();
         return ok(organisation.render(realUser.organisations));
     }

     public Result addOrganisations(String idUser) {
         return ok(organisation_add.render());
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
