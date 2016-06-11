package models;

/*
 13-03-2016
 Team Cowabunga :    Dany Deroy
 Francis Bilodeau
 Simon Zeni
 John Béjot
 Julien Girard

 Table Pattern (master) pour la sauvegarde des patrons (pattern)
 */
import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

import util.*;

public class Pattern extends Model implements JsonSerializable {

    private Long id;
    private String name;
    private String description;
    private Date dateLastModified;
    private Date dateCreated;
    private boolean isValid;
    
    private Set<FunctionalProcess> funcProcessSet;

    
    
    public Set<FunctionalProcess> getFuncProcessSet() {
        return funcProcessSet;
    }

    public void setFuncProcessList(Set<FunctionalProcess> funcProcessList) {
        this.funcProcessSet = funcProcessList;
    }
    
    @Override
    public String toJson() {
        JsonBuilder json = new JsonBuilder();
        json.add("id", id);
        json.add("name", name);

        return json.toString();
    }
    
    public int getNumberFuncProcess() {
        return funcProcessSet.size();
    }
    
    public int getCFPSize() {
        
        for(FunctionalProcess funcProcess : funcProcessSet) {
            funcProcessSet
        }
        return 0;
    }
}
