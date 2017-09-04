import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        int t;
        Scanner sc = new Scanner(System.in);
        t=sc.nextInt();
        while(t-->0){
            int n=sc.nextInt();
            int xor=0;
            String s="";
            while(n-->0){
                int x=sc.nextInt();
                xor^=x;
            }
            if(xor==0){
                s+="Second";
            }else{
                s+="First";
            }
            System.out.println(s);
        }
    }
}