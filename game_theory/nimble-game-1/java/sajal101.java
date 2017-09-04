import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        int num,count;
        int[] arr;
        for(int i=0;i<t;i++) {
            num = scan.nextInt();
            arr = new int[num];
            count=0;
            for(int j=0;j<num;j++) {
                arr[j] = scan.nextInt();
                if(arr[j]>0) {
                    if(arr[j]%2==0)
                        count ^= 0;
                    else
                        count ^= j;   
                }
            }
            System.out.println(count==0?"Second":"First");
        }
    }
}