package com.machinecoding.factories;

import com.machinecoding.queue.MessageQueue;

public class MessageQueueFactory {

    public static MessageQueue createQueue() {
        return new MessageQueue();
    }
}
