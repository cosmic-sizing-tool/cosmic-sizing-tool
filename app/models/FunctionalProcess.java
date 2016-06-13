package models;

import com.avaje.ebean.Model;

import java.util.*;
import javax.persistence.*;

@Entity
public class FunctionalProcess extends Model {
    @Id
    private Long id;
    private String name;
    
    @OneToMany(cascade = CascadeType.ALL)
    private Set<DataGroup> dataGroupSet = new HashSet<>();

    public FunctionalProcess(String name) {
        this.name = name;
    }

    public Set<DataGroup> getDataGroupSet() {
        return dataGroupSet;
    }

    public void setDataGroups(Set<DataGroup> dataGroupSet) {
        this.dataGroupSet = dataGroupSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
    public int getCFPSize() {
        int total = 0;
        for(DataGroup dataGroup : dataGroupSet) {
            total += dataGroup.getCFPSize();
        }
        return total;
    }
}
