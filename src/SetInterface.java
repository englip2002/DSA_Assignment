
/**
 * Author: KONG ZHI LIN
 * Set ADT Specification:
 * Description : To use the Set Algorithm and it is an interface which do not have any implementation part
 * Objective: Set data is unique which means do not appear duplicate data and can be resuable by any implementor
 **/

//Use the concept of iterator to loop through the set 
import java.util.Iterator;

public interface SetInterface<T> extends Iterable<T>{

    // Add new element
    public boolean add(T newElement);

    // Remove an element
    public boolean remove(T anElement);

    // Check the adt is empty or not
    public boolean isEmpty();

    // Check adt is full or not
    public boolean isFull();

    // Checks if the set contains e and return that element
    public boolean contains(T anElement);

    // To get the whole set
    public T[] getElement();

    // To get the specific element of the data
    public T getElementAtPos(int indexNo);

    //
    public boolean checkSubset(SetInterface<T> anotherSet);

    //
    public void union(SetInterface<T> anotherSet);

    public SetInterface<T> intersection(SetInterface<T> anotherSet);

    public int getNumberOfEntries();

    public Iterator<T> iterator();

}