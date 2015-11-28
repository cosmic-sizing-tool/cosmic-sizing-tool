package controllers;

import play.*;
import play.mvc.*;
import views.html.*;
import java.util.*;
import models.*;
import com.fasterxml.jackson.databind.JsonNode;
import play.mvc.Http.RequestBody;

public class Measures extends Controller {

    public Result add() {
    	return ok(mesures_add.render());
    }

    public Result postSaveTemp() {
    	JsonNode json = request().body().asJson();
    	System.out.println(json);
    	// Implement save to db
    	return ok();
    }

}
