package tw.com.softleader.training.java_exp;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@MappedSuperclass
public class GenericEntity {

  @Id
  @GeneratedValue
  Long id;

  @CreatedDate
  LocalDateTime createdTime;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
