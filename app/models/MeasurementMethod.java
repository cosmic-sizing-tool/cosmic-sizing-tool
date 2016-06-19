package models;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class MeasurementMethod extends Model {
  @Id
  public UUID id;

  @Column
  public String name;

  public MeasurementMethod(String name){
    this.id = UUID.randomUUID();
    this.name = name;
  }

  @OneToMany(cascade = CascadeType.ALL)
  public List<MeasurementMethodVersion> measurementMethodVersions = new ArrayList<MeasurementMethodVersion>();
}
