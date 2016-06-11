package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import com.lambdaworks.crypto.SCryptUtil;
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

 // Password is scrypted.
 @Column(length = 255, nullable = false)
 @Constraints.MaxLength(255)
 @Constraints.Required
 private String password;

 public void setPassword(String password) {
  this.password = SCryptUtil.scrypt(password, 16384, 8, 1);
 }

 public String getPasswordHash() {
  return this.password;
 }


 public static Finder<Long,TeamMember> find = new Finder<Long,TeamMember>(Long.class, TeamMember.class);

}
