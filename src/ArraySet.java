/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package Assignment;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Author: KONG ZHI LIN
 * Implementer class for ADT MenuInterface
 **/

public class ArraySet<T> implements SetInterface<T>, Serializable {

    // Constant
    // To set the array size as 50
    private static final int arraySize = 50;

    // Class attribute
    private T[] array;
    private int numberOfEntries;

    // Constructor
    public ArraySet() {
        numberOfEntries = 0;
        array = (T[]) new Object[arraySize];
    }

    @Override
    public boolean add(T newElement) {
        // Step 1: check if array is full or not
        // is full, class increaseArraySize();
        if (isFull()) {
            expandArraySize();
        }

        // Setp 2: check if newElement is part of array
        if(contains(newElement)){
                return false; // if same means already exist in set (cannot add again)
        }

        // Step 3: add Element to array
        array[numberOfEntries] = newElement;
        numberOfEntries++;

        return true;
    }

    @Override
    public boolean contains(T item) {

        for (T each : array)
            if(each.equals(item))
                return true;

        return false;
    }

    @Override
    public boolean remove(T anElement) {
        for (int i = 0; i < numberOfEntries; i++) {
            if (array[i] == anElement) { // check if element exists
                removeGap(i + 1);
                // remove last entires
                numberOfEntries--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkSubset(SetInterface anotherSet) {
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

    @Override
    public void union(SetInterface anotherSet) {
        // no need validation since validation done in add()
        for (T item : (T[]) anotherSet.getElement()) {
            this.add(item);
        }
    }

    @Override
    public SetInterface intersection(SetInterface anotherSet) {
        SetInterface intersactionSet = new ArraySet<T>();
        // find the same element
        for (T item1 : (T[]) anotherSet.getElement()) {
            for (T item2 : array) {
                if (item1 == item2)
                    intersactionSet.add(item1);
            }
        }
        return intersactionSet;
    }

    @Override
    public boolean isEmpty() {
        if (numberOfEntries == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isFull() {
        if (numberOfEntries == array.length) {
            return true;
        }
        return false;
    }

    @Override
    public T[] getElement() {
        return array;
    }
    
    @Override
    public T getElementAtPos(int indexNo) {
        return array[indexNo];
    }

    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    private void expandArraySize() {

        T[] tempArray = array;
        array = (T[]) new Object[array.length * 2];
        for (int i = 0; i < tempArray.length; i++)
            array[i] = tempArray[i];
    }

    // To move the element 1 place front
    private void removeGap(int givenPosition) {

        int removedIndex = givenPosition - 1;
        int lastIndex = numberOfEntries - 1;

        for (int index = removedIndex; index < lastIndex; index++) {
            array[index] = array[index + 1];
        }
    }

    
    @Override
    public Iterator<T> iterator() {
        return new SetIterator();
    }

    //inner class
    private class SetIterator implements Iterator<T>{

        int currentIndex;

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
