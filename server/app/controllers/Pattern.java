package controllers;

/*
    13-03-2016
    Team Cowabunga :    Dany Deroy
                        Francis Bilodeau
                        Simon Zeni
                        John Béjot
                        Julien Girard

    Controller principal des pages de la Gestion des patrons
    Les views sont dans /views/pattern/
 */

import com.avaje.ebean.Model;
import com.avaje.ebean.Query;
import com.fasterxml.jackson.databind.JsonNode;
import play.*;
import play.api.libs.json.Json;
import play.api.libs.ws.ssl.SystemConfiguration;
import play.data.*;
import  play.data.validation.Constraints;
import play.mvc.*;
import views.html.pattern.*;
import java.util.*;
import models.*;
import play.data.Form;
import static play.data.Form.*;

public class Pattern extends Controller {

    public Result index() {
        return ok(index.render());
    }

    public Result modification(long id_pattern) {
        return ok(modification.render());
    }

    public Result delete(long id_pattern) {
        return ok("affichage delete");
    }


    public Result create() {
        /* Pattern du json
           {
                "id": "85",
                "desc_short": "",
                "desc_long": "",
                "processes": [{
                    "name": "",
                    "lines": [{
                        "data_group": " ",
                        "data_type": ""
                    }]
                }]
            }
         */

        // Recupere la data avec GET et stock dans la base de donnees
        JsonNode json = request().body().asJson();
        int taille = 0;

        if(json == null) {
            return badRequest("Expecting Json data");
        } else {
            String id = json.findPath("id").textValue();
            if(id == null)
                return badRequest("Missing parameter [id]");

            String desc_short = json.findPath("desc_short").textValue();
            if(desc_short == null)
                return badRequest("Missing parameter [desc_short]");

            if(desc_short.length() > 20)
                return badRequest("[desc_short] is over 20 char");

            // Valider le contenu de processes
            List<JsonNode> processes = json.findValues("processes");

        }

        return ok();//TODO mettre dans la database
    }

    public Result update(long id_pattern) {return ok("affichage update");
    }

    //Va chercher le json dans la db
    public Result description(long id_pattern){
        return  null;
    }


}







