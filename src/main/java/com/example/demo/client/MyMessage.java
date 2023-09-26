package com.example.demo.client;

import java.util.Arrays;

public class MyMessage extends Message {

    public MyMessage(byte[] payloadAsBytes) {
        super(payloadAsBytes);
    }

    @Override
    public String getContentAsString() {
        return super.getContentAsString().toUpperCase();
    }
}
