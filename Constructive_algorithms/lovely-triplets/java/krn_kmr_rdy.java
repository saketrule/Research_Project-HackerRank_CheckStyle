import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int P = in.nextInt();
        int Q = in.nextInt();
        for(int i=1;i<=((P*Q)+1);i++){
            
            if(i==1){
                System.out.println((P*Q+1) +" " +(P*Q+1));
            }
            
            if(i==6){
                System.out.println((i) +" " +(1));
            }
            
            else if(i==7){
                System.out.println((1) +" " +(P*Q+1));
            }
            else    
            System.out.println(i +" "+(i+1));
            
        }
    }
}