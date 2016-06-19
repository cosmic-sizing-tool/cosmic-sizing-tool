package models;

import javax.persistence.Id;
import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Address extends Model {

    @Id
    public UUID id;

    @Column
    public String postalCode;

    @Column
    public String street;

    @Column
    public String appartment;

    @ManyToOne(cascade = CascadeType.ALL)
    public City city;

    @ManyToOne(cascade = CascadeType.ALL)
    public Organisation organisation;
}
