import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        for (int i=0; i<T; i++) {
            int n = scan.nextInt();
            int s = 0;
            for (int j=0; j<n; j++) {
                s = s ^ scan.nextInt();
            }
            if (s==0) {
                System.out.println("Second");
            }
            else {
                System.out.println("First");
            }
        }
    }
}