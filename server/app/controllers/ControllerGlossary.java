package controllers;

import play.*;
import play.mvc.*;
import views.html.*;
import java.util.*;
import models.*;

public class ControllerGlossary extends Controller {
    private ModelGlossary m;
    private String language;
    private HashMap<String, String> g;

    // Default constructor
    public ControllerGlossary () {
        m = new ModelGlossary();
        language = m.getLang();
        g = m.getGlossary(language);
    }

    // Shows the glossary, with or without filters
    public HashMap<String, String> showGlossary(String filter1, String filter2, String filter3, boolean andSlashOr){
        HashMap<String, String> glo = new HashMap();
        if (filter1.isEmpty() && filter2.isEmpty() && filter3.isEmpty()) {
            // get the glossary based on filters
            glo = m.getGlossaryWithFilters(language, filter1, filter2, filter3, andSlashOr);
        }

        return glo;
    }

    // checks if a word is in the glossary
    public boolean isWordOfGlossary(String word){
        //return glossary.containsKey(word);
        return true;
    }

    // get the description of a word
    public String getDescription(String word){
        //return glossary.get(word).toString();
        return "";
    }

    public Result glossary(){
        return ok(glossary.render(g));
    }
}
