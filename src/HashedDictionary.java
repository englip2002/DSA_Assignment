// ChiewHongKuang

/**
 * A public class that implements the ADT dictionary by using hashing and
 * separate chaining to resolve collisions.
 * The dictionary is not sorted and has distinct search keys.
 * DEFAULT_SIZE (TABLE SIZE) = 101 == Prime Number
 * MAX_LOAD_FACTOR (LAMBDA) = 0.9 < 1.0
 */
public class HashedDictionary<K, V> implements DictionaryInterface<K, V> {

    private Node<K, V>[] hashTable;
    private int numberOfEntries;
    private static final int DEFAULT_SIZE = 101;
    private static final double MAX_LOAD_FACTOR = 0.9;

    public HashedDictionary() {
        this(DEFAULT_SIZE);
    }

    public HashedDictionary(int tableSize) {
        hashTable = new Node[getPrime(tableSize)];
        numberOfEntries = 0;
    }

    /**
     * A public method that inherited from the DictionaryInterface to add a pair
     * of dictionary entity by the given key and given value.
     *
     * @param key: The given key.
     * @param value: The given value.
     * @return: The old value of the given key if the key is previously defined.
     */
    @Override
    public V add(K key, V value) {
        V oldValue = null;
        // STEP 1: If the hash table is overloaded, perform rehashing.
        if (isTableOverloaded()) {
            rehashing();
        }
        // STEP 2: Hash the key given to obtain its hash index.
        int i = hashing(key);
        // STEP 3: If the hash index is empty, add the pair of dictionary entity by the given key and the given value.
        if (hashTable[i] == null) {
            hashTable[i] = new Node(key, value);
            // STEP 4: Increase the number pair of dictionary entities.
            numberOfEntries++;
        } else {
            // STEP 5: If the hash index is not empty, search through the Node Objects chain to check whether the given key is distinct.
            Node<K, V> currentNode = hashTable[i];
            boolean isDistinct = true;
            while (currentNode != null) {
                if (key.equals(currentNode.key)) {
                    isDistinct = false;
                    break;
                }
                currentNode = currentNode.next;
            }
            // STEP 6: If the given key is distinct, add the pair of dictionary entity by the given key and the given value.
            if (isDistinct) {
                currentNode = new Node(key, value);
                // STEP 7: Increase the number pair of dictionary entities.
                numberOfEntries++;
            } else {
                // STEP 8: If the given key is not distinct, grab the old value and replace it with the given value.
                oldValue = currentNode.value;
                currentNode.value = value;
            }
        }
        return oldValue;
    }

    /**
     * A public method that inherited from the DictionaryInterface to remove the
     * dictionary entity by the given key.
     *
     * @param key: The given key.
     * @return: An removed value that corresponding to the key given, null if no
     * value has been removed.
     */
    @Override
    public V remove(K key) {
        V removedValue = null;
        // STEP 1: Hash the key given to obtain its hash index.
        int i = hashing(key);
        // STEP 2: Search through the Node Objects chain to find the Node Object that has the key same with the key given.
        Node<K, V> previousNode = null;
        Node<K, V> currentNode = hashTable[i];
        boolean hasKey = false;
        while (currentNode != null) {
            if (key.equals(currentNode.key)) {
                hasKey = true;
                break;
            }
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        // STEP 3: If the Node Object is found, remove it by connecting the pointer of previous node to the next node of it.
        if (hasKey) {
            if (currentNode == hashTable[i]) {
                hashTable[i] = currentNode.next;
            } else {
                previousNode.next = currentNode.next;
            }
            // STEP 4: Grab the removed value and decrease the number pair of dictionary entities.
            removedValue = currentNode.value;
            numberOfEntries--;
        }
        return removedValue;
    }

    /**
     * A public method that inherited from the DictionaryInterface to get the
     * value corresponding to the key given.
     *
     * @param key: The key given.
     * @return: An value that corresponding to the key given, null if no value.
     */
    @Override
    public V getValue(K key) {
        V dataValue = null;
        // STEP 1: Hash the key given to obtain its hash index.
        int i = hashing(key);
        // STEP 2: Search through the Node Objects chain to find the Node Object that has the key same with the key given.
        Node<K, V> currentNode = hashTable[i];
        while (currentNode != null) {
            // STEP 3: Grab the data value if found.
            if (key.equals(currentNode.key)) {
                dataValue = currentNode.value;
                break;
            }
            currentNode = currentNode.next;
        }
        return dataValue;
    }

    /**
     * A public method that inherited from the DictionaryInterface to verify
     * whether the given key has a corresponding value.
     *
     * @param key: The key given.
     * @return: True if the key has a corresponding value, False if not.
     */
    @Override
    public boolean contains(K key) {
        return getValue(key) != null;
    }

    /**
     * A public method that inherited from the DictionaryInterface to verify
     * whether the hash table is empty.
     *
     * @return: True if the number pair of dictionary entities is 0, False if
     * not.
     */
    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    /**
     * A public method that inherited from the DictionaryInterface to verify
     * whether the hash table is full.
     *
     * @return: Always False because the nature of the separate chaining hash
     * dictionary.
     */
    @Override
    public boolean isFull() {
        return false;
    }

    /**
     * A public method that inherited from the DictionaryInterface to get the
     * number pair of dictionary entities.
     *
     * @return: An integer value that represents the number of dictionary
     * entities.
     */
    @Override
    public int getSize() {
        return numberOfEntries;
    }

    /**
     * A public method that inherited from the DictionaryInterface to reset the
     * hashed dictionary.
     */
    @Override
    public final void clear() {
        hashTable = new Node[getPrime(DEFAULT_SIZE)];
        numberOfEntries = 0;
    }

    /**
     * A public method that inherited from String class to produce a string with
     * every pair of dictionary entity.
     *
     * @return: A String object with the format of { key1=value1, key2=value2,
     * ..., keyN=valueN }.
     */
    @Override
    public String toString() {
        String str = "{ ";
        Node<K, V> currentNode = null;
        Node<K, V> previousNode = null;
        boolean hasFirstOccupiedNode = false;
        for (Node<K, V> each : hashTable) {
            currentNode = each;
            if (currentNode != null) {
                if (hasFirstOccupiedNode) {
                    str += previousNode.key + "=" + previousNode.value + ", ";
                } else {
                    hasFirstOccupiedNode = true;
                }
                while (currentNode.next != null) {
                    str += currentNode.key + "=" + currentNode.value + ", ";
                    currentNode = currentNode.next;
                }
                previousNode = currentNode;
            }
        }
        if (previousNode != null) {
            str += previousNode.key + "=" + previousNode.value;
        }
        return str + " }";
    }

    /**
     * A public method that use to get all the keys in the dictionary.
     *
     * @return: A list of String that consists of the keys in the dictionary.
     */
    public K[] getKeys() {
        K[] keys = null;
        Node<K, V> currentNode = null;
        int j = 0;
        // STEP 1: Search through the hash table to find the Node Objects that consists of key.
        for (Node<K, V> each : hashTable) {
            currentNode = each;
            // STEP 2: Search through the Node Object chain to find the Node Objects that consists of key.
            while (currentNode != null) {
                keys[j] = currentNode.key;
                j++;
                currentNode = currentNode.next;
            }
        }
        return keys;
    }

    /**
     * A private class that create Node Object to store the dictionary entity
     * and the pointer to the next Node Object.
     */
    private class Node<S, T> {

        private S key;
        private T value;
        private Node<S, T> next;

        private Node(S searchKey, T dataValue) {
            key = searchKey;
            value = dataValue;
            next = null;
        }

        private Node(S searchKey, T dataValue, Node<S, T> nextNode) {
            key = searchKey;
            value = dataValue;
            next = nextNode;
        }
    }

    /**
     * A utility method to perform hashing based on the hash code of the key
     * given.
     *
     * @param key: The key given.
     * @return: An integer value that represents the hash index generated.
     */
    private int hashing(K key) {
        //STEP 1: Produce the remainder of the key hash code with hash table length as the hash index.
        int i = key.hashCode() % hashTable.length;
        //STEP 2: If the hash index is less than 0, add with hash table length.
        if (i < 0) {
            i = i + hashTable.length;
        }
        return i;
    }

    /**
     * A utility method to verify loads of the hash table.
     *
     * @return: True if the average number of dictionary entries per Node
     * Objects chain is larger than the maximum load factor, False if not.
     */
    private boolean isTableOverloaded() {
        return numberOfEntries / hashTable.length > MAX_LOAD_FACTOR;
    }

    /**
     * A utility method to perform rehashing by expand the hash table al least 2
     * times larger than the old hash table.
     */
    private void rehashing() {
        // STEP 1: Store the old hash table to a temporary Node Objects list.
        Node<K, V>[] oldTable = hashTable;
        // STEP 2: Overwrite the old hash table with a empty new hash table.
        hashTable = new Node[getPrime(oldTable.length * 2)];
        numberOfEntries = 0;
        // STEP 3: Reallocate the data from old hash table to new hash table.
        for (int i = 0; i < oldTable.length; ++i) {
            Node<K, V> currentNode = oldTable[i];
            while (currentNode != null) {
                add(currentNode.key, currentNode.value);
                currentNode = currentNode.next;
            }
        }
    }

    /**
     * A utility method to verify whether the given integer is a prime number.
     *
     * @param integer: The integer value given.
     * @return: True if the integer given is a prime number, False if not.
     */
    private boolean isPrime(int integer) {
        // STEP 1: Prime number is large than 1.
        if (integer <= 1) {
            return false;
        }
        // STEP 2: Prime number is an odd integer that not divisible by every odd integer up to its square root.
        for (int i = 3; i * i <= integer; i += 2) {
            if (integer % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * A utility method to obtain the next prime number given the integer lower
     * boundary.
     *
     * @param integer: The integer value that represents the lower boundary of
     * the next prime number.
     * @return: The next prime number after the lower boundary given.
     */
    private int getPrime(int integer) {
        // STEP 1: If the integer is even number or 2, increase 1 become odd number.
        if (integer % 2 == 0 && integer != 2) {
            integer += 1;
        }
        // STEP 2: If the integer is not a prime number, increment 2 to the next odd number except for 1 that only increment 1.
        while (!isPrime(integer)) {
            if (integer != 1) {
                integer += 2;
            } else {
                integer += 1;
            }
        }
        return integer;
    }
}
