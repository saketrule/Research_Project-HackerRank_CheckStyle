import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int[] res = new int[100];
        res[0] = 2; 
        for(int i = 1; i < 100; i++){
            if(i <= 5){
                res[i] = 1;
                continue;
            }
            if(res[i - 2] == 1 && res[i - 3] == 1 && res[i - 5] == 1)
                res[i] = 2;
            else
                res[i] = 1;
        }
        int T = in.nextInt();
        while(T > 0){
            System.out.println(res[in.nextInt() - 1] == 1? "First": "Second");
            T--;
        }
    }
}