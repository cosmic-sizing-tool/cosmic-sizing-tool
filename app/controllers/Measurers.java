package controllers;

/**
 * Created by Mathieu on 6/11/2016.
 */

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

/**
 * Created by Mathieu on 6/11/2016.
 */
public class Measurers extends Controller {

  public Result index() {
      return ok("index!");
  }

  public Result search() {
    return ok("search!");
  }
}
