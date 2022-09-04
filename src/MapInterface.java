
// ChiewHongKuang
public interface MapInterface<K, V> {

    public V put(K key, V value);

    public V remove(K key);

    public V getValue(K key);

    public K[] keySet();

    public boolean contains(K key);

    public boolean isEmpty();

    public boolean isFull();

    public int getSize();

    public void clear();
}
