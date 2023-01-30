package core;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class ArraySortingBenchmark {

    //todo: use State object to simulate less:true/false and combine into 1 function
    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1)
    @Measurement(iterations = 2)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void sortArrayIntsLess(Blackhole bh) {
        int[] tmp = ArrayIntTest.ints.apply(false);

        Arrays.sort(tmp);  //in-place sorting
        bh.consume(tmp[0]);

//        Arrays.sort(ArrayTest.ints.apply(false), Comparator.comparingInt( i -> ));
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1)
    @Measurement(iterations = 2)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void sortArrayInts(Blackhole bh) {
        int[] tmp = ArrayIntTest.ints.apply(true);

        Arrays.sort(tmp);  //in-place sorting
        bh.consume(tmp[0]);

//        Arrays.sort(ArrayTest.ints.apply(false), Comparator.comparingInt( i -> ));
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1)
    @Measurement(iterations = 2)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void sortStreamIntsLess(Blackhole bh) {
        bh.consume(Arrays.stream(ArrayIntTest.ints.apply(true))
                .sorted()
                .findFirst()
                .orElseThrow());


//        Arrays.sort(Variables.ints(initialized), Comparator.comparingInt( i -> ));
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1)
    @Measurement(iterations = 2)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void sortStreamInts(Blackhole bh) {
        bh.consume(Arrays.stream(ArrayIntTest.ints.apply(false))
                .sorted()
                .findFirst()
                .orElseThrow());


//        Arrays.sort(Variables.ints(initialized), Comparator.comparingInt( i -> ));
    }



    public static void main(String[] args) throws Exception {
//        org.openjdk.jmh.Main.main(args);
        var opt = new OptionsBuilder()
                .include(core.ArraySortingBenchmark.class.getName())
                .forks(1)
                .build() ;

        new Runner(opt).run() ;
    }
}
