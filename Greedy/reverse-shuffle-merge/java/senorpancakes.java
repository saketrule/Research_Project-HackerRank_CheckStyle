import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        StringBuilder s = new StringBuilder(in.next());
        s = s.reverse();
        StringBuilder answer = new StringBuilder("");
        int length = s.length();
        int[] chars = new int[26];
        int[] charsTaken = new int[26];
        for(int i = 0; i < length; i++ ){
            chars[((int)s.charAt(i))-97]++;
        }
        for(int i = 0; i < 26; i++){
            chars[i]=chars[i]/2;
        }
        System.arraycopy(chars, 0, charsTaken, 0, 26);
        char defaultChar = 'z';
        int defaultIndex = 0;
        int[] defaultArray = new int[26];
        
        for(int i = 0; i < length; i++){
            char cc = s.charAt(i);
         //   System.out.print(cc);  System.out.print(charsTaken[((int)cc)- 97]); System.out.print(chars[((int)cc)- 97]);

            if (cc < defaultChar && charsTaken[((int)cc)- 97] > 0 ){
                defaultChar = cc;
                defaultIndex = i;
                System.arraycopy(chars, 0, defaultArray, 0, 26);
                //System.out.print(cc);  System.out.print(charsTaken[((int)cc)- 97]); System.out.print(chars[((int)cc)- 97]);
                          

            }
            
            if (chars[((int)cc)- 97] <=0){
                if(charsTaken[((int)cc)- 97] > 0){
                //    System.out.print(" Taking " + defaultChar + " ");
                    answer.append(defaultChar);
                    charsTaken[((int)defaultChar)- 97]--;
                    i = defaultIndex;
                    defaultIndex = i+1;
                    defaultChar = '}';
                    System.arraycopy(defaultArray, 0, chars, 0, 26);     
                    //   chars[((int)cc)- 97]++;
                }
            }
            
            else{
                chars[((int)cc)- 97]--;
            }
            
            
        }
        
        System.out.println(answer);   
        
        
        
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}