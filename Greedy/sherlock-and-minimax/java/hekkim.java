import java.util.Scanner;
import java.util.Arrays;
import java.lang.Math;

public class Solution {
    public static int minDiffIndex(int[] arr, int num) {
        int left = 0;
        int right = arr.length-1;
        int center = (left+right)/2;
        
        while(left < right) {
            if(arr[center] == num)
                return center;
            else if(arr[center] > num)
                right = center-1;
            else
                left = center+1;
            center = (left+right)/2;
        }

        if(center == 0) {
            int arr_c = arr[center];
            int arr_r = arr[center+1];
            if(Math.abs(num-arr_c) < Math.abs(num-arr_r))
                return center;
            else
                return center+1;
        }
        else if(center == arr.length-1){
            int arr_c = arr[center];
            int arr_l = arr[center-1];
            if(Math.abs(num-arr_c) < Math.abs(num-arr_l))
                return center;
            else
                return center-1;
        }
        else {
            int arr_l = arr[center-1];
            int arr_c = arr[center];
            int arr_r = arr[center+1];
            if(Math.abs(num-arr_r) < Math.abs(num-arr_l)) {
                if(Math.abs(num-arr_c) < Math.abs(num-arr_r))
                    return center;
                else
                    return center+1;
            }
            else {
                if(Math.abs(num-arr_c) < Math.abs(num-arr_l))
                    return center;
                else
                    return center-1;
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        
        for(int i=0 ; i<n ; i++) {
            arr[i] = sc.nextInt();
        }
        
        int p = sc.nextInt();
        int q = sc.nextInt();
        
        Arrays.sort(arr);
        int max = -1;
        int max_index = 0;
        int index = minDiffIndex(arr, p);
        
        if(p >= arr[arr.length-1])
            max_index = p;
        else if(q <= arr[0])
            max_index = q;
        else {
            for(int i=p ; i<=q ; i++) {
                // If index needs to be updated, update it
                if(index < arr.length-1 &&
                   Math.abs(arr[index]-i) > Math.abs(arr[index+1]-i))
                        index++;

                // Compare with a number in array pointed by index
                if(Math.abs(arr[index]-i) > max) {
                    max = Math.abs(arr[index]-i);
                    max_index = i;
                }
            }
        }
        
        System.out.println(max_index);
    }
}