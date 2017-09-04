import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
         try{
  BufferedReader br = 
                      new BufferedReader(new InputStreamReader(System.in));
 
  String input;
 
  while((input=br.readLine())!=null){
   System.out.println(solve(input));
            break;
  }
 
 }catch(IOException io){
  io.printStackTrace();
 }
    }
    
    public static String solve(String input){
        //two step appraoch
        //Step 1:find the anagrams simply be recording the count
        int[] ana_count = new int[26];
        for(int i = 0; i < input.length(); i++){
            ana_count[input.charAt(i) - 'a'] ++;
        }
        //so the count is double the count of a anagram
        
        //Step 2: reverse the string and find the lexicographically smallest.
        StringBuilder sb = new StringBuilder();
        sb.append(input);
        sb = sb.reverse();
        String smallest = findMin(sb.toString(), ana_count);

        return smallest;
    }
    
    
    //Step 2, given a anagram count map
    //Find the lexicographically smallest anagram in the string
    //come up with a O(N) time and O(N) space using a stack , Can I do better?
    //when get iterate to a new element  c in str
    //compare c to the top of the stack, say h, remove the stack if the following condition applies:
    // c is smaller than h and h has enough counts among the elements after c
    public static String findMin(String str, int[] ana_count){
        Stack<Character>  stack = new Stack<Character>();
        
        int[] available_spots = Arrays.copyOf(ana_count, ana_count.length);
        //divide count by 2
        for(int i = 0 ; i < available_spots.length; i++){
         available_spots[i] /= 2;
        }
        char[] cs = str.toCharArray();
        for(int i = 0; i < cs.length; i++){
            char c = cs[i];
            //ana_count[c-'a'] records how many c we have ahead
            ana_count[c-'a'] --;
            
            //skip the loop
            //if c won't get put, there is no reason it should cause the pop of previous element
            if(available_spots[c - 'a'] <= 0){
             continue;
            }
            
            //the third boolean being true means we still have enough c ahead to fill in the res available spots, feel free to move the current one
            while(stack.size() > 0 && stack.peek() > c && (ana_count[stack.peek()-'a'] >  available_spots[stack.peek()-'a'])){
                char h = stack.pop();
                available_spots[h - 'a'] ++;
            }
            
            //only push c if the available spots are more than zero
            //think why this will sccuceed
            //when we reach here and avaialbe_spots[c-'a'] == 0, the elements on the top of the stack either 1. smaller than c or 2. not having enough count ahead
            //it is to understand the second situation that we don't want to push c
            //how about the first?  we have the same c in the stack why we give up the current one? The reason is think about why the previous c are not poped, that is because some greater chars are short of count, so it is necessary to keep those c
            available_spots[c - 'a'] --;
            stack.push(c);
        }
        
        StringBuilder sb = new StringBuilder();
        while(stack.size() > 0){
            sb.append(stack.pop());
        }
        
        //now sb contains the reverse of min
        //reverse it
        sb = sb.reverse();
        return sb.toString();
    }
}