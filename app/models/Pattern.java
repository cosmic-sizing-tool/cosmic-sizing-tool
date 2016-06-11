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
public class Pattern extends Model implements JsonSerializable {
    @Id
    private Long id;
    private String name;
    private String description;
    private Date dateLastModified;
    private Date dateCreated;
    private boolean isValid;
    @ManyToOne
    private BasicUser userId;
    
    @OneToMany(cascade = CascadeType.ALL)
    private Set<FunctionalProcess> funcProcessSet = new HashSet<>();

    public Pattern(String name, String description, Date dateLastModified, Date dateCreated, boolean isValid, BasicUser userId) {
        this.name = name;
        this.description = description;
        this.dateLastModified = dateLastModified;
        this.dateCreated = dateCreated;
        this.isValid = isValid;
        this.userId = userId;
    }
    
    public Set<FunctionalProcess> getFuncProcessSet() {
        return funcProcessSet;
    }

    public void setFuncProcessList(Set<FunctionalProcess> funcProcessList) {
        this.funcProcessSet = funcProcessList;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateLastModified() {
        return dateLastModified;
    }

    public void setDateLastModified(Date dateLastModified) {
        this.dateLastModified = dateLastModified;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isIsValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public BasicUser getUserId() {
        return userId;
    }

    public void setUserId(BasicUser userId) {
        this.userId = userId;
    }
    
    
    
    @Override
    public String toJson() {
        JsonBuilder json = new JsonBuilder();
        json.add("id", id);
        json.add("name", name);

        return json.toString();
    }
    
    public int getNumberFuncProcess() {
        return funcProcessSet.size();
    }
    
    public int getCFPSize() {
        int total = 0;
        for(FunctionalProcess funcProcess : funcProcessSet) {
            total += funcProcess.getCFPSize();
        }
        return total;
    }
}
