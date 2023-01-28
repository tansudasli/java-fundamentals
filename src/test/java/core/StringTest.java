package core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringTest {

    /* no built-in string type in Java.
     * use String (sequences of Unicode characters
     * each quoted string is instance of String class
     **/

    public static Supplier<String> shortText = () ->
            """
            The fox was already in your chicken house.
            """;

    @ParameterizedTest
    @ValueSource(strings = {"the"})
    void testFirstThreeChars(String v) {

        Assertions.assertEquals(v, shortText.get().substring(0, 3).toLowerCase());
    }
    @ParameterizedTest
    @ValueSource(chars = {'t'})
    void testFirstChar(Character v) {

//        core.shortText.get()
//                .codePoints()
//                .map()
//        System.out.println(Character.toChars( core.shortText.get().chars().findFirst().orElse(0)));
//        core.shortText.get().chars().limit(3).mapToObj(Character::toChars).forEach(System.out::println);
        Assertions.assertEquals(v, Character.toLowerCase(shortText.get().charAt(0)));

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

        Assertions.assertEquals(v, shortText.get());

        System.out.println(v == shortText.get());          //pointer check!
        System.out.println(v.compareTo(shortText.get()));  //-1, 0== equal, 1 greater
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
        assertEquals(0, v.compareTo(shortText.get()));
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
        Assertions.assertTrue(shortText.get().startsWith(v));
    }

    @ParameterizedTest
    @ValueSource(strings = "fox")
    void searchFox(String v) {
//        assertTrue(core.shortText.get().indexOf(v) > 0);

        Assertions.assertEquals(4, shortText.get().indexOf(v));  //index found or -1
    }

    //todo: search with regex !

    @ParameterizedTest
    @CsvSource({"The, This"})
    void replaceString(String o, String n) {
        Assertions.assertEquals(n, shortText.get().replace(o, n).substring(0, 4));
    }

    //todo: consider src/jmh/java/core for all benchmarks !!



    public static Supplier<String> concatWithPlusOperator = () -> "The " + "fox " + "was " + "already " + "in " + "your " + "chicken " + "house.";
    public static Supplier<String> concatWithJoin = () -> String.join(" ", "The", "fox","was","already", "in", "your", "chicken", "house.");
    public static Supplier<String> concatWithJoinStream = () -> Arrays.asList("The ", "fox ", "was ", "already ", "in ", "your ", "chicken ", "house.")
            .stream()
            .collect(Collectors.joining());
    public static Supplier<String> concatWithConcat = () -> "The ".concat("fox ").concat("was ").concat("already ").concat("in ").concat("your ").concat("chicken ").concat("house.");
    public static Supplier<String> concatWithBuilder = () -> new StringBuilder().append("The ").append("fox ").append("was ").append("already ").append("in ").append("your ").append("chicken ").append("house.").toString();
    public static Supplier<String> concatWithBuffer = () -> new StringBuffer().append("The ").append("fox ").append("was ").append("already ").append("in ").append("your ").append("chicken ").append("house.").toString();

    @ParameterizedTest
    @ValueSource(strings = "The fox was already in your chicken house.")
    void testConcatenations(String v) {

        assertEquals(v, StringTest.concatWithPlusOperator.get());
        assertEquals(v, StringTest.concatWithConcat.get());
        assertEquals(v, StringTest.concatWithJoin.get());
        assertEquals(v, StringTest.concatWithJoinStream.get());
        assertEquals(v, StringTest.concatWithBuilder.get());
        assertEquals(v, StringTest.concatWithBuffer.get());
    }

    /* formatting
     *  If a class implements Formattable interface, formatTo() function executed otherwise
     *  toString() func. executed to turn an object into string!
     *
     *  String.format() can also be used, without printing it.
     *  String.format("Hello %s", name);
     *
     * */



}
