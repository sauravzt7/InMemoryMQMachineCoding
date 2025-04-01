package com.machinecoding.dispatchers;

import com.machinecoding.policies.FixedRetryPolicy;
import com.machinecoding.policies.IRetryPolicy;
import com.machinecoding.queue.MessageQueue;
import com.machinecoding.queue.managers.QueueManager;
import com.machinecoding.subscribers.ISubscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dispatcher implements IDispatcher {

    private final QueueManager queueManager;
    private final IRetryPolicy retryPolicy;

    private final Map<String, List<ISubscriber>> queueSubscribersMap; // {queueName -> List<ISubscriber>}

    public Dispatcher(QueueManager queueManager){
        this.queueManager = queueManager;
        this.queueSubscribersMap = new HashMap<>();
        this.retryPolicy = new FixedRetryPolicy();
    }

    @Override
    public void addSubscriber(String queueName, ISubscriber subscriber) {
        queueSubscribersMap
                .computeIfAbsent(queueName, k -> new ArrayList<>())
                .add(subscriber);
        System.out.println("Subscriber " + subscriber.getId() + " added to queue " + queueName);
    }
    @Override
    public void removeSubscriber(String queueName, ISubscriber subscriber) {
        List<ISubscriber> subscribers = queueSubscribersMap.get(queueName);
        if(subscribers != null) {
            subscribers.removeIf(s -> s.getId().equals(subscriber.getId()));
            System.out.println("Subscriber " + subscriber.getId() + " removed from queue " + queueName);
        }
    }

    @Override
    public void startConsuming(String queueName){
        if(!queueManager.queueExists(queueName)) {
            throw new RuntimeException("Queue does not exist: " + queueName);
        }
        MessageQueue messageQueue = queueManager.getQueue(queueName);
        List<ISubscriber> subscribers = queueSubscribersMap.getOrDefault(queueName, new ArrayList<>());

        while(!messageQueue.isEmpty()) {
            for(ISubscriber subscriber : subscribers) {
                int batchSize = subscriber.getBatchSize();
                if (messageQueue.isEmpty()) {
                    System.out.println("Queue is empty, no more messages to consume.");
                    break;
                }
                List<Map<String, Object>> batch = new ArrayList<>();
                for (int i = 0; i < batchSize && !messageQueue.isEmpty(); i++) {
                    batch.add(messageQueue.dequeue());
                }
                boolean success = subscriber.onMessages(batch);

                if (success) {
                    System.out.println("Subscriber " + subscriber.getId() + " processed batch successfully.");
                } else {
                    System.out.println("Subscriber " + subscriber.getId() + " failed to process batch.");
                }

                // if success is false, we can choose to requeue the messages or handle them differently

            }
        }


    }

    @Override
    public void setRetryPolicy() {
        //this.retryPolicy
    }



}
