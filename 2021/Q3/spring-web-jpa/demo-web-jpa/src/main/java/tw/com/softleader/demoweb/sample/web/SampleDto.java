package tw.com.softleader.demoweb.sample.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Schema(description = "範例物件")
@Getter
@Setter
@ToString
public class SampleDto {

    @Schema(description = "ID")
    Long id;
    @Schema(description = "姓名")
    @Size(min = 2, max = 20)
    String name;
    @Schema(description = "日期")
    @NotNull
    LocalDate date;

    @Schema(description = "明細檔")
    List<SampleDetailDto> details;
}
