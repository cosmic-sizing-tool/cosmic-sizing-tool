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
import play.data.*;
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

    public Result description(long id_pattern) {
        return ok(modification.render());
    }

    public Result delete(long id_pattern) {
        return ok("affichage delete");
    }

    public Result create() {

        // Recupere la data avec GET et affiche la data reçue
        Form<InfoIDPatron> infoIDPatronForm = form(InfoIDPatron.class);
        InfoIDPatron infoIDPatron = infoIDPatronForm.bindFromRequest().get();
        System.out.println("form Patron");
        System.out.println(infoIDPatron.nom);
        System.out.println(infoIDPatron.descCourte);
        System.out.println(infoIDPatron.descLongue);

        return ok("affichage create");
    }

    public Result update(long id_pattern) {
        return ok("affichage update");
    }


    // Premier form pour le patron
    public class InfoIDPatron {
        public String nom;
        public String descCourte;
        public String descLongue;

        public String validate() {
            if(nom == null || descCourte == null) {
                return "Nom et description courte requis";
            }

            if(nom.length() > 20) {
                return "Nom trop long, seulement 20 caractères sont permis";
            }

            if(descCourte.length() > 30) {
                return "Description trop longue, seulement 30 caractères sont permis";
            }
            return null;
        }
    }
}





