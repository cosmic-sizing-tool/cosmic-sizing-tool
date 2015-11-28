package models;

import java.util.*;
import javax.persistence.*;
import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;


@Entity
public class User extends Model {
    @Id
    @Constraints.Min(10)
    public Long id;
    
    @Constraints.Required
    public String alias;
    
    @Constraints.Required
    
    public String name;
    
    @Constraints.Required
    public String password;
    
    @Constraints.Required
    @Column(unique = true) 
    public String email;
    
    @Constraints.Required
    boolean deleted;
    
    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
    public List<Organisation> organisations = new ArrayList<Organisation>();

 //   @ManyToOne(cascade = CascadeType.ALL)
//    public List<Organisation> administrators = new ArrayList<Organisation>();
    public static Finder<Long, User> find = new Finder<Long,User>(User.class);

    

}
