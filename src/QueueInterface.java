
/**
 *
 * @author Thong So Xue
 */

import java.lang.Iterable;

public interface QueueInterface<T> extends Iterable<T> {

    /**
     * Task: Adds a new entry to the back of the queue. The newly added entry is now
     * positioned at the back of the queue. The queue's size is increased by 1.
     *
     * @param newEntry the object to be added as a new entry
     */
    public void enqueue(T newEntry);

    /**
     * Task: Removes and retrieves an entry from the front of the queue. The second
     * entry from the front of the queue is now positioned at the front of the
     * queue. The queue's size is decreased by 1.
     *
     * @return the entry that was removed from the front of the queue, or
     *         {@code null} if the queue is empty.
     */
    public T dequeue();

    /**
     * Task: Retrieves the entry at the front of the queue. All entries in the
     * queue are not affected.
     * 
     * @return the entry at the front of the queue, or {@code null} if the queue is
     *         empty.
     */
    public T getFront();

    /**
     * Task: Retrieves the number of entries in the queue.
     * 
     * @return an integer value representing the number of entries in the queue.
     */
    public int size();

    /**
     * Task: Checks if the queue is empty, or if the number of entries in
     * the queue is zero.
     * 
     * @return true if the queue is empty, false if not.
     */
    public boolean isEmpty();

    /**
     * Task: Removes all entries in the queue.
     */
    public void clear();
}
