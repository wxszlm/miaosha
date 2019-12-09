package top.yxf.test.thread;

public class Desk {

    // 食物数量
    public static int count = 10;

    // 锁的对象
    public static final Object lock = new Object();

    // 判断是否被锁机制
    public static boolean flag = true;
}
