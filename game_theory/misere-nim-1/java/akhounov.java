import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i=0;i<t;i++) {
            int n = sc.nextInt();
            int nim_sum = 0;
            boolean redux = true;
            boolean even = true;
            for(int j=0;j<n;j++) {
                int s = sc.nextInt();
                nim_sum ^= s;
                if (s>=2) {
                    redux = false;
                } else {
                    even = !even;
                }
            }
            if (redux) {
                System.out.println(even?"First":"Second");
            } else {
                System.out.println(nim_sum!=0?"First":"Second");
            }
        }
    }
}