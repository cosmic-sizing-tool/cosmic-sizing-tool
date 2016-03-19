package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Certification extends Model {

    @Id
    public Long idCertification;

    @Column(length = 255, nullable = false)
    @Constraints.MaxLength(255)
    @Constraints.Required
    public String method;

    @Column(length = 255, nullable = false)
    @Constraints.MaxLength(255)
    @Constraints.Required
    public String version;

    @Constraints.Required
    public int month;

    @Constraints.Required
    public int year;

    @ManyToOne(cascade = CascadeType.ALL)
    public User user; 


    public static Finder<Long,Certification> find = new Finder<Long,Certification>(Long.class, Certification.class);

}
