import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        
        int numTests = s.nextInt();
        
        for (int i = 0; i < numTests; i++){
            int n = s.nextInt();
            int k = s.nextInt();
            
            long mask = 0;
            for (int j = 0; j<n; j++){
                int num = s.nextInt();
                mask = mask ^ num;
            }
            
            if (mask == 0){
                System.out.println("Second");
            } else {
                System.out.println("First");
            }
        }
        
    }
}