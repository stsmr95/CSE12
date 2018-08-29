/** NAME: Son Tang
  * ID: A11370127
  * LOGIN: cs12shb
  * 
  * Description: A class that implements the BoundedQueue interface.
  * */
import java.util.*;

public class Queue12<E> implements BoundedQueue<E>
{
  private Deque12<E> baseArr;
  
  /**
   * Constructs a new Queue12 object that implements BoundedQueue.
   * Requires the capacity of the number of objects that the list can hold.
   * Parameter cannot be negative or throws IllegalArgumentException.
   * @param input the number of objects that the list can hold.
   */
  public Queue12(int input) throws IllegalArgumentException
  {
    if(input < 0)
    {
      throw new IllegalArgumentException();
    }
    baseArr = new Deque12<E>(input);
  }
  
  /**
   * Returns the capacity of this BoundedQueue, that is,
   * the maximum number of elements it can hold.
   * <br>PRECONDITION: none
   * <br>POSTCONDITION: the BoundedQueue is unchanged.
   * @return the capacity of this BoundedQueue
   */
  public int capacity()
  {
    return baseArr.capacity();
  }

  /**
   * Returns the number of elements in this BoundedQueue.
   * <br>PRECONDITION: none
   * <br>POSTCONDITION: the BoundedQueue is unchanged.
   * @return the number of elements in this BoundedQueue
   */
  public int size()
  {
    return baseArr.size();
  }

  /**
   * Adds the specified element to the tail of this BoundedQueue.
   * Returns true if the operation succeeded, else false.
   * <br>PRECONDITION: the BoundedQueue's size is less than its capacity.
   * <br>POSTCONDITION: the element is now the tail element in this
   * BoundedQueue, none of the other elements have been changed, and
   * the size is increased by 1.
   * @param e the element to add to the queue
   * @return <tt>true</tt> if the element was added, else <tt>false</tt>.
   * @throws NullPointerException if the specified element is null,
   * and size is less than capacity
   */
  public boolean enqueue(E e) throws NullPointerException
  {
    if(e == null)
    {
      throw new NullPointerException();
    }
    else if (baseArr.size() < baseArr.capacity())
    {
      baseArr.addBack(e);
      return true;
    }
    else
    {
      return false;
    }
  }

  /**
   * Removes the element at the head of this BoundedQueue.
   * Returns the element removed, or <tt>null</tt> if there was no such element.
   * <br>PRECONDITION: the BoundedQueue's size is greater than zero.
   * <br>POSTCONDITION: the head element in this BoundedQueue has been removed,
   * none of the other elements have been changed, and
   * the size is decreased by 1.
   * @return  the element removed, or <tt>null</tt> if the size was zero.
   */
  public E dequeue()
  {
    if(baseArr.size() > 0)
    {
      return baseArr.removeFront();
    }
    else
    {
      return null;
    }
  }

  /**
   * Returns the element at the head of this BoundedQueue,
   * or <tt>null</tt> if there was no such element.
   * <br>PRECONDITION: the BoundedQueue's size is greater than zero.
   * <br>POSTCONDITION: The BoundedQueue is unchanged.
   * @return  the element at the head, or <tt>null</tt> if the size was zero.
   */
  public E peek()
  {
    if(baseArr.size() > 0)
    {
      return baseArr.peekFront();
    }
    else
    {
      return null;
    }
  }
  }
