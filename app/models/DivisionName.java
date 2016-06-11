package models;
import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

/**
 * Created by ethienneroy on 2016-06-11.
 */
@Entity
public class DivisionName extends Model{

    public String name;
}