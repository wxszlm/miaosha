package top.yxf.test.b;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LongEventMain {


    public static void main(String[] args) throws Exception {
        // Executor that will be used to construct new threads for consumers
        //创建线程池
        Executor executor = Executors.newCachedThreadPool();

        //事件工厂
        LongEventFactory factory = new LongEventFactory();

        //ringBuffer 的缓冲区的大小是1024 必须是2的N次方
        int bufferSize = 1024;

        //创建一个disruptor
        //ProducerType.MULTI:创建一个环形缓冲区支持多事件发布到一个环形缓冲区
        Disruptor<LongEvent> disruptor = new Disruptor<>(factory, bufferSize, executor, ProducerType.MULTI, new BlockingWaitStrategy());

        //创建一个消费者
        disruptor.handleEventsWith(new LongEventHandler());

        //启动并初始化disruptor
        disruptor.start();

        //获取已经初始化好的ringBuffer
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        ////获取已经初始化好的ringBuffer
        LongEventProducer producer = new LongEventProducer(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; true; l+=3) {
            //存入数据
            bb.putLong(0, l);
            producer.onData(bb);
//            producer
            Thread.sleep(1000);
        }
    }
}
