package tw.com.softleader.training;

import java.util.List;
import java.util.stream.Collectors;

public class StreamSample {

    public static void main(String[] args) {
        List<String> sources = List.of("1", "2", "3", "4", "5", "10");

        String result = sources.stream()
            .filter(s -> s.length() < 2)
            .map(Integer::valueOf)
            .map(i -> i * 2)
            .map(Object::toString)
            .collect(Collectors.joining());
        System.out.println(result);

//        var sb = new StringBuilder();
//        for (String source : sources) {
//            Integer integer = Integer.valueOf(source);
//            int result = integer * 2;
//            sb.append(result);
//        }
//        System.out.println(sb.toString());
    }

}
