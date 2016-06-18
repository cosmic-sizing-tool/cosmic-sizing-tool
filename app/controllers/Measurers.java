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

public class Measurers extends Controller {

  public Result index() {

      // send all methods and versions to this view

      return ok(views.html.measurers.render());
  }

  public Result search() {
    String method = Controller.request().queryString().get("method")[0];
    String country = Controller.request().queryString().get("country")[0];
    String state = Controller.request().queryString().get("state")[0];
    String city = Controller.request().queryString().get("city")[0];


    // {
    //   users: [
    //     cityCode: "",
    //     email: "",
    //     name: "",
    //     certification_methods: [
    //       {
              //   method: "",
              //   version: ""
              // }
    //     ]
    //   ]
    // }

    //CosmicUser.

    return ok();
  }
}
