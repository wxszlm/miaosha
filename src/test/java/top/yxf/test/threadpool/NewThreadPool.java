package top.yxf.test.threadpool;

import java.util.concurrent.*;

public class NewThreadPool {

    /**
     * CallerRunsPolicy:在调用execut方法的调用线程中直接执行线程池拒绝的任务；
     *
     * AbortPolicy：以抛出一个RejectedExecutionException的方式处理拒绝执行的任务；
     *
     * DiscardPolicy：以直接忽略的方式处理拒绝执行的任务；
     *
     * DiscardOldestPolicy：忽略掉最老的没有处理的拒绝任务，然后继续尝试执行execute方法，知道线程池关闭，那么放弃找个任务。
     */

    public static void main(String[] args) throws Exception{
        // 有界队列
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(5);
        // 放弃拒绝的任务并抛出异常
        RejectedExecutionHandler abortPolicyHandler = new ThreadPoolExecutor.AbortPolicy();
        RejectedExecutionHandler discardPolicyHandler = new ThreadPoolExecutor.DiscardPolicy();

        ThreadPoolExecutor threadPool =
                new ThreadPoolExecutor(5, 10, 30, TimeUnit.SECONDS, workQueue, abortPolicyHandler);
        long start = System.currentTimeMillis();

        for (int i = 0; i < 40; i++) {

            threadPool.execute(new MyTask());
            System.out.println("核心线程数" + threadPool.getCorePoolSize());
            System.out.println("最大线程数" + threadPool.getMaximumPoolSize());
            System.out.println("线程池数" + threadPool.getPoolSize());
            System.out.println("队列任务数" + threadPool.getQueue().size());
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        }

        System.out.println(System.currentTimeMillis() - start);
        threadPool.shutdown();
        if (threadPool.awaitTermination(6, TimeUnit.SECONDS)) {
            threadPool.shutdownNow();
        }
    }

}
class MyTask implements Runnable {

    @Override
    public void run() {
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ", hello");
    }

}
