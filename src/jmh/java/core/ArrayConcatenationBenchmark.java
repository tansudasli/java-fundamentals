package core;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ArrayConcatenationBenchmark {
    //benchmark long string[]
    //todo: change short and long String[]

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void concatWithJoinStream(Blackhole bh) {
        bh.consume(Arrays.stream(ArrayStringTest.textAsWords.get())
                .collect(Collectors.joining(" ")));
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void concatWithJoin(Blackhole bh) {
        bh.consume(String.join(" ", ArrayStringTest.textAsWords.get()));
    }


    // java -cp build/libs/java-fundamentals-1.0-SNAPSHOT-jmh.jar core.ArrayConcatenationBenchmark
    public static void main(String[] args) throws Exception {

        var opt = new OptionsBuilder()
                .include(core.ArrayConcatenationBenchmark.class.getName())
                .jvmArgs("-Xms1g", "-Xmx1g", "-XX:+UseG1GC")
                .warmupForks(1)
                .warmupIterations(1)
                .measurementIterations(2)
                .forks(3)
                .build() ;

        new Runner(opt).run() ;
    }
}
