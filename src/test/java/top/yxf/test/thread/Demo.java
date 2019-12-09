package top.yxf.test.thread;

public class Demo {

    public static void main(String[] args) {
        ConsumerThread consumerThread = new ConsumerThread();

        ProviderThread providerThread = new ProviderThread();

        providerThread.start();
        consumerThread.start();


    }

}
