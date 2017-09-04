import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for(int test = 0; test < n; test++) {
            int size = in.nextInt();
            int result = 0;
            for(int i = 0; i < size; i++){
                long next = in.nextLong();
                if(i > 0 && next % 2 == 1){
                    result ^= i;
                }
            }            
            System.out.println(result == 0 ? "Second" : "First");
        }
    }
}