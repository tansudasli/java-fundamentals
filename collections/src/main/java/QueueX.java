import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueX {
    /*
     * Queue
     * processing in the given order
     *
     * It is implemented as PriorityQueue. default order is insert-order (FIFO)
     *
     *
     *
     *
     *
     */

    public static void main(String[] args) {

        List<String> list = List.of("Fly", "Zebra", "Monkey", "Cat", "X");

        //init
        Queue<String> queue = new PriorityQueue<>(); //or, new PriorityQueue<>(list)
                                                     //or, queue.addAll(list)
                                                     //or, list.stream().forEach(queue::add)

        //diff priority
        Queue<String> queueByLength = new PriorityQueue<>(Comparator.comparingInt(String::length));

        //add
        queue.offer("Apple"); queue.offer("Orange");
        System.out.println(queue);

        queue.poll(); //get head
        System.out.println(queue);

        queueByLength.addAll(list);
        System.out.println(queueByLength);


    }
}
