package tw.com.softleader.training;

import java.util.ArrayList;
import java.util.List;

public class LambdaSample {

    public static void main(String[] args) {

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        int r1 = summarize(numbers, new DoAdd());
        System.out.println(r1);

        int r2 = summarize(numbers, new DoMinus());
        System.out.println(r2);

        int r3 = summarize(numbers, (x, y) -> x * y);
        System.out.println(r3);

    }

    public static int summarize(List<Integer> ints, DoCalc doCalc) {
        List<Integer> input = new ArrayList<>(ints);
        int result = input.remove(0);
        for (Integer i : input) {
            result = doCalc.calc(result, i);
        }
        return result;
    }

    static interface DoCalc {
        int calc(int x, int y);
    }

    static class DoAdd implements DoCalc {
        @Override public int calc(int x, int y) {
            return x + y;
        }
    }

    static class DoMinus implements DoCalc {
        @Override public int calc(int x, int y) {
            return x - y;
        }
    }

}
