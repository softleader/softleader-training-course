package tw.com.softleader.springdatatraining.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MemberForm {
    String name;
    BigDecimal salary;
    LocalDateTime loginTime;
    MemberType type;
}
