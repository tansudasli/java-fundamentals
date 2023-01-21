/**
 * Twenty students were asked to rate on a scale of 1 to 5 the quality of the food in the student cafeteria,
 * with 1 being “awful” and 5 being “excellent.”
 *
 * Place the 20 responses in an integer array and determine the frequency of each rating.
 * */
public class PollAnalysis {

    static int[] responses = {1, 2, 5, 4, 3, 5, 2, 1, 3, 3, 1, 4, 3, 3, 3, 2, 3, 3, 2, 1};
    static int[] frequency = new int[6];

    public static void main(String[] args) {

        //so elegant
        for ( int i : responses)
            ++frequency[responses[i]];



        System.out.printf("%s%10s%n", "Rating", "Frequency");

//        for (int rating = 1; rating < frequency.length; rating++)
//            System.out.printf("%6d%10d%n", rating, frequency[rating]);
//

        //draw bar chart histogram
        for (int rating = 1; rating< frequency.length; rating++) {
            System.out.printf("%6d%10d", rating, frequency[rating]);

            //another elegant way
            for (int stars = 0; stars < frequency[rating]; stars++)
                System.out.print("*");

            System.out.println();
        }

    }
}
