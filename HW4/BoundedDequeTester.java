/** NAME: Son Tang
  * ID: A11370127
  * LOGIN: cs12shb
  * 
  * Description: Tester for the Deque12 class.
  * */
import junit.framework.*;
import java.util.*;

public class BoundedDequeTester extends TestCase
{
  /** Tests the constructor by initializing it at 0 and a negative number. */
  public void testConstructor()
  {
    try
    {
      Deque12<Integer> myD = new Deque12<Integer>(0);
      Deque12<Integer> myD2 = new Deque12<Integer>(-1);
    }
    catch(IllegalArgumentException ex)
    { }
  }
  
  /** Tests the addBack() method. */
  public void testAddBack()
  {
    Deque12<Integer> myD = new Deque12<Integer>(4);
    myD.addBack(new Integer(1));
    myD.addBack(new Integer(3));
    assertEquals(2, myD.size()); 
  }
  
  
  /** Tests the addBack() and addFront() method with null values. */
  public void testAddNull()
  {   
    try
    {
      Deque12<Integer> myD = new Deque12<Integer>(2);
      myD.addFront(null);
      myD.addBack(null);
    }
    catch(NullPointerException ex)
    {
    }
  }
  
  /** Tests the addFront() method. */
  public void testAddFront()
  {
    Deque12<Integer> myD = new Deque12<Integer>(4);
    myD.addFront(new Integer(1));
    myD.addFront(new Integer(3));
    assertEquals(2, myD.size()); 
  }
  
  
  /** Tests what happens when we add to a full Deque. */
  public void testAddFull()
  {
    Deque12<Integer> myD = new Deque12<Integer>(1);
    myD.addFront(new Integer(1));   
    myD.addFront(new Integer(1));    
    myD.addFront(new Integer(3));
    assertEquals(1, myD.size());
  }
  
  /** Tests the capacity() method. */
  public void testCapacity()
  {
    Deque12<Integer> myD = new Deque12<Integer>(1);
    myD.addFront(new Integer(1)); 
    Deque12<Integer> myD2 = new Deque12<Integer>(3);
    myD2.addFront(new Integer(2)); 
    Deque12<Integer> myD3 = new Deque12<Integer>(0);
    myD3.addFront(new Integer(4)); 
    assertEquals(1, myD.capacity());
    assertEquals(3, myD2.capacity());
    assertEquals(0, myD3.capacity());
  }
  
  /** Tests the peekBack() method. */
  public void testPeekBack()
  {
    Deque12<Integer> myD = new Deque12<Integer>(6);
    myD.addBack(new Integer(7));
    assertEquals(new Integer(7), myD.peekBack());
    myD.addBack(new Integer(3));
    myD.addBack(new Integer(4));
    myD.addFront(new Integer(5));
    assertEquals(new Integer(4), myD.peekBack());
  }
  
  /** Tests the peekFront() method. */
  public void testPeekFront()
  {
    Deque12<Integer> myD = new Deque12<Integer>(6);
    myD.addFront(new Integer(7));
    assertEquals(new Integer(7), myD.peekFront());
    myD.addFront(new Integer(3));
    myD.addFront(new Integer(4));
    myD.addBack(new Integer(5));
    assertEquals(new Integer(4), myD.peekFront());
  }
  
  /** Tests the removeBack method by removing values beyond the number of values added. */
  public void testRemoveBack()
  {
    Deque12<Integer> myD = new Deque12<Integer>(3);
    myD.addFront(new Integer(8));
    myD.addBack(new Integer(6));
    myD.addBack(new Integer(7));
    assertEquals(new Integer(7),myD.removeBack());
    assertEquals(new Integer(6),myD.removeBack());
    assertEquals(new Integer(8),myD.removeBack());
    assertEquals(null,myD.removeBack());
    assertEquals(0,myD.size());
  }
  
  
  /** Tests the removeFront() method. */
  public void testRemoveFront()
  {
    Deque12<Integer> myD = new Deque12<Integer>(3);
    myD.addFront(new Integer(8));
    myD.addBack(new Integer(6));
    myD.addBack(new Integer(7));
    assertEquals(new Integer(8),myD.removeFront());
    assertEquals(new Integer(6),myD.removeFront());
    assertEquals(1,myD.size());
  }
  
  /** Tests the size() method. */
  public void testSize()
  {
    Deque12<Integer> myD = new Deque12<Integer>(3);
    myD.addFront(new Integer(8));
    assertEquals(1, myD.size());
    myD.addFront(new Integer(7));
    myD.addBack(new Integer(7));   
    assertEquals(3, myD.size());
    myD.addBack(new Integer(7));
    assertEquals(3, myD.size());
  }
  
}