import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        
        int t = in.nextInt();
        for (int i=0;i<t;i++) {
            int n = in.nextInt();
            int[] aa = new int[n];
            int sum =0;
            int suma = 0;
            for (int j=0;j<n;j++) {
                aa[j] = in.nextInt();
                sum ^= aa[j];
                suma += aa[j];
            }
//            System.out.println(sum);
//            System.out.println(suma+" "+suma/n);
            if (suma/n==1) {
                if (n%2==0) {
                    System.out.println("First");
                } else {
                    System.out.println("Second");
                }
            } else {
                if (sum==0) {
                    System.out.println("Second");
                } else {
                    System.out.println("First");
                }
            }
        }
        
    }
}