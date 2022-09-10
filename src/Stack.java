/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.Serializable;
import java.util.Iterator;
/**
 *
 * @author Foo Seanron
 * @param <T>
 */


public class Stack<T> implements StackInterface<T> ,Iterator<T>, Serializable{
    //attrib
    private Node top;
    private Node iterate;
    //constructors
    public Stack(){
        top = null;
        iterate = null;
    }
    
   
    
    @Override
    public void push(T newEntry) {
        if (isEmpty()) {
            Node newNode = new Node(newEntry);
            top = newNode;
            iterate = newNode;
        }
        else{
            Node newNode = new Node(newEntry, top);
            top = newNode;
            iterate = newNode;
        }
    }

    @Override
    public T pop() {
        T temp;
        if (isEmpty()) {
            return (T)"Nothing To Pop Here. ps:try pushing";
        }
        else{
            if (top.next == null) {
                temp = top.data;
                top = null;
                return temp;
            }
            else{
            
            temp = top.data;
            top = top.next;
            iterate = top.next;
            return temp;
        }}
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            return (T)"Nothing To See Here. ps:try pushing";
        }
        else{
            return top.data;
        }
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }
    @Override
    public void clear() {
        top = null;
        iterate = null;
    }
    @Override
    public boolean hasNext() {
        if (iterate == null) {
            iterate = top;
            return false;
        }
       return iterate != null;
    }
    @Override
    public T next() {
        T next;
        next = iterate.data;
        iterate = iterate.next;
        return next;
    }

    @Override
    public int howMuch() {
        int i = 0;
        iterate = top;
        while(hasNext()){
            next();
            i++;
        }
        
        return i;
    }
    
    //innner class
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
 
}
