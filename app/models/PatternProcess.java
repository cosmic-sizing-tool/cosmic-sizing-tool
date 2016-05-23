package models;

/*
    13-03-2016
    Team Cowabunga :    Dany Deroy
                        Francis Bilodeau
                        Simon Zeni
                        John Béjot
                        Julien Girard

    Table PatternProcess
    Clé étrangère vers la table Pattern
*/

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

import util.*;

@Entity
public class PatternProcess extends Model implements JsonSerializable{

    @Id
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    /* foreign key vers Pattern */
    @Column(nullable = false)
    private long pattern;


    public PatternProcess(long parentId, String name) {
        id=null;
        this.name = name;
        pattern = parentId;
    }

    @Override
    public String toJson()
    {
        JsonBuilder json = new JsonBuilder();
        json.add("id", id);
        json.add("name", name);
        json.add("pattern_id", pattern);
        json.add("data_groups", getDataGroup());

        return json.toString();
    }

    @Override
    public void delete()
    {
        List<PatternDataGroup> dgs = getDataGroup();
        for(PatternDataGroup dg : dgs) dg.delete();

        super.delete();
    }

    public long getId(){return id;}
    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public List<PatternDataGroup> getDataGroup() {
        return PatternDataGroup.find.where().eq("process", id).findList();
    }

    public static Finder<Long,PatternProcess> find = new Finder<Long,PatternProcess>(Long.class, PatternProcess.class);
}
