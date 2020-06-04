package top.yxf.test.queue;

import java.util.PriorityQueue;
import java.util.Queue;

public class TestQueue {

    public static void main(String[] args) {
        Queue<String> queue = new PriorityQueue<>();

        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");

        String first = queue.poll();
        String second = queue.poll();
        String three = queue.poll();
        String four = queue.poll();

        System.out.println(first);
        System.out.println(second);
        System.out.println(three);
        try {
            System.out.println(four);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(queue.peek());
        System.out.println(queue.offer("1"));
        System.out.println(queue.poll());

    }

}
