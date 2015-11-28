package controllers;



import com.fasterxml.jackson.databind.JsonNode;

import models.Organisation;
import models.TeamMember;
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
}



