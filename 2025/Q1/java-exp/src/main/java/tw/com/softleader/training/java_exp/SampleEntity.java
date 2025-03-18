package tw.com.softleader.training.java_exp;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class SampleEntity extends GenericEntity {

  String name;

//  @OneToMany(cascade = { jakarta.persistence.CascadeType.ALL })
//  @JoinColumn(name = "sample_id")
//  List<SubSampleEntity> subSamples;

  @ElementCollection
  List<String> tags;

  @ElementCollection
  List<SubSampleEmbed> subSamples;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

//  public List<SubSampleEntity> getSubSamples() {
//    return subSamples;
//  }
//
//  public void setSubSamples(List<SubSampleEntity> subSamples) {
//    this.subSamples = subSamples;
//  }

  public List<SubSampleEmbed> getSubSamples() {
    return subSamples;
  }

  public void setSubSamples(List<SubSampleEmbed> subSamples) {
    this.subSamples = subSamples;
  }

  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }
}
