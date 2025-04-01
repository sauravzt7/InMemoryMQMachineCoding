# Message Queue Dispatcher

## Overview
This project implements a message queue dispatcher system in Java. 
It allows for managing message queues, adding/removing subscribers, and dispatching messages to subscribers in batches.

## Project Structure
- `src/main/java/com/machinecoding/subscribers/ISubscriber.java`: Interface for subscribers.
- `src/main/java/com/machinecoding/subscribers/Subscriber.java`: Implementation of the `ISubscriber` interface.
- `src/main/java/com/machinecoding/dispatchers/Dispatcher.java`: Dispatcher class that manages message queues and subscribers.

## Key Components
### Messages are taken as `Map<String, Object>` representing an id -> value pair where value is of type `Object`.:
### ISubscriber
Defines the contract for subscribers:
- `getId()`: Returns the subscriber ID.
- `getBatchSize()`: Returns the batch size for message processing.
- `onMessages(List<Map<String, Object>> messages)`: Processes a batch of messages.

### Subscriber
Implements the `ISubscriber` interface:
- Constructor: `Subscriber(String id, int batchSize)`
- Methods: `getId()`, `getBatchSize()`, `onMessages(List<Map<String, Object>> messages)`

### Dispatcher
Manages message queues and subscribers:
- Constructor: `Dispatcher(QueueManager queueManager)`
- Methods:
  - `addSubscriber(String queueName, ISubscriber subscriber)`: Adds a subscriber to a queue.
  - `removeSubscriber(String queueName, ISubscriber subscriber)`: Removes a subscriber from a queue.
  - `startConsuming(String queueName)`: Starts consuming messages from a queue.

## Usage
1. **Add a Subscriber**:
   ```java
   Dispatcher dispatcher = new Dispatcher(queueManager);
   ISubscriber subscriber = new Subscriber("subscriber1", 10);
   dispatcher.addSubscriber("queue1", subscriber);