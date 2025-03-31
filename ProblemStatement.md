Machine Coding Assignment: In-Memory Message Queueing System
Problem Statement
Design and implement a lightweight message queueing system, where a Publisher can publish messages to a Queue, and Subscribers can consume those messages asynchronously via callbacks.

This system mimics a simplified version of systems like Kafka, RabbitMQ, or Pub/Sub, designed with in-memory data structures only and must support real-time extensibility (adding/removing subscribers at runtime).

Functional Requirements
1. Custom Queue (No Java Queue API)
   Build your own custom queue (linked list or array-based).

The queue will hold messages in JSON format.

2. Multiple Named Queues
   The system must support multiple named queues (e.g., "EmailQueue", "PaymentQueue").

Each queue operates independently.

3. Publisher
   There will be one publisher responsible for pushing messages to the queues.

Messages must be standard JSON objects (can be modeled via Map<String, Object> or JSONObject).

4. Subscribers
   Each queue can have multiple subscribers.

Subscribers must:

Be addable/removable at runtime.

Register a callback to handle incoming messages.

Be loosely coupled with the queue (interface-based).

Be able to consume messages in batches (configurable batch size).

Handle failures gracefully.

Bonus Requirements
Retry Mechanism
In case of callback failure (exception, API failure, etc.), the system must:

Retry the callback.

Support a retry strategy (e.g., maxRetries, delay).

Commands / Features to Implement
Feature	Description
createQueue(queueName)	Create a new named queue.
publish(queueName, jsonMessage)	Publish a JSON message to a specific queue.
addSubscriber(queueName, subscriberId, callbackFunction, batchSize)	Register a subscriber for a queue.
removeSubscriber(queueName, subscriberId)	Deregister a subscriber.
startConsuming(queueName)	Start dispatching messages to subscribers.
setRetryPolicy(subscriberId, maxRetries, delay)	(Optional) Configure retry policy per subscriber.
Sample Scenario
plaintext
Copy
Edit
createQueue("OrderQueue")
createQueue("EmailQueue")

addSubscriber("OrderQueue", "Sub1", OrderCallbackFn, 1)
addSubscriber("OrderQueue", "Sub2", OrderCallbackFn2, 2)

publish("OrderQueue", { "orderId": 1, "status": "created" })
publish("OrderQueue", { "orderId": 2, "status": "shipped" })
publish("OrderQueue", { "orderId": 3, "status": "delivered" })

startConsuming("OrderQueue")
→ Sub1: Consumes 1 message
→ Sub2: Consumes messages in batch of 2
System Components
Class	Responsibility
MessageQueue	Custom implementation of a queue (linked list preferred).
QueueManager	Manages multiple queues.
Publisher	Interface to publish messages.
Subscriber	Interface for any subscriber (with ID, callback, batch size, retry policy).
Dispatcher	Handles message consumption and dispatch to subscribers.
RetryPolicy	Encapsulates retry logic (maxRetries, delay, etc.).
CallbackHandler	Wraps and invokes external callback API (mocked).
Constraints
Use only in-memory data structures (no databases, files).

Do not use built-in Queue classes (LinkedList, PriorityQueue, etc.).

No third-party libraries (except for JSON parsing if needed).

Bonus Points
Retry logic with backoff.

Thread-safe queue (if you choose to support concurrency).

Configurable delay/scheduler between batch pulls.

CLI input parser or REPL to simulate commands.

Sample Output
plaintext
Copy
Edit
createQueue("EmailQueue")
→ Queue EmailQueue created

addSubscriber("EmailQueue", "EmailService", EmailCallback, 2)
→ Subscriber EmailService added to EmailQueue

publish("EmailQueue", { "to": "user@example.com", "subject": "Hello" })
→ Message published to EmailQueue

startConsuming("EmailQueue")
→ EmailService: Processing 1 message
→ Email sent to user@example.com
Deliverables
✅ Full working Java code (no frameworks).

✅ Main driver or input simulation.

✅ Clean, testable, modular code.

✅ Console outputs.