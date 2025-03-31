package com.machinecoding.subscribers;

import java.util.List;
import java.util.Map;

public interface ISubscriber {
    String getId();
    int getBatchSize();
    boolean onMessages(List<Map<String, Object>> messages);

}
