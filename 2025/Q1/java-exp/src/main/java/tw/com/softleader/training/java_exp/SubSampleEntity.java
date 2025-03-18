package tw.com.softleader.training.java_exp;

import jakarta.persistence.Entity;

@Entity
public class SubSampleEntity extends GenericEntity {

  String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
