import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        while(T > 0){
            int N = in.nextInt();
            int M = in.nextInt();
            if(N % 2 == 0)
                System.out.println(2);
            else{
                if(M == 1)
                    System.out.println(2);
                else
                    System.out.println(1);
            }
            
            T--;
        }
    }
}