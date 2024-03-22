package tw.com.softleader.training.effectiveusingdatajpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 附約
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(
    name = "coverage",
    indexes = {
        @Index(name = "coverage_idx", columnList = "coverageNo")
    })
public class CoverageEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String coverageNo;

  private String itemCode; // 險種代碼

  //  @ManyToOne(fetch = FetchType.EAGER)
  //  private PolicyEntity policy;

}
