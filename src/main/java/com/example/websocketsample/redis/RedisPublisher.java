package com.example.websocketsample.redis;

import com.example.websocketsample.Greeting;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class RedisPublisher {

    //message를 redis에서 인식 가능한 형태로 변환해주는 클래스
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisPublisher(final RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void publish(final ChannelTopic topic, final Greeting greeting) {
        redisTemplate.convertAndSend(topic.getTopic(), greeting);
    }
}
