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
            int numOfTowers = in.nextInt();
            int size = in.nextInt();
            s.whoWins(numOfTowers, size);
        }
    }
    
    public void whoWins(int numOfTowers, int size){
        
        if(size == 1 || numOfTowers%2 == 0){
            System.out.println("2");
        }
        else
            System.out.println("1");
    }
}