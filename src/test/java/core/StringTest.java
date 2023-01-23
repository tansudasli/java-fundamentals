package core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @ParameterizedTest
    @CsvSource({"The, This"})
    void replaceString(String o, String n) {
        Assertions.assertEquals(n, Variables.greeting().replace(o, n).substring(0, 4));
    }

    /* String concatenation operator is +.
     * But much performer way is StringBuilder. Single thread ops.
     *
     * StringBuilder builder = new StringBuilder();
     * builder.append(str);
     * completedString = builder.toString(); , when you done building..
     *
     * */
    @ParameterizedTest
    @ValueSource(strings = {"The"})
    void joinString(String c) {

        Assertions.assertEquals(Variables.greeting().substring(0, 3), String.join("", c));
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(2)
    @Measurement(iterations = 10, time = 1)
    @Warmup(iterations = 1, time = 1)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public static String concatWithPlusStrings() {
        String s = "The";
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
    @Measurement(iterations = 10, time = 1)
    @Warmup(iterations = 1, time = 1)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public static String concatWithBuilderStrings() {

        StringBuilder s = new StringBuilder();
        s.append("The");
        s.append("fox ");
        s.append("was ");
        s.append("already ");
        s.append("in ");
        s.append("your ");
        s.append("chicken ");
        s.append("house.");

        return s.toString();
    }

//    + operator is x1.2 slower than builder
//    Benchmark                            Mode  Cnt  Score   Error  Units
//    StringTest.concatWithBuilderStrings  avgt   20  0.086 ± 0.012  us/op
//    StringTest.concatWithPlusStrings     avgt   20  0.107 ± 0.015  us/op

}
