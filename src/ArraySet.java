/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment;

/**
Author: KONG ZHI LIN
Implementer class for ADT MenuInterface 
 **/

public class ArraySet<T> implements SetInterface<T>{
    
    //Constant 
    //To set the array size as 50 
    private static final int arraySize = 50;
    
    //Class attribute
    private T[] menuArray;
    private int numberOfEntries;
    
    //Constructor 
    public ArraySet(){
        numberOfEntries = 0;
        menuArray = (T[]) new Object[arraySize];
    }
    
    @Override
    public boolean add(T newElement){
        //Step 1: check if array is full or not
        //is full, class increaseArraySize();
        if(isFull()){
            expandArraySize();
        }
        
        //Setp 2: check if newElement is part of array 
        for(T dish: menuArray){
            if (dish == newElement)
                return false; //if same means already exist in set (cannot add again)          
        }
        
        //Step 3: add Element to array 
        menuArray[numberOfEntries] = newElement;
        numberOfEntries++;
        
         return true;
    }
    
    @Override
     public boolean remove(T anElement) {
        for (int i = 0; i < numberOfEntries; i++) {
            if (menuArray[i] == anElement) { //check if element exists
                removeGap(i+1); 
                //remove last entires
                numberOfEntries--;
                return true;
            }
        }
        return false;
    }
     
    @Override
    public boolean checkSubset(SetInterface anotherSet) {
        boolean isSubset;

        //check every element
        for (T item1 : (T[]) anotherSet.getElement()) {
            isSubset = false;
            for (T item2 : menuArray) {
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
        //no need validation since validation done in add()
        for (T item : (T[]) anotherSet.getElement()) {
            this.add(item);
        }
    }

    @Override
    public SetInterface intersection(SetInterface anotherSet) {
        SetInterface intersactionSet = new ArraySet<T>();
        //find the same element
        for (T item1 : (T[]) anotherSet.getElement()) {
            for (T item2 : menuArray) {
                if (item1 == item2) 
                    intersactionSet.add(item1);
            }
        }
        return intersactionSet;
    }
    
    @Override
    public boolean isEmpty(){
        if(numberOfEntries==0){
            return true;
        }
        return false;
    }
    
    @Override
    public boolean isFull(){
        if(numberOfEntries == menuArray.length){
            return true;
        }
        return false;
    }
    
    @Override
    public String toString(){
        String out = "";
        
        for(int idx = 0; idx<numberOfEntries; idx++){
            out += menuArray[idx] + " ";
            
        }
        out += "\n";
        return out;
    }
    
    @Override
    public T[] getElement() {
        return menuArray;
    }
    
    public int getNumberOfEntries(){
        return numberOfEntries;
    }
            
    
    private void expandArraySize(){
        
        T[] tempArray = menuArray;
        menuArray = (T[]) new Object[menuArray.length * 2];
        for(int i=0; i<tempArray.length; i++)
            menuArray[i] = tempArray[i];
    }
    
    //To move the element 1 place front 
    private void removeGap(int givenPosition) {
        
        int removedIndex = givenPosition - 1;
        int lastIndex = numberOfEntries - 1;

        for (int index = removedIndex; index < lastIndex; index++) {
          menuArray[index] = menuArray[index + 1];
        }
    }
    
}
