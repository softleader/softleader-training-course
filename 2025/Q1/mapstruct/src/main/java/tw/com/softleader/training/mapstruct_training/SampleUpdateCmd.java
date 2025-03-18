package tw.com.softleader.training.mapstruct_training;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class SampleUpdateCmd {

  Long id;

  String name;

  BigDecimal age;
}
