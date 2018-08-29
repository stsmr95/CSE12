/* TODO:  Add your name, login, and ID as specified in the instructions */
/**
 * NAME: Son Tang <br>
 * ID: A11370127 <br>
 * LOGIN: cs12shb <br>
 * */
import junit.framework.TestCase;

public class CounterTest extends TestCase {

 private Counter counter, counter2;

 @Override
 /* this sets up the test fixture. JUnit invokes this method before
     every testXXX method */
 protected void setUp() throws Exception {
  super.setUp();
  counter = new Counter();
  counter2 = new Counter(2);
 }

 public void testDefaultValueOfCounterIsZero() {
  System.out.println("Checking Default Counter Value is Zero");
  assertEquals(0, counter.getCount());
  assertEquals(0, counter2.getCount());
 }

 public void testIncrement() {
  System.out.println("Checking Proper Increment");
  counter.increment();
  assertEquals(1, counter.getCount());
  counter2.increment();
  assertEquals(2, counter2.getCount());
 }

 public void testMultipleIncrements() {
  System.out.println("Checking Multiple Increments");
  for (int i = 0; i < 10; i++) {
   counter.increment();
   assertEquals(i + 1, counter.getCount());
  }
 }

 public void testReset() {
  System.out.println("Checking Reset");
  counter.reset();
  assertEquals(1, counter.getCount());  
  counter2.reset();
  assertEquals(1, counter2.getCount());
  /* TODO: write a test the verifies Reset */
 }

 public void testDecrement() {
  System.out.println("Checking Decrement");
  for (int i = 0; i < 5; i++)
  {
   counter.increment();
   counter2.increment();
  }
  for (int i = 0; i < 6; i++)
  {
   counter.decrement();
   counter2.decrement();
  }
  assertEquals(0, counter.getCount());
  assertEquals(0, counter2.getCount());
  /* TODO: write a test that verifies the proper values of
    counter and counter2  */
  
 }
}
