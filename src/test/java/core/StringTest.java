package core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringTest {

    /* no built-in string type in Java.
     * use String (sequences of Unicode characters
     * each quoted string is instance of String class
     **/

    @ParameterizedTest
    @ValueSource(strings = {"the"})
    void testFirstThreeChars(String v) {

        Assertions.assertEquals(v, Variables.greeting().substring(0, 3).toLowerCase());
    }
    @ParameterizedTest
    @ValueSource(chars = {'t'})
    void testFirstChar(Character v) {

//        core.Variables.greeting()
//                .codePoints()
//                .map()
//        System.out.println(Character.toChars( core.Variables.greeting().chars().findFirst().orElse(0)));
//        core.Variables.greeting().chars().limit(3).mapToObj(Character::toChars).forEach(System.out::println);
        Assertions.assertEquals(v, Character.toLowerCase(Variables.greeting().charAt(0)));

    }

    /* equality

     * use String.equals() , not == operator for string equality
     * str == null
     * str.length() == 0
     *
     * str != null && str.length() != 0  , check null first :)
     *
     * */
    @ParameterizedTest
    @ValueSource(strings = {"The fox was already in your chicken house."})
    //greetings == The fox was already in your chicken house.
    void checkStrings(String v) {

        Assertions.assertEquals(v, Variables.greeting());

        System.out.println(v == Variables.greeting());          //pointer check!
        System.out.println(v.compareTo(Variables.greeting()));  //-1, 0== equal, 1 greater
    }

    /* comparison
     *
     * str.compareTo(String other) , -1 (less),0 (equal),+1 (greater)
     * String.equals() , not == operator for string content equality
     *
     * */
    @ParameterizedTest
    @ValueSource(strings = {"The fox was already in your chicken house."})
    void compareStrings(String v) {
        assertEquals(0, v.compareTo(Variables.greeting()));
    }

    /* search
     *
     * String.startsWith("prefix"), true,false
     * String.endsWith("suffix"), true, false
     *
     * String.indexOf(String str), indexFound or -1 if not occurs
     *
     * String.replace(CharSequence oldString, newString)
     *
     * String.substring(beginIndex, endIndex), returns a new string
     *
     * String join(CharSequence delimiter, CharSequence..elements), puts a delimiter between all elements
     * */

    @ParameterizedTest
    @ValueSource(strings = {"The"})
    void searchThe(String v) {
        Assertions.assertTrue(Variables.greeting().startsWith(v));
    }

    @ParameterizedTest
    @ValueSource(strings = "fox")
    void searchFox(String v) {
//        assertTrue(core.Variables.greeting().indexOf(v) > 0);

        Assertions.assertEquals(4, Variables.greeting().indexOf(v));  //index found or -1
    }

    //todo: search with regex !

    @ParameterizedTest
    @CsvSource({"The, This"})
    void replaceString(String o, String n) {
        Assertions.assertEquals(n, Variables.greeting().replace(o, n).substring(0, 4));
    }

    //todo: consider src/jmh/java/core for all benchmarks !!
    //todo: consider injecting (Benchmark bh) to methods and bh.consume(...)


    /* concatenation
     * String concatenation operator is +.
     *
     * But, StringBuilder is much better. And, It is Single thread ops. (not synchronized)
     * If you need tread safe, then use StringBuffer !
     * */
    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1, time = 1)
    @Measurement(iterations = 2, time = 1)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public static String concatWithPlusOperator() {
        String s = "The ";
        s += "fox ";
        s += "was ";
        s += "already ";
        s += "in ";
        s += "your ";
        s += "chicken ";
        s += "house.";

        return s;
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(2)
    @Warmup(iterations = 1, time = 1)
    @Measurement(iterations = 2, time = 1)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public static String joinString() {


        return java.lang.String.join(" ", "The",
                "fox",
                "was",
                "already",
                "in",
                "your",
                "chicken",
                "house.");
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(2)
    @Warmup(iterations = 1, time = 1)
    @Measurement(iterations = 2, time = 1)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public static String concatWithConcat() {
        String s = "The ";
        return s.concat("fox ")
                .concat("was ")
                .concat("already ")
                .concat("in ")
                .concat("your ")
                .concat("chicken ")
                .concat("house.");

    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1, time = 1)
    @Measurement(iterations = 2, time = 1)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public static String concatWithBuilder() {

        StringBuilder s = new StringBuilder();
        s.append("The ");
        s.append("fox ");
        s.append("was ");
        s.append("already ");
        s.append("in ");
        s.append("your ");
        s.append("chicken ");
        s.append("house.");

        return s.toString();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(2)
    @Warmup(iterations = 1, time = 1)
    @Measurement(iterations = 2, time = 1)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public static String concatWithBuffer() {
            StringBuffer b = new StringBuffer();

            b.append("The ");
            b.append("fox ");
            b.append("was ");
            b.append("already ");
            b.append("in ");
            b.append("your ");
            b.append("chicken ");
            b.append("house.");

            return b.toString();
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Fork(2)
    @Warmup(iterations = 1, time = 1)
    @Measurement(iterations = 2, time = 1)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public static String concatWithStream() {
        List<String> l = Arrays.asList("The ", "fox ", "was ", "already ", "in ", "your ", "chicken ", "house.");

        return l.stream().collect(Collectors.joining());
    }

//    + operator is x1.2 slower than builder
//    So. 4 ways to concatenate
//
//    Benchmark                           Mode  Cnt   Score    Error   Units
//    StringTest.concatWithBuilder       thrpt    4  14.122 ± 62.475  ops/us
//    StringTest.concatWithPlusOperator  thrpt    4  14.585 ± 17.941  ops/us
//    StringTest.concatWithStream        thrpt    4   3.697 ±  2.707  ops/us
//    StringTest.concatWithBuffer         avgt    4   0.074 ±  0.152   us/op
//    StringTest.concatWithBuilder        avgt    4   0.047 ±  0.021   us/op
//    StringTest.concatWithConcat         avgt    4   0.059 ±  0.017   us/op
//    StringTest.concatWithPlusOperator   avgt    4   0.057 ±  0.009   us/op
//    StringTest.concatWithStream         avgt    4   0.212 ±  0.077   us/op
//    StringTest.joinString               avgt    4   0.175 ±  0.014   us/op



    @ParameterizedTest
    @ValueSource(strings = "The fox was already in your chicken house.")
    void testConcatenations(String v) {
        assertEquals(v, StringTest.joinString());
        assertEquals(v, StringTest.concatWithConcat());
        assertEquals(v, StringTest.concatWithPlusOperator());
        assertEquals(v, StringTest.concatWithBuilder());
        assertEquals(v, StringTest.concatWithStream());
        assertEquals(v, StringTest.concatWithBuffer());
    }

    /* formatting
     *  If a class implements Formattable interface, formatTo() function executed otherwise
     *  toString() func. executed to turn an object into string!
     *
     *  String.format() can also be used, without printing it.
     *  String.format("Hello %s", name);
     *
     * */

    @ParameterizedTest
    @ValueSource(strings = {"You"})
    void testGeneratedStrings(String e) {
       assertEquals(e, Variables.stringsDataGenerator()[12]);
    }

}
