import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        
        for(int i=0; i<t; i++){
            int c = 0;
            int n = in.nextInt();
            int res = 0;
            for(int j=0; j<n; j++){
                int arr = in.nextInt();
                if(j == 0)
                    c = arr;
                if(arr>1)c = 0;
                res ^= arr;
            }
            if(res != c){
                System.out.println("First");
            }else{
                System.out.println("Second");
            }
        }
    }
}