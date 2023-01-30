package core;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

public class ArraySearchBenchmark {


    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void searchWithStream(Blackhole bh) {
       bh.consume(ArrayStringTest.frequencyOf.apply(false, "fox"));
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void searchWithRegex(Blackhole bh) {
        bh.consume(ArrayStringTest.frequencyOfRegex.apply(false, "[f|Fox]"));
    }

    // java -cp build/libs/java-fundamentals-1.0-SNAPSHOT-jmh.jar core.ArraySearchBenchmark
    public static void main(String[] args) throws Exception {

        var opt = new OptionsBuilder()
                .include(core.ArraySearchBenchmark.class.getName())
                .jvmArgs("-Xms1g", "-Xmx1g", "-XX:+UseG1GC")
                .warmupForks(1)
                .warmupIterations(1)
                .measurementIterations(2)
                .forks(3)
                .build() ;

        new Runner(opt).run() ;
    }
}
