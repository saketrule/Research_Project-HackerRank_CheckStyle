import java.io.*;
import java.util.*;

public class Solution {
    
    public static void main(String[] a) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] test = br.readLine().toCharArray();
        
        methode3(test);
    }

 private static void methode3(char[] test) {

  char[] alphapet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
  int[] counter = new int[alphapet.length];
  int[] counterExp = new int[alphapet.length];

  // Count the shit up
  char[] exp = new char[test.length / 2];
  for (int i = 0; i < test.length; i++) {
   for (int j = 0; j < alphapet.length; j++) {
    if (test[i] == alphapet[j]) {
     if (counter[j] % 2 > 0) {
      counterExp[j] = ++counterExp[j];
     }
     counter[j] = ++counter[j];

     break;
    }
   }
  }

  int[] tempCounter = Arrays.copyOfRange(counter, 0, counter.length);

  // remember to reintegrated skipped
  char smallestChar = 'z' + 1;
  int smallestCharPositionTest = test.length;
  int smallestCharPositionAlpa = alphapet.length;

  int lastUsedTestPosition = test.length;

  int expPosition = 0;
  for (int testPosition = test.length - 1; testPosition >= 0; testPosition--) {
   for (int alphapetPosition = 0; alphapetPosition < alphapet.length; alphapetPosition++) {
    char currentChar = alphapet[alphapetPosition];
    if (test[testPosition] == currentChar) {
     if (smallestChar > currentChar
       && counterExp[alphapetPosition] > 0) {
      smallestChar = currentChar;
      smallestCharPositionTest = testPosition;
      smallestCharPositionAlpa = alphapetPosition;
     }

     boolean mustbeUse = mustbeUse(test, tempCounter,
       counterExp, alphapetPosition);

     tempCounter[alphapetPosition] = --tempCounter[alphapetPosition];

     if (mustbeUse) {
      exp[expPosition++] = smallestChar;
      counterExp[smallestCharPositionAlpa] = --counterExp[smallestCharPositionAlpa];

      char[] skipped = Arrays.copyOfRange(test,
        smallestCharPositionTest, lastUsedTestPosition);

      for (int k = 0; k < skipped.length; k++) {
       for (int l = 0; l < alphapet.length; l++) {
        if (skipped[k] == alphapet[l]) {
         counter[l] = --counter[l];
         break;
        }
       }
      }

      testPosition = smallestCharPositionTest;

      lastUsedTestPosition = smallestCharPositionTest;

      tempCounter = Arrays.copyOfRange(counter, 0,
        counter.length);

      // Reset Smallest
      smallestChar = 'z' + 1;
      smallestCharPositionTest = test.length;
      smallestCharPositionAlpa = alphapet.length;

     }

     break;
    }
   }

  }

  for (int i = 0; i < exp.length; i++) {
   char c = exp[i];
   System.out.print(c);
  }
  System.out.println();
 }

 private static boolean mustbeUse(char[] test, int[] counter,
   int[] counterExp, int alphapetPos) {

  // Only one char left, take it
  if (counter[alphapetPos] == 1 && counterExp[alphapetPos] == 1) {
   return true;
  }

  // no char left nothing to do
  if (counterExp[alphapetPos] == 0) {
   return false;
  }

  // char count equals, we must take all
  if (counterExp[alphapetPos] == counter[alphapetPos]) {
   return true;
  }

  int allCounter = 0;
  int allCounterExp = 0;
  for (int i = 0; i < counter.length; i++) {
   // Char is the lexical smallest possible, take it
   if (i == alphapetPos && allCounter == 0) {
    return true;
   }
   allCounter = allCounter + counter[i];
   allCounterExp = allCounterExp + counterExp[i];

  }

  // only so many chars left as chars there, take it
  if (allCounter == allCounterExp) {
   return true;
  }

  return false;
 }
}