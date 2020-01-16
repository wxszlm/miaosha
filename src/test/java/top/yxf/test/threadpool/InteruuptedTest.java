package top.yxf.test.threadpool;

public class InteruuptedTest {

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();
        thread.interrupt();
        System.out.println("第一次调用thread.isInterrupted()：" + thread.isInterrupted());
        System.out.println("第二次调用thread.isInterrupted()：" + thread.isInterrupted());
        //测试interrupted（）函数
        System.out.println("第一次调用thread.interrupted()：" + thread.interrupted());
        System.out.println("第二次调用thread.interrupted()：" + thread.interrupted());
        System.out.println("thread是否存活：" + thread.isAlive());
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("i=" + (i + 1));
            }
        }
    }
}



