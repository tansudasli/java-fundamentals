import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Loops {


    public static void o2(List<Long> source) {
        long startTime = System.nanoTime();

        long total = 0;

        //double all
        //double only even numbers
        for (long i : source)
            total += (i % 2 == 0) ? (i * 2) : 0;

        long elapsedTime = System.nanoTime() - startTime;

        System.out.println(elapsedTime + " nano sec via loops " + total);
    }

    public static void s2(LongStream source){
        long startTime = System.nanoTime();

        long total = source
                .parallel()
                .filter(e -> e % 2 == 0)
                .map(x -> (x * 2))
                .sum();

        long elapsedTime = System.nanoTime() - startTime;

        System.out.println(elapsedTime + " nano sec via streams " + total);
    }

    public static void main(String[] args) {

        List<Long> data = LongStream
                            .rangeClosed(0, 100000000)
                            .boxed()
                            .collect(Collectors.toList());

        LongStream data2 = data
                            .stream()
                            .mapToLong(e -> e);

        /*
         * as computing steps increases or max number, streams perform better
         * streams more concise
         */
        o2(data);
        s2(data2);

    }
}
