package com.example.websocketsample;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    private final SimpMessagingTemplate template;

    public GreetingController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/hello/{id}")
    public void greeting(@DestinationVariable Long id, HelloMessage message) throws Exception {
//        Thread.sleep(1000); // simulated delay
        System.out.println("id = " + id);
        Greeting payload = new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
        this.template.convertAndSend("/topic/greetings/" + id, payload);
    }

}
