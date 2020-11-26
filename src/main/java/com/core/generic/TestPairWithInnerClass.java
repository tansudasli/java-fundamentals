package com.core.generic;



/** The static minmax method traverses an array and simultaneously computes the minimum and maximum value.
 * It uses a Pair object to return both results.
 * version: Inner class
 */
public class TestPairWithInnerClass {

    //TODO : inner class ları tekrar okuyup bak

    public static void main(String[] args) {

        String[] words = { "a", "aa", "e", "abc", "ee" };
        int[] numbers = { 1, 4, 5, 3, 2 };

        //TODO: static inner class olayına bak
        class PairInner<T> {

            T first;
            T last;

            public PairInner() {
                this.first = null;
                this.last = null;
            }

            public PairInner(T first, T last) {
                this.first = first;
                this.last = last;
            }

            private PairInner<String> minmax(String[] input) {
                if (input == null || input.length == 0)
                    throw new NullPointerException();

                String min = input[0];
                String max = input[0];

                for (String s:input) {
                    min = min.compareTo(s) > 0 ? s : min;
                    max = max.compareTo(s) > 0 ? max : s;
                }

                return new PairInner<>(min, max);
            }

            private PairInner<Integer> minmax(int[] input) {
                if (input == null || input.length == 0)
                    throw new NullPointerException();

                int min = input[0];
                int max = input[0];

                for (Integer i:input) {
                    min = min > i ? i : min;
                    max = max > i ? max : i;
                }

                return new PairInner<>(min, max);
            }
        }

        System.out.println(new PairInner().minmax(words).first.toString() + " :min | max: " + new PairInner().minmax(words).last.toString() );
        System.out.println(new PairInner().minmax(numbers).first.toString() + " :min | max: " + new PairInner().minmax(numbers).last.toString() );


    }

}
