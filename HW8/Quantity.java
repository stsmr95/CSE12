/* Name: Jeffrey Phung, Sonny Tang
 * Login: cs12sfw, cs12shb
 * PID: A11264719, A11370127
 * Date: 5/27/2014
 * 
 * 
 */


import java.util.*;
import java.util.Map.Entry;
import java.text.DecimalFormat;

public class Quantity{
  
  //Instance Variables
  private double value = 0;
  private Map<String, Integer> unit;
  
  //Constructors
  
  public Quantity(){
    value = 1;
    unit = new HashMap<String, Integer>();
  }
  
  public Quantity(Quantity deepCopy){
    value = deepCopy.value;
    unit = new HashMap<String, Integer>();
    unit.putAll(deepCopy.unit);
  }
  
  public Quantity(double inputValue, List<String> unitNum, List<String> unitDenom){
    if(unitNum == null || unitDenom == null){
      throw new IllegalArgumentException();
    }
    this.value = inputValue; //Sets the value of quantity.
    
    unit = new HashMap<String, Integer>(); 
    
    for(int i = 0; i < unitNum.size(); i++) //Sets the numerator.
    {
      if(unitNum.get(i) == "")
      { }
      //its getting nothing to check for if statement
      else if(unit.containsKey(unitNum.get(i)))
      {
        unit.put(unitNum.get(i), unit.get(unitNum.get(i)) + 1);
        if(unit.get(unitNum.get(i)) == 0)
        {
          unit.remove(unitNum.get(i));
        }
      }
      else
        unit.put(unitNum.get(i), 1);
    }
    
    for(int i = 0; i < unitDenom.size(); i++) //Sets the denominator.
    {
      if(unitDenom.get(i) == "")
      { }
      else if(unit.containsKey(unitDenom.get(i)))
      {
        unit.put(unitDenom.get(i), unit.get(unitDenom.get(i)) - 1);
        if(unit.get(unitDenom.get(i)) == 0)
        {
          unit.remove(unitDenom.get(i));
        }
      }
      else
        unit.put(unitDenom.get(i), -1);
    }
    
  }
  
  //Math Methods
  public Quantity mul(Quantity mulBy) throws IllegalArgumentException
  {
    if(mulBy == null)
      throw new IllegalArgumentException();
    Quantity retQuantity = new Quantity();
    retQuantity.value = this.value * mulBy.value; //multiplies the value.
    
    retQuantity.unit.putAll(this.unit); //copies this map into retQuantity map.
    
    for(Map.Entry<String,Integer> entry : mulBy.unit.entrySet())
    {
      if(retQuantity.unit.containsKey(entry.getKey())){
        retQuantity.unit.put(entry.getKey(), retQuantity.unit.get(entry.getKey()) + entry.getValue());
        if(retQuantity.unit.get(entry.getKey()) == 0)
        {
          retQuantity.unit.remove(entry.getKey());
        }
      }
      else //copies values that don't exist in retQuantity map.
        retQuantity.unit.put(entry.getKey(), entry.getValue());
    }
    return retQuantity;
  }
  
  public Quantity div(Quantity divBy) throws IllegalArgumentException
  {
    if(divBy == null)
      throw new IllegalArgumentException();
    Quantity retQuantity = new Quantity();
    retQuantity.value = this.value/divBy.value; //divides the value.
    
    retQuantity.unit.putAll(this.unit);//copies this map into retQuantity map.
    
    for(Map.Entry<String,Integer> entry : divBy.unit.entrySet())
    {
      if(entry.getValue() >= 1 && retQuantity.unit.containsKey(entry.getKey())){
        retQuantity.unit.put(entry.getKey(), retQuantity.unit.get(entry.getKey()) - entry.getValue());
        if(retQuantity.unit.get(entry.getKey()) == 0)
        {
          retQuantity.unit.remove(entry.getKey()); //remove powers of 0.
        }
      }
      else //copies values that don't exist in retQuantity map.
        retQuantity.unit.put(entry.getKey(), -1 * entry.getValue());
    }
    return retQuantity;
  }
  
  public Quantity add(Quantity addBy)
  {
    Quantity addQuantity;
    
    if(addBy == null || !addBy.unit.equals(this.unit)){
      throw new IllegalArgumentException();   //units could be null. addBy cannot be null.
    }
    else{
      addQuantity = new Quantity();
      addQuantity.value = this.value + addBy.value; //adds the value.
      addQuantity.unit.putAll(this.unit); //copies this map into addQuantity map because units don't change.
    }
    
    return addQuantity;
  }
  
  public Quantity sub(Quantity subBy)
  {
    Quantity subQuantity;
    
    if(subBy == null || !subBy.unit.equals(this.unit)){
      throw new IllegalArgumentException();   //units could be null. addBy cannot be null.
    }
    else{
      subQuantity = new Quantity();
      subQuantity.value = this.value - subBy.value; //subtracts the value.
      subQuantity.unit.putAll(this.unit); //copies this map into subQuantity map.
    }
    
    return subQuantity;
  }
  
  public Quantity negate()
  {
    Quantity negQuantity = new Quantity();
    negQuantity.value = this.value * -1; //negative value.
    negQuantity.unit.putAll(this.unit); //copies the units.
    return negQuantity;
  }
  
  public Quantity pow(int powerBy)
  {
    Quantity powQuantity = new Quantity();
    
    double powValue = value;
    powValue = Math.pow(powValue, powerBy);
    powQuantity.value = powValue;
    powQuantity.unit.putAll(this.unit);
    return powQuantity;
  }
  
  public boolean equals(Quantity compareTo)
  {
    if(this.value == compareTo.value && this.unit.entrySet().equals(compareTo.unit.entrySet()))
      return true;
    else
      return false;
  }
  
  public static Quantity normalizedUnit(String unitName, Map<String,Quantity> database)
  {
    Quantity retQuantity = new Quantity(1.0, Arrays.asList(unitName), Arrays.asList(""));
       //if key (unit name) isn't located in the database
    if(!database.containsKey(unitName)){
      retQuantity = new Quantity(1, Arrays.asList(unitName), Collections.<String>emptyList());
      return retQuantity;
    }
    retQuantity = retQuantity.normalizedUnitHelper(unitName, database);
    return retQuantity;
  }
  
  public Quantity normalize(Map<String,Quantity> database)
  {
    Quantity newQuan = new Quantity(this);
    Set<Map.Entry<String,Integer>> set = newQuan.unit.entrySet();
    for(Entry<String,Integer> entry : set)
    {
      while(entry.getValue() != 0)
      {
        if(entry.getValue() > 0)
        {
          newQuan = newQuan.mul(normalizedUnit(entry.getKey(), database));
          entry.setValue(entry.getValue() - 1);
        }
        if(entry.getValue() < 0)
        {
          newQuan = newQuan.div(Quantity.normalizedUnit(entry.getKey(), database));
          entry.setValue(entry.getValue() + 1);
        }
      }
      if(entry.getValue() == 0)
      {
        newQuan.unit.remove(entry.getKey());
      }
    }
    return newQuan;
  }
  
  public int hashCode()
  {
    Double doub = new Double(value);
    return unit.hashCode() + doub.hashCode();
  }
  
  public String toString()
  {
    double valueOfTheQuantity = this.value;
    Map<String,Integer> mapOfTheQuantity = this.unit;
    // Ensure we get the units in order
    TreeSet<String> orderedUnits =
      new TreeSet<String>(mapOfTheQuantity.keySet());
    StringBuffer unitsString = new StringBuffer();
    for (String key : orderedUnits) {
      int expt = mapOfTheQuantity.get(key);
      unitsString.append(" " + key);
      if (expt != 1)
        unitsString.append("^" + expt);
    }
    // Used to convert doubles to a string with a
    // fixed maximum number of decimal places.
    DecimalFormat df = new DecimalFormat("0.0####");
    // Put it all together and return.
    return df.format(valueOfTheQuantity)
      + unitsString.toString();
  }
  
  //Private helper methods
  private Quantity normalizedUnitHelper(String unitName, Map<String,Quantity> database)
  {
    
      this.value = this.value * database.get(unitName).value;
      Set<Entry<String, Integer>> theSet = database.get(unitName).unit.entrySet();
      for (Entry<String, Integer> entry : theSet){
        String oldName = unitName;
        unitName = entry.getKey();
        if(entry.getValue() > 0)
        {
          Quantity newQuan = this.mul(normalizedUnit(unitName, database));
          this.unit.putAll(newQuan.unit);
          this.value = newQuan.value;
          this.unit.remove(oldName);
        }
        else 
        {        
          Quantity newQuan = this.div(normalizedUnit(unitName, database));
          this.unit.putAll(newQuan.unit);
          this.value = newQuan.value;
          this.unit.remove(oldName);
        }
      }
      return this;
    
  }
  
  //Main Method
  public static void main(String[] args)
  {
    Map<String,Quantity> db = new HashMap<String,Quantity>();
    db = QuantityDB.getDB();
    
     Quantity quan2 = Quantity.normalizedUnit("kph", db);
     Set<Map.Entry<String,Integer>> set = quan2.unit.entrySet();
     for(Entry<String,Integer> entry: set)
     {
     System.out.println(entry.getKey() + " " + entry.getValue());
     }
     System.out.println(quan2.toString());
     System.out.println("-------------------"); 
    
    Quantity quan3 = new Quantity(9000, Arrays.asList("kph", "hr"), Arrays.asList("day"));
    Set<Map.Entry<String,Integer>> set3 = quan3.unit.entrySet();
    for(Entry<String,Integer> entry3: set3)
    {
      System.out.println(entry3.getKey() + " " + entry3.getValue());
    }
    System.out.println(quan3.toString());
    System.out.println("-------------------");
    
    Quantity quan4 = quan3.normalize(db);   
    System.out.println(quan4.toString()); 
     System.out.println("-------------------"); 
    
     Quantity quan5 = new Quantity(9, Arrays.asList("m"), Arrays.asList(""));
     Quantity quan6 = quan5.pow(2);
     System.out.println(quan6.toString());
     System.out.println("-------------------"); 
     
  }
  
  
  
}