package core;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

public class ArraySearchBenchmark {


    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 2)
    @Measurement(iterations = 2)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void searchWithStream(Blackhole bh) {
       bh.consume(ArrayStringTest.frequencyOf.apply(false, "fox"));
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 2)
    @Measurement(iterations = 2)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void searchWithRegex(Blackhole bh) {
        bh.consume(ArrayStringTest.frequencyOfRegex.apply(false, "[f|Fox]"));
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }
}
