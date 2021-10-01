package tw.com.softleader.training.mvcdemo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "範例物件")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HelloObj {

    @Schema(description = "名稱")
    String name;

    @Schema(description = "數字")
    Integer number;

}