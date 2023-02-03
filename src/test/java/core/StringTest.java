package core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Creates shortText and text fields and
 * some String ops. (length, regex, search, etc..)
 * <p>
 * as always in C, char[] is String. Don't treat it like String[] !!
 * <p>
 * If you need some ops. on chars, you should get codePoint (int value),
 * then you can act on every char!
 */
public class StringTest implements IVariable{

    /* no built-in string type in Java.
     * use String (sequences of Unicode characters
     * each quoted string is instance of String class
     */


    @ParameterizedTest
    @DisplayName("Show String data")
    @CsvSource({"true", "false"})
    public void shoutAt(boolean less) {
        System.out.println(sentences.apply(less));
    }

    //char[] is String. Don't treat it like String[] !!
    @ParameterizedTest
    @DisplayName("Test 1st N{0,3} chars of String")
    @CsvSource({"true, the", "false, it"})
    void testFirstNCharsOfText(Boolean less, String e) {

        assertEquals(e, sentences.apply(less).substring(0, e.length()).trim().toLowerCase());
        assertEquals(e.charAt(0), Character.toLowerCase(sentences.apply(less).charAt(0)));
        assertEquals(e.charAt(0), Character.toLowerCase(sentences.apply(less).toCharArray()[0]));

//        Stream.of(text.get().toCharArray()).findFirst().orElseThrow()[0] is meaningless!!!!!
//        but, if you need ops. on every Character,
//        then First get stream of codePoints,
//        then get String representation. As below.
        System.out.println(text.get()
                .codePoints()
                .mapToObj(Character::toString)
                .limit(3)
                .collect(Collectors.joining("/")));

//        text.get()
//             .chars()   //IntStream -- same as codePoints()
//             .forEach(System.out::println);
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
    @CsvSource({"true, 35", "false, 4608"})
    void countOfChars(Boolean less, int count) {
//        assertEquals(count, sentences.apply(less).length()); //counts spaces!
        assertEquals(count, Pattern.compile("\\S")
                                   .matcher(sentences.apply(less))
                                   .results()
                                   .count());
    }

    @ParameterizedTest
    @CsvSource({"true, 8", "false, 1002"})
    void countOfWords(Boolean less, int count) {
        assertEquals(count, Pattern.compile("\\w+")
                                    .matcher(sentences.apply(less))
                                    .results()
                                    .count());
    }

    /* comparison
     *
     * str.compareTo(String other) , -1 (less),0 (equal),+1 (greater)
     * String.equals() , not == operator for string content equality
     *
     * */
    @ParameterizedTest
    @ValueSource(strings = {"The fox was already in your chicken house."})
    void compareToShortText(String e) {
        assertEquals(0, e.compareTo(shortText.get()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"The"})
    void isTextStartsWithThe(String e) {
        assertTrue(shortText.get().startsWith(e));
        assertFalse(text.get().startsWith(e));
    }

    @ParameterizedTest
    @ValueSource(strings = "fox")
    void isTextContainsFox(String e) {

        assertTrue(shortText.get().indexOf(e) > 0);  //index found or -1

        assertTrue(Pattern.compile("[f|F]ox").matcher(shortText.get()).find());
        assertTrue(Pattern.compile("[f|F]ox").matcher(text.get()).find());
    }

    @ParameterizedTest
    @CsvSource({"fox, ###"})
    void replaceFoxInText(String o, String n) {
        System.out.println(shortText.get().toLowerCase().replace(o, n));
        System.out.println(text.get().replaceAll("[f|F]ox", n).substring(0, 150));

        assertTrue(shortText.get().toLowerCase().replace(o, n).contains(n));
        assertTrue(text.get().replaceAll("[f|F]ox", n).contains(n));
    }


    //todo: Statistical String ops. frequency
    @ParameterizedTest
    @CsvSource({"true, fox, 1", "false, fox, 38"})
    void frequencyOfFoxWithRegex(boolean isShort, String regex, int frequency) {

        Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
                .matcher(sentences.apply(isShort))
                .results()
                .forEach(e -> System.out.println(e.group(0))); //.group holds matched value

       assertEquals(frequency, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
               .matcher(sentences.apply(isShort))
               .results()
               .count());
    }


    public static Supplier<String> concatWithPlusOperator = () -> "The " + "fox " + "was " + "already " + "in " + "your " + "chicken " + "house.";
    public static Supplier<String> concatWithJoin = () -> String.join(" ", "The", "fox","was","already", "in", "your", "chicken", "house.");

    /**Concatenation
     * <li>String.concat performs always worse!</li>
     * <li>+ operator is ok less-than 1000, but prefer String.join !</li>
     * <li>StringBuilder/Buffer is the best in every-case</li>
     * <p>
     * {@see core.ArrayConcatenationBenchmark.class detailed benchmarks}
     * @param e expected
     */
    @ParameterizedTest
    @ValueSource(strings = "The fox was already in your chicken house.")
    void testConcatenations(String e) {

        assertEquals(e, StringTest.concatWithPlusOperator.get());
        assertEquals(e, StringTest.concatWithJoin.get());
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
