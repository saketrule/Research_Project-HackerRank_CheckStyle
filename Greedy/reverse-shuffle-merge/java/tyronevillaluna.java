import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        char s[] = scanner.nextLine().toCharArray();
        
        // Count chars
        Map<Character, Integer> countMap = new TreeMap<>();
        for (int i=0; i<s.length; i++) {
            Integer count = countMap.get(s[i]);
            if (count == null) {
                count = 1;
            } else {
                count = count+1;
            }
            countMap.put(s[i], count);
        }
        
        // Create discard map (Max allowed a char can be discarded)
        // Create select map (Max char to add)
        Map<Character, Integer> discardMap = new TreeMap<>();
        Map<Character, Integer> selectMap = new TreeMap<>();
        for (Character c : countMap.keySet()) {
            discardMap.put(c, countMap.get(c)/2);
            selectMap.put(c, countMap.get(c)/2);
        }
        
        // Find lowest lexy
        List<Character> lexy = new ArrayList<Character>();
        for (int i=s.length-1; i>=0; i--) {
            
           // append char only if select allowed is not exhausted
           if (selectMap.get(s[i]) > 0) {
               int cutAt = -1;
               
               // check current lexy where to put char best
               for (int j=lexy.size()-1; j>=0; j--) {
                   
                   // only if the char is lexy lower than on current lexy and that can still be discarded
                   if (s[i] < lexy.get(j) && discardMap.get(lexy.get(j)) > 0) {
                       cutAt=j;
                       // reduce discard allowed
                       discardMap.put(lexy.get(j), discardMap.get(lexy.get(j))-1);

                       // increase back select allowed if necessary
                       if (selectMap.get(lexy.get(j)) < countMap.get(lexy.get(j))/2) {
                           selectMap.put(lexy.get(j), selectMap.get(lexy.get(j))+1);
                       }
                   } else {
                       
                       break;
                   }
               }
               if (cutAt != -1) {
                   lexy = lexy.subList(0, cutAt);
               } 
               lexy.add(s[i]);
               selectMap.put(s[i], selectMap.get(s[i])-1);
               
           } else {
               // discard char
               discardMap.put(s[i], discardMap.get(s[i])-1);
           }
           
        }
        
        StringBuilder sb = new StringBuilder();        
        for (Character c : lexy) {
            sb.append(c);
        }
        
        System.out.println(sb.toString());    
    }
}