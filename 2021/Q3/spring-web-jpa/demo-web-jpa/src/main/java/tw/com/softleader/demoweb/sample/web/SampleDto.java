package tw.com.softleader.demoweb.sample.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Schema(description = "範例物件")
@Getter
@Setter
@SuperBuilder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SampleDto {

    @Schema(description = "ID")
    Long id;
    @Schema(description = "姓名")
    @Size(min = 2, max = 20)
    String name;
    @Schema(description = "錢")
    @DecimalMax("1000000")
    BigDecimal amount;
    @Schema(description = "日期")
    @NotNull
    LocalDate date;

    @Schema(description = "明細檔")
    List<SampleDetailDto> details;
}
