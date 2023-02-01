package core;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

public class StringConcatenationBenchmark {


    /* concatenation. + is default operator

       Small manipulations:
         - Use + over String.concat(). String.join() etc.. for clarity..
       in a Loop or more ops.,
         - use StringBuilder or StringBuffer (threadsafe)

       If multiple operations at stake, use stream(), then do things via function-chaining...

       -> concatWithJoin vs concatWithJoinStream ... Just for concatenation, getting a stream may cost a bit!
       -> concatWithBuffer vs concatWithBuilder ... Very close throughput and timing.
     */

    //    + operator is x1.2 slower than builder
//    So. 4 ways to concatenate
//
//Benchmark                                             Mode  Cnt     Score     Error   Units
//StringConcatenationBenchmark.concatWithBuffer        thrpt    6  1624.685 ± 168.801  ops/us
//StringConcatenationBenchmark.concatWithBuilder       thrpt    6  1397.534 ± 157.024  ops/us
//StringConcatenationBenchmark.concatWithConcat        thrpt    6    20.267 ±   3.779  ops/us
//StringConcatenationBenchmark.concatWithJoin          thrpt    6     6.530 ±   0.539  ops/us
//StringConcatenationBenchmark.concatWithPlusOperator  thrpt    6  1311.588 ±  72.581  ops/us
//StringConcatenationBenchmark.concatWithBuffer         avgt    6     0.001 ±   0.001   us/op
//StringConcatenationBenchmark.concatWithBuilder        avgt    6     0.001 ±   0.001   us/op
//StringConcatenationBenchmark.concatWithConcat         avgt    6     0.046 ±   0.003   us/op
//StringConcatenationBenchmark.concatWithJoin           avgt    6     0.160 ±   0.023   us/op
//StringConcatenationBenchmark.concatWithPlusOperator   avgt    6     0.001 ±   0.001   us/op

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1, time = 1)
    @Measurement(iterations = 1, time = 1)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public static void concatWithPlusOperator(Blackhole bh) {
        bh.consume(StringTest.concatWithPlusOperator.get());
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1, time = 1)
    @Measurement(iterations = 1, time = 1)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public static void concatWithJoin(Blackhole bh) {
        bh.consume(StringTest.concatWithJoin.get());
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1, time = 1)
    @Measurement(iterations = 1, time = 1)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public static void concatWithConcat(Blackhole bh) {
        bh.consume(StringTest.concatWithConcat.get());
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1, time = 1)
    @Measurement(iterations = 1, time = 1)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public static void concatWithBuilder(Blackhole bh) {
        bh.consume(StringTest.concatWithBuilder);
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1, time = 1)
    @Measurement(iterations = 1, time = 1)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public static void concatWithBuffer(Blackhole bh) {
        bh.consume(StringTest.concatWithBuffer);
    }


    // java -cp build/libs/java-fundamentals-1.0-SNAPSHOT-jmh.jar core.StringConcatenationBenchmark
    public static void main(String[] args) throws Exception {

        var opt = new OptionsBuilder()
                .include(core.StringConcatenationBenchmark.class.getName())
                .jvmArgs("-Xms1g", "-Xmx1g", "-XX:+UseG1GC")
                .warmupIterations(1)
                .measurementIterations(2)
                .forks(3)
                .build() ;

        new Runner(opt).run() ;
    }

}
