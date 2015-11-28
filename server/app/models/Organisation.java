package models;

import java.util.*;
import javax.persistence.*;
import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;


@Entity
public class Organisation extends Model {
	@Id
    @Constraints.Min(10)
    public Long id;

    @Constraints.Required
    public String name;
    
    @ManyToMany(cascade = CascadeType.ALL)
    public List<TeamMember> users = new ArrayList<TeamMember>();
    
    public static Finder<Long, Organisation> find = new Finder<Long,Organisation>(Organisation.class);

}



