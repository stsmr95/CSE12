/**
 * NAME: Son Tang <br>
 * ID: A11370127 <br>
 * LOGIN: cs12shb <br>
 * 
 * Title: class MyLinkedList
 * Description: My implementation of the LinkedList class.
 * 
 * @version 1.0
 * @author Son Tang
 * */
import java.util.*;
public class MyLinkedList<E> extends AbstractList<E> {

 private int nelems;
 private Node head;
 private Node tail;

 protected class Node {
  E data;
  Node next;
  Node prev;

  /** Constructor to create singleton Node. */
  public Node(E element)
  {
    data = element;
    this.next = tail;
    this.prev = head;
  }
  /** Constructor to create singleton link it between previous and next.
   *   @param element Element to add, can be null.
   *   @param prevNode predecessor Node, can be null.
   *   @param nextNode successor Node, can be null.
   */
   public Node(E element, Node prevNode, Node nextNode)
  {
     data = element;
     this.next = nextNode;
     this.prev = prevNode;
     if(nextNode != null)
     {
       nextNode.prev = this;
     }
     if(prevNode != null)
     {
       prevNode.next = this;
     }
  }
  /** Remove this node from the link. Update previous and next nodes. */
  public void remove()
  {
    this.prev.next = this.next;
    this.next.prev = this.prev;
    this.next = null;
    this.prev = null;
  }
  /** Set the previous node in the list.
   *  @param p new previous node.
   */
  public void setPrev(Node p)
  {
    p.next = this.prev.next;
    p.prev = this.prev;
    this.prev.next = p;
    this.prev = p;
  }
  /** Set the next node in the list.
   *  @param n new next node.
   */
  public void setNext(Node n)
  {
    n.prev = this.next.prev;
    n.next = this.next;
    this.next.prev = n;
    this.next = n;
  }
     
  /** Set the element.
   *  @param e new element.
   */
  public void setElement(E e)
  {
    try
    {
    this.data = e;
    }
    catch(NullPointerException ex)
    {
      System.out.println("Cannot add null element.");
    }
  }
  /** Accessor to get the next Node in the list.
    * @return Node the next Node in the list.
    */
  public Node getNext()
  {
    return this.next;
  }
  /** Accessor to get the prev Node in the list.
    * @return Node the previous Node in the list.
    */
  public Node getPrev()
  {
    return this.prev;
  } 
  /** Accessor to get the Nodes Element.
    * @return E the element of the node.
    */
  public E getElement()
  {
    return this.data;
  } 
 }

    /** ListIterator implementation */ 
 protected class MyListIterator implements ListIterator<E> {

        private boolean forward;
        private boolean canRemove;
        private Node left,right; // Cursor sits between these two nodes
        private int idx;        // Tracks current position. what next() would
                                // return 
        public MyListIterator()
        {
          forward = true;
          canRemove = false;
          left = head;
          right = head.next;
          idx = 0;
        }
        
        /**
         * Insert the given item into the list immediately before 
         * whatever would have been returned by a call to next().
         * @param e the element of the object to be added.
         * @throws NullPointerException
         * */
        @Override
        public void add(E e) throws  NullPointerException
        {
          if(e == null)
          {
            throw new NullPointerException();
          }
          new Node(e, left, right);
          idx += 1;
          previous();
          canRemove = false;
        }
        /**
         * Return true if there are more elements when going
         * in the forward direction.
         * @return boolean true = next Node exists. 
         * false = next Node does not exist.
         */
        @Override
        public boolean hasNext()
        {
          Node testNode = getNth(idx);
          if(testNode.getElement() == null)
          {
            return false;
          }
          else
          {
            return true;
          }
        }
         /**
         * Return true if there are more elements when going 
         * in the reverse direction.
         * @return boolean true = previous Node exists. 
         * false = previous Node does not exist.
         */
        @Override
        public boolean hasPrevious()
        {
          if(left == null)
          {
            return false;
          }
          else
          {
            return true;
          }
        }
       /**
         * Return the next element in the list when going forward.
         * @return E the element of the next Node.
         * @throws NoSuchElementException
         */
        @Override
        public E next() throws NoSuchElementException
        {
          if(hasNext() == false)
          {
            throw new NoSuchElementException();
          }
          idx = idx + 1;
          Node theNode = getNth(idx -1);
          left = right;
          right = right.next;
          canRemove = true;
          forward = true;
          return theNode.getElement(); 
        }
        
        /**
         * Return the index of the element that would be returned 
         * by a call to next().
         * @return int the next index.
         */
        @Override
        public int nextIndex()
        {
          return idx+1; 
        }
       /**
         * Return the next element in the list when going backwards.
         * @return E the element of the previous Node.
         * @throws NoSuchElementException
         */
        @Override
        public E previous() throws NoSuchElementException
        { 
          if(hasPrevious() == false)
          {
            throw new NoSuchElementException();
          }
          idx = idx - 1;
          Node theNode = getNth(idx);
          left = left.prev;
          right = left;
          canRemove = false;
          forward = false;
          return theNode.getElement(); 
        }
       /**
         * Return the index of the element that would be returned 
         * by a call to previous().
         * @return int the previous index.
         */
        @Override
        public int previousIndex()
        {
          return idx-1;
        }
        /**
         * Remove the last element returned by the most 
         * recent call to either next/previous.
         * @throws IllegalStateException
         */
        @Override
        public void remove() throws IllegalStateException
        {
          if(canRemove == true)
          {
            if(forward = true)
            {
              Node theNode = getNth(idx-1);
              theNode.remove();
              idx = idx-1;
              canRemove = false;
            }
            else
            {
              Node theNode = getNth(idx);
              theNode.remove();
              canRemove = false;
            }
          }
          else
          {
            throw new IllegalStateException();
          }
        }
        
        /** 
         * Change the value in the node returned by the most 
         * recent next/previous with the new value.
         * @throws NullPointerException
         */
        @Override
        public void set(E e) throws NullPointerException
        {
          if(canRemove = true)
          {
            if(forward = true)
            {
              Node theNode = getNth(idx - 1);
              theNode.data = e;
            }
            else
            {
              Node theNode = getNth(idx);
              theNode.data = e;
            }
          }
          else
          {
            throw new IllegalArgumentException();
          }
        }
        
 }
 
 
 //  Implementation of the MyLinkedList Class
 
 
 /** Only 0-argument constructor is define */
 public MyLinkedList()
 {
   head = new Node(null, null, tail);
   tail = new Node(null, head, null);
 }
 
 /**
  * Return the number of elements being stored.
  * @return int the size of the list.
  */
 @Override
 public int size()
 {
   int numLinks = 0;
   Node currNode = this.head.next; 
   while(currNode.next != null)
   {
     numLinks++;
     currNode = currNode.next;
   }
   return numLinks;
 }
 
 /**
  * Get contents at position i.
  * @param index the position to get contents.
  * @return E the element of the Node at position i.
  */
 @Override
 public E get(int index) throws IndexOutOfBoundsException
 {
   if(index > size() - 1  || index < 0)
   {
     throw new IndexOutOfBoundsException();
   }
   Node currNode = getNth(index);
   return currNode.getElement(); 
 }
 
 @Override
 /** Add an element to the list.
   * @param index  where in the list to add.
   * @param data data to add.
   * @throws IndexOutOfBoundsException.
   * @throws NullPointerException.
   */ 
   public void add(int index, E data) 
   throws IndexOutOfBoundsException,NullPointerException
 {
   if(index > size() || index < 0)
   {
     throw new IndexOutOfBoundsException();
   }
   Node currNode = getNth(index);
   if(data == null)
   {
     throw new NullPointerException();
   }
   new Node(data, currNode.prev, currNode);
 }
 /** Add an element to the end of the list .
   * @param data data to add.
   * @throws NullPointerException
   */ 
 public boolean add(E data) throws NullPointerException
 {
   if(data == null)
   {
     throw new NullPointerException();
   }
   add(size(), data);
   return true; 
 }
 
 /** Set the element at an index in the list.
   * @param index  where in the list to add.
   * @param data data to add.
   * @return element data added.
   * @throws IndexOutOfBoundsException
   * @throws NullPointerException
   */ 
 public E set(int index, E data) 
   throws IndexOutOfBoundsException,NullPointerException
 {
   if(index > size() || index < 0)
   {
     throw new IndexOutOfBoundsException();
   }
   Node currNode = getNth(index);
   if(data == null)
   {
     throw new NullPointerException();
   }
   currNode.data = data;
   return currNode.data; 
 }
 
 /** Remove the element at an index in the list 
   * @param index  where in the list to add
   * @return element the data found
   * @throws IndexOutOfBoundsException
   */ 
 public E remove(int index) throws IndexOutOfBoundsException
 {
   if(index < 0 || index > size() - 1)
   {
     throw new IndexOutOfBoundsException();
   }
   Node currNode = getNth(index);
   E retData = currNode.data;
   currNode.remove();
   return retData; 
 }
 
 /** Clear the linked list */
 public void clear()
 {
   Node currNode = this.head;
   while(currNode.next != this.tail)
   {
     currNode.next.remove();
   }
 }
 
 /** Determine if the list empty 
   *  @return true if empty, false otherwise */
 public boolean isEmpty()
 {
   Node currNode = this.head;
   if(currNode.next == this.tail)
   {
     return true;
   }
   else
   {
     return false;
   }
 }
 
 public Iterator<E> QQQiterator()
 {
   return new MyListIterator();
 }
 public ListIterator<E> QQQlistIterator()
 {
   return new MyListIterator();
 }
 
 // Helper method to get the Node at the Nth index
 /**
  * A method that returns the Node at a specified index.
  * @param index the index of the Node to return.
  * @Node the Node at the specified index.
  */
 private Node getNth(int index) 
 {  
   int currIndex = 0;
   Node currNode = this.head.next;
   while(currIndex < index)
   {
     currNode = currNode.getNext();
     currIndex++;
   }
   return currNode;
 }
 
//  UNCOMMENT the following when you believe your MyListIterator class is
// functioning correctly
 public Iterator<E> iterator()
 {
   return new MyListIterator();
 }
 public ListIterator<E> listIterator()
 {
   return new MyListIterator();
 }
 
}

// VIM: set the tabstop and shiftwidth to 4 
// vim:tw=78:ts=4:et:sw=4

