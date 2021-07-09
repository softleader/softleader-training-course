package tw.com.soflteader.training.demo.sample2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupOnlyDto {

    Long id;
    String name;

}
