package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

import util.*;

public class DataGroup extends Model implements JsonSerializable {

    private Long id;
    private String name;
    
    /* 0 if mouvement is not there, 1 if yes, not booleans because of possible
        other options */
    private int entry;
    private int exit;
    private int read;
    private int write;

    public DataGroup(String name, int entry, int exit, int read, int write) {
        this.name = name;
        this.entry = entry;
        this.exit = exit;
        this.read = read;
        this.write = write;
    }

    @Override
    public String toJson() {
        JsonBuilder json = new JsonBuilder();
        json.add("id", id);
        json.add("name", name);
        json.add("entry", entry);
        json.add("exit", exit);
        json.add("read", read);
        json.add("write", write);

        return json.toString();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getEntry() {
        return entry == 1;
    }

    public boolean getExit() {
        return exit == 1;
    }

    public boolean getRead() {
        return read == 1;
    }

    public boolean getWrite() {
        return write == 1;
    }

    public void setEntry(int val) {
        entry = val;
    }

    public void setExit(int val) {
        exit = val;
    }

    public void setRead(int val) {
        read = val;
    }

    public void setWrite(int val) {
        write = val;
    }
    
    public int getCFPSize() {
        return entry + exit + read + write;
    }

    public static Finder<Long, DataGroup> find = new Finder<Long, DataGroup>(Long.class, DataGroup.class);
}
