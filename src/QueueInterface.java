
/**
 *
 * @author Thong So Xue
 */

import java.lang.Iterable;
public interface QueueInterface<T> extends Iterable<T> {
    
    public void enqueue(T newEntry);
    public T dequeue();
    public T getFront();
    public int size();
    public boolean isEmpty();
    public void clear();
}
