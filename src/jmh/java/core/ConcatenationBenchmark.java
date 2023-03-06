package core;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static core.IVariable.textAsWords;


/**
 * to make a decision in this scenario, average time is more meaningful than throughput !!
 *     <li>String.concat performs worse in every case!. Don't use it.</li>
 *     <li>+ operator is OK till 1000 strings. But prefer String.join !</li>
 *     <li>String.join is OK till 1m strings.</li>But prefer Collector.join & Streams for >1m strings!
 *     <li>It is OK Collector.join or StringBuilder/Buffer. Prefer the latter but not must. If >1m strings, definitely use the latter</li>
 * <p><p>
 * StringBuilder or StringBuffer in stream, outperforms in every case !
 */
@State(Scope.Benchmark)
public class ConcatenationBenchmark {

    @Param({"1000"})
    private int capacity;

    @Param({"100", "1000", "100000", "1000000"})
    private int dataSize;

    private static String[] dataGenerator;

    @Setup(Level.Trial)
    public void init() {

        //use textAsWords as base array. no need in fact!
        var baseData = textAsWords.get();

        //generate few data: 100, 1000
        //more data: 100K, 1M array
        dataGenerator = Arrays.copyOf(baseData, dataSize);

        if (dataSize > 1000)
            Arrays.fill(dataGenerator, 1000, dataSize -1, "bla");

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
    public void stringWithConcat(Blackhole bh) {

        String sentence = "";
        for(String word: dataGenerator)
            sentence = sentence.concat(" ").concat(word);

        System.out.println(sentence);
        bh.consume(sentence);
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void stringWithPlusOperator(Blackhole bh) {

        String sentence = "";
        for(String word: dataGenerator)
            sentence = sentence + " " + word;

        bh.consume(sentence);
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

        StringBuilder stringBuilder = new StringBuilder(capacity);

        Arrays.stream(dataGenerator)
              .forEach(stringBuilder::append);

        bh.consume(stringBuilder.toString());
    }

    //Benchmark                                            (capacity)   (size)   Mode  Cnt       Score      Error   Units
    //ConcatenationBenchmark.streamWithCollectorJoin             1000      100  thrpt    6     272.290 ±   57.017  ops/ms
    //ConcatenationBenchmark.streamWithCollectorJoin             1000     1000  thrpt    6      23.704 ±    5.264  ops/ms
    //ConcatenationBenchmark.streamWithCollectorJoin             1000   100000  thrpt    6       0.159 ±    0.210  ops/ms
    //ConcatenationBenchmark.streamWithCollectorJoin             1000  1000000  thrpt    6       0.004 ±    0.006  ops/ms
    //ConcatenationBenchmark.streamWithStringBuilder             1000      100  thrpt    6     734.694 ±  188.670  ops/ms
    //ConcatenationBenchmark.streamWithStringBuilder             1000     1000  thrpt    6      65.799 ±    3.855  ops/ms
    //ConcatenationBenchmark.streamWithStringBuilder             1000   100000  thrpt    6       0.734 ±    0.087  ops/ms
    //ConcatenationBenchmark.streamWithStringBuilder             1000  1000000  thrpt    6       0.065 ±    0.057  ops/ms
    //ConcatenationBenchmark.stringWithConcat                    1000      100  thrpt    6       3.095 ±    0.886  ops/ms
    //ConcatenationBenchmark.stringWithConcat                    1000     1000  thrpt    6       0.375 ±    0.191  ops/ms
    //ConcatenationBenchmark.stringWithJoin                      1000      100  thrpt    6     745.505 ±  392.131  ops/ms
    //ConcatenationBenchmark.stringWithJoin                      1000     1000  thrpt    6      39.015 ±    3.008  ops/ms
    //ConcatenationBenchmark.stringWithJoin                      1000   100000  thrpt    6       0.488 ±    0.030  ops/ms
    //ConcatenationBenchmark.stringWithJoin                      1000  1000000  thrpt    6       0.002 ±    0.006  ops/ms
    //ConcatenationBenchmark.stringWithPlusOperator              1000      100  thrpt    6     286.634 ±   31.477  ops/ms
    //ConcatenationBenchmark.stringWithPlusOperator              1000     1000  thrpt    6       3.709 ±    0.069  ops/ms
    //ConcatenationBenchmark.stringWithPlusOperator              1000   100000  thrpt    6      ≈ 10⁻³             ops/ms
    //ConcatenationBenchmark.stringWithPlusOperator              1000  1000000  thrpt    6      ≈ 10⁻⁶             ops/ms
    //ConcatenationBenchmark.streamWithCollectorJoin             1000      100   avgt    6       0.003 ±    0.001   ms/op
    //ConcatenationBenchmark.streamWithCollectorJoin             1000     1000   avgt    6       0.036 ±    0.003   ms/op
    //ConcatenationBenchmark.streamWithCollectorJoin             1000   100000   avgt    6       5.116 ±    5.175   ms/op
    //ConcatenationBenchmark.streamWithCollectorJoin             1000  1000000   avgt    6     191.264 ±  154.875   ms/op
    //ConcatenationBenchmark.streamWithStringBuilder             1000      100   avgt    6       0.001 ±    0.001   ms/op
    //ConcatenationBenchmark.streamWithStringBuilder             1000     1000   avgt    6       0.016 ±    0.001   ms/op
    //ConcatenationBenchmark.streamWithStringBuilder             1000   100000   avgt    6       1.349 ±    0.032   ms/op
    //ConcatenationBenchmark.streamWithStringBuilder             1000  1000000   avgt    6      13.894 ±    0.402   ms/op
    //ConcatenationBenchmark.stringWithConcat                    1000      100   avgt    6       0.242 ±    0.188   ms/op
    //ConcatenationBenchmark.stringWithConcat                    1000     1000   avgt    6       1.968 ±    0.708   ms/op
    //ConcatenationBenchmark.stringWithJoin                      1000      100   avgt    6       0.002 ±    0.001   ms/op
    //ConcatenationBenchmark.stringWithJoin                      1000     1000   avgt    6       0.025 ±    0.002   ms/op
    //ConcatenationBenchmark.stringWithJoin                      1000   100000   avgt    6       2.047 ±    0.125   ms/op
    //ConcatenationBenchmark.stringWithJoin                      1000  1000000   avgt    6     968.580 ± 2286.333   ms/op
    //ConcatenationBenchmark.stringWithPlusOperator              1000      100   avgt    6       0.003 ±    0.001   ms/op
    //ConcatenationBenchmark.stringWithPlusOperator              1000     1000   avgt    6       0.268 ±    0.005   ms/op
    //ConcatenationBenchmark.stringWithPlusOperator              1000   100000   avgt    6    2234.226 ±   50.788   ms/op
    //ConcatenationBenchmark.stringWithPlusOperator              1000  1000000   avgt    6  320125.936 ± 8842.822   ms/op


    //./gradlew clean build jmhJar
    // java -cp build/libs/java-fundamentals-1.0-SNAPSHOT-jmh.jar core.ConcatenationBenchmark
    //   -rf json -rff result.json
    public static void main(String[] args) throws Exception {

        var opt = new OptionsBuilder()
                .include(ConcatenationBenchmark.class.getName())
                .jvmArgs("-Xms2g", "-Xmx2g", "-XX:+UseG1GC")
                .threads(2)
                .warmupIterations(1)
                .measurementIterations(2)
                .forks(3)
                .resultFormat(ResultFormatType.JSON)
                .result("build/".concat(ConcatenationBenchmark.class.getName()).concat(".json"))
                .build() ;

        new Runner(opt).run() ;
    }
}
