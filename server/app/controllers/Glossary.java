package controllers;

import play.*;
import play.mvc.*;
import views.html.*;
import java.util.*;
import models.*;

public class Glossary extends Controller {
    private static HashMap<String, String> g; // The whole glossary

    // Shows the glossary, with or without filters
    public void showGlossary(String filter1, String filter2, String filter3, boolean andSlashOr){
        ModelGlossary m = new ModelGlossary();

        if (filter1.isEmpty() && filter2.isEmpty() && filter3.isEmpty()) {
            // get the glossary based on filters
            g = m.getGlossaryWithFilters(filter1, filter2, filter3, andSlashOr);
        } else {
            g = m.getGlossary();
        }
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
