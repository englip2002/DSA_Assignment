import java.util.Iterator;
import java.io.Serializable;

/**
 * A Queue implemented with doubly linked methods using linked Nodes. This means
 * that the LinkedQueue will hold two references to all of the entries in the
 * queue, namely {@code firstNode} and {@code lastNode}. The {@code firstNode}
 * will hold a reference to the Node or entry at the front of the queue, while
 * the {@code lastNode} will hold a reference to the Node or entry at the back
 * of the queue. All objects added to the LinkedQueue will be encapsulated
 * inside a Node, before being added to the back or rear of the LinkedQueue.
 * 
 * Each Node holds a reference {@code data} to the data it is storing, and a
 * {@code next} reference that points to the next Node in the queue. As such,
 * the last Node in the LinkedQueue will have a {@code next} reference of
 * null, as there are no more entries after the last Node.
 * 
 * @author Thong So Xue
 * @param <T> the type of elements held in this queue
 */
public class LinkedQueue<T> implements QueueInterface<T>, Serializable {
    /**
     * The number of entries in the queue.
     */
    private int size;

    /**
     * The first Node of the queue, representing the front of the queue. If the
     * queue is empty, firstNode will be equal to null.
     */
    private Node firstNode;

    /**
     * The last Node of the queue, representing the back of the queue. If the queue
     * is empty, lastNode will be equal to null.
     */
    private Node lastNode;

    /**
     * The Node class that will be added into the LinkedQueue, while holding the
     * data it represents in the queue, and a pointer to the next Node in the queue.
     */
    private class Node implements Serializable {
        /**
         * The data it holds / represents.
         */
        private T data;

        /**
         * A reference pointing to the next Node in the queue.
         */
        private Node next;

        /**
         * Creates a {@code Node} which holds the data it was given, and a next pointer
         * of null as it will be the last Node of the queue.
         * 
         * @param newEntry the object to be added as a new entry into the queue
         */
        public Node(T newEntry) {
            this.data = newEntry;
            this.next = null;
        }
    }

    /**
     * Creates a {@code LinkedQueue} with zero number of entries.
     */
    public LinkedQueue() {
        firstNode = null;
        lastNode = null;
        size = 0;
    }

    /**
     * Adds a new entry to the back of the queue. The newly added entry will be
     * encapsulated in a {@code Node}, and then added to the back of the queue,
     * becoming the new {@code lastNode} of the queue. The size of the queue is
     * increased by 1.
     * 
     * @param newEntry the object to be added as a new entry.
     */
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

    /**
     * Removes and retrieves the data held by {@code firstNode} at the front of the
     * queue. The second entry in the queue now becomes the new {@code firstNode} of
     * the queue. The size of the queue is increased by 1
     * 
     * @return the entry that was removed from the front of the queue, or
     *         {@code null} if the queue is empty.
     */
    @Override
    public T dequeue() {
        // Return error if there are no elements to dequeue
        if (isEmpty()) {
            return null;
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

    /**
     * Retrieves the data held by {@code firstNode}. The queue remains unchanged.
     * 
     * @return the data held by the Node at the front of the queue, or {@code null}
     *         if the queue is empty.
     */
    @Override
    public T getFront() {
        if (isEmpty()) {
            return null;
        }
        return firstNode.data;
    }

    /**
     * Retrieves the number of entries in the queue.
     * 
     * @return an integer value representing the number of entries in the queue.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if the queue is empty, which means the number of entries in the queue
     * is zero.
     * 
     * @return true if the queue is empty, false if not.
     */
    @Override
    public boolean isEmpty() {
        // Only check if firstNode is null because
        // if firstNode is null, then lastNode must also be null
        return firstNode == null;
    }

    /**
     * Removes all entries in the queue.
     */
    @Override
    public void clear() {
        firstNode = null;
        lastNode = null;
        size = 0;
    }

    /**
     * Private iterator class used to iterate through all the entries in the
     * LinkedQueue.
     */
    private class LinkedQueueIterator implements Iterator<T> {
        /**
         * Node that represents the current position or Node of the iteration.
         */
        private Node iterNode;

        /**
         * Creates an iterator for this LinkedQueue, and initializes the current
         * iteration position to the {@code firstNode} in the {@code LinkedQueue}
         */
        public LinkedQueueIterator() {
            iterNode = firstNode;
        }

        /**
         * Checks if there exists a Node next to the current Node in the iteration.
         * 
         * @return true if there is a next Node in queue, false if not.
         */
        @Override
        public boolean hasNext() {
            return iterNode != null;
        }

        /**
         * Returns the data held by the current Node in the iteration, and moves the
         * current Node to the next Node in queue.
         * 
         * @return the data held by the current Node in the iteration.
         */
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

    /**
     * Returns an iterator object used to iterate the {@code LinkedQueue}. Used
     * automatically by foreach operations.
     * 
     * @return an iterator object used to iterate the {@code LinkedQueue}
     */
    @Override
    public Iterator<T> iterator() {
        return new LinkedQueueIterator();
    }
}
