package tw.com.softleader.training.mapstruct_training;

import java.util.List;
import lombok.Data;

@Data
public class SamplePackUpdateCmd {

  Long id;

  List<SampleUpdateCmd> samples;
}
