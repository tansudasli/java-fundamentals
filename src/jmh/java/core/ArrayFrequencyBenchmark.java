package core;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static core.IVariable.words;
import static core.IVariable.frequencyOf;
import static core.IVariable.frequencyOfRegex;

/**
 * used text field.
 * <p/>
 * countByRegex vs countByRegex2  same code & perf.
 * all 4 methods somehow very similar considering throughput & avrg. times.
 */
public class ArrayFrequencyBenchmark {

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public static void countByStream(Blackhole bh) {
        //gets words by regex from text
        //then, creates a stream
        bh.consume(frequencyOf.apply(false, "fox"));
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public static void countBySequential(Blackhole bh) {
        //gets words by regex from text
        //then, loops
        String[] w = words.apply(false);

        int count = 0;
        for (String s : w)
            if (s.toLowerCase().contains("fox"))
                count++;

        bh.consume(count);
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public static void countByRegex(Blackhole bh) {
        //gets words by regex from text
        //loops over regex
        String[] w = words.apply(false);

        Pattern pattern = Pattern.compile("[f|F]ox");
        Matcher matcher = pattern.matcher(Arrays.toString(w));

        int count = 0;
        while (matcher.find())
            count++;

        bh.consume(count);  // better:: matcher.results().count
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public static void countByRegex2(Blackhole bh) {
        //same as countByRegex, but defined as functional
        bh.consume(frequencyOfRegex.apply(false, "[f|F]ox"));
    }

//    Benchmark                                   Mode  Cnt  Score   Error   Units
//    ArrayFrequencyBenchmark.countByRegex       thrpt    6  5.851 ± 1.536  ops/ms
//    ArrayFrequencyBenchmark.countByRegex2      thrpt    6  5.963 ± 1.029  ops/ms
//    ArrayFrequencyBenchmark.countBySequential  thrpt    6  8.495 ± 3.533  ops/ms
//    ArrayFrequencyBenchmark.countByStream      thrpt    6  6.544 ± 0.974  ops/ms
//    ArrayFrequencyBenchmark.countByRegex        avgt    6  0.178 ± 0.038   ms/op
//    ArrayFrequencyBenchmark.countByRegex2       avgt    6  0.188 ± 0.114   ms/op
//    ArrayFrequencyBenchmark.countBySequential   avgt    6  0.186 ± 0.066   ms/op
//    ArrayFrequencyBenchmark.countByStream       avgt    6  0.193 ± 0.095   ms/op

    // java -cp build/libs/java-fundamentals-1.0-SNAPSHOT-jmh.jar core.ArrayFrequencyBenchmark
    public static void main(String[] args) throws Exception {

        var opt = new OptionsBuilder()
                .include(core.ArrayFrequencyBenchmark.class.getName())
                .jvmArgs("-Xms1g", "-Xmx1g", "-XX:+UseG1GC")
                .warmupIterations(1)
                .measurementIterations(2)
                .forks(3)
                .resultFormat(ResultFormatType.JSON)
                .result("build/".concat(ArrayFrequencyBenchmark.class.getName()).concat(".json"))
                .build() ;

        new Runner(opt).run() ;
    }
}
