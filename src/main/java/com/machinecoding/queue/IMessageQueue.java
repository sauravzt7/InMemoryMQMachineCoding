package com.machinecoding.queue;

import java.util.Map;

public interface IMessageQueue {

    void enqueue(Map<String, Object> message);
    Map<String, Object> dequeue();
    Map<String, Object> peek();
    boolean isEmpty();
}
