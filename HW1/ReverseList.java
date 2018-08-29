/**
 * NAME: Son Tang <br>
 * ID: A11370127 <br>
 * LOGIN: cs12shb <br>
 * */
import java.io.*;
import java.util.*;

public class ReverseList
{
  
  public static void main (String[] args) throws IOException
  {    
    try
    {
      try
      {
        Scanner input = new Scanner(new File(args[0]));
        LinkedList<String> stringList = new LinkedList<String>();
        while(input.hasNext())
        {
          stringList.add(input.nextLine());
        }
        for(int i = stringList.size() - 1; i >= 0; i--)
        {
          System.out.println(stringList.get(i));
        }
      }
      catch(FileNotFoundException ex)
      {
        System.out.println("File Not Found");
      }
    }
    catch (ArrayIndexOutOfBoundsException ex)
    {
      System.out.println("Usage: ReverseList <filename>");
    }
  }
  
}




