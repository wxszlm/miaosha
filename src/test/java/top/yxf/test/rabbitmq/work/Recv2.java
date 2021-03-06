package top.yxf.test.rabbitmq.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import top.yxf.test.rabbitmq.ConnectionUtil;

import java.io.IOException;

public class Recv2 {


    private final static String QUEUE_NAME = "test_queue_work";


    public static void main(String[] args) {
        Connection connection = null;
        Channel channel = null;
        //创建连接
        try {
            connection = ConnectionUtil.getConnection();
            //创建通道
            channel = connection.createChannel();

            // 声明队列
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);

            // 同一时刻服务器只会发一条消息给消费者
            //channel.basicQos(1);
            // 定义队列的消费者
            QueueingConsumer consumer = new QueueingConsumer(channel);

            // 监听队列，false表示手动返回完成状态，true表示自动
            channel.basicConsume(QUEUE_NAME, false, consumer);

            // 获取消息
            while (true) {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String message = new String(delivery.getBody());
                System.out.println(" [y] Received '" + message + "'");
                //休眠
                Thread.sleep(100);
                // 返回确认状态，注释掉表示使用自动确认模式
                //channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //最后关闭通关和连接
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
