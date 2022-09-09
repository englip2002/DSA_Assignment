
/**
 *
 * @author Thong So Xue
 */

import java.util.Iterator;
import java.io.Serializable;

public class LinkedQueue<T> implements QueueInterface<T>, Serializable {
    // Class attributes
    private int size;
    private Node firstNode, lastNode;

    // Private Node class
    private class Node implements Serializable {
        private T data;
        private Node next;

        // Node constructor
        public Node(T newEntry) {
            this.data = newEntry;
            this.next = null;
        }
    }

    // Constructors
    public LinkedQueue() {
        firstNode = null;
        lastNode = null;
        size = 0;
    }

    // Methods
    @Override
    public void enqueue(T newEntry) {
        Node newNode = new Node(newEntry);
        if (isEmpty()) {
            // Point firstNode and lastNode to the only node
            firstNode = newNode;
            lastNode = newNode;
        } else {
            // Append newNode to the end of the queue
            lastNode.next = newNode;
            lastNode = newNode;
        }
        size++;
    }

    @Override
    public T dequeue() {
        // Return error if there are no elements to dequeue
        if (isEmpty()) {
            return (T) "The LinkedQueue is empty, no data to dequeue. ";
        }

        // Keep reference of the data to be returned
        T output = firstNode.data;

        // Update firstNode (and lastNode if queue is empty after dequeue operation)
        firstNode = firstNode.next;
        if (isEmpty()) {
            lastNode = null;
        }
        size--;
        return output;
    }

    @Override
    public T getFront() {
        return firstNode.data;
    }
    
    @Override 
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        // Only check if firstNode is null because
        // if firstNode is null, then lastNode must also be null
        return firstNode == null;
    }

    @Override
    public void clear() {
        firstNode = null;
        lastNode = null;
        size = 0;
    }

    // Iterator class
    private class LinkedQueueIterator implements Iterator<T> {
        private Node iterNode;

        public LinkedQueueIterator() {
            iterNode = firstNode;
        }

        @Override
        public boolean hasNext() {
            return iterNode != null;
        }

        @Override
        public T next() {
            if (hasNext()) {
                T output = iterNode.data;
                iterNode = iterNode.next;
                return output;
            } else {
                return null;
            }
        }
    }

    // Iterator
    @Override
    public Iterator<T> iterator() {
        return new LinkedQueueIterator();
    }
}
