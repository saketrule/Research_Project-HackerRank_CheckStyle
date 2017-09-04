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
            int k=sc.nextInt();
            int xor=0;
            for(int i=0; i<n; i++){
                int x=sc.nextInt();
                xor^=x;
            }
            if(xor==0){
                System.out.println("Second");
            }else{
                System.out.println("First");
            }
        }
    }
}