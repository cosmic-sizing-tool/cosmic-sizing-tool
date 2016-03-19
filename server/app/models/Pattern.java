package models;

/*
    13-03-2016
    Team Cowabunga :    Dany Deroy
                        Francis Bilodeau
                        Simon Zeni
                        John Béjot
                        Julien Girard
*/

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

import util.*;

// TODO: 18/03/16 table primaire d'un patron 

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


    public Pattern() {

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

    public List<Process> getProcess() {
        return Process.find.where().eq("process", id).findList();
    }
}
