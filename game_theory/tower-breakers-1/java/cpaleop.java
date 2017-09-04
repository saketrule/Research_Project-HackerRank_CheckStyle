import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        
        while(cases-- > 0)
        {
            int n = input.nextInt();
            int m = input.nextInt();
            
            System.out.println((m == 1 || n % 2 == 0 ? "2" : "1"));
        }
    }
}