import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println(canWin(sc.nextInt(), sc.nextInt()) ? 1 : 2);
        }
    }
    
    public static boolean canWin(int n, int m) {
        if (m == 1) return false;
        else {
            return n % 2 == 1;
        }
    }
}