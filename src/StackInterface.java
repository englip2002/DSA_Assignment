/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package defaultPackage;

/**
 *
 * @author User
 * @param <T>
 */
public interface StackInterface<T> {

  public void push(T newEntry);
  public T pop();
  public T peek();
  public boolean isEmpty();
  public void clear();
  public int howMuch();
  public boolean hasNext();
  public T next();
 // end StackInterface
}
