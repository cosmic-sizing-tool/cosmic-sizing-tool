package models;

import java.util.*;
import javax.persistence.*;
import java.util.ArrayList;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

public class ModelGlossary extends Model{
    private String language;
    public final static String pathGlossary = System.getProperty("user.dir")+"/"+"Glossary.txt";
    ArrayList gloss = new ArrayList();



    void setGlossary(String language) {

        this.language = language;
        //setChanged();
        //notifyObservers();
    }

    public HashMap<String, String> getGlossary(String language) {
        return new HashMap();
    }

    public String getLang() {
        return language;
    }

    public HashMap<String, String> getGlossaryWithFilters(String language, String filter1, String filter2, String filter3, boolean andSlashOr) {
        return new HashMap();
    }
}
