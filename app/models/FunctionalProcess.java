package models;

import com.avaje.ebean.Model;

import java.util.*;

public class FunctionalProcess extends Model {
    private Long id;
    private String name;
    
    private Set<DataGroup> dataGroupSet;

    public FunctionalProcess() {
        id = null;
        this.dataGroupSet = new HashSet<>();
    }

    public Set<DataGroup> getDataGroupSet() {
        return dataGroupSet;
    }

    public void setDataGroups(Set<DataGroup> dataGroupSet) {
        this.dataGroupSet = dataGroupSet;
    }
    
    public int getCFPSize() {
        for(FunctionalProcess funcProcess : dataGroups) {
            funcProcessSet
        }
    }
}
