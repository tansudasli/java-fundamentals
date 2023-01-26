package core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayTest {

    @ParameterizedTest
    @DisplayName("Test max of int[]")
    @CsvSource({"true, 5", "false, 10000"})
    public void testMaxOfInts(boolean initialized, int max) {

        assertEquals(max, Arrays.stream(Variables.ints(initialized))
                .max()
                .orElse(-1));
    }

    @ParameterizedTest
    @DisplayName("Test length of String[]")
    @CsvSource({"true, 8", "false, 122"})
    public void testLengthOfStrings(boolean initialized, int length) {

        assertEquals(length, Variables.strings(initialized).length);
    }

    @ParameterizedTest
    @DisplayName("Test length of String[]")
    @CsvSource({"true, already", "false, hounds"})
    public void testLengthOfFiveStrings(boolean initialized, String e) {

//        String[] -> stream         Stream<String>
//                      ..filter
//                      ..map
//                      ..findFirst  Optional<String>

        assertEquals(e, Arrays.stream(Variables.strings(initialized))
                .filter(s -> s.length() > 5)
                .map(String::trim)
                .findFirst()
                .orElse(null));
    }

    @Test
    @DisplayName("Show Int[] data")
    public void shoutIntDataGenerator() {

        System.out.println(Arrays.stream(Variables.intsDataGenerator())
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(" ")));

    }

    @Test
    @DisplayName("Show String[] data")
    public void shoutStringDataGenerator() {

        System.out.println(Arrays.stream(Variables.stringsDataGenerator())
                .collect(Collectors.joining(" ")));

//        Arrays.stream(Variables.stringsDataGenerator())
//                .forEach(s -> System.out.printf("%s ", s));

//        for (String x : Variables.stringsDataGenerator())
//            System.out.format("%s ", x) ;

    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1)
    @Measurement(iterations = 2)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void joiningStreamPerf(Blackhole bh) {
       bh.consume(Arrays.stream(Variables.stringsDataGenerator())
                .collect(Collectors.joining(" ")));
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1)
    @Measurement(iterations = 2)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void joiningPerf(Blackhole bh) {
        bh.consume(String.join(" ", Variables.stringsDataGenerator()));
    }


//    Benchmark                           Mode  Cnt   Score    Error   Units
//    ArrayTest.joiningPerf              thrpt    4   0.097 ±  0.051  ops/us
//    ArrayTest.joiningStreamPerf        thrpt    4   0.087 ±  0.020  ops/us
//    ArrayTest.joiningPerf               avgt    4  10.718 ±  8.012   us/op
//    ArrayTest.joiningStreamPerf         avgt    4  12.522 ±  6.666   us/op

}
