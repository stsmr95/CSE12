/**
 * NAME: Son Tang
 * ID: A11370127
 * LOGIN: cs12shb
 */
import junit.framework.* ;
import java.util.LinkedList;
import java.util.ListIterator;
/**
 *  Title: class MyLinkedListTester
 *  Description: JUnit test class for MyLinkedList class
 *  @author Son Tang
 *  @version 3.0 April 15, 2014
 */
public class MyLinkedListTester extends TestCase
{
  private MyLinkedList<Integer> empty ;
  private MyLinkedList<Integer> one ;
  private MyLinkedList<Integer> several ;
  private MyLinkedList<String>  slist ;
  static final int DIM = 5;
  static final int FIBMAX = 30;
  
  public MyLinkedListTester()
  {
    super() ;
  }
  /**
   * Standard Test Fixture. An empty list, a list with one entry (0) and 
   * a list with several entries (0,1,2)
   */ 
  public void setUp()
  {
    empty = new MyLinkedList<Integer>();
    one = new MyLinkedList<Integer>();
    one.add(0,new Integer(0));
    several = new MyLinkedList<Integer>() ;
    // List: 1,2,3,...,Dim
    for (int i = DIM; i > 0; i--)
      several.add(0,new Integer(i));
    
    // List: "First","Last"
    slist = new MyLinkedList<String>();
    slist.add(0,"First");
    slist.add(1,"Last");
  }
  /** Test if heads of the lists are correct */
  public void testGetHead()
  {
    assertEquals("Check 0",new Integer(0),one.get(0)) ;
    assertEquals("Check 0",new Integer(1),several.get(0)) ;
    
    assertEquals("Check DIM", new Integer(5), several.get(4)); //Should return 5. (within bounds)
  }
  
  /** Test if size of lists are correct */
  public void testListSize()
  {
    assertEquals("Check Empty Size",0,empty.size()) ;
    assertEquals("Check One Size",1,one.size()) ;
    assertEquals("Check Several Size",DIM,several.size()) ;
  }
  
  /** Test setting a specific entry */
  public void testSet()
  {
    slist.set(1,"Final");
    assertEquals("Setting specific value", "Final",slist.get(1));
  }
  
  /** Test isEmpty */
  public void testEmpty()
  {
    assertTrue("empty is empty",empty.isEmpty()) ;
    assertTrue("one is not empty",!one.isEmpty()) ;
    assertTrue("several is not empty",!several.isEmpty()) ;
  }
  
  /** Test iterator on empty list and several list */
  public void testIterator()
  {
    int counter = 0 ;
    ListIterator<Integer> iter;
    for (iter = empty.listIterator() ; iter.hasNext(); )
    {
      fail("Iterating empty list and found element") ;
    }
    counter = 0 ;
    for (iter = several.listIterator() ; iter.hasNext(); iter.next())
      counter++;
    assertEquals("Iterator several count", counter, DIM);
  }
  
  /** Test out of bounds exception on get */
  public void testGetException()
  {
    try 
    {
      empty.get(0);
      fail("Should have generated an exception");
      
      several.get(DIM+1);
      fail("Should have generated an exception");
      
      several.get(-1);
      fail("Should have generated an exception");
    }
    catch(IndexOutOfBoundsException e)
    {
      //  normal
    }
  }
  
  /** test Iterator Fibonacci */
  public void testIteratorFibonacci()
  {
    
    MyLinkedList<Integer> fib  = new MyLinkedList<Integer>();
    ListIterator<Integer> iter;
    // List: 0 1 1 2 3 5 8 13 ... 
    // Build the list with integers 1 .. FIBMAX
    int t, p = 0, q = 1;
    fib.add(0,p);
    fib.add(1,q);
    for (int k = 2; k <= FIBMAX; k++)
    {
      t = p+q;
      fib.add(k,t);
      p = q; q = t; 
    }
    // Now iterate through the list to near the middle, read the
    // previous two entries and verify the sum.
    iter = fib.listIterator();
    int sum = 0;
    for (int j = 1; j < FIBMAX/2; j++)
      sum = iter.next();
    iter.previous();
    assertEquals(iter.previous()+iter.previous(),sum);
    // Go forward with the list iterator
    assertEquals(iter.next() + iter.next(),sum);
  }
  
  /** Tests if the hasNext() and hasPrevious() methods are corect. */
  public void testIterHasMethods()
  {
    MyLinkedList<Integer> list  = new MyLinkedList<Integer>();
    ListIterator<Integer> iter = list.listIterator();
    assertEquals(false, iter.hasNext());
    list.add(new Integer(1));
    assertEquals(true,iter.hasNext());
    assertEquals(true,iter.hasPrevious());
    iter.next();
    assertEquals(false, iter.hasNext());
  }
  
  /** Test if the get methods are correct if the index is greater than the bounds. */ 
  public void testGetGreaterThanBounds()
  {
    try
    {
      assertEquals("Check 1 (OutOfBounds)", null, one.get(1)); //Should get IndexOutOfBoundsException
      assertEquals("Check DIM + 1 (OutOfBounds)", null, several.get(DIM + 1)); //Should get IndexOutOfBoundsException
    }
    catch(IndexOutOfBoundsException ex)
    {
    }
  }
  
  /** Test if the get methods are correct if the index is less than the bounds.*/ 
  public void testGetLessThanBounds()
  {
    try
    {
      assertEquals("Check -1 (OutOfBounds)", null, one.get(-1)); //Should get IndexOutOfBoundsException (below bounds)
      assertEquals("Check -1 (OutOfBounds)", null, several.get(-1)); //Should get IndexOutOfBoundsException (below bounds)
    }
    catch(IndexOutOfBoundsException ex)
    {
    }
    
  }
  /** Tests for the NullPointerExceptionsin the MyListIterator class. */
  public void testIterNullPointers()
  {
    try
    {
      MyLinkedList<Integer> list  = new MyLinkedList<Integer>();
      ListIterator<Integer> iter = list.listIterator();
      iter.add(new Integer(5));
      iter.next();
      iter.add(new Integer(null)); //Should return NullPointerException
    }
    catch(NullPointerException ex)
    {
    }
  }
  
  /** Tests thee add method for the MyListIterator class. */
  public void testIterAdd()
  {
    ListIterator<Integer> iter = one.listIterator();
    iter.add(new Integer(5));
    assertEquals(2, one.size());
  }
  
  /** Tests for the remove() method. */
  public void testRemove()
  {
    one.remove(0);
    assertEquals(0,one.size());
    several.remove(0);
    assertEquals(DIM - 1, several.size());
  }
  
  /** Tests for when remove() fails. */
  public void testRemoveFail()
  {
    try
    {
      empty.remove(0);
      several.remove(DIM + 1);
    }
    catch(IndexOutOfBoundsException ex)
    {
    }
  }
  
  /** Tests for the add() method. */
  public void testAdd()
  {
    empty.add(0);
    assertEquals(1,empty.size());
    several.add(0);
    assertEquals(DIM +1, several.size());
  }
  
  /** Tests for when add() fails. */
  public void testAddFail()
  {
    try
    {
      empty.add(1, new Integer(1));
      several.add(DIM + 1, new Integer(10));
    }
    catch(IndexOutOfBoundsException ex)
    {
    }
  }
  
  /** Tests for when null is passed into add(). */
  public void testAddNull()
  {
    try
    {
      one.add(0, null);
      empty.add(null);
    }
    catch(NullPointerException ex)
    {
    }
  }
  
}
