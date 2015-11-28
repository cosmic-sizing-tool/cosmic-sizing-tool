package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class TeamMember extends Model {

 @Id
 public Long id;

 @Column(length = 255, nullable = false)
 @Constraints.MaxLength(255)
 @Constraints.Required
 public String name;
 
 @Column(length = 255, nullable = false)
 @Constraints.MaxLength(255)
 @Constraints.Required
 public String email;
 
 @Column(length = 255, nullable = false)
 @Constraints.MaxLength(255)
 @Constraints.Required
 public String password;

 
 public static Finder<Long,TeamMember> find = new Finder<Long,TeamMember>(Long.class, TeamMember.class); 

}
