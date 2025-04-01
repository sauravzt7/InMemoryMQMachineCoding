package com.machinecoding.subscribers;

import com.machinecoding.policies.IRetryPolicy;

import java.util.List;
import java.util.Map;

public interface ISubscriber {
    String getId();
    int getBatchSize();
    boolean onMessages(List<Map<String, Object>> messages);
    void setRetryPolicy();
    IRetryPolicy getRetryPolicy();

}
