package tw.com.softleader.example.training_20241129.data;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SampleMainVo {

  Long id;

  String name;

  List<SampleDetailVo> details;
}
