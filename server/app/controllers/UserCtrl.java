package controllers;

import com.fasterxml.jackson.databind.JsonNode;

import models.Organisation;
import models.User;
import models.Organization;

import play.*;
import play.db.ebean.Transactional;
import play.libs.Json;
import play.mvc.*;
import views.html.*;
import play.mvc.Http.RequestBody;
import java.util.List;

import com.avaje.ebean.*;

@Transactional
public class UserCtrl extends Controller {

	public Result show() {
		//Long id = 12L;
		//User temp = User.find.byId(id);

		//return ok(profil.render(temp.name));
		return ok(profil.render("Profil"));
	}

	public Result test() {
		User u1 = new User();
		u1.id = 12L;
		u1.name = "Jhone";
		u1.password = "secret";
		u1.email = "paper@email.com";
		u1.alias = "tantan";
		u1.save();

		return ok(index.render("User test create"));
	}

	public Result createUser() {

		// parse the JSON as a JsonNode
		JsonNode json = Json.parse(request().body().asJson().toString());
		User user = new User();
		user.id = Long.parseLong(json.findPath("id").toString()
				.replaceAll("\"", ""));
		user.name = json.findPath("name").toString().replaceAll("\"", "");
		user.password = json.findPath("password").toString().replaceAll("\"", "");
		user.email = json.findPath("email").toString().replaceAll("\"", "");
		user.alias = json.findPath("alias").toString().replaceAll("\"", "");
		user.deleted = false;
		user.save();
		// read the JsonNode as a Person
		return ok(index.render("created"));
	}

	public Result getUser(String userInformation) {
		JsonNode personJson = Json.toJson(findUser(userInformation));

		return ok(personJson);
	}

	public Result addOrganisation(Long id) {

		JsonNode json = Json.parse(request().body().asJson().toString());
		String OrganisationName = json.findPath("name").toString()
				.replaceAll("\"", "");
		List<Organisation> orgListe = Organisation.find.where()
				.ilike("name", OrganisationName).findList();
		User user = User.find.byId(id);
		user.organisations.add(orgListe.get(0));
		user.save();
		return ok(index.render("added"));
	}

	public Result updatePassword() {
		RequestBody body = request().body();
        String email = "paper@email.com";
		//return ok(index.render("Body = " + body.asFormUrlEncoded().get("newPassword")[0]));
		JsonNode json = Json.parse(body.asJson().toString());
		String newPassword = json.findPath("newPassword").toString()
				.replaceAll("\"", "");
		String confirmationPassword = json.findPath("confirmationPassword").toString()
				.replaceAll("\"", "");
		String oldPassword = json.findPath("oldPassword").toString()
				.replaceAll("\"", "");
		User user = findUser(email);
        
        if(user.password.equals(oldPassword)) {
            if(newPassword.equals(confirmationPassword)) {
                user.password = newPassword;
		        user.save();
		        flash("success", "Utilisateur sauvegardé");
            }else{
                flash("error", "Les deux nouveaux mot de passe ne sont pas identiques");
            }
        }else{
            flash("success", "Ancien mot de passe ne correspond pas");
        }
		return ok(account_settings.render());
	}

	public Result updateInformation(String email) {
		JsonNode json = Json.parse(request().body().asJson().toString());
		String newEmail = json.findPath("newemail").toString()
				.replaceAll("\"", "");
		String newAlias = json.findPath("newalias").toString()
				.replaceAll("\"", "");
		String newPassword = json.findPath("newpassword").toString()
				.replaceAll("\"", "");
		User u1 = new User();
		boolean change = false;
		List<User> user = User.find.where().ilike("email", email).findList();
		if (user.size() == 1) {
			u1 = user.get(0);
			if (newEmail.length() != 0) {
				u1.email = newEmail;
				change = true;
			} else if (newAlias.length() != 0) {
				u1.alias = newAlias;
				change = true;
			} else if (newPassword.length() != 0) {
				u1.password = newPassword;
				change = true;
			}

		} else {
			return notFound(index.render("Not updated!"));
		}
		if (change) {
			u1.save();
		} else {
			return badRequest(index.render("Invalid json syntax!"));
		}

		return ok(index.render("updated"));

	}

	public Result deleteUser(Long id) {
		UserCtrl userCtrl = new UserCtrl();
		Organisation organisation = new Organisation();
		List<Organisation> userAdmin =  Organisation.find
				.where().ilike("id_admin", id.toString()).findList();

		if (userCtrl.userExist(id)) {
			User user = User.find.byId(id);
			if (user.organisations.size() == 0) {
				if (userAdmin.size() == 0) {

					user.delete();
				} else {
					return badRequest("Utilisateur est affecter a un ou plusieurs organisations");
				}
			} else {
				return badRequest("Utilisateur est affecter a un ou plusieurs organisations");
			}

		} else {
			return notFound(index.render("User dont exist"));
		}

		return ok(index.render("deleted"));
	}
/* Helpers */

	public User findUser(String userInformation) {
		User user = null;
		if (isNumeric(userInformation)) {
			Long id = Long.parseLong(userInformation);
			user = User.find.byId(id);
			return user;
		} else {
			List<User> users = User.find.where()
					.ilike("email", userInformation).findList();
			if (users.size() == 1) {
				user = users.get(0);
			  return user;
			}
		}
		return user;
	}

	public boolean userExist(long id) {

		boolean exist = false;
		User user = User.find.byId(id);
		if (user != null) {
			exist = true;
		}

		return exist;
	}

	public static boolean isNumeric(String str) {
		try {
			Long id = Long.parseLong(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

}
