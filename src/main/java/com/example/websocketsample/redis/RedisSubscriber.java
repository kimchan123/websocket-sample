package com.example.websocketsample.redis;

import com.example.websocketsample.Greeting;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisSubscriber implements MessageListener {

    private final ObjectMapper objectMapper;
    private final RedisTemplate redisTemplate;
    private final SimpMessageSendingOperations messagingTemplate;

    public RedisSubscriber(ObjectMapper objectMapper, RedisTemplate redisTemplate,
                           SimpMessageSendingOperations messagingTemplate) {
        this.objectMapper = objectMapper;
        this.redisTemplate = redisTemplate;
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        final String publishMessage = (String) redisTemplate.getStringSerializer().deserialize(message.getBody());
        System.out.println("publishMessage = " + publishMessage);
        try {
            final Greeting greeting = objectMapper.readValue(publishMessage, Greeting.class);
            messagingTemplate.convertAndSend("/topic/greetings/" + greeting.getId(), greeting);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
