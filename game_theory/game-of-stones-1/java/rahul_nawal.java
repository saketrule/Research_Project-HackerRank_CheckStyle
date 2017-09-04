import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static boolean func(int n){
        boolean[] arr = new boolean[n+1];
        arr[0] = false;
        arr[1] = false;
        
        for(int i=2;i<=n;i++){
            if(i-2 >= 0){
                arr[i] = !arr[i-2];
            }
            if(i-3>=0){
                arr[i] = arr[i] || !arr[i-3];
            }
            if(i-5 >= 0){
                arr[i] = arr[i] || !arr[i-5];
            }
        }
        return arr[n];
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        
        for(int i=0;i<t;i++){
            int n = Integer.parseInt(br.readLine());
            boolean flag = func(n);
            if(flag){
                System.out.println("First");
            }
            else{
                System.out.println("Second");
            }
        }
    }
}