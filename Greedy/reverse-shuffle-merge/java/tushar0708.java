import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Collections;
import java.lang.StringBuilder;


public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        String S = scan.nextLine();
        
        S = new StringBuilder(S).reverse().toString();
        //get characters in A
        HashMap<Character, Integer> A = new HashMap<Character, Integer>();
        String ASorted = "";
        for (int i=0; i<S.length(); i++) {
            if (A.containsKey(S.charAt(i))) {
                A.put(S.charAt(i), A.get(S.charAt(i)) + 1);
            }
            else {
                A.put(S.charAt(i), 1);
            }
        }
        for (char c : A.keySet()) {
            A.put(c, A.get(c)/2);
            ASorted += c;
        }
        char[] temp = ASorted.toCharArray();
        Arrays.sort(temp);
        ASorted = new String(temp);
        //Collections.reverse(temp);
        //ASorted = new String(temp);
        
        HashMap<Character, Integer> buffer = (HashMap<Character, Integer>) A.clone();
        
       
        //find A
        String AString="";
        
        int previous = 0;
        int next = 0;
        
        for (int j=0; j<S.length()/2; j++) {
            
        HashMap<Character, Integer> foundChar = new HashMap<Character, Integer>();
            
            next = findBufferEnd(S, next, buffer);
        
            
        for (int i = previous; i<=next; i++) {
            if (foundChar.containsKey(S.charAt(i))) {
                foundChar.put(S.charAt(i), foundChar.get(S.charAt(i)) + 1);
            }
            else
                foundChar.put(S.charAt(i), 1);
        }
            
            
                String newASorted = "";
                for (int k=0; k<ASorted.length(); k++) {
                    char c = ASorted.charAt(k);
                    if (!A.containsKey(c))
                        System.out.println("error with A");
                    
                    if (A.get(c)<=0) {
                        continue;
                    }
                    else {
                        if (foundChar.containsKey(c) && foundChar.get(c)>0) {
                            AString += c;
                            A.put(c, A.get(c)-1);
                            if (A.get(c)>0) {
                                newASorted += c;
                            }
                            newASorted += ASorted.substring(k+1, ASorted.length());
                            
                            foundChar.put(c, foundChar.get(c)-1);
                            buffer.put(c, buffer.get(c)+1);
                            //find position in S
                            String temp1 = S.substring(previous, next+1);
                            int temp2 = temp1.indexOf(c);
                            if (temp2<0)
                                System.out.println("Error with temp1");
                            previous +=temp2+1;
                            break;
                        }
                        else if (k==ASorted.length()-1)
                            System.out.println("error with foundChar and ASorted");
                        else
                            newASorted += c;
                    }
                }
            //System.out.println(newASorted);
            
            ASorted = newASorted;
            
            
        }
        
        //System.out.println(new StringBuilder(AString).reverse().toString());
        System.out.println(AString);
        
        
        
        
    }
    
    //returns last index of S when buffer exceeded
    public static int findBufferEnd(String S, int start, HashMap<Character, Integer> buffer) {
        
        for (int i=start; i<S.length(); i++) {
            if (!buffer.containsKey(S.charAt(i)))
                System.out.println("Error with buffer");
            
            if (buffer.get(S.charAt(i))<=0)
                return i;
            else
                buffer.put(S.charAt(i), buffer.get(S.charAt(i))-1);
        }
        
        return S.length();
    }
}