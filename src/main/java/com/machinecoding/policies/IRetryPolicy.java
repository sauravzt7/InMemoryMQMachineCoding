package com.machinecoding.policies;

public interface IRetryPolicy {

    boolean shouldRetry(int attemptCount);

}
