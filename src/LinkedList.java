import java.io.Serializable;
import java.util.Iterator;

//Tan Eng Lip

public class LinkedList<T> implements ListInterface<T>, Serializable {

    private Node firstNode;
    private int numberOfEntries;

    public LinkedList() {
        clear();
    }

    @Override
    // add new value to last index
    public boolean add(T newEntry) {
        Node newNode = new Node(newEntry);
        if (isEmpty()) {
            firstNode = newNode;
        } else {
            Node currentNode = getNode(numberOfEntries - 1);
            currentNode.next = newNode;
        }
        numberOfEntries++;
        return true;
    }

    @Override
    // add new value to specific index
    public boolean add(int newIndex, T newEntry) {
        if (newIndex >= 0 && newIndex < numberOfEntries) {
            Node newNode = new Node(newEntry);
            // if array is empty or index = 0, put the new item into first
            if (isEmpty() || newIndex == 0) {
                newNode.next = firstNode;
                firstNode = newNode;
            } else {
                Node previousNode = getNode(newIndex - 1);
                // to pass the next value of Node(newIndex-1) to newNode.next
                newNode.next = previousNode.next;
                // change the previousNode's next to newNode
                previousNode.next = newNode;
            }
            numberOfEntries++;
            return true;
        }
        return false;
    }

    @Override
    // remove from specific index
    public T remove(int removeIndex) {
        T removeData = null;
        if (removeIndex >= 0 && removeIndex < numberOfEntries) {
            if (removeIndex == 0) {
                removeData = firstNode.data;
                // firstNode.next = null when only one data
                firstNode = firstNode.next;
            } else {
                Node previousNode = getNode(removeIndex - 1);
                // previousNode.next = currentNode
                removeData = previousNode.next.data;
                // previousNode.next.next = currentNode.next (next node of removeIndex)
                previousNode.next = previousNode.next.next;
            }
            numberOfEntries--;
        }
        return removeData;
    }

    @Override
    // clear the whole collection
    public final void clear() {
        firstNode = null;
        numberOfEntries = 0;
    }

    @Override
    // replace the node(newIndex).data to newEntry
    public boolean replace(int newIndex, T newEntry) {
        if (newIndex >= 0 && newIndex < numberOfEntries) {
            Node currentNode = getNode(newIndex);
            currentNode.data = newEntry;
            return true;
        }
        return false;
    }

    @Override
    // get the data of entryIndex
    public T getEntry(int entryIndex) {
        if (entryIndex >= 0 && entryIndex < numberOfEntries) {
            return getNode(entryIndex).data;
        }
        return null;
    }

    @Override
    public boolean contains(T anEntry) {
        Node currentNode = firstNode;
        // loop and check the data from begining
        for (int i = 0; i < numberOfEntries; i++) {
            if (anEntry.equals(currentNode.data)) {
                return true;
            } else {
                currentNode = currentNode.next;
            }
        }
        return false;
    }

    @Override
    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public String toString() {
        if (!isEmpty()) {
            String str = "[ ";
            Node currentNode = firstNode;
            while (currentNode.next != null) {
                str += currentNode.data + ", ";
                currentNode = currentNode.next;
            }
            str += currentNode.data + " ]";
            return str;
        } else {
            return "No data contained!";
        }

    }

    // inner class
    private class Node implements Serializable{

        private T data;
        private Node next;

        private Node(T data) {
            this.data = data;
            this.next = null;
        }

        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    // utility method
    private Node getNode(int nodeIndex) {
        Node result = firstNode;
        for (int i = 0; i < nodeIndex; i++) {
            result = result.next;
        }
        return result;
    }

    // Iterator class
    private class LinkedListIterator implements Iterator<T> {
        private Node iterNode;

        public LinkedListIterator() {
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
        return new LinkedListIterator();
    }
}
