package models;

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

    @Column(length = 255, nullable = false)
    private String name;

    @Column(length = 255, nullable = false)
    private String comment;

    /* foreign key vers projet */
    @Column(nullable = false)
    private long patternProcess;

    /* booleans */
    @Column(nullable = false)
    private int entry;

    @Column(nullable = false)
    private int exit;

    @Column(nullable = false)
    private int read;

    @Column(nullable = false)
    private int write;

    public PatternDataGroup(long parentId) {
        id=null;
        name="";
        comment="";
        patternProcess=parentId;
        entry=0;
        exit=0;
        read=0;
        write=0;
    }

    @Override
    public String toJson()
    {
        JsonBuilder json = new JsonBuilder();
        json.add("id", id);
        json.add("name", name);
        json.add("comment", comment);
        json.add("process_id", patternProcess);
        json.add("entry", entry);
        json.add("exit", exit);
        json.add("read", read);
        json.add("write", write);

        return json.toString();
    }

    public long getId(){return id;}
    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public String getComment(){return comment;}
    public void setComment(String comment){this.comment = comment;}

    public boolean getEntry(){ return entry == 1; }
    public boolean getExit(){ return exit == 1; }
    public boolean getRead(){ return read == 1; }
    public boolean getWrite(){ return write == 1; }

    public void setEntry(boolean val){ entry = val ? 1 : 0; }
    public void setExit(boolean val){ exit = val ? 1 : 0; }
    public void setRead(boolean val){ read = val ? 1 : 0; }
    public void setWrite(boolean val){ write = val ? 1 : 0; }

    public static Finder<Long,PatternDataGroup> find = new Finder<Long,PatternDataGroup>(Long.class, PatternDataGroup.class);
}
