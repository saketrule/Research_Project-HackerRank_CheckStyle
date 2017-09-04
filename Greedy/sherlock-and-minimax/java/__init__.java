import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String[] args) {
        Scanner stdin=new Scanner(System.in);
        int n = stdin.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = stdin.nextInt();
        }
        int P = stdin.nextInt();
        int Q = stdin.nextInt();
        Arrays.sort(arr);
        int[] P_pos = search(arr, P);
        int[] Q_pos = search(arr, Q);
        int localmax = 0;
     
        int start = Math.max(0, P_pos[0]);
        int end = Math.min(Q_pos[1], arr.length - 1);
        int i = start;
        int j = P;
        int M = P;
        if (P_pos[0] < 0) {
            localmax = arr[0] - P;
            j = arr[0];
        }
        while (i < end && j <= Q && j <= arr[arr.length - 1]) {
            if (j > arr[i+1])
                i++;
            else {
                if (Math.min(arr[i+1] - j, j - arr[i]) > localmax) {
                    localmax = Math.min(arr[i+1] - j, j - arr[i]);
                    M = j;
                }
                j++;
            }
        }
        if (Q > arr[arr.length - 1] && Q - arr[arr.length - 1] > localmax)
            System.out.println(Q);
        else 
            System.out.println(M); 
    }
    
    private static int[] search(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            if (target < arr[low])
                return new int[]{low-1, low};
            if (target > arr[high])
                return new int[]{high, high+1};
            int mid = (low + high) / 2;
            if (target == arr[mid])
                return new int[]{mid, mid};
            if (target > arr[mid])
                low = mid + 1;
            else
                high = mid - 1;
        }
        return null;
    }
}