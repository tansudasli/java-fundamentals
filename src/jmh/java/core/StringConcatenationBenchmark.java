package core;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

public class StringConcatenationBenchmark {


    /* concatenation. + is default operator

       Small manipulations:
         - Use + over String.concat().
       in a Loop or more ops.,
         - use StringBuilder or StringBuffer (threadsafe)

       If multiple operations at stake, use stream(), then do things via function-chaining...

       -> concatWithJoin vs concatWithJoinStream ... Just for concatenation, getting a stream may cost a bit!
       -> concatWithBuffer vs concatWithBuilder ... Very close throughput and timing.
     */

    //    + operator is x1.2 slower than builder
//    So. 4 ways to concatenate
//
//    Benchmark                                             Mode  Cnt     Score     Error   Units
//    StringConcatenationBenchmark.concatWithBuffer        thrpt    4  1354.827 ± 848.650  ops/us
//    StringConcatenationBenchmark.concatWithBuilder       thrpt    4  1233.561 ± 208.028  ops/us
//    StringConcatenationBenchmark.concatWithConcat        thrpt    4    12.214 ±  28.881  ops/us
//    StringConcatenationBenchmark.concatWithJoin          thrpt    4     5.028 ±   4.502  ops/us
//    StringConcatenationBenchmark.concatWithJoinStream    thrpt    4     4.181 ±   7.177  ops/us
//    StringConcatenationBenchmark.concatWithPlusOperator  thrpt    4   228.719 ± 355.720  ops/us
//    StringConcatenationBenchmark.concatWithBuffer         avgt    4     0.001 ±   0.002   us/op
//    StringConcatenationBenchmark.concatWithBuilder        avgt    4     0.001 ±   0.004   us/op
//    StringConcatenationBenchmark.concatWithConcat         avgt    4     0.094 ±   0.073   us/op
//    StringConcatenationBenchmark.concatWithJoin           avgt    4     0.184 ±   0.058   us/op
//    StringConcatenationBenchmark.concatWithJoinStream     avgt    4     0.213 ±   0.095   us/op
//    StringConcatenationBenchmark.concatWithPlusOperator   avgt    4     0.004 ±   0.004   us/op

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1, time = 1)
    @Measurement(iterations = 2, time = 1)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public static void concatWithPlusOperator(Blackhole bh) {
        bh.consume(StringTest.concatWithPlusOperator.get());
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1, time = 1)
    @Measurement(iterations = 2, time = 1)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public static void concatWithJoin(Blackhole bh) {
        bh.consume(StringTest.concatWithJoin.get());
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1, time = 1)
    @Measurement(iterations = 2, time = 1)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public static void concatWithConcat(Blackhole bh) {
        bh.consume(StringTest.concatWithConcat.get());
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1, time = 1)
    @Measurement(iterations = 2, time = 1)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public static void concatWithBuilder(Blackhole bh) {
        bh.consume(StringTest.concatWithBuilder);
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1, time = 1)
    @Measurement(iterations = 2, time = 1)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public static void concatWithBuffer(Blackhole bh) {
        bh.consume(StringTest.concatWithBuffer);
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1, time = 1)
    @Measurement(iterations = 2, time = 1)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public static void concatWithJoinStream(Blackhole bh) {
        bh.consume(StringTest.concatWithJoinStream.get());
    }

    public static void main(String[] args) throws Exception {
//        org.openjdk.jmh.Main.main(args);
        var opt = new OptionsBuilder()
                .include(core.StringConcatenationBenchmark.class.getName())
                .forks(1)
                .build() ;

        new Runner(opt).run() ;
    }

}
