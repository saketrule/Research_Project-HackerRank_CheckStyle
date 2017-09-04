import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int k = input.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++)
            {
            arr[i] = input.nextInt();
        }
        int start =0;
        int end = arr.length-1;
        if(k<=((end-start)/2))
            {
            System.out.println((end-start)/2);
        }
    }
}