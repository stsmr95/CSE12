/**
 * NAME: Son Tang <br>
 * ID: A11370127 <br>
 * LOGIN: cs12shb <br>
 * */
import java.io.*;
import java.util.*;

public class ReverseArray
{
  public static void main (String[] args) throws IOException
  {    
    try
    {
      Scanner input = new Scanner(new File(args[0]));
      int numLines = 100;
      String[] stringArray = new String[numLines];
      int index = 0;
      while(input.hasNext())
      {
        try
        {
          if(stringArray[index] == null)
          {
          stringArray[index] = input.nextLine();
          index++;
          }
        }
        catch(ArrayIndexOutOfBoundsException ex)
        {
          numLines = numLines + 100;
          String[] newArray = new String[numLines];
          for(int u = 0; u < stringArray.length; u++)
          {
            newArray[u] = stringArray[u];
          }
          stringArray = newArray;
        }
      }
      for(int i = stringArray.length - 1; i >= 0; i--)
      {
        if(stringArray[i] != null)
        {
        System.out.println(stringArray[i]);
        }
      }
    }
    catch(FileNotFoundException ex)
    {
      System.out.println("File Not Found");
    }
    catch (ArrayIndexOutOfBoundsException ex)
    {
      System.out.println("Usage: ReverseList <filename>");
    }
  }
  
}




