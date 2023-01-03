package org.example.util;

import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * static utility operations
 */
public class MyUtil
{

  /// Convert the pipe chars in given String to commas
  public static String pipeStringToCommaString(String pipeString)
  {
    String rezult = "";
    if(pipeString != null && pipeString.length() > 0) { // TODO: Use Optional
      String[] arr = pipeString.split("\\|");
      for(String str : arr) {
        rezult += (str + ",");
      }
      rezult = MyUtil.removeLastCharacterInString(rezult);
    }
    return rezult;
  }

  // Remove the last character of the given String
  // TODO: check error handling and update to
  // be removeLastCharacterInStringMatching(str, ",")
  public static String removeLastCharacterInString(String str)
  {
    String rezult = Optional.ofNullable(str)
    .filter(sStr -> sStr.length() != 0)
    .map(sStr -> sStr.substring(0, sStr.length() - 1))
    .orElse(str);
    return rezult;
  }

  public static String[] getArrayFromPipeString(String customerNumberPipeString)
  {
    String[] myArray = customerNumberPipeString.split("\\|"); // TODO: use Optional
    return myArray;
  }
  
  // Create a simpler alias for the Long version
  public static List<Long> stringToList(String myString, String delimiter)
  {
    return MyUtil.stringToListLong(myString, delimiter);
  }
  
  // converts a delimiter-separated String to a List of Longs. Used to parse supervisingPhysicianKeys in Utilization
  public static List<Long> stringToListLong(String myString, String delimiter)
  {
    List<Long> myList = new ArrayList<Long>();
    String[] myArray = myString.split(delimiter);
    for(String s : myArray) {
      myList.add( new Long(s) ); // TODO: try-catch this?
    }
    return myList;
  }
  
  // converts a delimiter-separated String to a List of Strings. Used to parse supervisingPhysicianKeys in Utilization
  public static List<String> stringToListString(String myString, String delimiter)
  {
    List<String> myList = new ArrayList<String>();
    String[] myArray = myString.split(delimiter);
    for(String s : myArray) {
      myList.add( s ); // TODO: try-catch this?
    }
    return myList;
  }  
  
  // if the given customerNumber has a value then put that in a List and return.
  // if empty then return the list of customer numbers that matches value in X-License
  public static List<String> makeCustomerNumberList(Optional<String> customerNumber, List<String> customerNumberList)
  {
    // TODO: null check customerNumber and customerNumberList
    List<String> myCustomerNumberList = new ArrayList<String>();
    if(customerNumber.isPresent()) {
      myCustomerNumberList.add( customerNumber.get() );    // Create a List with 1 item, //customerNumberList.add("0100281387");
    }
    else {
      myCustomerNumberList = customerNumberList;
    }
    return myCustomerNumberList;
  }

  // Used by Repositories to fetch SQL files from classpath
  public static String getSqlFromFile(Resource resource)
  {
    String sqlString = null;
    //if(resource == null) {
    //  log.error("The given SQL file is empty or not exists");
    //}
    //else {
    
      try {
        sqlString = StreamUtils.copyToString(resource.getInputStream(), Charset.defaultCharset() );
      }
      catch(java.io.IOException ioe) {
        //log.error("Error fetching sql file '" + resource.getFilename() + "' from classpath. IOException: " + ioe.getMessage());
        ioe.printStackTrace();
      }
      
    //}
    //log.trace("getSqlFromFile, sqlString: " + sqlString);
    return sqlString;
  }

  public static boolean stringIsEmpty(String str)
  {
    boolean rezultFlag = true;
    if(str != null && str.length() > 0) {
      rezultFlag = false;
    }
    return rezultFlag;
  }

  public static boolean stringIsNotEmpty(String str)
  {
    return !(MyUtil.stringIsEmpty(str));
  }

  // convert a generic Set to a List
  public static List<Long> setToList(Set<Long> mySet)
  {
    int setCount = mySet.size();
    List<Long> myList = new ArrayList<Long>(setCount);
    for(Long val : mySet)
      myList.add(val);
    return myList;
  }
  
  // Accepts a List of Strings and returns a String with values separated by given delimiter
  public static String listToDelimitedString(List<Long> keyList, String delimiter)
  {
    StringBuffer b = new StringBuffer();
    int listSize = keyList.size();
    int cnt = 0;
    for(Long key : keyList) {  
      b.append(key);           // always append the value - ya
      if(cnt < listSize) {     // if not last, then append delimiter
        b.append(delimiter);
      }
      cnt++;
    }
    return b.toString();
  }
  
  
  // Formats a given java.util.Date to a String of format yyyy-MM-dd.
  // If given value is null or conversion wont work then return empty String
  public static String dateToString(Date myDate)
  {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String rezult = "";
    if(myDate != null) {
      try {
        rezult = sdf.format(myDate);
      }
      catch(Exception e) {} // do nothing
    }
    return rezult;
  }


  // Look for the given tokenText in sqlQuery and replace with replaceText
  //public static String replaceTokenInString(String input, String replaceText, String tokenText)
  //{
  //  Pattern pattern = Pattern.compile.(tokenText);
  //  Matcher matcher = pattern.matcher(input);
  //  input = matcher.replaceAll(replaceText);
  //}




}