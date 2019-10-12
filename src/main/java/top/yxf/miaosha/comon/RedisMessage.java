package top.yxf.miaosha.comon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

/**
 *@author wuxiangsheng
 *@description redis接收消息
 *@className RedisMessage
 *@date 2019/10/12 15:34
 *
 **/
@Component
public class RedisMessage implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {

//        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer(Charset.defaultCharset());
        String msg = stringRedisSerializer.deserialize(message.getBody());

        System.out.println("接收到的消息是：" + msg);

    }
}
