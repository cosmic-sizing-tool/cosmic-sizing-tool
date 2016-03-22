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
        return ok(emails_settings.render());
    }
    public Result addEmail() {
        RequestBody body = request().body();

        String adresse = body.asFormUrlEncoded().get("adresse")[0];
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
        User user = User.find.byId(id);
        temp.user = user;
        temp.save();
        user.emails.add(temp);
        user.save();

        flash("success", "Courriel ajouté");
        return redirect(routes.EmailCtrl.emails());
    }
    public Result getUserEmails() {
      Long id = 15L;
      User user = User.find.byId(id);
      List<Email> emails = user.emails;
      return ok(Json.toJson(emails));
    }
    public Result test(){
        User user_test = new User();
        user_test.name = "test";
        user_test.password = "1234";
        user_test.email = "admin12345@email.com";
        user_test.alias = "testadmin";
        user_test.save();
        return ok(emails_settings.render());
    }

}
