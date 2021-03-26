package com.multithread;

class Task1 extends Thread {
    @Override
    public void run() {
        for (int i = 0 ; i < 30; i++)
            System.out.println(i + " " + Thread.currentThread());
    }
}

public class Core {
    /*
     * Multi-threading
     *
     * - IO intensive
     * - Computing intensive
     *
     * 2 ways (Use class or interface)
     * - extends Thread: create a Task class, then imp. run(), then new Task().start().
     * - implement Runnable:
     *
     *
     *
     */

    public static void main(String[] args) {
        //Thread-0
        (new Task1()).start();

        //Thread-main
        for (int i = 101 ; i < 111; i++)
            System.out.println(i + " " + Thread.currentThread());

    }

}
