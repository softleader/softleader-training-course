package tw.com.softleader.stream_2021;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Random;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = "name")
public class Customer {
    String name;
    LocalDate birthday;
    String password;

    static final Random random = new Random();
    static final int birthday_year_max = 2000;
    static final int birthday_year_min = 1970;
    static Customer generate(String name) {
        var year = random.nextInt(birthday_year_max - birthday_year_min) + birthday_year_min;
        var month = random.nextInt(12 - 1) + 1;
        var days = YearMonth.of(year, month).lengthOfMonth();
        var dayOfMonth = random.nextInt(days - 1) + 1;
        return Customer.builder()
            .name(name)
            .birthday(LocalDate.of(year, month, dayOfMonth))
            .password(UUID.randomUUID().toString() + "_lower")
            .build();
    }


}
