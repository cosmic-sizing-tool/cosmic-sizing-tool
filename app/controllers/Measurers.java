package controllers;

/**
 * Created by Mathieu on 6/11/2016.
 */

import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;


import play.*;
import play.db.ebean.Transactional;
import play.libs.Json;
import play.mvc.*;
import views.html.*;

import com.avaje.ebean.*;

import models.MeasurementMethod;

public class Measurers extends Controller {

  public Result index() {
      // TEST remove this when the DB is prepopulated
        if(Ebean.find(MeasurementMethod.class).findList().size() == 0) {
          MeasurementMethod o1 = new MeasurementMethod("name1");
          Ebean.save(o1);
        }
      // TEST

      // Retrieving certification method names
      List<MeasurementMethod> measurementMethods = Ebean.find(MeasurementMethod.class).findList();
      List<String> measurementMethodNames = new ArrayList<String>();
      for (MeasurementMethod measurementMethod : measurementMethods) {
        measurementMethodNames.add(measurementMethod.name);
      }

      // TODO: repeat the last to render Countries, Regions and Cities from the DB.

      return ok(measurers.render(measurementMethodNames));
  }

  // TODO: the response format should look like:
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
  public Result search() {
    String method = Controller.request().queryString().get("method")[0];
    String country = Controller.request().queryString().get("country")[0];
    String state = Controller.request().queryString().get("state")[0];
    String city = Controller.request().queryString().get("city")[0];

    // TODO, query the database
    // Relevant tables are: Address, City, CosmicUser, Country, MeasurementMethod, MeasurementMethodVersion

    return ok();
  }
}
