package tw.com.softleader.training.java_exp;

import jakarta.persistence.Embeddable;

@Embeddable
public class SubSampleEmbed {

  String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
