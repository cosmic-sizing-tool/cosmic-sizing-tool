package controllers;

import play.*;
import play.mvc.*;
import views.html.*;
import java.util.*;
import models.*;

public class ControllerGlossary extends Controller {
    private ModelGlossary m; // Model of the glossary
    private String language; // Language of the user
    private HashMap<String, String> g; // The whole glossary

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

    // Checks if a word is in the glossary
    public boolean isWordOfGlossary(String word){
        return g.containsKey(word);
    }

    // Get the description of a word
    public String getDescription(String word){
        return g.get(word).toString();
    }

    // Renders the view of the glossary
    public Result glossary(){
        return ok(glossary.render(g));
    }
}
