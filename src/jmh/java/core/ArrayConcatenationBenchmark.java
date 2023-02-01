package core;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


/**
 * In case of no loops,
 * <p> use + operator for simplicity
 * or StringBuilder/StringBuffer. Both performs well.
 * <p>In case of loops,
 * <li>String.join vs Collector.join() in stream, very similar perf. results.
 * String.join wins the clarity.
 * Don't make it complex just for join!
 * <li>StringBuilder or StringBuffer in stream, outperforms in every case !
 * <p><p>
 *     streamWithStringBuilder performs better streamWithCollectorJoin, even-though both in streams.
 *     streamWithStringBuilder performs better stringJoin, even-though it creates a stream.
 */
@State(Scope.Benchmark)
public class ArrayConcatenationBenchmark {

//    @Param({"100", "1002", "2000"})
//    private int capacity;

    @Param({"100", "1000", "100000", "1000000"})
    private int size;

    private static String[] dataGenerator;

    @Setup(Level.Trial)
    public void init() {

        //use textAsWords as base array. no need in fact!
        var baseData = ArrayStringTest.textAsWords.get();

        //generate a 1000, 100K, 1M array
        dataGenerator = Arrays.copyOf(baseData, size);

        if (size > 1000)
            Arrays.fill(dataGenerator, 1000, size-1, "bla");

    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void stringWithJoin(Blackhole bh) {
        bh.consume(String.join(" ", dataGenerator));
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void streamWithCollectorJoin(Blackhole bh) {

        bh.consume(Arrays.stream(dataGenerator)
                .collect(Collectors.joining(" ")));
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void streamWithStringBuilder(Blackhole bh) {

        StringBuilder stringBuilder = new StringBuilder(1000);

        Arrays.stream(dataGenerator)
              .forEach(stringBuilder::append);

        bh.consume(stringBuilder.toString());
    }

    //Benchmark                                             Mode  Cnt   Score    Error   Units
    //ArrayConcatenationBenchmark.streamWithCollectorJoin  thrpt    6  23.909 ± 2.569  ops/ms
    //ArrayConcatenationBenchmark.streamWithStringBuilder  thrpt    6  50.112 ± 6.805  ops/ms
    //ArrayConcatenationBenchmark.stringWithJoin           thrpt    6  36.621 ± 1.248  ops/ms
    //ArrayConcatenationBenchmark.streamWithCollectorJoin   avgt    6   0.039 ± 0.001   ms/op
    //ArrayConcatenationBenchmark.streamWithStringBuilder   avgt    6   0.029 ± 0.007   ms/op
    //ArrayConcatenationBenchmark.stringWithJoin            avgt    6   0.037 ± 0.018   ms/op


    //./gradlew clean build jmhJar
    // java -cp build/libs/java-fundamentals-1.0-SNAPSHOT-jmh.jar core.ArrayConcatenationBenchmark
    public static void main(String[] args) throws Exception {

        var opt = new OptionsBuilder()
                .include(core.ArrayConcatenationBenchmark.class.getName())
                .jvmArgs("-Xms2g", "-Xmx2g", "-XX:+UseG1GC")
                .warmupIterations(1)
                .measurementIterations(2)
                .forks(3)
                .build() ;

        new Runner(opt).run() ;
    }
}
