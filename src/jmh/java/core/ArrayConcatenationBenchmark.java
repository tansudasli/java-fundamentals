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
    @Measurement(iterations = 3)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void concatWithJoinStream(Blackhole bh) {
        bh.consume(Arrays.stream(ArrayStringTest.textAsWords.get())
                .collect(Collectors.joining(" ")));
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1)
    @Measurement(iterations = 3)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void concatWithJoin(Blackhole bh) {
        bh.consume(String.join(" ", ArrayStringTest.textAsWords.get()));
    }

    /** to run, via terminal
     * <li/>`./gradlew clean build jmhJar`, then
     * <li/>`java -cp build/libs/java-fundamentals-1.0-SNAPSHOT-jmh.jar core.ArrayConcatenationBenchmark`
     * <p><p>
     * Normally, `./gradlew jmh` runs,
     *  <li/> running via IDEs jmh plugin for method-based is OK,
     *  <li/> Somehow running via IDE's run main method, does not work anymore!!
     *
     * @param args of Main
     * @throws Exception of Main
     */
    public static void main(String[] args) throws Exception {
//        org.openjdk.jmh.Main.main(args);
        var opt = new OptionsBuilder()
                .include(core.ArrayConcatenationBenchmark.class.getName())
                .forks(1)
                .build() ;

        new Runner(opt).run() ;
    }
}
