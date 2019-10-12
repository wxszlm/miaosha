package top.yxf.miaosha.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import top.yxf.miaosha.comon.MessageReceiveOne;
import top.yxf.miaosha.comon.MessageReceiveTwo;
import top.yxf.miaosha.comon.RedisMessage;

/**
 *@author wuxiangsheng
 *@description redis的一些配置
 *@className RedisConfig
 *@date 2019/10/12 13:47
 *
 **/
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String,Object>template=new RedisTemplate<>();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        //关联
        template.setConnectionFactory(factory);
        //设置key的序列化器
        // template.setKeySerializer(new StringRedisSerializer());
        //设置value的序列化器
        // template.setValueSerializer(new StringRedisSerializer());
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashKeySerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        return template;
    }

    /**
     * redis消息监听器容器
     * 可以添加多个监听不同话题的redis监听器，只需要把消息监听器和相应的消息订阅处理器绑定，该消息监听器
     * 通过反射技术调用消息订阅处理器的相关方法进行一些业务处理
     * @param connectionFactory
     * @param listenerAdapter
     * @return
     */
    @Bean
    public RedisMessageListenerContainer container2(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter2, MessageListenerAdapter listenerAdapter,MessageListenerAdapter listenerAdapter3){

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //订阅多个频道
        container.addMessageListener(listenerAdapter2,new PatternTopic("fullDataUpload"));
        container.addMessageListener(listenerAdapter2,new PatternTopic("analysis"));
        container.addMessageListener(listenerAdapter,new PatternTopic("fullDataUpload"));
        container.addMessageListener(listenerAdapter3,new PatternTopic("fullDataUpload"));

        //序列化对象（特别注意：发布的时候需要设置序列化；订阅方也需要设置序列化）
        Jackson2JsonRedisSerializer seria = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        seria.setObjectMapper(objectMapper);

        container.setTopicSerializer(seria);
        return container;

    }

    //表示监听一个频道
    @Bean
    protected MessageListenerAdapter listenerAdapter(MessageReceiveTwo receiver){
        //这个地方 是给messageListenerAdapter 传入一个消息接受的处理器，利用反射的方法调用“MessageReceiveTwo ”
        return new MessageListenerAdapter(receiver,"getMessage");
    }

    @Bean
    protected MessageListenerAdapter listenerAdapter2(MessageReceiveOne receiver){
        //这个地方 是给messageListenerAdapter 传入一个消息接受的处理器，利用反射的方法调用“MessageReceiveOne ”
        return new MessageListenerAdapter(receiver,"getMessage");
    }

    @Bean
    protected MessageListenerAdapter listenerAdapter3(RedisMessage receiver){
        //这个地方 是给messageListenerAdapter 传入一个消息接受的处理器，利用反射的方法调用“MessageReceiveOne ”
        return new MessageListenerAdapter(receiver,"onMessage");
    }
}
