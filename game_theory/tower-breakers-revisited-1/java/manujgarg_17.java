import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static int countPrime(int num){
        if(num==1){
            return 0;
        }
        int s=(int)Math.sqrt(num)/1;
        for(int j=2; j<=s; j++){
            if(num%j==0){
                return 1+countPrime(num/j);
            }
        }
        return 1;
    }
    public static void main(String[] args) {
        Scanner read=new Scanner(System.in);
        int T=read.nextInt();
        for(int i=0; i<T; i++){
            int N=read.nextInt();
            int ans=0;
            for(int j=0; j<N; j++){
                ans=ans^countPrime(read.nextInt());
            }
            if(ans==0){
                System.out.println(2);
            }
            else{
                System.out.println(1);
            }
        }
    }
}