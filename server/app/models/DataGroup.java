package models;

import java.util.Date;

/**
 * Created by louis on 15-11-28.
 */
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

}
