package controllers;



import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import models.Organisation;
import models.CosmicUser;
//import models.User;
import play.*;
import play.db.ebean.Transactional;
import play.libs.Json;
import play.mvc.*;
import views.html.*;

import com.avaje.ebean.*;


@Transactional
public class OrganisationCtrl extends Controller {



	public Result index() {
		Organisation o1 = new Organisation();
    	o1.id = 12L ;
    	o1.name = "UQAM";
    	o1.save();
        return ok(index.render("o1 created"));
    }
    //curl -H "Content-Type: application/json" -X POST -d '{"name":"UQAM"}' http://127.0.0.1:9000/user/123/orgs
    @BodyParser.Of(play.mvc.BodyParser.Json.class)
    public Result createOrganisation(String idUser){
    	//User newUser = Json.fromJson(request().body().asJson(), User.class);
    	//newUser.save();

    	// parse the JSON as a JsonNode
    	CosmicUser user = CosmicUser.find.byId(Long.valueOf(idUser).longValue());
    	List<Organisation> list = Organisation.find.all();
    	JsonNode json = request().body().asJson();
    	if(json == null) {
    	    return badRequest("Expecting Json data");
    	} else {
    	System.out.println(json);
    	Organisation o1 = new Organisation();
    	o1.id = Long.valueOf(list.size() + 1);
    	o1.name = json.findPath("name").textValue();
    	o1.description =  json.findPath("description").toString().replaceAll("\"", "");
        o1.urlOrgnisation =  json.findPath("url_orgnisation").toString().replaceAll("\"", "");
        o1.urlImage =  json.findPath("url_image").toString().replaceAll("\"", "");
        o1.nomContact =  json.findPath("nom_contact").toString().replaceAll("\"", "");
        o1.telContact =  json.findPath("tel_contact").toString().replaceAll("\"", "");
        o1.courrielContact =  json.findPath("courriel_contact").toString().replaceAll("\"", "");;
        o1.adresse1 =  json.findPath("adresse1").toString().replaceAll("\"", "");
        o1.adresse2 =  json.findPath("adresse2").toString().replaceAll("\"", "");
        o1.pays =  json.findPath("pays").toString().replaceAll("\"", "");
        o1.etat =  json.findPath("etat").toString().replaceAll("\"", "");
        o1.ville =  json.findPath("ville").toString().replaceAll("\"", "");
    	//u1.deleted = false;

        o1.users.add(user);
    	o1.save();

    	user.organisations.add(o1);
    	user.save();
    	// read the JsonNode as a Person
    	return ok(index.render("created"));
    	}
    }

    @BodyParser.Of(play.mvc.BodyParser.Json.class)
    public Result modifyOrganisation(String idUser, String  idOrganisation){
    	//User newUser = Json.fromJson(request().body().asJson(), User.class);
    	//newUser.save();

    	// parse the JSON as a JsonNode
    	CosmicUser user = CosmicUser.find.byId(Long.valueOf(idUser).longValue());
    	JsonNode json = request().body().asJson();
    	if(json == null) {
    	    return badRequest("Expecting Json data");
    	} else {

    	 for(int i=0;i<user.organisations.size();i++){
			if(user.organisations.get(i).id == Long.valueOf(idOrganisation)) {
			    Organisation o1 = user.organisations.get(i);
    	        o1.name = json.findPath("name").textValue();
    	        o1.description =  json.findPath("description").toString().replaceAll("\"", "");
                o1.urlOrgnisation =  json.findPath("url_orgnisation").toString().replaceAll("\"", "");
                o1.urlImage =  json.findPath("url_image").toString().replaceAll("\"", "");
                o1.nomContact =  json.findPath("nom_contact").toString().replaceAll("\"", "");
                 o1.telContact =  json.findPath("tel_contact").toString().replaceAll("\"", "");
                o1.courrielContact =  json.findPath("courriel_contact").toString().replaceAll("\"", "");;
                 o1.adresse1 =  json.findPath("adresse1").toString().replaceAll("\"", "");
                o1.adresse2 =  json.findPath("adresse2").toString().replaceAll("\"", "");
                o1.pays =  json.findPath("pays").toString().replaceAll("\"", "");
                o1.etat =  json.findPath("etat").toString().replaceAll("\"", "");
                o1.ville =  json.findPath("ville").toString().replaceAll("\"", "");
    	        o1.save();
			  }
		}

    	//u1.deleted = false;
    	user.save();
    	// read the JsonNode as a Person
    	return ok(index.render("modified"));
    	}
    }

	public Result addAdminToAnOrganisation(long id) {
		UserCtrl user = new UserCtrl();
		if(organisationExist(id)){
			JsonNode json = Json.parse(request().body().asJson().toString());

			Long idAdmin = Long.parseLong(json.findPath("idAdmin").toString()
					.replaceAll("\"", ""));

			if(user.userExist(idAdmin)){

				Organisation o1 = new Organisation();
				o1 = Organisation.find.byId(id);

				o1.id_admin=idAdmin;

				o1.save();

			}else{
				return notFound(index.render("User not found!"));
			}

		}else{
			return notFound(index.render("Organisation not found!"));
		}



		return ok(index.render("deleted"));

	}
	public Result deleteOrganisation(String idUser, String idOrganisation){

		if(organisationExist(Long.valueOf(idOrganisation))){
		    CosmicUser user = CosmicUser.find.byId(Long.valueOf(idUser).longValue());
			for(int i=0;i<user.organisations.size();i++){
			    if(user.organisations.get(i).id == Long.valueOf(idOrganisation)) {
			        user.organisations.remove(i);
			    }
			}
			user.save();


		}else{
			return notFound("Aucune organisation n'a été trouvé!");
		}


		return ok(index.render("deleted"));
	}

	public boolean organisationExist(long id) {

		boolean exist = false;
		Organisation organisation = Organisation.find.byId(id);
		if (organisation != null) {
			exist = true;
		}

		return exist;
	}

	public List<Organisation> organisationShowAll() {
		return Organisation.find.all();
	}
	/**public Result getUserAdmin(){

	}*/
}
