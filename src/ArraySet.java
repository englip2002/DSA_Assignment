import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Author: KONG ZHI LIN
 * Implementer class for ADT SetInterface
 * Contain all of the method of the SetInterface 
 * This implementor class are using the Array to implement the Set ADT 
 **/

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;

public class ArraySet<T> implements SetInterface<T>, Serializable {

    // Constant --> To set the array size as 50
    private static final int ARRAY_SIZE = 50;

    // Class attribute
    private T[] array;
    private int numberOfEntries;

    // Constructor
    public ArraySet() {
        this(ARRAY_SIZE);
    }

    public ArraySet(int arraySize) {
        numberOfEntries = 0;
        array = (T[]) new Object[arraySize];
    }

    @Override
    public boolean add(T newElement) {
        // Setp 1: Check array is full or not (if is full then expancd the array size)
        if (isFull()) {
            expandArraySize();
        }

        // Step 2: Check the newElement is one of the array
        if (!isEmpty()) {
            if (contains(newElement)) {
                return false; // Return false(Failed to add) when the new element had exist in the array set
            }
        }

        // Step 3: add new element tp the array set
        array[numberOfEntries] = newElement;
        numberOfEntries++;

        return true; // Successfully to add
    }

    // Remove an element
    public boolean remove(T anElement) {
        // Step 1: Check the element have exist in the array
        if (!contains(anElement)) {
            return false; // Failed to remove because that element do not exist in the array
        }

        // Step 2: Use for loop to ensure that paricular data in the array and remove
        // the array and remove the gap of the array
        for (int i = 0; i < numberOfEntries; i++) {
            if (array[i]==(anElement)) {
                removeGap(i + 1);

                // Step 3: Reduce the number of entries
                numberOfEntries--;

            }
        }
        return true; // Success remove
    }

    // Check the adt is empty or not
    public boolean isEmpty() {
        if (numberOfEntries == 0) {
            return true;
        }
        return false;
    }

    // Check adt is full or not
    public boolean isFull() {
        if (numberOfEntries == array.length) {
            return true;
        }
        return false;
    }

    // Checks if the set contains e and return that element
    public boolean contains(T anElement) {
        for (int i = 0; i < numberOfEntries; i++) {
            if (array[i].equals(anElement)) {
                return true;
            }
        }
        return false;
    }

    // To get the whole set
    public T[] getElement() {
        return array;
    }

    // To get the specific element of the data
    public T getElementAtPos(int indexNo) {
        return array[indexNo];
    }

    //
    public boolean checkSubset(SetInterface<T> anotherSet) {

        boolean isSubset;

        // check every element
        for (T item1 : (T[]) anotherSet.getElement()) {
            isSubset = false;
            for (T item2 : array) {
                if (item1 == item2) {
                    isSubset = true;
                }
            }
            if (isSubset == false) {
                return false;
            }
        }

        return true;
    }

    //
    public void union(SetInterface<T> anotherSet) {
        for (T item : (T[]) anotherSet.getElement()) {
            this.add(item);
        }
    }

    public SetInterface<T> intersection(SetInterface<T> anotherSet) {
        SetInterface<T> intersactionSet = new ArraySet<T>();
        // find the same element
        for (T item1 : (T[]) anotherSet.getElement()) {
            for (T item2 : array) {
                if (item1 == item2)
                    intersactionSet.add(item1);
            }
        }
        return intersactionSet;
    }

    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    private void expandArraySize() {
        int newSize = array.length * 2;
        array = Arrays.copyOf(array, newSize);
    }

    // To move the element 1 place front (Shift the array to left forward)
    private void removeGap(int givenPosition) {
        int removedIndex = givenPosition - 1;
        int lastIndex = numberOfEntries - 1;

        for (int i = removedIndex; i < lastIndex; i++) {
            array[i] = array[i + 1];
        }
    }

    public String toString() {

        String outputStr = "";

        if (array.length < 1) {
            outputStr = "";
        }

        for (int i = 0; i < numberOfEntries; i++) {
            outputStr += array[i] + "\n";
        }

        return outputStr;
    }

    // Iterator override
    @Override
    public Iterator<T> iterator() {
        return new SetIterator();
    }

    // Innner class
    private class SetIterator implements Iterator<T> {

        private int currentIndex;

        public SetIterator() {
            currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < numberOfEntries;
        }

        @Override
        public T next() {
            T result = array[currentIndex];
            currentIndex++;
            return result;
        }
    }
}
