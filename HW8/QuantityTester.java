/* Name: Jeffrey Phung, Sonny Tang
 * Login: cs12sfw, cs12shb
 * PID: A11264719, A11370127
 * Date: 5/27/2014
 * 
 * 
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import junit.framework.TestCase;


public class QuantityTester extends TestCase {
 
 private  Map<String,Quantity> db;
 
 public void setUp() throws Exception {
    db =  QuantityDB.getDB();
 }
 
 public void testConstructorNoArg()
 {
  Quantity qq = new Quantity();
  assertEquals(new Quantity(1, Collections.<String>emptyList(),Collections.<String>emptyList()).toString(), qq.toString());
  
 }
 
 public void testConstructorOneArg()
 {
  Quantity qq = new Quantity(new Quantity());
  assertEquals(new Quantity(1, Collections.<String>emptyList(),Collections.<String>emptyList()).toString(), qq.toString());
 }
 
 public void testConstructorThreeArg()
 {
  Quantity qq = new Quantity(9.8, Arrays.asList("meters"), Collections.<String>emptyList());
  assertEquals(new Quantity(9.8, Arrays.asList("meters"), Collections.<String>emptyList()).toString(), qq.toString().toString());
 }

 public void testMul()
 {
  Quantity qMul = new Quantity(9.8, Arrays.asList("hr"), Arrays.asList("s"));
  Quantity qMul2 = new Quantity(12.0, Arrays.asList("m"), Arrays.asList("s"));
  assertEquals(new Quantity(117.6, Arrays.asList("hr", "m"), Arrays.asList("s", "s")).toString(), qMul.mul(qMul2).toString());
 }
 
 public void testDiv()
 {
  Quantity qDiv = new Quantity(9.8, Arrays.asList("m"),Collections.<String>emptyList());
  Quantity qDiv2 = new Quantity(12.0, Arrays.asList("m"), Collections.<String>emptyList());
  assertEquals(new Quantity(.81667, Collections.<String>emptyList(), Collections.<String>emptyList()).toString(), qDiv.div(qDiv2).toString());
 }
 
 public void testAdd()
 {
  Quantity qAdd = new Quantity(9.8, Arrays.asList("m"), Arrays.asList("s"));
  Quantity qAdd2 = new Quantity(12.0, Arrays.asList("m"), Arrays.asList("s"));
  assertEquals(new Quantity(21.8, Arrays.asList("m"), Arrays.asList("s")).toString(), qAdd.add(qAdd2).toString());
 }
 
 public void testSub()
 {
  Quantity qSub = new Quantity();
  Quantity qSub2 = new Quantity();
  assertEquals(new Quantity(0.0, Collections.<String>emptyList(), Collections.<String>emptyList()).toString(), qSub.sub(qSub2).toString());
 }
 
 public void testNegate()
 {
  Quantity qNegate = new Quantity();
  Quantity qNegate2 = new Quantity(12.0, Arrays.asList("m"), Arrays.asList("s"));
  assertEquals(new Quantity(-1, Collections.<String>emptyList(), Collections.<String>emptyList()).toString(), qNegate.negate().toString());
  assertEquals(new Quantity(-12.0, Arrays.asList("m"), Arrays.asList("s")).toString(), qNegate2.negate().toString());
 }
 
 public void testNormalizedUnit()
 {
  assertEquals(new Quantity(1000, Arrays.asList("meter"), Collections.<String>emptyList()).toString(), Quantity.normalizedUnit("km", db).toString());
  assertEquals(new Quantity(1, Arrays.asList("smoot"), Collections.<String>emptyList()).toString(), Quantity.normalizedUnit("smoot", db).toString());
 }
 
 public void testNormalize()
 {
  Quantity qNormalize = new Quantity();
  Quantity qNormalize2 = new Quantity();
  assertEquals(new Quantity(1,Arrays.asList(""), Arrays.asList("")).toString(), qNormalize.normalize(db).toString());
 }
 
 public void testPow()
 {
  Quantity qPow = new Quantity(4, Arrays.asList("m"), Arrays.asList("s"));
  assertEquals(new Quantity(16.0, Arrays.asList("m"), Arrays.asList("s")).toString(), qPow.pow(2).toString());
 }
 
 public void testEquals()
 {
  Quantity qEquals = new Quantity(9.8, Arrays.asList("m"), Arrays.asList("s"));
  Quantity qEquals2 = new Quantity();
  assertEquals(false, qEquals.equals(qEquals2));
 }
 
 public void testHashCode()
 {
  Quantity quan1 = new Quantity(9.8, Arrays.asList("smoot"), Arrays.asList("smoot"));
  Quantity quan2 = new Quantity(9.8, Arrays.asList("smoot"), Arrays.asList("smoot"));
  Quantity quan3 = new Quantity(10.8, Arrays.asList("smoot"), Collections.<String>emptyList());
  assertEquals(quan1.hashCode(), quan2.hashCode());
  assertTrue(quan1.hashCode() != quan3.hashCode());
 }
}
