/** NAME: Son Tang
  * ID: A11370127
  * LOGIN: cs12shb
  * 
  * Description: A class that implements the BoundedDeque interface.
  * */
import java.util.*;

public class Stack12<E> implements BoundedStack<E>
{ 
  private Deque12<E> baseArr;
  
  /**
   * Constructs a new Stack12 object that implements BoundedStack.
   * Requires the capacity of the number of objects that the list can hold.
   * Parameter cannot be negative or throws IllegalArgumentException.
   * @param input the number of objects that the list can hold.
   */
  public Stack12(int input) throws IllegalArgumentException
  {
    if(input < 0)
    {
      throw new IllegalArgumentException();
    }
    baseArr = new Deque12<E>(input);
  }
  
  /**
   * Returns the capacity of this BoundedStack, that is,
   * the maximum number of elements it can hold.
   * <br>PRECONDITION: none
   * <br>POSTCONDITION: the BoundedStack is unchanged.
   * @return the capacity of this BoundedStack
   */
  public int capacity()
  {
    return baseArr.capacity();
  }
  
  /**
   * Returns the number of elements in this BoundedStack.
   * <br>PRECONDITION: none
   * <br>POSTCONDITION: the BoundedStack is unchanged.
   * @return the number of elements in this BoundedStack
   */
  public int size()
  {
    return baseArr.size();
  }
  
  /**
   * Adds the specified element to the top of this BoundedStack.
   * Returns true if the operation succeeded, else false.
   * <br>PRECONDITION: the BoundedStack's size is less than its capacity.
   * <br>POSTCONDITION: the element is now the top element in this
   * BoundedStack, none of the other elements have been changed, and
   * the size is increased by 1.
   * @param e the element to add to the stack
   * @return <tt>true</tt> if the element was added, else <tt>false</tt>.
   * @throws NullPointerException if the specified element is null,
   * and size is less than capacity
   */
  public boolean push(E e) throws NullPointerException
  {
    if(baseArr.size() < baseArr.capacity())
    {
      baseArr.addFront(e);
      return true;
    }
    else if(e == null)
    {
      throw new NullPointerException();
    }
    else
    {
      return false;
    }
  }
  
  /**
   * Removes the element at the top of this BoundedStack.
   * Returns the element removed, or <tt>null</tt> if there was no such element.
   * <br>PRECONDITION: the BoundedStack's size is greater than zero.
   * <br>POSTCONDITION: the top element in this BoundedStack has been removed,
   * none of the other elements have been changed, and
   * the size is decreased by 1.
   * @return  the element removed, or <tt>null</tt> if the size was zero.
   */
  public E pop()
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
   * Returns the element at the top of this BoundedStack,
   * or <tt>null</tt> if there was no such element.
   * <br>PRECONDITION: the BoundedStack's size is greater than zero.
   * <br>POSTCONDITION: The BoundedStack is unchanged.
   * @return  the element at the top, or <tt>null</tt> if the size was zero.
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