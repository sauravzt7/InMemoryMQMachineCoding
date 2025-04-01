package com.machinecoding.dispatchers;

import com.machinecoding.subscribers.ISubscriber;

public interface IDispatcher {

    void addSubscriber(String queueName, ISubscriber subscriber);
    void removeSubscriber(String queueName, ISubscriber subscriber);
    void startConsuming(String queueName);
    void setRetryPolicy();

}
