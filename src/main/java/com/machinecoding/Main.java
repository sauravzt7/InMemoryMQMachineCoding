package com.machinecoding;

import com.machinecoding.dispatchers.Dispatcher;
import com.machinecoding.publishers.IPublisher;
import com.machinecoding.publishers.Publisher;
import com.machinecoding.queue.managers.QueueManager;
import com.machinecoding.subscribers.ISubscriber;
import com.machinecoding.subscribers.Subscriber;

import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        QueueManager queueManager = new QueueManager();
        IPublisher publisher = new Publisher(queueManager);
        Dispatcher dispatcher = new Dispatcher(queueManager);

        queueManager.createQueue("OrderQueue");
        queueManager.createQueue("EmailQueue");

        ISubscriber sub1 = new Subscriber("sub1", 1);
        ISubscriber sub2 = new Subscriber("sub2", 2);

        dispatcher.addSubscriber("OrderQueue", sub1);
        dispatcher.addSubscriber("OrderQueue", sub2);

        publisher.publish("OrderQueue", Map.of("orderId", 123, "amount", 250.0));
        publisher.publish("OrderQueue", Map.of("orderId", 124, "amount", 300.0));
        dispatcher.startConsuming("OrderQueue");
    }
}