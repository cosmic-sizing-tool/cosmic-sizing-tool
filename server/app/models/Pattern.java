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

@Entity
public class Pattern extends Model implements JsonSerializable{

    @Id
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 30, nullable = false)
    private String descriptionCourte;

    @Column(length = 250, nullable = false)
    private String descriptionLongue;


    public Pattern(String name, String descriptionCourte, String descriptionLongue) {
        id = null;
        this.descriptionCourte = descriptionCourte;
        this.descriptionLongue = descriptionLongue;
    }

    @Override
    public String toJson()
    {
        JsonBuilder json = new JsonBuilder();
        json.add("id", id);
        json.add("name", name);
        json.add("process", getProcess());

        return json.toString();
    }

    public List<PatternProcess> getProcess() {
        return PatternProcess.find.where().eq("pattern", id).findList();
    }
}
