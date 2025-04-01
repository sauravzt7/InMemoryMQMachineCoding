package com.machinecoding.queue;

import com.machinecoding.models.Node;

import java.util.Map;

public class MessageQueue implements IMessageQueue {

    private Node head;
    private Node tail;
    private int size;

    public MessageQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;

    }


    @Override
    public void enqueue(Map<String, Object> message) {
        Node newNode = new Node(message);
        if(tail == null){
            head = newNode;
            tail = newNode;
        } else {
            tail.next = (newNode);
            tail = newNode;
        }

        this.size++;
    }

    @Override
    public Map<String, Object> dequeue() {
        if(head == null){
            return null;
        }
        Map<String, Object> msg = head.message;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return msg;
    }

    public Map<String, Object> peek() {
        return (head == null) ? null : head.message;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
