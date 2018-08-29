import java.lang.Comparable;
import java.util.List;
import java.util.ArrayList;
import java.lang.*;
/**
 * NAME: Son Tang
 * ID: A11370127
 * LOGIN: cs12shb
 * */
public class Merge12 implements Sort12
{
  public  <T extends Comparable<? super T>> void  sort(List<T> list)
  {
    if ( list == null )
      throw new NullPointerException("Null argument to sort");
    
    // Create the arrayList to insert into
    ArrayList<T> inputArray = new ArrayList<T>(list.size());
    ArrayList<T> tempArray = new ArrayList<T>(list.size());
    
    for(int i = 0; i < list.size(); i++)
    {
      inputArray.add(list.get(i));
    }
    for(int i = 0; i < list.size(); i++)
    {
      tempArray.add(list.get(i));
    }
    
    internalMergeSort(inputArray, tempArray, 0, list.size() - 1);
    
    for(int i = 0; i < list.size(); i++)
    {
      list.set(i, inputArray.get(i));
    } 
  } //sort
  
  
  private  <T extends Comparable<? super T>> void 
    internalMergeSort(ArrayList<T> inputArray, ArrayList<T> tempArray,
                      int first, int last)
  {   
    if(first >= last)
    {
      return;
    }
    else
    {
      int mid = first + (last - first) / 2;
      internalMergeSort(inputArray, tempArray, first, mid);
      internalMergeSort(inputArray, tempArray, mid + 1, last);
      merge(inputArray, tempArray, first, mid, last);
    }
  } // internalMergeSort
  
  
  private  <T extends Comparable<? super T>> void 
    merge(ArrayList<T> inputArray, ArrayList<T> tempArray,
          int first, int mid, int last)
  {
    for(int i = first; i <= last; i++)
    {
      tempArray.set(i, inputArray.get(i));
    }
    int i = first;
    int o = mid + 1;
    int u = first;
    
    while(i <= mid && o <= last)
    {
      if(tempArray.get(i).compareTo(tempArray.get(o)) > 0)
      {
        inputArray.set(u, tempArray.get(o));
        o++;
      }
      else
      {
        inputArray.set(u, tempArray.get(i));
        i++;
      }
      u++;

    }
    while(i <= mid)
    {
      inputArray.set(u, tempArray.get(i));
      u++;
      i++;
    }
    while(o <= last)
    {
      inputArray.set(u, tempArray.get(o));
      u++;
      o++;
    } 
  } // merge
  
  
  public static void main(String[] args)
  {
    ArrayList<String> list = new ArrayList<String>();
    list.add(new String("0sss"));
    list.add(new String("0ss"));
    list.add(new String("zvxzbva4325432swws"));
    list.add(new String("dsadsass"));
    list.add(new String("66547dsfadafs"));
    
    ArrayList<Integer> list2 = new ArrayList<Integer>();
    list2.add(new Integer(3));
    list2.add(new Integer(4));
    list2.add(new Integer(0));
    list2.add(new Integer(7));
    list2.add(new Integer(8));
    list2.add(new Integer(1));
    list2.add(new Integer(-1));
    list2.add(new Integer(2));
    list2.add(new Integer(6));
    list2.add(new Integer(5));
    list2.add(new Integer(9));
    
    System.out.println("Size of Array is " + String.valueOf(list.size()));
    Merge12 merger = new Merge12();
    merger.sort(list2);
    merger.sort(list);
    System.out.println("Values of Array:");
    for(int i = 0; i<list.size(); i++)
    {
      System.out.println(String.valueOf(list.get(i)));
    }    
    System.out.println("");
    System.out.println("Values of Array:");
    for(int i = 0; i<list2.size(); i++)
    {
      System.out.println(String.valueOf(list2.get(i)));
    }
  } 
}
// vim:ts=4:sw=4:sw=78
