package top.yxf.test.leetcode;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {


    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);

        try {
//

            new Thread(() -> {
                for (int i = 0; i < 10 ; i++) {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (i >= 6) {
                        System.out.println(Thread.currentThread().getName() + "-------->" +  i);
                    }
                }
            }).start();

            new Thread(() -> {
                for (int i = 0; i < 10 ; i++) {

                    if (i >= 8) {
                        System.out.println(Thread.currentThread().getName() + "-------->" +  i);
                        latch.countDown();
                    }
                }

            }).start();

//            latch.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
