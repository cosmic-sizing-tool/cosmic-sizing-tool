package controllers;

import com.fasterxml.jackson.databind.JsonNode;

import models.*;
import util.SendEmail;

import play.*;
import play.db.ebean.Transactional;
import play.libs.Json;
import play.mvc.*;
import views.html.*;
import play.mvc.Http.RequestBody;
import play.mvc.Http.MultipartFormData.FilePart;
import java.util.*;
import java.io.*;
import com.avaje.ebean.*;

@Transactional
public class UserCtrl extends Controller {

    CosmicUser userGlobal;

	public Result show() {
		Long id = 15L;
		userGlobal = CosmicUser.find.byId(id);

		return ok(profil.render(userGlobal.certifications));
	}

	public Result settings() {
    	Long id = 12L;
		userGlobal = CosmicUser.find.byId(id);
	  return ok(account_settings.render());
	}

	public Result test1() {
		CosmicUser u1 = new CosmicUser();
		u1.id = 12L;
		u1.name = "Jhone";
		u1.password = "secret";
		u1.email = "paper@email.com";
    u1.alias = "tantan";
    Email temp = new Email();
    temp.addresse = u1.email;
    temp.main = true;
    temp.user = u1;
    temp.save();
    u1.emails.add(temp);
		u1.save();

		return ok(index.render("User test1 create"));
	}
	public Result test2() {
		CosmicUser u1 = new CosmicUser();
		u1.id = 15L;
		u1.name = "bob";
		u1.password = "admin";
		u1.email = "bobb23y@email.com";
		u1.alias = "tarzan12";
    Email temp = new Email();
    temp.addresse = u1.email;
    temp.main = true;
    temp.user = u1;
    temp.save();
    u1.emails.add(temp);
		u1.save();

		return ok(index.render("User test2 create"));
	}


	public Result createUser() {

		// parse the JSON as a JsonNode
		JsonNode json = Json.parse(request().body().asJson().toString());
		CosmicUser user = new CosmicUser();
		user.id = Long.parseLong(json.findPath("id").toString()
				.replaceAll("\"", ""));
		user.name = json.findPath("name").toString().replaceAll("\"", "");
		user.password = json.findPath("password").toString().replaceAll("\"", "");
		user.email = json.findPath("email").toString().replaceAll("\"", "");
		user.alias = json.findPath("alias").toString().replaceAll("\"", "");
		user.deleted = false;
		user.save();

		return redirect(routes.UserCtrl.show());
	}

	public Result getUser() {
    	Long id = 15L;
    	userGlobal = CosmicUser.find.byId(id);

		return ok(Json.toJson(userGlobal));
	}

	public CosmicUser getUser(String email) {
		userGlobal = CosmicUser.find.where().eq("email", email).findUnique();
		/*System.out.println("Appel a getUser() on dump le contenu de l'objet: ");
		System.out.println(userGlobal);*/

		return userGlobal;
	}

  public Result getUserCertifications() {
	Long id = 15L;
	userGlobal = CosmicUser.find.byId(id);
	List<Certification> certifications= userGlobal.certifications;
	return ok(Json.toJson(certifications));
  }

    public Result addCertification() {

        CosmicUser user = userGlobal; //test

        RequestBody body = request().body();

        String method = body.asFormUrlEncoded().get("method")[0];
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
            if(body.asFormUrlEncoded().get("dispo") == null) {
                user.setDisponible(false);
            }else{
                user.setDisponible(true);
    		    }
            Ebean.update(user);
            flash("success", "Certification ajouté");
        }else{
            flash("error", "Vous avez déjà une certification identique !");
        }
        return redirect(routes.UserCtrl.show());
    }

    public Result resetPassword(String email) {
		String message = "";

		if (email == null) {
		}
		else {
			CosmicUser user = findUser(email);
			if (user != null) {
				user.password = "bob";//crypto.encrypt("bob");
				// send email
				String emailContent = "Hello %NAME%,\n\nWe have received a password recovery request for this email address.\n\nYour password is : %PASSWORD%\n\nIf you did not request your password, please ignore this message.\n\nThank you,\nThe COSMIC-sizing-tool team.\n\nN.B.  This message was generated automatically. Please do not reply to this address.";
				emailContent = emailContent.replace("%NAME%", user.name);
				emailContent = emailContent.replace("%PASSWORD%", user.password);

				SendEmail sendEmail = new SendEmail("cosmic@do-not-respond.com", email, "Password recovery", emailContent);
				sendEmail.send();
				message = "Email sent.";
			}
			else {
				message = "Invalid email address.";
			}
		}

        return ok(reset_pwd.render(message));
    }

	public Result updatePassword() {
	  CosmicUser user = userGlobal; //test

		RequestBody body = request().body();

		String newPassword = body.asFormUrlEncoded().get("newPassword") == null ? "" : body.asFormUrlEncoded().get("newPassword")[0];
		String confirmationPassword = body.asFormUrlEncoded().get("confirmationPassword") == null ? "" : body.asFormUrlEncoded().get("confirmationPassword")[0];
		String oldPassword = body.asFormUrlEncoded().get("oldPassword") == null ? "" : body.asFormUrlEncoded().get("oldPassword")[0];
		//User user = User.find.byId();

        if(user.password.equals(oldPassword)) {
            if(newPassword.equals(confirmationPassword)) {
		            user.setPassword(newPassword);
                Ebean.update(user);
		            flash("success", "Nouveau mot de passe sauvegardé");
            }else{
                flash("error", "Les deux nouveaux mot de passe ne sont pas identiques");
            }
        }else{
            flash("success", "Ancien mot de passe ne correspond pas");
        }
		return redirect(routes.UserCtrl.settings());
	}

	public Result changeUsername(){
	    CosmicUser user = userGlobal; //test

	    RequestBody body = request().body();
	    String newUsername = body.asFormUrlEncoded().get("newUsername") == null ? "" : body.asFormUrlEncoded().get("newUsername")[0];
		//User user = User.find.byId(id);

		if(!newUsername.equals("")) {
		    if(email_valid(newUsername) && !email_exist(newUsername)) {
		        user.setEmail(newUsername);
            Ebean.update(user);
            flash("success", "Email modifié avec succès!");
		    }else{
		        if(!username_exist(newUsername)) {
		            user.setAlias(newUsername);
                Ebean.update(user);
                flash("success", "Nom d'utilisateur modifié avec succès!");
		        }else{
		            flash("error", "Erreur serveur");
		        }
		    }
		}else{
		    flash("error", "Un nom d'utilisateur/email ne peut être vide !");
		}
		return redirect(routes.UserCtrl.settings());
	}

	public Result updateProfil(){

		RequestBody body = request().body();

		String emailSended = body.asFormUrlEncoded().get("email") == null ? "" : body.asFormUrlEncoded().get("email")[0];
		String nameSended =  body.asFormUrlEncoded().get("name") == null ? "" : body.asFormUrlEncoded().get("name")[0];
		String urlSended = body.asFormUrlEncoded().get("url") == null ? "" : body.asFormUrlEncoded().get("url")[0];
		String companySended = body.asFormUrlEncoded().get("company") == null ? "" : body.asFormUrlEncoded().get("company")[0];
		String locationSended = body.asFormUrlEncoded().get("location") == null ?  "" :body.asFormUrlEncoded().get("location")[0];

		CosmicUser user = userGlobal;

		if(nameSended.length() == 0){

		    flash("error", "Nom invalide!");

		}else{

    		if(user != null){
            /*Http.MultipartFormData data = body.asMultipartFormData();
            FilePart picture = data.getFile("picture");
            if (picture != null) {
                String fileName = picture.getFilename();
                String contentType = picture.getContentType();
                File file = picture.getFile();
            }*/
            user.setEmail(emailSended);
            user.setName(nameSended);
            user.setCompany(companySended);
            user.setLocation(locationSended);
            Ebean.update(user);
    		    flash("success", "Modifications effectuées avec succes");

    		}else{

    		    flash("error", "Serveur indisponible. Réessayer plus tard");
    		}
		}

		return redirect(routes.UserCtrl.show());
    }

	public Result deleteUser() {
	    CosmicUser user = userGlobal; //test
	    Long id = user.id; //test

		if (userExist(id)){
		    //User user = User.find.byId(id);
		    List<Organisation> userAdmin = Organisation.find
				.where().ilike("id_admin", id.toString()).findList();
			if (user.organisations.size() == 0) {
        if (userAdmin.size() == 0) {
          user.setDeleted(true);
          Ebean.update(user);
          flash("success", "Compte supprimé");
			  } else {
					return badRequest("Utilisateur est administrateur d'une ou plusieurs organisations");
				}
			} else {
				return badRequest("Utilisateur est affecté a une ou plusieurs organisations");
			}
	  }

		return redirect(routes.Application.index());
  }


/* Helpers */
	public CosmicUser findUser(String userInformation) {
		CosmicUser user = null;
		if (isNumeric(userInformation)) {
			Long id = Long.parseLong(userInformation);
			user = CosmicUser.find.byId(id);
			return user;
		} else {
			List<CosmicUser> users = CosmicUser.find.where()
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
		CosmicUser user = CosmicUser.find.byId(id);
		if (user != null) {
			exist = true;
		}

		return exist;
	}

	public boolean email_exist(String mail) {
	   List<CosmicUser> users = CosmicUser.find.where()
					.ilike("email", mail).findList();
		return (users.size() == 1 ? true : false);
	}

    public boolean username_exist(String username) {
        List<CosmicUser> users = CosmicUser.find.where()
					.ilike("alias", username).findList();
	     return (users.size() >= 1 ? true : false);
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


	public ArrayList justePourTest(CosmicUser globalUser) {

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
