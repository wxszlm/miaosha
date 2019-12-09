package top.yxf.test.thread;

import sun.security.krb5.internal.crypto.Des;

/**
 * @author wuxiangsheng
 * @description 消费者
 * @className ConsumerThread
 * @date 2019/12/9 15:05
 **/
public class ConsumerThread extends Thread {

    @Override
    public void run() {

        try {
            while (true) {
                synchronized (Desk.lock) {
                    if (Desk.count == 0) {
                        break;
                    } else {
                        if (!Desk.flag) {
                            Desk.count--;
                            Desk.flag = true;
                            Desk.lock.notifyAll();
                            System.out.println("吃货正在吃食品");
                        } else {
                            Desk.lock.wait();
                        }
                    }

                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
