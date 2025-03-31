package com.machinecoding.queue.managers;

import com.machinecoding.queue.IMessageQueue;
import com.machinecoding.queue.MessageQueue;

import java.util.HashMap;
import java.util.Map;

public class QueueManager implements IQueueManager {

    private final Map<String, MessageQueue> queuesMap;

    public QueueManager() {
        this.queuesMap = new HashMap<>();
    }


    @Override
    public void createQueue(String queueName) {
        if(!queuesMap.containsKey(queueName)) {
            queuesMap.put(queueName, new MessageQueue());
        }
        else {
            throw new RuntimeException("Queue already exists: " + queueName);
        }

    }

    @Override
    public MessageQueue getQueue(String queueName) {
        return queuesMap.get(queueName);
    }

    @Override
    public boolean queueExists(String queueName) {
        return queuesMap.containsKey(queueName);
    }
}
