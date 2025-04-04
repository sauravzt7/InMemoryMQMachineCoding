package com.machinecoding.subscribers;

import com.machinecoding.policies.FixedRetryPolicy;
import com.machinecoding.policies.IRetryPolicy;

import java.util.List;
import java.util.Map;

public class Subscriber implements ISubscriber {
    private final String id;
    private final int batchSize;
    private final IRetryPolicy retryPolicy;

    public Subscriber(String id, int batchSize) {
        this.id = id;
        this.batchSize = batchSize;
        this.retryPolicy = new FixedRetryPolicy();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public int getBatchSize() {
        return batchSize;
    }

    @Override
    public boolean onMessages(List<Map<String, Object>> messages) {
        try {
            // Simulate processing each message
            for (Map<String, Object> msg : messages) {
                // Example: call some external service or do some business logic
                System.out.println("Subscriber " + this.id + " processing: " + msg);
            }
            return true; // indicate success
        } catch (Exception e) {
            // Log exception, return false to indicate a failure for potential retry
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void setRetryPolicy(){

    }

    public IRetryPolicy getRetryPolicy() {
        return retryPolicy;
    }
}
