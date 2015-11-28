package models;

import java.util.*;
import javax.persistence.*;
import java.util.ArrayList;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

public class ModelGlossary extends Model{
    public final static String pathGlossary = System.getProperty("user.dir")+"/"+"Glossary.txt";
    HashMap<String, String> gloss = new ArrayList();

    public HashMap<String, String> getGlossary(String language) {
        return new HashMap();
    }

    public String getLang() {
        return "EN"; // TODO - Get this from session
    }

    public HashMap<String, String> getGlossaryWithFilters(String filter1, String filter2, String filter3, boolean andSlashOr) {
        return new HashMap();
    }
}
