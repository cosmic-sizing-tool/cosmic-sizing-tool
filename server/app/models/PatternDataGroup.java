package models;

/*
    13-03-2016
    Team Cowabunga :    Dany Deroy
                        Francis Bilodeau
                        Simon Zeni
                        John Béjot
                        Julien Girard

    Table PatternDataGroup
    Clé étrangère vers la table PatternProcess
*/

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

import util.*;

@Entity
public class PatternDataGroup extends Model implements JsonSerializable{

    @Id
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 4, nullable = false)
    private String mouvement;

    /* foreign key vers projet */
    @Column(nullable = false)
    private long process;

    public PatternDataGroup(long parentId, String name, String mouvement) {
        id=null;
        this.name = name;
        this.mouvement = mouvement;
        this.process = parentId;
    }

    @Override
    public String toJson()
    {
        JsonBuilder json = new JsonBuilder();
        json.add("id", id);
        json.add("name", name);
        json.add("mouvement", mouvement);
        json.add("process_id", process);

        return json.toString();
    }

    public long getId(){return id;}
    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public static Finder<Long,PatternDataGroup> find = new Finder<Long,PatternDataGroup>(Long.class, PatternDataGroup.class);
}
