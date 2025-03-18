package tw.com.softleader.training.mapstruct_training;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class SampleSaveCmd {

  String name;

  BigDecimal age;
}
