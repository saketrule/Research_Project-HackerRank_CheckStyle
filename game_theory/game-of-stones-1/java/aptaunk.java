import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int t=0; t<T; t++) {
            int n = sc.nextInt()+1;
            boolean[] wins = new boolean[n];
            for (int i=0; i<n; i++) {
                if (  i-2 >= 0 && !wins[i-2]
                   || i-3 >= 0 && !wins[i-3]
                   || i-5 >= 0 && !wins[i-5]) {
                    wins[i] = true;
                } else {
                    wins[i] = false;
                }
            }
            if (wins[n-1]) {
                System.out.println("First");
            } else {
                System.out.println("Second");
            }
        }
    }
}