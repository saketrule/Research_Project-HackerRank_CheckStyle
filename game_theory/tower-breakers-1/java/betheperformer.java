import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int N = 0, M = 0;
        while (T>0) {
            N = sc.nextInt();
            M = sc.nextInt();
            if (N%2==0) {
                System.out.println("2");
            } else {
                if (M==1) {
                    System.out.println("2");
                } else {
                    System.out.println("1");
                }                
            }
            T--;
    }
    }
}