package models;

import javax.persistence.Id;
import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

/**
 * Created by ethienneroy on 2016-06-11.
 */
@Entity
public class CountryDivisionType extends Model{

    public String name;
    @Id
    public String shortName;

    public Country country;

    public DivisionName divisionName;

}
