/** NAME: Son Tang
  * ID: A11370127
  * LOGIN: cs12shb
  * 
  * Description: A class that implements the BoundedDeque interface.
  * */
import java.util.*;

public class Deque12<E> implements BoundedDeque<E>
{ 
  private int capacity;
  private int dSize;
  private ArrayList<E> dArray;
  private int head;
  private int tail;
  
  /**
   * Constructs a new Deque12 object that implements BoundedDeque.
   * Requires the capacity of the number of objects that the list can hold.
   * Parameter cannot be negative or throws IllegalArgumentException.
   * @param cap the number of objects that the list can hold.
   */
  public Deque12(int cap) throws IllegalArgumentException
  {
    if(cap < 0)
    {
      throw new IllegalArgumentException();
    }
    capacity = cap;
    dSize = 0;
    dArray = new ArrayList<E>(cap);
    for(int i = 0; i <= cap; i++)
    {
      dArray.add(i,null);
    }
    head = 0;
    tail = cap;
  }
  
  /**
   * Returns the capacity of this BoundedDeque, that is,
   * the maximum number of elements it can hold.  
   * <br>PRECONDITION: none 
   * <br>POSTCONDITION: the BoundedDeque is unchanged.  
   * @return the capacity of this BoundedDeque
   */
  public int capacity()
  {
    return capacity;
  }
  
  /**
   * Returns the number of elements in this BoundedDeque. 
   * <br>PRECONDITION: none 
   * <br>POSTCONDITION: the BoundedDeque is unchanged. 
   * @return the number of elements in this BoundedDeque
   */
  public int size()
  {
    return dSize;
  }
  
  /**
   * Adds the specified element to the front of this BoundedDeque.
   * Returns true if the operation succeeded, else false. 
   * <br>PRECONDITION: the BoundedDeque's size is less than its capacity. 
   * <br>POSTCONDITION: the element is now the front element in this 
   * BoundedDeque, none of the other elements have been changed, and
   * the size is increased by 1. 
   * @param e the element to add to the front of the list
   * @return <tt>true</tt> if the element was added, else <tt>false</tt>.
   * @throws NullPointerException if the specified element is null,
   * and size is less than capacity
   */
  public boolean addFront(E e) throws NullPointerException
  {
    if(e == null)
    {
      throw new NullPointerException();
    }
    if(dSize < capacity)
    {
      dArray.set(head,e);
      head++;
      if(head > capacity)
      {
        head = 0;
      }
      dSize++;
      return true;
    }
    return false;
  }
  
  /**
   * Adds the specified element to the back of this BoundedDeque.
   * Returns true if the operation succeeded, else false. 
   * <br>PRECONDITION: the BoundedDeque's size is less than its capacity. 
   * <br>POSTCONDITION: the element is now the back element in this 
   * BoundedDeque, none of the other elements have been changed, and
   * the size is increased by 1. 
   * @param e the element to add to the back of the list
   * @return <tt>true</tt> if the element was added, else <tt>false</tt>.
   * @throws NullPointerException if the specified element is null,
   * and size is less than capacity
   */
  public boolean addBack(E e) throws NullPointerException
  {
    if(e == null)
    {
      throw new NullPointerException();
    }
    if(dSize < capacity)
    {
      dArray.set(tail,e);
      tail--;
      if(tail < 0)
      {
        tail = capacity;
      }
      dSize++;
      return true;
    }
    return false;
  }
  
  /**
   * Removes the element at the front of this BoundedDeque.
   * Returns the element removed, or <tt>null</tt> if there was no such element.
   * <br>PRECONDITION: the BoundedDeque's size is greater than zero.
   * <br>POSTCONDITION: the front element in this BoundedDeque has been removed,
   * none of the other elements have been changed, and
   * the size is decreased by 1.
   * @return  the element removed, or <tt>null</tt> if the size was zero.
   */
  public E removeFront()
  {
    E retData = null;
    if(size() > 0)
    {
      head = head - 1;
      if(head < 0)
      {
        head = capacity;
      }
      retData = dArray.get(head);
      dArray.set(head, null);
      dSize--;
    }
    return retData;
  }
  
  /**
   * Removes the element at the back of this BoundedDeque.
   * Returns the element removed, or <tt>null</tt> if there was no such element.
   * <br>PRECONDITION: the BoundedDeque's size is greater than zero.
   * <br>POSTCONDITION: the back element in this BoundedDeque has been removed,
   * none of the other elements have been changed, and
   * the size is decreased by 1.
   * @return  the element removed, or <tt>null</tt> if the size was zero.
   */
  public E removeBack()
  {
    E retData = null;
    if(size() > 0)
    {
      tail = tail + 1;
      if(tail > capacity)
      {
        tail = 0;
      }
      retData = dArray.get(tail);
      dArray.set(tail, null);
      dSize--;
    }
    return retData;
  }
  
  /**
   * Returns the element at the front of this BoundedDeque,
   * or <tt>null</tt> if there was no such element.
   * <br>PRECONDITION: the BoundedDeque's size is greater than zero.
   * <br>POSTCONDITION: The BoundedDeque is unchanged.
   * @return  the element at the front, or <tt>null</tt> if the size was zero.
   */
  public E peekFront()
  {
    if(size() == 0)
    {
      return null;
    }
    int retValue = head - 1;
    if(retValue < 0)
    {
      retValue = capacity;
    }
    return dArray.get(retValue);
  }
  
  /**
   * Returns the element at the back of this BoundedDeque,
   * or <tt>null</tt> if there was no such element.
   * <br>PRECONDITION: the BoundedDeque's size is greater than zero.
   * <br>POSTCONDITION: The BoundedDeque is unchanged.
   * @return  the element at the back, or <tt>null</tt> if the size was zero.
   */
  public E peekBack()
  {
    if(size() == 0)
    {
      return null;
    }
    int retValue = tail + 1;
    if(retValue > capacity)
    {
      retValue = 0;
    }
    return dArray.get(retValue);
  }
}