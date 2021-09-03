package tw.com.softleader.training;

import java.util.Optional;

public class OptionalSample {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            runOpt3();
        }

        Optional<String> s1 = get().map(s -> s.content).orElse(null);
        String s2 = get().flatMap(s -> s.content).orElse(null);

        System.out.println("================");

        for (int i = 0; i < 10; i++) {
            runOpt4();
        }
    }

    private static void run() {
        String s = sometimesNullValue();
        if (s != null) {
            boolean good = s.equals("good");
            System.out.println("good");
        } else {
            System.out.println(notGood());
        }
    }

    private static void runOpt() {
        String result = Optional.ofNullable(sometimesNullValue())
            .orElse(notGood());
        System.out.println(result);
    }

    private static void runOpt2() {
        String result = maybeNullValue().orElse(notGood());
        System.out.println(result);
    }

    private static void runOpt3() {
        String result = maybeNullValue().orElseGet(() -> notGood());
        System.out.println(result);
    }

    private static void runOpt4() {
        maybeNullValue().ifPresentOrElse(
            s -> System.out.println(s),
            () -> System.out.println(notGood())
        );
    }

    private static void runOpt5() {
        String s1 = maybeNullValue()
            .orElseGet(() -> maybeNullValue2().orElseGet(() -> notGood()));
        System.out.println("s1=" + s1);

        String s2 = maybeNullValue()
            .or(() -> maybeNullValue2())
            .orElse(notGood());
        System.out.println("s2=" + s1);
    }

    private static String notGood() {
        System.out.println("do return not good");
        return "not good";
    }

    public static String sometimesNullValue() {
        return Math.random() > 0.5 ? null : "good";
    }

    public static Optional<String> maybeNullValue() {
        return Math.random() > 0.5 ? Optional.empty() : Optional.of("good");
    }

    public static Optional<String> maybeNullValue2() {
        return Math.random() > 0.5 ? Optional.empty() : Optional.of("good");
    }

    public static Optional<SuperOptional> get() {
        return Optional.of(new SuperOptional());
    }

    static class SuperOptional {
        Optional<String> content = Optional.empty();
    }

}
