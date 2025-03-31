package com.machinecoding.queue.managers;

import com.machinecoding.queue.MessageQueue;

public interface IQueueManager {


    void createQueue(String queueName);
    MessageQueue getQueue(String queueName);
    boolean queueExists(String queueName);

}
