package com.machinecoding.publishers;

import com.machinecoding.queue.MessageQueue;
import com.machinecoding.queue.managers.QueueManager;

import java.util.Map;

public class Publisher implements IPublisher {
    private final QueueManager queueManager;

    public Publisher(QueueManager queueManager) {
        this.queueManager = queueManager;
    }


    @Override
    public void publish(String queueName, Map<String, Object> message) {
        if(!queueManager.queueExists(queueName)) {
            throw new RuntimeException("Queue does not exist: " + queueName);
        }

        MessageQueue queue = queueManager.getQueue(queueName);

        queue.enqueue(message);
        System.out.println("Message published to queue: " + queueName);

    }
}
