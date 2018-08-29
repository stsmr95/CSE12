import java.lang.Comparable;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
/** NAME: Son Tang
 * ID: A11370127
 * LOGIN: cs12shb
 * SECTION: B00
 * DATE: May 15,2014
 * 
 *  This is a Heap Sort
 *  @author Philip Papadopoulos 
 *  @date 28 April 2014
 */
public class HeapSort12 implements Sort12
{
 public  <T extends Comparable<? super T>> void sort(List<T> list)
 {
   Heap12<T> heapList = new Heap12<T>(list.size(), false);
   for(int i = 0; i < list.size(); i++)
   {
     heapList.offer(list.get(i));
   }
   for(int u = 0; u < list.size(); u++)
   {
     list.set(u, heapList.poll());
   }
 }
 
}
// vim:ts=4:sw=4:sw=78
