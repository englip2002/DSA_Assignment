/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package Assignment;

import java.util.Iterator;

/**
Author: KONG ZHI LIN
Set ADT Specification:
Description : To store the values of the menu in set collection
Objective: To build a menu for user viewing (add, remove, modify)
 **/

public interface SetInterface<T> {
    
    //Add new element into the menu set
    public boolean add(T newElement);

    //Remove an element from the menu set
    public boolean remove(T anElement);
    
    //Check the menu set is empty or not 
    boolean isEmpty();
    
    //Check the menu is full or not
    boolean isFull();
    
    //To get the array from the menu set
    public T[] getElement();
    
    
    public T getElementAtPos(int indexNo);

    //Checks if the set contains e and return that elements (search) 
    //public T[] contains(T anElement);

    //Recommended drink and food under its beverage categories 
    public boolean checkSubset(SetInterface anotherSet);
    
    //
    public void union(SetInterface anotherSet);

    public SetInterface intersection(SetInterface anotherSet);
    
    public int getNumberOfEntries();

    public Iterator<T> iterator();

    //difference(S, T)  // Returns the difference of set S and set T
    //public 
    
}