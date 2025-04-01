package com.machinecoding.policies;

public class FixedRetryPolicy implements IRetryPolicy {


    @Override
    public boolean shouldRetry(int attemptCount) {
        return false;
    }
}
