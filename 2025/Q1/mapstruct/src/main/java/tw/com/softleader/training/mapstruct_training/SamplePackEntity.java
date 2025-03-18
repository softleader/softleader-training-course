package tw.com.softleader.training.mapstruct_training;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SamplePackEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @Singular
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "SAMPLE_PACK_ID")
  List<SampleEntity> samples;
}
