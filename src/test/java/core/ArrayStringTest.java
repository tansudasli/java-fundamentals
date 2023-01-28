package core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayStringTest {



    public static Supplier<String> text = () ->
    """
    It is an evil sign too see a fox lick a lamb.
    You can have no more of the fox than the skin.
    The fox knew too much, that's how he lost his tail.
    The hounds lost the scent of the fox.
    The quick brown fox jumps over a lazy dog.
    He's a cunning old fox.
    A fox may grow gray, but never good.
    He's a cunning/sly/wily old fox.
    He is cunning as a fox.
    The huntsmen rode fast, chasing after the fox.
    He sets the fox to keep the geese.
    He's a sly old fox.
    He was as cunning as a fox.
    It is a blind silly goose that comes to the fox’s sermon
    It took an instant for her eyes to adjust to the dim coop interior, and then she found herself staring into the desperate eyes of a red fox.
    Henceforth Bentham was a frequent guest at Boxwood, where he saw the best society and where he met Miss Caroline Fox (daughter of the second Lord Holland), to whom he afterwards made a proposal of marriage.
    He saw the whips in their red caps galloping along the edge of the ravine, he even saw the hounds, and was expecting a fox to show itself at any moment on the rye field opposite.
    He ordered Fox's liberation, and in November 1657 issued a general order directing that Quakers should be treated with leniency, and be discharged from confinement.
    When the midsummer vacation arrived, he was preparing to set out with his family to Fox How in Westmoreland, where he had purchased some property and built a house
    No notable rivers flow into Lake Michigan, the largest being the Big Manistee and Muskegon on the east shore, and on the west shore the Menominee and the Fox, both of which empty into Green Bay, the most important arm of the lake.
    Most of the leading breeds have clubs or societies, which have been founded by admirers with a view to furthering the interests of their favourites; and such combinations as the Bulldog Club (incorporated), the London Bulldog Society, the British Bulldog Club, the Fox Terrier Club, the Association of Bloodhound Breeders - under whose management the first man-hunting trials were held, - the Bloodhound Hunt Club, the Collie Club, the Dachshund Club, the Dandie Dinmont Terrier Club, the English Setter Club, the Gamekeepers' Association of the United Kingdom, the International Gun Dog League, the Irish Terrier Club, the Irish Wolfhound Club, the St Bernard Club, the National Terrier Club, the Pomeranian Club, the Spaniel Club, the Scottish Terrier Club and the Toy Bulldog Club have done good work in keeping the claims of the breeds they represent before the dogowning public and encouraging the breeding of dogs to type.
    In 1652 a number of people in Westmoreland and north Lancashire who had separated from the common national worship,' came under the influence of Fox, and it was this community (if it can be so called) at Preston Patrick which formed the nucleus of the Quaker church
    As cunning as fox is a simile used to denote the slyness of a person. It is usually taken in a negative manner.
    For example, the thief was as cunning as a fox.  Even as a child, Moriarty was as cunning as a fox. She thought she was as cunning as a fox but she still could not win.
    Such teaching necessarily brought Fox and his friends into conflict with all the religious bodies of England, and they were continually engaged in strife with the Presbyterians, Independents, Baptists, Episcopalians and the wilder sectaries, such as the Ranters and the Muggletonians.
    John Wilkinson and John Story of Westmorland, together with William Rogers of Bristol, raised a party against Fox concerning the management of the affairs of the society, regarding with suspicion any fixed arrangement for meetings for conducting church business, and in fact hardly finding a place for such meetings at all.
    In 1666 Fox established Monthly Meetings; in 1727 elders were first appointed; in 1752 overseers were added; and in 1737 the right of children of Quakers to be considered as members was fully recognized.
    But in 1806, Lord Grenville and Fox having come into power, a bill was passed in both Houses to put an end to the British slave trade for foreign supply, and to forbid the importation of slaves into the colonies won by the British arms in the course of the war.
    On the 10th of June of the same year Fox brought forward a resolution " that effectual measures should be taken for the abolition of the African slave trade in such a manner and at such a period as should be deemed advisable," which was carried by a large majority.
    The chief articles of export (together with those that have lapsed) have been already indicated; but they may be summarized as including seal-oil, seal, fox, bird and bear skins, fish products and eiderdown, with some quantity of worked skins.
    From the Scandinavian peninsula and the British Islands the range of the fox extends eastwards across Europe and central and northern Asia to Japan, while to the south it embraces northern Africa and Arabia, Persia, Baluchistan, and the northwestern districts of India and the Himalaya.
    When living near the coast foxes will, however, visit the shore at low water in search of crabs and whelks; and the old story of the fox and the grapes seems to be founded upon a partiality on the part of the creature for that fruit.
    In a second phase of the species, the colour, which often displays a slaty hue (whence the name of blue fox), remains more or less the same throughout the year, the winter coat being, however, recognizable by the great length of the fur.
    It is the burial-place of Fox the martyrologist and Milton the poet, and contains some fine wood-carving by Grinling Gibbons.
    Nor ought any critical admirer of Fox
    """;

    public static Supplier<String[]> stringsGenerator = () -> text.get().split("\\W+");



    public static Supplier<String[]> stringsShortGenerator = () -> StringTest.shortText.get().split("\\W+");
    public static Function<Boolean, String[]> strings =
            less -> less ? stringsShortGenerator.get() : ArrayStringTest.stringsGenerator.get();


    @ParameterizedTest
    @DisplayName("Test 1st of String[]")
    @CsvSource({"true, the", "false, it"})
    void testGeneratedStrings(Boolean less, String e) {
        assertEquals(e, ArrayStringTest.strings.apply(less)[0]
                .toLowerCase()
                .trim());
    }

    @ParameterizedTest
    @DisplayName("Test length of String[]")
    @CsvSource({"true, 8", "false, 1002"})
    public void testLengthOfStrings(boolean less, int length) {
        assertEquals(length, ArrayStringTest.strings.apply(less).length);
    }

    public static BiFunction<Boolean, String, Long> searchWithStream =
            (less, target) -> Arrays.stream(ArrayStringTest.strings.apply(less))
                    .map(String::toLowerCase)
                    .map(String::trim)
                    .filter(s -> s.contains(target.toLowerCase()))
                    .count();

    @ParameterizedTest
    @DisplayName("Test frequency of 'fox' in String[]")
    @CsvSource({"true, fox, 1", "false, fox, 38"})
    public void testFrequencyOfFox(boolean less, String target, long e) {
        assertEquals(e, ArrayStringTest.searchWithStream.apply(less, target));

        //Arrays.binarySearch( ArrayStringTest.strings.apply(false), "fox");

    }
    //todo: search stream vs search w/ regex
    //todo: make it more clean below via extract function refactoring technique

    public static BiFunction<Boolean, String, Long> searchWithRegex = (less, regex) -> {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(less ? StringTest.shortText.get() : text.get());

        long count = 0;
        while (m.find())
            count++;

        return count;
    };

    @ParameterizedTest
    @DisplayName("Test frequency of 'fox' in String[]")
    @CsvSource({"true, [F|f]ox, 1", "false, [F|f]ox, 38"})
    public void testFrequencyOfFoxWithRegex(boolean less, String regex, long e) {
        assertEquals(e,  searchWithRegex.apply(less, regex));
    }

    @ParameterizedTest
    @DisplayName("Show String[] data")
    @ValueSource(strings = {"true", "false"})
    public void shoutStrings(boolean less) {

        System.out.println(
                Arrays.stream(ArrayStringTest.strings.apply(less))
                        .limit(10)
                        .collect(Collectors.joining(" ")));

//        Arrays.stream(Variables.stringsDataGenerator())
//                .forEach(s -> System.out.printf("%s ", s));

//        for (String x : Variables.stringsDataGenerator())
//            System.out.format("%s ", x) ;

    }





    /*
     * for (variable : collection) statement
     *
     * Iterable interface has to be implemented, such as ArrayList
     *
     * Arrays.copyOf(arrayX, 2 * arrayXLength);  So, old array values + added new elements to the new easily.
     * Arrays.sort(), QuickSort.. it returns void!
     * Arrays.binarySearch(), BinarySearch algorithm, it returns a value!
     * Arrays.stream.sorted() is also possible!!
     *
     * double[][] balances = new double[3][4]; multi-dimensional arrays. Multidimensional arrays are faked as “arrays of arrays.” in Java
     * */



}
