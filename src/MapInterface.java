
// ChiewHongKuang
public interface MapInterface<K, V> {

    public V put(K key, V value);

    public V remove(K key);

    public V get(K key);

    public K[] keySet();

    public V[] values();

    public boolean containsKey(K key);

    public boolean containsValue(V value);

    public boolean isEmpty();

    public boolean isFull();

    public int size();

    public void clear();
}
