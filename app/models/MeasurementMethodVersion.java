package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class MeasurementMethodVersion extends Model {

  int methodNumber;

  // @ManyToOne
  // public MeasurementMethod measurementMethod;
}
