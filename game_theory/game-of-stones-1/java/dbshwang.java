import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] arr = new int[size];
        
        for(int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }
        
        for(int i = 0; i < size; i++) {
            if(arr[i] % 7 == 0 || arr[i] % 7 == 1) {
                System.out.println("Second");
            } else {
                System.out.println("First");
            }
        }
    }
}