package controllers;

import play.*;
import play.mvc.*;
import views.html.*;
import java.util.*;
import models.*;

public class ControllerGlossaire extends Controller {
    private ModelGlossaire m;
    private ViewGlossaire v;
    private String language;

    // Default constructor
    public ControllerGlossaire () {
        this->m = new ModelGlossaire();
        this->language = "EN"; // TODO see how to get the user's langugage
    }

    // Shows the glossary, with or without filters
    public Glossary showGlossary(String filter1, String filter2, String filter3, boolean andSlashOr){
        Glossary glo;
        if (filter1.isEmpty() && filter2.isEmpty() && filter3.isEmpty()) {
            glo = this->m.getGlossary(this->language);
        } else {
            glo = this->m.getGlossaryWithFilters(this->language, filter1, filter2, filter3, andSlashOr);
        }

        return glo;
    }

    // checks if a word is in the glossary
    public boolean isWordOfGlossary(String word){
        return this.m->isInGlossary(word);
    }

    // get the description of a word
    public String getDescription(String word){
        if(this.isWordOfGlossary(word)){
            return this->m.getDescription(word);
        } else {
            return "";
        }
    }
}
