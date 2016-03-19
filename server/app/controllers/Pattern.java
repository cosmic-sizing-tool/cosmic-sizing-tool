package controllers;

/*
    13-03-2016
    Team Cowabunga :    Dany Deroy
                        Francis Bilodeau
                        Simon Zeni
                        John Béjot
                        Julien Girard
 */

import play.*;
import play.api.libs.json.Json;
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

        // Recupere la data avec GET et affiche la data reçue
        //JsonNode json = request().body().asJson();

        return ok("affichage create");
    }

    public Result update(long id_pattern) {
        return ok("affichage update");
    }

    public Result description(long id_pattern){
        return ok("description");
    }

    public class InfoPatron {

        //JsonNode json = Json.parse
        //Patron p = Json.fromJson(JSONICI, Patron.class);

    }

    public class Patron{
        public int id;
        public String desc_short;
        public String desc_long;
        public Process lignes[];

        public Patron(int id, String desc_short, String desc_long, Process[] lignes) {
            this.id = id;
            this.desc_short = desc_short;
            this.desc_long = desc_long;
            this.lignes = lignes;
        }
    }

    private class Process{
        private String name;
        private String lines[];

        public Process(String name, String[] lines) {
            this.name = name;
            this.lines = lines;
        }

        public String getName() {
            return name;
        }

        public String[] getLines() {
            return lines;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setLines(String[] lines) {
            this.lines = lines;
        }
    }
}







