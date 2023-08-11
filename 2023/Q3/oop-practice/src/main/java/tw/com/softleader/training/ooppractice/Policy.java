package tw.com.softleader.training.ooppractice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Policy {

  LocalDate effDate; // 保單生效日
  BigDecimal premium; // 保費

}
