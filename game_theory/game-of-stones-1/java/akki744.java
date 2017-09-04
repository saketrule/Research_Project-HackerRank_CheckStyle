import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        int t;
        Scanner sc = new Scanner(System.in);
        t=sc.nextInt();
        while(t-->0){
            int n=sc.nextInt();
            boolean[] arr = new boolean[n+1];
            arr[1]=false;
            for(int i=2; i<=n&&i<=6; i++){
                arr[i]=true;
            }
            for(int i=7; i<=n; i++){
                boolean and=arr[i-2]&arr[i-3]&arr[i-5];
                if(and){
                    arr[i]=false;
                }else{
                    arr[i]=true;
                }
            }
            if(arr[n]){
                System.out.println("First");
            }else{
                System.out.println("Second");
            }
        }
    }
}