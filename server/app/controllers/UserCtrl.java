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
import java.util.*;

import com.avaje.ebean.*;

@Transactional
public class UserCtrl extends Controller {

	public Result show() {
		Long id = 13L;
		User temp = User.find.byId(id);
        /*User essai = new User();
        essai.save();
		Organization org = new Organization("COSMIC");
		org.save();
		List<Organization> orgListe = Organization.find.where()
				.ilike("name", "COSMIC").findList();
		return ok(profil.render("id"+ essai.id+" "+orgListe.size() + " "+" "+temp.name));*/
		return ok(profil.render("Profil"));
	}
	
	public Result settings() {
	    return ok(account_settings.render());
	}

	public Result test1() {
		User u1 = new User();
		u1.id = 12L;
		u1.name = "Jhone";
		u1.password = "secret";
		u1.email = "paper@email.com";
		u1.alias = "tantan";
		u1.save();

		return ok(index.render("User test1 create"));
	}
	public Result test2() {
		User u1 = new User();
		u1.id = 13L;
		u1.name = "bob";
		u1.password = "admin";
		u1.email = "bobby@email.com";
		u1.alias = "tarzan";
		u1.addEmail("bobby2@gmail.com");
		u1.save();

		return ok(index.render("User test2 create"));
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
		
		return ok(index.render("created"));
	}

	public Result getUser(String userInformation) {
		JsonNode personJson = Json.toJson(findUser(userInformation));
		
		return ok(profil.render(""));
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
	
	public Result changeUsername(long id){
	    RequestBody body = request().body();
	    JsonNode json = Json.parse(body.asJson().toString());
	    String newUsername = json.findPath("newUsername").toString()
				.replaceAll("\"", "");
		User user = User.find.byId(id);
		
		if(!newUsername.equals("")) {
		    if(email_valid(newUsername) && !email_exist(newUsername)) {
		        user.email = newUsername;
		        user.save();
		    }else{
		        if(!username_exist(newUsername)) {
		            user.alias = newUsername;
		            user.save();
		        }else{
		            flash("success", "Nom d'utilisateur/email modifié avec succès!");
		        }
		    }
		}else{
		    flash("error", "Un nom d'utilisateur/email ne peut être vide !");
		}
		return ok(account_settings.render());
	}

	public Result updateProfil(){
		
		RequestBody body = request().body();
		
		//String userInfo = body.asFormUrlEncoded().get("userId")[0];
		String emailSended = body.asFormUrlEncoded().get("email")[0];
		String nameSended = body.asFormUrlEncoded().get("name")[0];
		String urlSended = body.asFormUrlEncoded().get("url")[0];
		String companySended = body.asFormUrlEncoded().get("company")[0];
		String locationSended = body.asFormUrlEncoded().get("location")[0];
		
		User user = findUser("bobby@email.com");
		
		ArrayList validations = areValideInformations(nameSended,urlSended,companySended,locationSended);
		
		if(validations.size() != 0){

		    flash("error", String.join(", ", validations));
		    
		}else{
		    
    		if(user != null){
    		    
    		    user.email = emailSended;
    		    user.name = nameSended;
    		    user.url = urlSended;
    		    user.company = companySended;
    		    user.location = locationSended;
    		    
    		    user.save();
    		    
    		    flash("success", "Modifications effectuées avec succes");
    		    
    		}else{
    
    		    flash("error", "Serveur indisponible. Réessayer plus tard");
    		}		    
		}

		return ok(profil.render("Body : " + String.join(", ", justePourTest(user))));            
    }

	public Result deleteUser(Long id) {
		if (userExist(id)){
		    User user = User.find.byId(id);
		    List<Organisation> userAdmin = Organisation.find
				.where().ilike("id_admin", id.toString()).findList();
			if (user.organisations.size() == 0) {
				if (userAdmin.size() == 0) {
					user.delete();
			    } else {
					return badRequest("Utilisateur est administrateur d'une ou plusieurs organisations");
				}
			} else {
				return badRequest("Utilisateur est affecté a une ou plusieurs organisations");
			}
		}

		return ok(account_settings.render());
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
	
	public boolean email_exist(String mail) {
	   List<User> users = User.find.where()
					.ilike("email", mail).findList();
		return (users.size() == 1 ? true : false);
	}

    public boolean username_exist(String username) {
        List<User> users = User.find.where()
					.ilike("alias", username).findList(); 
	     return (users.size() == 1 ? true : false);
    }
    
    public boolean email_valid(String mail){
        String regex = "^.*@[a-z]+\\.[a-z]{2,3}$";
        return mail.matches(regex);
    }
    
	public static boolean isNumeric(String str) {
		try {
			Long id = Long.parseLong(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	
	public ArrayList areValideInformations(String name,String url, String company,String location){

	    ArrayList result = new ArrayList();
	    
	    if(name.length() == 0){
	        result.add("Le nom est invalide");   
	    }
	    
	    if(url.length() == 0){
	        result.add("L'url ne correspond pas au format valide");
	    }
	    
	    if(company.length() == 0){
	        result.add("Le nom de la compagnie est invalide");   
	    }
	    
	    if(location.length() == 0){
	       result.add("La location est invalide"); 
	    }
	    
	    return result;    
	}
	
	public ArrayList justePourTest(User globalUser) {
	    
	    ArrayList userInfoList = new ArrayList<>();
        
        userInfoList.add(globalUser.name);
        userInfoList.add(globalUser.password);
        userInfoList.add(globalUser.email);
        userInfoList.add(globalUser.alias);
        userInfoList.add(globalUser.url);
        userInfoList.add(globalUser.company);
        userInfoList.add(globalUser.location);	
        
        return userInfoList;
	}
}
