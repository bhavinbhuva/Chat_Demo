package com.example.chat_demo;

public class Chat {
    private String Message,MessageReceiver,MessageSender,Time;

    public Chat() {
    }
    public Chat(String message, String messageReceiver, String messageSender, String time) {
        Message = message;
        MessageReceiver = messageReceiver;
        MessageSender = messageSender;
        Time = time;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public void setMessageReceiver(String messageReceiver) {
        MessageReceiver = messageReceiver;
    }

    public void setMessageSender(String messageSender) {
        MessageSender = messageSender;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getMessage() {
        return Message;
    }

    public String getMessageReceiver() {
        return MessageReceiver;
    }

    public String getMessageSender() {
        return MessageSender;
    }

    public String getTime() {
        return Time;
    }
}
