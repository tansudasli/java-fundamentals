package core;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

import static core.IVariable.frequencyOf;
import static core.IVariable.frequencyOfRegex;

public class ArraySearchBenchmark {


    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void searchWithStream(Blackhole bh) {
       bh.consume(frequencyOf.apply(false, "fox"));
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void searchWithRegex(Blackhole bh) {
        bh.consume(frequencyOfRegex.apply(false, "[f|Fox]"));
    }

    // java -cp build/libs/java-fundamentals-1.0-SNAPSHOT-jmh.jar core.ArraySearchBenchmark
    public static void main(String[] args) throws Exception {

        var opt = new OptionsBuilder()
                .include(core.ArraySearchBenchmark.class.getName())
                .jvmArgs("-Xms1g", "-Xmx1g", "-XX:+UseG1GC")
                .warmupIterations(1)
                .measurementIterations(2)
                .forks(3)
                .resultFormat(ResultFormatType.JSON)
                .result("build/".concat(ArraySearchBenchmark.class.getName()).concat(".json"))
                .build() ;

        new Runner(opt).run() ;
    }
}
