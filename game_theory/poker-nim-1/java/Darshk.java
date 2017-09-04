import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt(); // Test cases
        scanner.nextLine();
        
        while (T-->0) {
            scanner.nextLine();
            String c[]=(scanner.nextLine().split(" "));
            int ans = 0;
            for(int i =0;i<c.length ;i++){
                int x= Integer.parseInt(c[i]);
                //System.out.println(x);
                    ans= ans^x;
                }
            
            
            if(ans==0) System.out.println("Second");
            else System.out.println("First");
                        }
                                      }
                        }