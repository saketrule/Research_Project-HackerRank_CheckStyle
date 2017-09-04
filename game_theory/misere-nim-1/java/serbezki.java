import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int g = scanner.nextInt();
        while (g-->0){
            int n = scanner.nextInt();
            int[] pile = new int[n];
            boolean hasBiggerThanOne = false;
            for (int i = 0; i < n; i++){
                pile[i] = scanner.nextInt();
                if (pile[i] > 1) hasBiggerThanOne = true;
            }
            int xorSum = pile[0];
            for (int i = 1; i < n; i++){
                xorSum ^= pile[i];
            }
            if (((xorSum == 0) && hasBiggerThanOne) || ((xorSum == 1) && !hasBiggerThanOne)) System.out.println("Second");
            else System.out.println("First");
        }
    }
}