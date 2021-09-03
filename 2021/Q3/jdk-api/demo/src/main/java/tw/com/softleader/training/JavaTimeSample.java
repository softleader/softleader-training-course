package tw.com.softleader.training;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.chrono.MinguoDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class JavaTimeSample {

    public static void main(String[] args) {
        LocalDateTime time = LocalDateTime.now();
        LocalDate date = LocalDate.now();
        LocalTime now = LocalTime.now();

        LocalDateTime start = LocalDate.of(2021, 9, 1).atTime(LocalTime.MIN);
        LocalDateTime end = LocalDate.of(2021, 9, 30).atTime(LocalTime.MAX);

        System.out.println(YearMonth.from(start));

        Date oldDate = new Date();
        Instant instant = oldDate.toInstant();
        System.out.println(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()));

        System.out.println(MinguoDate.from(time) + " " + time.toLocalTime());
        System.out.println(time.format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println(MinguoDate.from(time).format(DateTimeFormatter.ofPattern("[民國] yyy/MM/dd")));

        System.out.println(date.plusDays(1));

        // 加一工作日
        System.out.println(plusWorkDate(LocalDate.now(), 8));
        // SELECT TOP 1 date FROM holiday WHERE date > start AND workDate = true
        // ORDER BY date OFFSET workDates ROWS
    }

    public static LocalDate plusWorkDate(LocalDate start, int workDates) {
        ArrayList<Holiday> holidays = new ArrayList<>(HOLIDAYS);
        holidays.sort(Comparator.comparing(new Function<Holiday, LocalDate>() {
            @Override
            public LocalDate apply(Holiday holiday) {
                return holiday.getDate();
            }
        }));

        LocalDate date = holidays.stream()
            .filter(h -> h.date.isAfter(start))
            .filter(h -> h.workDate)
            .skip(workDates - 1)
            .findFirst()
            .map(Holiday::getDate)
            .orElseThrow(() -> new IllegalArgumentException(String.format("找不到此日期:%s+%d工作日的時間點", start, workDates)));

//        int left = workDates;
//        for (Holiday holiday : HOLIDAYS) {
//            if (!holiday.date.isAfter(start)) {
//                continue;
//            }
//            if (holiday.workDate) {
//                left--;
//            }
//            if (left == 0) {
//                return holiday.date;
//            }
//        }
//        throw new IllegalArgumentException(String.format("找不到此日期:%s+%d工作日的時間點", start, workDates));
        return date;
    }

    static List<Holiday> HOLIDAYS = List.of(
        new Holiday(LocalDate.of(2021, 9, 1), true),
        new Holiday(LocalDate.of(2021, 9, 2), true),
        new Holiday(LocalDate.of(2021, 9, 3), true),
        new Holiday(LocalDate.of(2021, 9, 4), false),
        new Holiday(LocalDate.of(2021, 9, 5), false),
        new Holiday(LocalDate.of(2021, 9, 6), true),
        new Holiday(LocalDate.of(2021, 9, 7), true),
        new Holiday(LocalDate.of(2021, 9, 8), true),
        new Holiday(LocalDate.of(2021, 9, 9), true),
        new Holiday(LocalDate.of(2021, 9, 10), true),
        new Holiday(LocalDate.of(2021, 9, 11), true),
        new Holiday(LocalDate.of(2021, 9, 12), false),
        new Holiday(LocalDate.of(2021, 9, 13), true),
        new Holiday(LocalDate.of(2021, 9, 14), true),
        new Holiday(LocalDate.of(2021, 9, 15), true),
        new Holiday(LocalDate.of(2021, 9, 16), true),
        new Holiday(LocalDate.of(2021, 9, 17), true),
        new Holiday(LocalDate.of(2021, 9, 18), false),
        new Holiday(LocalDate.of(2021, 9, 19), false),
        new Holiday(LocalDate.of(2021, 9, 20), false),
        new Holiday(LocalDate.of(2021, 9, 21), false),
        new Holiday(LocalDate.of(2021, 9, 22), true),
        new Holiday(LocalDate.of(2021, 9, 23), true),
        new Holiday(LocalDate.of(2021, 9, 24), true),
        new Holiday(LocalDate.of(2021, 9, 25), false),
        new Holiday(LocalDate.of(2021, 9, 26), false),
        new Holiday(LocalDate.of(2021, 9, 27), true),
        new Holiday(LocalDate.of(2021, 9, 28), true),
        new Holiday(LocalDate.of(2021, 9, 29), true),
        new Holiday(LocalDate.of(2021, 9, 30), true)
    );

    @Getter
    @AllArgsConstructor
    static class Holiday {
        LocalDate date;
        Boolean workDate;
    }

}
