/* Name: Jeffrey Phung, Sonny Tang
 * Login: cs12sfw, cs12shb
 * PID: A11264719, A11370127
 * Date: 5/27/2014
 * 
 * 
 */

import java.util.Map;

import junit.framework.*;

/** Tester for the AST class.
 * 
 */ 
public class ASTTester extends TestCase
{
 Unicalc uni;
 private  Map<String,Quantity> database;
 
 public void setUp(){
  
  uni = new Unicalc();
  database = QuantityDB.getDB();
 }
 
 public void testProduct()
 {
  uni.tokenize("9 meters*9 meters");
  AST theProd = uni.parse();
  assertEquals("81.0 meters",theProd.eval(database).toString());
 }

 public void testQuotient()
 {
  uni.tokenize("9 meters/9 meters");
  AST theQuotient = uni.parse();
  assertEquals("1.0 meters",theQuotient.eval(database).toString());
 }

 public void testSum()
 {
  uni.tokenize("9 meters+9 meters");
  AST theSum = uni.parse();
  assertEquals("18.0 meters",theSum.eval(database).toString());
 }

 public void testDifference()
 {
  uni.tokenize("9 pennyweight-9 pennyweight");
  AST theDiff = uni.parse();
  assertEquals("0.0 meters",theDiff.eval(database).toString());
 }

 public void testPower()
 {
  uni.tokenize("9 joules^2");
  AST thePower = uni.parse();
  assertEquals("9.0 joules^2",thePower.eval(database).toString());
 }

 public void testNegation()
 {
  uni.tokenize("-9meters");
  AST theProd = uni.parse();
  assertEquals("81.0 meters",theProd.eval(database).toString());
 }

 public void testDefine()
 {
  uni.tokenize("def smoot 1 meter");
  AST define = uni.parse();
  assertEquals("1.0 meter",define.eval(database).toString());
 }

 public void testValue()
 {
  uni.tokenize("1");
  AST value = uni.parse();
  assertEquals("1.0",value.eval(database).toString());
 }

}