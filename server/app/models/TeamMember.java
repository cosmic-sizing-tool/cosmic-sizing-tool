package models;

import java.util.*;
import javax.persistence.*;
import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;


@Entity
public class TeamMember extends Model {
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
    public String email;
    
    @Constraints.Required
    boolean deleted;
    
    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
    public List<Organisation> organisations = new ArrayList<Organisation>();

    public static Finder<Long, TeamMember> find = new Finder<Long,TeamMember>(TeamMember.class);

    

}
