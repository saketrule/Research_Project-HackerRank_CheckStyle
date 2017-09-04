import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
          Scanner in = new Scanner(System.in);
  String input_str;
  String finalAns = new String();
  int[] charBucket = new int[26];
  int[] ansCharBucket = new int[26];

  input_str = in.nextLine();

  // convert input string to lower case
  input_str = input_str.toLowerCase();

  for (int i = 0; i < input_str.length(); i++) {
   charBucket[input_str.charAt(i) - 'a']++;
  }
  for (int i = 0; i < 26 ;i++)
   ansCharBucket[i] = charBucket[i]/2;
  

  char smallest = 'z' + 1;
  String jumbledAns = new String();
  int i = input_str.length() - 1;
  Stack<Character> charStack = new Stack<Character>();

  while (finalAns.length() < input_str.length() / 2) {

   for (; i >= 0; i--) {
    charStack.push(input_str.charAt(i));
    if (input_str.charAt(i) < smallest && ansCharBucket[input_str.charAt(i) - 'a'] > 0) {
     smallest = input_str.charAt(i);
     charStack.clear();
    }

    charBucket[input_str.charAt(i) - 'a']--;
    if (charBucket[input_str.charAt(i) - 'a'] < ansCharBucket[input_str.charAt(i) - 'a']) {
     break;
    }
   }

   while (!charStack.isEmpty()) {
    charBucket[charStack.pop() - 'a']++;
    i++;
   }

   finalAns += Character.toString(smallest);
   ansCharBucket[smallest-'a'] --;
   i--;
   smallest = 'z' + 1;

  }

  System.out.println(finalAns);
    }
}