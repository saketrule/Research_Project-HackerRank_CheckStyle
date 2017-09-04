import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int noOfTests=in.nextInt();
        long[] arr;
        for(int i=0;i<noOfTests;i++){
            int length=in.nextInt();
            arr= new long[length];
            for(int j=0;j<length;j++){
                arr[j]=in.nextLong();
            }
            findWinner(arr);
        }
    }
    public static void findWinner(long[] arr){
        if(arr==null ||arr.length==0)
            return;
        long total=0;
        int temp=0;
        for(int i=0;i<arr.length;i++){
            if(!(arr[i]%2==0)){
                temp=i;
            }
            else
                temp=0;
            total=total^temp;
           
        }
           if(total==0)
            System.out.println("Second");
        
        else
              System.out.println("First");
    }
}