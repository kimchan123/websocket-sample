package com.example.websocketsample;

public class Greeting {

    private Long id;
    private String content;

    public Greeting() {
    }

    public Greeting(Long id, String content) {
        this.id = id;
        this.content = content;
    }


    public String getContent() {
        return content;
    }

}
