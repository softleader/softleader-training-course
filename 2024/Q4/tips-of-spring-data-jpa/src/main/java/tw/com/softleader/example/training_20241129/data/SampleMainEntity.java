package tw.com.softleader.example.training_20241129.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SampleMainEntity {

  @Id @GeneratedValue Long id;

  String name;

  @OneToMany(
      cascade = {jakarta.persistence.CascadeType.ALL},
      orphanRemoval = true)
  @JoinColumn(name = "main_id")
  List<SampleDetailEntity> details;
}
