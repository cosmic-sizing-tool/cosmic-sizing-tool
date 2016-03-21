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
public class UserCtrl extends Controller {
    
    User userGlobal;
    
	public Result show() {
		Long id = 15L;
        
		return ok(profil.render(User.find.byId(id)));
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
        Email temp = new Email();
        temp.addresse = u1.email;
        temp.primary = true;
        temp.user = u1;

        //try {
            u1.save();
            temp.save();
            u1.emails.add(temp);
            u1.save();
            
       // }catch(Exception ie){

       /* }finally {
            List<User> users = User.find.where()
                    .ilike("email", "paper@email.com").findList();
            u1 = users.get(0);
        }*/
        userGlobal = u1;
		return ok(index.render("User test1 create"));
	}
	public Result test2() {
		User u1 = new User();
		u1.id = 15L;
		u1.name = "bob";
		u1.password = "admin";
		u1.email = "bobb23y@email.com";
        u1.alias = "tarzan12";
        Email temp = new Email();
        temp.addresse = u1.email;
        temp.primary = true;
        temp.user = u1;

        //try {
            u1.save();
            temp.save();
            u1.emails.add(temp);
            u1.save();
        //}catch(Exception ie){

        /*}finally {
            List<User> users = User.find.where()
                    .ilike("email", "bobb23y@email.com").findList();
            u1 = users.get(0);
        }*/
        userGlobal = u1;
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

	public Result getUser() {
	    User temp = userGlobal;
		return ok(Json.toJson(temp));
	}

    public Result addCertification() {
        
        User user = userGlobal; //test
        
        RequestBody body = request().body();
        
        String method = body.asFormUrlEncoded().get("certification")[0];
        String version = body.asFormUrlEncoded().get("version")[0];
        String date = body.asFormUrlEncoded().get("date")[0];

        Certification temp = new Certification();
        temp.method = method;
        temp.version = version;
        temp.date = date;
        temp.user = user;
        boolean valide = true;
        if(user.certifications != null && user.certifications.size() !=0) {
            for(Certification c : user.certifications) {
                if(c.method.equals(temp.method) && temp.version.equals(c.version)){
                    valide = false;
                }
            }
        }
        if(valide) {
            temp.save();
            user.certifications.add(temp);
            user.save();
            flash("success", "Certification ajouté");
        }else{
            flash("error", "Vous avez déjà une certification identique !");
        }
        return ok(profil.render(user));
    }
    public Result resetPassword() {
        return ok(reset_pwd.render());
    }
    public Result disponibleMesure() {
        
        User user = userGlobal; //test
        
        RequestBody body = request().body();

        if(body.asFormUrlEncoded().get("dispo") == null) {
            user.disponible = false;
            user.save();
        }else{
			user.disponible = true;
			user.save();
		}
        
        return ok(profil.render(user));
    }
    
	public Result updatePassword() {
	    User user = userGlobal; //test
	    
		RequestBody body = request().body();

		String newPassword = body.asFormUrlEncoded().get("newPassword")[0];
		String confirmationPassword = body.asFormUrlEncoded().get("confirmationPassword")[0];
		String oldPassword = body.asFormUrlEncoded().get("oldPassword")[0];
		//User user = User.find.byId();
        
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
	
	public Result changeUsername(){
	    User user = userGlobal; //test
	    
	    RequestBody body = request().body();
	    String newUsername = body.asFormUrlEncoded().get("newUsername")[0];
		//User user = User.find.byId(id);
		
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
		
		String emailSended = body.asFormUrlEncoded().get("email")[0];
		String nameSended = body.asFormUrlEncoded().get("name")[0];
		String urlSended = body.asFormUrlEncoded().get("url")[0];
		String companySended = body.asFormUrlEncoded().get("company")[0];
		String locationSended = body.asFormUrlEncoded().get("location")[0];
		
		User user = findUser(userGlobal.email);
		
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

		return ok(profil.render(user));            
    }

	public Result deleteUser() {
	    User user = userGlobal; //test
	    Long id = user.id; //test
	    
		if (userExist(id)){
		    //User user = User.find.byId(id);
		    List<Organisation> userAdmin = Organisation.find
				.where().ilike("id_admin", id.toString()).findList();
			if (user.organisations.size() == 0) {
				if (userAdmin.size() == 0) {
					user.deleted = true;
                    //user.delete();
                    flash("success", "Compte supprimé");
			    } else {
					return badRequest("Utilisateur est administrateur d'une ou plusieurs organisations");
				}
			} else {
				return badRequest("Utilisateur est affecté a une ou plusieurs organisations");
			}
		}

		return ok(index.render(""));
	}

	public Result get_user_certifications() {
		User user = userGlobal;
		return ok(Json.toJson(user.certifications));
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
