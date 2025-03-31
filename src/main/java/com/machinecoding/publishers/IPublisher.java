package com.machinecoding.publishers;

import java.util.Map;

public interface IPublisher {
    void publish(String queueName, Map<String, Object> message);
}
