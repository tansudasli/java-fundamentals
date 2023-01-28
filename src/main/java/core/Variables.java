package core;


public class Variables {









    /* concatenation.

       Use + for small number of concatenation. But, String.concat is better...
       in a Loop or more ops., use StringBuilder or StringBuffer (threadsafe)
       StringBuilder perf is similar to StringBuffer.
     */
//    public static String greeting() {
//        return "The fox was already in your chicken house.";
//    }







    /*
       data generation



       sorting


       searching


     */


    public static void main(String[] args) {



        /* operators
        *
        *  b ? 1 : 0
        *  zz < zzz ? 1 : 0
        *
        * */



        /*
        * for (variable : collection) statement
        *
        * Iterable interface has to be implemented, such as ArrayList
        *
        * Arrays.copyOf(arrayX, 2 * arrayXLength);  So, old array values + added new elements to the new easily.
        * Arrays.sort() , QuickSort
        * Arrays.binarySearch(), BinarySearch algorithm
        *
        * double[][] balances = new double[3][4]; multi-dimensional arrays. Multidimensional arrays are faked as “arrays of arrays.” in Java
        * */

        /* Date
        * Use LocalDate class. Date, GregorianCalendar class are deprecated or more complex operations!
        *
        * LocalDate x = LocalDate.now()
        * x.getYear()
        * y = x.plusDays(1000) , 1000 days later.
        * y.getYear() --> new year you get
        *
        * */

        /* The outer braces make an anonymous subclass of ArrayList
        * {{ object constructor
        * If you don’t need the array list again, it would be nice to make it anonymous

        new ArrayList<String>() {{ add("Harry"); add("Tony"); }};

        * or

        ArrayList<String> friends = new ArrayList<>();
        friends.add("Harry");
        friends.add("Tony");

        * */


    }
}
