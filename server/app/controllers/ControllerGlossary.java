package controllers;

import play.*;
import play.mvc.*;
import views.html.*;
import java.util.*;
import models.*;

public class ControllerGlossary extends Controller {
    private ModelGlossaire m;
    private ViewGlossaire v;
    private String language;
    private Map glossary;

    // Default constructor
    public ControllerGlossary () {
        this->m = new ModelGlossary();
        this->language = this->m.getLang();
        this->glossary = this->m.getGlossary(this->language);
    }

    // Shows the glossary, with or without filters
    public Map<String, String> showGlossary(String filter1, String filter2, String filter3, boolean andSlashOr){
        Map<String, String> glo;
        if (filter1.isEmpty() && filter2.isEmpty() && filter3.isEmpty()) {
            // get the glossary based on filters
            glo = this->m.getGlossaryWithFilters(this->language, filter1, filter2, filter3, andSlashOr);
        }

        return glo;
    }

    // checks if a word is in the glossary
    public boolean isWordOfGlossary(String word){
        return this->glossary.containsKey(word);
    }

    // get the description of a word
    public String getDescription(String word){
        return this->glossary.get(word);
    }
}
