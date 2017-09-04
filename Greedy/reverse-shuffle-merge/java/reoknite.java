import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
     setStandardIn();
     Scanner in = new Scanner(System.in);
     String s = in.nextLine();
     char[] sa = s.toCharArray();
     
     int[] charFreq = new int[26];
     for (char c : sa) {
      charFreq[c - 'a']++;
     }
     
     int aLength = 0;
     int[] remainingDeferredCharFreq = new int[26];
     for (int i = 0; i < charFreq.length; i++) {
      charFreq[i] = charFreq[i] / 2;
      remainingDeferredCharFreq[i] = charFreq[i];
      aLength += charFreq[i];
     }

     int freqIndex = 0;
     int aIndex = 0;
     char[] a = new char[aLength];
     int currentSmallestChar = -1;
     int currentSmallestCharLocation = -1;
     for (int i = sa.length - 1; i >= 0; i--) {
      char c = sa[i];
      int cIndex = c - 'a';
      
      while (freqIndex < charFreq.length && charFreq[freqIndex] <= 0) {
       freqIndex++;
      }
      if (freqIndex == charFreq.length) {
       break;
      }
      
      if (cIndex > freqIndex) {
       // will be lexicographically greater. (after reverse)
       // defer adding to a as long as possible
       // by borrowing from shuffled part of the merge.
       if (remainingDeferredCharFreq[cIndex] > 0) {
        remainingDeferredCharFreq[cIndex]--;
        
        if ((currentSmallestChar == -1 || cIndex < currentSmallestChar) && charFreq[cIndex] > 0) {
         currentSmallestChar = cIndex;
         currentSmallestCharLocation = i;
        }
       } else {
        if (currentSmallestChar != -1 && currentSmallestChar <= cIndex) {
         do {
          i++;
          c = sa[i];
          cIndex = c - 'a';
          remainingDeferredCharFreq[cIndex]++;
         } while (i < currentSmallestCharLocation);
         
        }
        
        if (charFreq[cIndex] > 0) {
         charFreq[cIndex]--;
         a[aIndex] = c;
         aIndex++;
         currentSmallestChar = -1;
        }
       }
      } else if (cIndex == freqIndex) {
       if (charFreq[cIndex] > 0) {
        // smallest lexicographically available. (after reverse)
        // add this to a.
        charFreq[cIndex]--;
        a[aIndex] = c;
        aIndex++;
        currentSmallestChar = -1;
       } else {
        // used up. the remaining belong to shuffled part.
       }
      } else {
       // cIndex < freqIndex
       // do nothing.
      }
     }
     
     System.out.println(new String(a));
     in.close();
    }
    
 public static void setStandardIn() {
  java.util.Map<String, String> env = System.getenv();
  if (env.containsKey("mta") && env.get("mta").equals("mta")) {
   try {
    System.setIn(new java.io.FileInputStream("in.txt"));
   } catch (FileNotFoundException e) {
    e.printStackTrace();
   }
  }
 }

    public static double roundToNthDecimal(double number, int roundToPlace) {
     double powerOfTen = Math.pow(10, roundToPlace);
     return Math.round(number * powerOfTen) / powerOfTen;
    }
}