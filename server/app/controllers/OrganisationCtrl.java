package controllers;



import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import models.Organisation;
import models.User;
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
    public Result createOrganisation(){
    	//User newUser = Json.fromJson(request().body().asJson(), User.class);
    	//newUser.save();
    	
    	// parse the JSON as a JsonNode
    	JsonNode json = Json.parse(request().body().asJson().toString());
    	Organisation o1 = new Organisation();
    	o1.id = Long.parseLong(json.findPath("id").toString().replaceAll("\"", ""));
    	o1.name = json.findPath("name").toString().replaceAll("\"", "");
   
    	//u1.deleted = false;
    	o1.save();
    	// read the JsonNode as a Person
    	return ok(index.render("created"));
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
	public Result deleteOrganisation(Long id){

		if(organisationExist(id)){
			List<Organisation> organisation = Organisation.find.where().ilike("id",id.toString()).findList();
			
			for(int i=0;i<organisation.size();i++){
				organisation.get(i).delete();
			}
			
			
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

}



