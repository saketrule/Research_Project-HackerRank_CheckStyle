import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan=new Scanner(System.in);
        int t=scan.nextInt();
        for(int i=0;i<t;i++){
            int x=scan.nextInt();
            int y=scan.nextInt();
            if(makeAmove(x,y))
                System.out.println("First");
            else
                System.out.println("Second");
        }
    }
    
   private static boolean makeAmove(int x,int y){
        if(x<=15 && y<=15 && x>=1 && y >=1){
         //System.out.println("X "+x+" Y "+y);
            int x1=x-2;       
            int x2=x+1;
            int x3=x-1;
            int y1=y+1;
            int y2=y-1;
            int y3=y-2;
            if(makeAmove(x1,y1)){
                if(makeAmove(x1,y2)){
                    if(makeAmove(x2,y3)){
                        if(makeAmove(x3,y3))
                                return false;
                else
                return true;
                    }else
                return true;
                } else
                return true;
            }else
                return true;
        }           
        else
            return true;                              
    }      
}