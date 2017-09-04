import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        Solution s = new Solution();
        int n = in.nextInt();
        for(int i=0;i<n;i++){
            int stones = in.nextInt();
            s.whowins(stones);
        }
    }
    
    public void whowins(int stones){
        if(stones%7 == 0 || stones%7 == 1) {
            System.out.println("Second");
        }
        else{
            System.out.println("First");
        }
    }
}