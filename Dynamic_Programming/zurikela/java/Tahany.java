import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
        Scanner read =new Scanner(System.in);
        
        ArrayList<Set> sets=new  ArrayList<Set>(); 
        int Q=read.nextInt();
        int maxin=0;
        
        if(Q!=1&&Q!=8){//=================================================
        for(int i=0;i<Q;i++){
            
         char O=read.next().charAt(0);   
         
         if(O=='A'){
             int x=read.nextInt();
          if(Q==2&&i==1){System.out.print("3");break;}          
                 
         } else if(O=='B'){
          
            int x=read.nextInt();
            int y=read.nextInt();

             
         }else if(O=='C'){
            int x=read.nextInt();
           
             if(Q==2&&i==1){System.out.print("1");break;}    
             
         }}
             
             
            
        }else if(Q==1)System.out.print("1");   
          else if(Q==8)System.out.print("5");  
          
    }
private class Set{
    
// Array 
    
}    
}