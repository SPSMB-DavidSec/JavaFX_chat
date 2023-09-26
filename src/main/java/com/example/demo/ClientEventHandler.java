package com.example.demo;

import com.example.demo.client.Message;

public abstract class ClientEventHandler {

    public void onMessageReceived(Message message) {
        System.out.println("New message received: " + message.getContentAsString());
    }


}
