package models;

import java.util.Date;

import com.avaje.ebean.Model;

public class DataGroup extends Model {
    Date timeStamp;
    String description;
    public boolean entry;
    public boolean exit;
    public boolean read;
    public boolean write;
    public boolean triggerEvent;

    public DataGroup(String description) {
        this.timeStamp = new Date();
        this.description = description;
    }

    @Override
    public int hashCode() {
        return timeStamp.hashCode();
    }

}
