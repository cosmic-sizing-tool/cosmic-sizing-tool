package controllers;

import com.fasterxml.jackson.databind.JsonNode;

import models.*;


import play.*;
import play.db.ebean.Transactional;
import play.libs.Json;
import play.mvc.*;
import views.html.*;
import play.mvc.Http.RequestBody;
import java.util.*;

import com.avaje.ebean.*;

@Transactional
public class EmailCtrl extends Controller {

    public Result emails() {
      Long id = 15L;
      CosmicUser user = CosmicUser.find.byId(id);
      return ok(emails_settings.render(user.emails));
    }
    public Result addEmail() {
        RequestBody body = request().body();

        String adresse = body.asFormUrlEncoded().get("adresse")[0];
        if(!exists(adresse)) {
          Email temp = new Email();
          temp.addresse = adresse;

          if(body.asFormUrlEncoded().get("hide") == null){
            temp.hidden = false;
          }else {
            temp.hidden = true;
          }
          //List<User> users = User.find.where().ilike("email", "admin12345@email.com").findList();
          //User user = users.get(0);
          Long id = 15L;
          CosmicUser user = CosmicUser.find.byId(id);
          temp.user = user;
          temp.save();
          user.emails.add(temp);
          user.save();
          flash("success", "Courriel ajouté");
        }else{
          flash("error", "Cette adresse courriel est déjà utilisée ...");
        }
        return redirect(routes.EmailCtrl.emails());
    }
    public Result getUserEmails() {
      Long id = 15L;
      CosmicUser user = CosmicUser.find.byId(id);
      List<Email> emails = user.emails;
      return ok(Json.toJson(emails));
    }
    public Result deleteEmail(Long id){
      Email temp = Email.find.byId(id);
      temp.setDeleted(true);
      Ebean.update(temp);
      return redirect(routes.EmailCtrl.emails());
    }
    public Result test(){
        CosmicUser user_test = new CosmicUser();
        user_test.name = "test";
        user_test.password = "1234";
        user_test.email = "admin12345@email.com";
        user_test.alias = "testadmin";
        user_test.save();
        return redirect(routes.EmailCtrl.emails());
    }

    public boolean exists(String e) {
      List<Email> emails = Email.find.all();
      boolean exist = false;
      for(Email email : emails) {
        if(email.addresse.equals(e)){
          exist = true;
        }
      }

      return exist;
    }

}
