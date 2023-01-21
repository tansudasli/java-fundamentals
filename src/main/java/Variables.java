/*
* Variables in Java.
* */
public class Variables {

    public static final double CM_PER_INCH = 2.54;

    enum Size {XS, S, M, L, XL, XXL}

    // public static void functionX (String s, Object...args) { }

    public static double max (double...args) {
        double max = 0;

        for (double v : args)
            if (v > max)
                max = v;

        return max;
    }


    public static void main(String[] args) {

        char pi = '\u03C0'; //not use char anymore due to UTF.

        System.out.println(pi);

        //appropriate usages
        long earthPopulation;
        boolean done;
        int vacationDays;
        double salary = 65000.00;

        //use final for constant values
        final double CM_PER_INCH = 2.54; //more appropriate as defined static. cause it is final, not changeable
        double paperWidth = 8.5;

        double x = 4;
        System.out.println("square of 4 = " + Math.sqrt(x));
        System.out.println("power of 4^2 = " + Math.pow(x,2));


        double z = 9.997;
        int zz = (int) z; //9
        int zzz = (int) Math.round(z); //10
        System.out.println("casting a double 9.997 to int = " + zz);
        System.out.println("casting a double 9.997 after round to int = " + zzz);


        /* operators
        *
        *  b ? 1 : 0
        *  zz < zzz ? 1 : 0
        *
        * */

        //enum
        Size xx = Size.S;
        System.out.println(xx);

        /* no built-in string type in Java.
        *  use String (sequences of Unicode characters
        *  each quoted string is instance of String class
        *  */
        String greeting = "Hello, Tansu dasli. What's up";
        System.out.println("first 3 chars of greetings = " + greeting.substring(0,3));
        System.out.println("first char of greetings = " + greeting.charAt(0));
        System.out.println("unicode point of greetings = " + greeting.codePointAt(0));

        /* handling strings as an array....

        * int[] codePoints = greeting.codePoints().toArray();
        * String greetings = new String(codePoints, 0, codePoints.length);
        *
        * */



        /*
        * use String.equals() , not == operator for string equality
        *
        * str == null
        * str.length() == 0
        *
        * str != null && str.length() != 0  , check null first :)
        *
        * */

        /* comparison
        *
        * str.compareTo(String other) , -1 (less),0 (equal),+1 (greater)
        * String.equals() , not == operator for string content equality
        *
        * */

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

        /* String concatenation operator is +.
         * But much performer way is StringBuilder. Single thread ops.
         *
         * StringBuilder builder = new StringBuilder();
         * builder.append(str);
         * completedString = builder.toString(); , when you done building..
         *
         * */
        String join = String.join("/","A","B","C","D");
        System.out.println("joined of = " + join);

        /* formatting
        *  If a class implements Formattable interface, formatTo() function executed otherwise toString() func. executed to turn an object into string!
        *
        *  String.format() can also be used, without printing it.
        *  String.format("Hello %s", name);
        *
        * */

        /* for more precision
        *
        * BigInteger a = BigInteger.valueOf(100);
        *
        * a.add(b); not a+b !!!
        * */

        /* Array
        *
        * int[] i = new int[100];
        *
        * i.length
        * */

        /*
        * for (variable : collection) statement
        *
        * Iterable interface has to be implemented, such as ArrayList
        *
        * Arrays.copyOf(arrayX, 2 * arrayXLength);
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

        //infinite variables
        System.out.println(max(12, 34, 25, 56));

        System.out.println(max(12, 34, 25, 56, 75, 789, 9999, 1899,2829829.04, 2829829, 33, 33, 33333, 8989));

    }
}
