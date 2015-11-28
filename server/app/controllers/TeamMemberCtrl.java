package controllers;

import com.fasterxml.jackson.databind.JsonNode;

import models.Organisation;
import models.TeamMember;

import play.*;
import play.db.ebean.Transactional;
import play.libs.Json;
import play.mvc.*;
import views.html.*;

import java.util.List;

import com.avaje.ebean.*;

@Transactional
public class TeamMemberCtrl extends Controller {

	public Result index() {
		TeamMember u1 = new TeamMember();
		u1.id = 12L;
		u1.name = "Jhone";
		u1.password = "secret";
		u1.email = "paper@email.com";
		u1.alias = "tantan";
		u1.save();
		return ok(index.render("Your new application is ready."));
	}

	// curl -H "Content-Type: application/json" -X POST -d
	// '{"id":"123","name":"Jean-paeeul","alias":"jp21","password":"Jean@paul.com","email":"Jean-paul","inactive":"false"}'
	// http://127.0.0.1:9000/users
	public Result createUser() {
		// User newUser = Json.fromJson(request().body().asJson(), User.class);
		// newUser.save();

		// parse the JSON as a JsonNode
		JsonNode json = Json.parse(request().body().asJson().toString());
		TeamMember u1 = new TeamMember();
		u1.id = Long.parseLong(json.findPath("id").toString()
				.replaceAll("\"", ""));
		u1.name = json.findPath("name").toString().replaceAll("\"", "");
		u1.password = json.findPath("password").toString().replaceAll("\"", "");
		u1.email = json.findPath("email").toString().replaceAll("\"", "");
		u1.alias = json.findPath("alias").toString().replaceAll("\"", "");
		// u1.deleted = false;
		u1.save();
		// read the JsonNode as a Person
		return ok(index.render("createed"));
	}

	public static boolean isNumeric(String str) {
		try {
			Long id = Long.parseLong(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public Result getUser(String userInformation) {
		JsonNode personJson = null;
		if (isNumeric(userInformation)) {
			Long id = Long.parseLong(userInformation);
			TeamMember user = TeamMember.find.byId(id);
			personJson = Json.toJson(user);
		} else {
			List<TeamMember> users = TeamMember.find.where()
					.ilike("email", userInformation).findList();
			if (users.size() == 1) {
				personJson = Json.toJson(users);
				
			}
		}
		return ok(personJson);
	}

	// curl -H "Content-Type: application/json" -X POST -d
	// '{"id":"11","name":"UQAM"}' http://127.0.0.1:9000/organisations
	public Result addOrganisation(Long id) {
		JsonNode json = Json.parse(request().body().asJson().toString());
		String OrganisationName = json.findPath("name").toString()
				.replaceAll("\"", "");
		List<Organisation> oListe = Organisation.find.where()
				.ilike("name", OrganisationName).findList();
		TeamMember user = TeamMember.find.byId(id);
		user.organisations.add(oListe.get(0));
		user.save();
		return ok(index.render("added"));
	}

	public Result updatePassword(String email) {
		JsonNode json = Json.parse(request().body().asJson().toString());
		TeamMember u1 = new TeamMember();
		String newPassword = json.findPath("newPassword").toString()
				.replaceAll("\"", "");
		List<TeamMember> user = TeamMember.find.where().ilike("email", email)
				.findList();
		if (user.size() == 1) {
			u1 = user.get(0);
			u1.password = newPassword;
			u1.save();
		}
		return ok(index.render("updated"));
	}

	public Result updateInformation(String email) {
		JsonNode json = Json.parse(request().body().asJson().toString());
		String newEmail = json.findPath("newemail").toString()
				.replaceAll("\"", "");
		String newAlias = json.findPath("newalias").toString()
				.replaceAll("\"", "");
		String newPassword = json.findPath("newpassword").toString()
				.replaceAll("\"", "");
		TeamMember u1 = new TeamMember();
		boolean change = false;
		List<TeamMember> user = TeamMember.find.where().ilike("email", email)
				.findList();
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
}
