package core;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * used text field.
 * countByRegex vs countByRegex2 same code
 */
public class ArrayFrequencyBenchmark {

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1)
    @Measurement(iterations = 5)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public static void countByStream(Blackhole bh) {
        bh.consume(ArrayStringTest.frequencyOf.apply(false, "fox"));
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1)
    @Measurement(iterations = 5)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public static void countBySequential(Blackhole bh) {
        String[] words = ArrayStringTest.words.apply(false);

        int count = 0;
        for (String s : words)
            if (s.toLowerCase().contains("fox"))
                count++;

        bh.consume(count);
    }

//    @Benchmark
//    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
//    @Fork(2)
//    @Warmup(iterations = 1)
//    @Measurement(iterations = 5)
//    @OutputTimeUnit(TimeUnit.MILLISECONDS)
//    public static void countByRegex(Blackhole bh) {
//        String[] words = ArrayStringTest.words.apply(false);
//
//        Pattern pattern = Pattern.compile("[f|F]ox");
//        Matcher matcher = pattern.matcher(Arrays.toString(words));
//
//        int count = 0;
//        while (matcher.find())
//            count++;
//
//        bh.consume(count);
//    }

//1 beast de ide den kac dk da yapiyor. 12 dk vs ?
//    2 monster daki koda main i ekle. calisiyor mu ide den bak
//2 beast deki kodu guncelle.

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1)
    @Measurement(iterations = 5)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public static void countByRegex2(Blackhole bh) {
        bh.consume(ArrayStringTest.frequencyOfRegex.apply(false, "[f|F]ox"));
    }

//    Benchmark                                   Mode  Cnt   Score   Error   Units
//    ArrayFrequencyBenchmark.countByRegex       thrpt   10   5.520 ± 0.571  ops/ms
//    ArrayFrequencyBenchmark.countByRegex2      thrpt   10  23.145 ± 0.521  ops/ms
//    ArrayFrequencyBenchmark.countBySequential  thrpt   10   7.665 ± 1.463  ops/ms
//    ArrayFrequencyBenchmark.countByStream      thrpt   10   6.440 ± 0.191  ops/ms
//    ArrayFrequencyBenchmark.countByRegex        avgt   10   0.170 ± 0.021   ms/op
//    ArrayFrequencyBenchmark.countByRegex2       avgt   10   0.043 ± 0.003   ms/op
//    ArrayFrequencyBenchmark.countBySequential   avgt   10   0.121 ± 0.001   ms/op
//    ArrayFrequencyBenchmark.countByStream       avgt   10   0.154 ± 0.006   ms/op

    public static void main(String[] args) throws Exception {
//        org.openjdk.jmh.Main.main(args);
        var opt = new OptionsBuilder()
                .include(core.ArrayFrequencyBenchmark.class.getName())
                .forks(1)
                .build() ;

        new Runner(opt).run() ;
    }
}
