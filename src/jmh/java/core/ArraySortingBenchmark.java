package core;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static core.IVariable.ints;

public class ArraySortingBenchmark {

    //todo: use State object to simulate less:true/false and combine into 1 function
    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void sortArrayIntsLess(Blackhole bh) {
        int[] tmp = ints.apply(false);

        Arrays.sort(tmp);  //in-place sorting
        bh.consume(tmp[0]);

//        Arrays.sort(ArrayTest.ints.apply(false), Comparator.comparingInt( i -> ));
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void sortArrayInts(Blackhole bh) {
        int[] tmp = ints.apply(true);

        Arrays.sort(tmp);  //in-place sorting
        bh.consume(tmp[0]);

//        Arrays.sort(ArrayTest.ints.apply(false), Comparator.comparingInt( i -> ));
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void sortStreamIntsLess(Blackhole bh) {
        bh.consume(Arrays.stream(ints.apply(true))
                .sorted()
                .findFirst()
                .orElseThrow());


//        Arrays.sort(Variables.ints(initialized), Comparator.comparingInt( i -> ));
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void sortStreamInts(Blackhole bh) {
        bh.consume(Arrays.stream(ints.apply(false))
                .sorted()
                .findFirst()
                .orElseThrow());


//        Arrays.sort(Variables.ints(initialized), Comparator.comparingInt( i -> ));
    }


    // java -cp build/libs/java-fundamentals-1.0-SNAPSHOT-jmh.jar core.ArraySortingBenchmark
    public static void main(String[] args) throws Exception {

        var opt = new OptionsBuilder()
                .include(core.ArraySortingBenchmark.class.getName())
                .jvmArgs("-Xms1g", "-Xmx1g", "-XX:+UseG1GC")
                .warmupIterations(1)
                .measurementIterations(2)
                .forks(3)
                .resultFormat(ResultFormatType.JSON)
                .result("build/".concat(ArraySortingBenchmark.class.getName()).concat(".json"))
                .build() ;

        new Runner(opt).run() ;
    }
}
