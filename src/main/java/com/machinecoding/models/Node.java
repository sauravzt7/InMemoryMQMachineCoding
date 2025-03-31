package com.machinecoding.models;

import java.util.Map;

public class Node {
    public Map<String, Object> message;
    public Node next;

    public Node(Map<String, Object> message){
        this.message = message;
        this.next = null;
    }
}
