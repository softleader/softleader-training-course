package tw.com.softleader.regex_2021;

import lombok.Builder;
import lombok.Data;

import java.time.Month;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Sample1 {

    public static void main(String[] args) {

        var datas = List.of(
            "16n0127A",
            "16n0127B",
            "1720202B",
            "1720202C",
            "1720202D",
            "A16n0126B",
            "M16n0126C",
            "M16n0126AA",
            "M16n0126AB"
        );

        var cardDatas = datas.stream()
            .map(Sample1::parse)
            .filter(Optional::isPresent)
            .collect(Collectors.toList());
        System.out.println(cardDatas);
    }

    static final Pattern CARD_PATTERN = Pattern.compile("(^[ABCDEM]{0,1})(\\d{2})([1-9ond])(\\d{4})($|[B-Z]|[A-Z]{2})");
    static final HashMap<String, Month> MONTH_DOC = new HashMap<>();
    static {
        MONTH_DOC.put("1", Month.JANUARY);
        MONTH_DOC.put("2", Month.FEBRUARY);
        MONTH_DOC.put("3", Month.MARCH);
        MONTH_DOC.put("4", Month.APRIL);
        MONTH_DOC.put("5", Month.MAY);
        MONTH_DOC.put("6", Month.JUNE);
        MONTH_DOC.put("7", Month.JULY);
        MONTH_DOC.put("8", Month.AUGUST);
        MONTH_DOC.put("9", Month.SEPTEMBER);
        MONTH_DOC.put("o", Month.OCTOBER);
        MONTH_DOC.put("n", Month.NOVEMBER);
        MONTH_DOC.put("d", Month.DECEMBER);
    }
    static Optional<CardData> parse(String src) {
        var matcher = CARD_PATTERN.matcher(src);
        if (matcher.matches()) {
            var yearTx = matcher.group(2);
            var monthTx = matcher.group(3);
            var yearNo = Integer.parseInt(yearTx);
            int year;
            if (yearNo >= 70) {
                year = 1900 + yearNo;
            } else {
                year = 2000 + yearNo;
            }
            var month = MONTH_DOC.get(monthTx);
            return Optional.of(CardData.builder()
                .cardType(matcher.group(1))
                .yearMonth(YearMonth.of(year, month))
                .seq(Integer.parseInt(matcher.group(4)))
                .batchNo(matcher.group(5))
                .build());
        } else {
            return Optional.empty();
        }
    }

    @Data
    @Builder
    static class CardData {
        String cardType;
        YearMonth yearMonth;
        int seq;
        String batchNo;
    }

}
