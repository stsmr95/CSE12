/**
 * CSE 12 Homework 7
 * Son Tang
 * A11370127
 * Section [B00]
 * May 20, 2014
 * 
 * TQTree.java
 * A Java class that supports a Binary Tree that plays the game of twenty questions
 * 
 * @author Christine Alvarado
 * @version 2.0
 * @date May 11, 2014
 */

import java.io.*;
import java.util.Scanner;
import java.text.ParseException;
import java.util.LinkedList;

public class TQTree {
  private TQNode root;
  
  /** Inner class that supports a node for a twenty questions tree.
    * You should not need to change this class. */
  class TQNode {
    /*  You SHOULD NOT add any instance variables to this class */
    TQNode yesChild;  // The node's right child
    TQNode noChild;   // The node's left child
    String data;      // A question (for non-leaf nodes) 
                      // or an item (for leaf nodes)
    
    int idx;        // index used for printing
     
    /** Make a new TWNode 
      * @param data The question or answer to store in the node. 
      */
    public TQNode( String data )
    {
      this.data = data;
    }    
    
    /** Setter for the noChild 
      * @param noChild The new left (no) child
      */
    public void setNoChild( TQNode noChild )
    {
      this.noChild = noChild;
    }
    
    
    /** Setter for the yesChild 
      * @param yesChild The new right (yes) child
      */
    public void setYesChild( TQNode yesChild )
    {
      this.yesChild = yesChild;
    }
    
        
    /** Getter for the yesChild 
      * @return The node's yes (right) child
      */
    public TQNode getYesChild()
    {
      return this.yesChild;
    }

    /** Getter for the noChild 
      * @return The node's no (left) child
      */
    public TQNode getNoChild()
    {
      return this.noChild;
    }
    
    /** Getter for the data
      * @return The data stored in this node
      */
    public String getData()
    {
      return this.data;
    }
    
      /** Setter for the data
      * @param dat data to be set.
      */
    public void setData(String dat)
    {
      this.data = dat;
    }
    
    /** Setter for the index
      * @param idx index of this for printing 
      */
    public void setIndex(int idx) {
      this.idx = idx;
    }
    
    /** get the index
      * @return idx index of this for printing 
      */
    public int getIndex() {
      return this.idx;
    }
  }  // End TQNode

  
  /** Builds a starter TQ tree with 1 question and 2 answers */
  public TQTree()
  {
    root = new TQNode("Is it bigger than a breadbox?");
    root.setIndex(0);
    TQNode noChild = new TQNode("spam");
    noChild.setIndex(1);
    TQNode yesChild = new TQNode("a computer scientist");
    yesChild.setIndex(2);
    root.setNoChild(noChild);
    root.setYesChild(yesChild);
    
    // TODO: Complete this method.  See the HW writeup for details 
    // about what the initial question and answers should be.
  }
  
  /** Builds a new TQTree by reading from a file 
    * @param filename The file containing the tree
    * @throws FileNotFoundException if the file cannot be found or read.
    * */
  public TQTree( String filename )
  {
    File f = new File( filename );
    LineNumberReader reader;
    try {
      reader = new LineNumberReader( new FileReader( f ));
    } catch ( FileNotFoundException e ) {
      System.err.println( "Error opening file " + filename );
      System.err.println( "Building default Question Tree." );
      
      root = new TQNode("Is it bigger than a breadbox?");
      root.setIndex(0);
      TQNode noChild = new TQNode("spam.");
      noChild.setIndex(1);
      TQNode yesChild = new TQNode("a computer scientist.");
      yesChild.setIndex(2);
      root.setNoChild(noChild);
      root.setYesChild(yesChild);
      return;
    }
    
    // TODO: Add code to read in the tree from a file here.  
    // Note: You will likely find the private method buidSubtree helpful here.  
    // Also, consider making your own private helper method to do most of the work here.
    try {
        root = buildSubtree(reader);
    }
    catch(ParseException e){
      System.err.println( "An error occured while parsing file.");
    }

    
    
    try {
      reader.close();
    } catch ( IOException e ) {
      System.err.println( "An error occured while closing file " + filename );
    }
    
  }
  
  /** Play one round of the game of Twenty Questions using this game tree 
    * Augments the tree if the computer does not guess the right answer
    */ 
  public void play()
  {
    Scanner input = new Scanner( System.in );
    TQNode currNode = root;
    while(currNode != null)
    {
      if(currNode.getYesChild() != null) //If not leaf node.
      {
        System.out.println(currNode.getData());
        String answer = input.nextLine();
        if(answer.equalsIgnoreCase( "y" ) || answer.equalsIgnoreCase( "yes" ))
        {
          currNode = currNode.getYesChild();
        }
        else
        {
          currNode = currNode.getNoChild();
        }
      }
      else //If leaf node
      {
       System.out.println("Is it " + currNode.getData() + "?"); 
       String answer = input.nextLine();
       if(answer.equalsIgnoreCase( "y" ) || answer.equalsIgnoreCase( "yes" )) //If guessed right.
       {
         return;
       }
       else //If guessed wrong
       {
         System.out.println("What was it?");
         String newAnswer = input.nextLine();
         
         System.out.println("Give me a question that would distinguish " + currNode.getData() 
                           + " from " + newAnswer + ".");
         String newQuestion = input.nextLine();
         
         System.out.println("Would the answer to the question for " + newAnswer +" be yes or no?");
         String newAnswerOption = input.nextLine();
         boolean newAnswerTrue = false;
         
         if(newAnswerOption.equalsIgnoreCase( "y" ) || newAnswerOption.equalsIgnoreCase( "yes" ))
         {
           TQNode yesNode = new TQNode(newAnswer);
           yesNode.setIndex((2*currNode.getIndex()) + 2);
           currNode.setYesChild(yesNode);
           
           TQNode noNode = new TQNode(currNode.getData());
           noNode.setIndex((2*currNode.getIndex()) + 1);
           currNode.setNoChild(noNode);
           
           currNode.setData(newQuestion);
         }
         else
         {
           TQNode noNode = new TQNode(newAnswer);
           noNode.setIndex((2*currNode.getIndex()) + 1);
           currNode.setNoChild(noNode);
           
           TQNode yesNode = new TQNode(currNode.getData());
           yesNode.setIndex((2*currNode.getIndex()) + 2);
           currNode.setYesChild(yesNode);
           
           currNode.setData(newQuestion);
         }
         return;
       }
      }
    }
  }
  
  /** Save this Twenty Questions tree to the file with the given name
    * @param filename The name of the file to save to
    * @throws FileNotFoundException If the file cannot be used to save the tree
    */
  public void save( String filename ) throws FileNotFoundException
  {
    PrintWriter output = new PrintWriter(filename);
    LinkedList<String> list = new LinkedList<String>();
    saveLines(root, list);
    for(int i = 0; i < list.size(); i++)
    {
      output.println(list.get(i));
    }
    output.close();
  }
  
  
  
  /** Print a level-order traversal of the tree to standard out (System.out)
    * */ 
  public void print()
  {
    LinkedList<TQNode> list = new LinkedList<TQNode>();
    queueNode(root, list);
    setIndexes(root,0);
    for(int i = 0; i < list.size(); i++)
    {
      Integer noIndex = null;
      Integer yesIndex = null;
      
      if(list.get(i).getNoChild() != null)
      {
        noIndex = Integer.valueOf(list.get(i).getNoChild().getIndex());
      }
      if(list.get(i).getYesChild() != null)
      {
        yesIndex = Integer.valueOf(list.get(i).getYesChild().getIndex());
      }
      
    System.out.println(list.get(i).getIndex() + ": '" + list.get(i).getData() + "' no:("  
                      + noIndex + ") yes:(" + yesIndex + ")");
    }
  }
 

  

  
    // PRIVATE HELPER METHODS
  // You will likely want to add a few more private helper methods here.    
  
  /** Recursive method to build a TQTree by reading from a file.
    * @param reader A LineNumberReader that reads from the file
    * @return The TQNode at the root of the created tree
    * @throws ParseException If the file format is incorrect
    */
  private TQNode buildSubtree( LineNumberReader reader ) throws ParseException 
  {
    
    String line;
    try {
      line = reader.readLine();
    }
    catch ( IOException e ) {
      throw new ParseException( "Error reading tree from file: " + e.getMessage(),
                               reader.getLineNumber() );
    }
    
    if ( line == null ) {
      // We should never be calling this method if we don't have any more input
      throw new ParseException( "End of file reached unexpectedly", reader.getLineNumber() );
    }
    
    String[] lineSplit = line.split( ":", 2 );
    String qOrA = lineSplit[0];
    String data = lineSplit[1];
    
    TQNode subRoot = new TQNode( data );
    if ( qOrA.equals( "Q" ) ) {
      subRoot.setNoChild( buildSubtree( reader ) );
      subRoot.setYesChild( buildSubtree( reader ) );
    }    
    return subRoot;
  }

  private void setIndexes(TQNode parent, int ind)
  {
    parent.setIndex(ind);
    if(parent.getNoChild() == null || parent.getYesChild() == null)
    {
      return;
    }
    else
    {
      setIndexes(parent.getNoChild(), (2*ind) + 1);
      setIndexes(parent.getYesChild(), (2*ind) + 2);
    }
  }
  
  private void saveLines(TQNode parent, LinkedList<String> list)
  {
    if(parent.getNoChild() == null || parent.getYesChild() == null)
    {
      list.add("A:" + parent.getData());
      return;
    }
    else
    {
      list.add("Q:" + parent.getData());
      saveLines(parent.getNoChild(), list);
      saveLines(parent.getYesChild(), list);
    }
  }
  
  private void queueNode(TQNode parent, LinkedList<TQNode> list)
  {
    list.add(parent);
    if(parent.getNoChild() == null || parent.getYesChild() == null)
    {
      return;
    }
    else
    {
      queueNode(parent.getNoChild(), list);
      queueNode(parent.getYesChild(), list);
    }
  }
}
