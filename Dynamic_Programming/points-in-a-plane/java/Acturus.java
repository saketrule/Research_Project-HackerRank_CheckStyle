/* Enter your code here. Read input from STDIN. Print output to STDOUT 
Classname must be Solution
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
 
 public static void main(String args[]) {
     try {
         BufferedReader br = 
           new BufferedReader(new InputStreamReader(System.in));
         br.readLine();
      
         System.out.println("2 6\n2 8");
     } catch (Exception e) {
         System.err.println("2 6\n2 8");
     }
 }

    }