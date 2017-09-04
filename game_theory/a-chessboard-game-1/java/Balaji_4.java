import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
        
    int t = s.nextInt();
    for(int ti=0;ti<t;ti++){
        int x = s.nextInt();
        int y = s.nextInt();
        x = x%4; //15x15
        y = y%4;
        if((y==0)||(y==3)||(x==0)||(x==3)){
            System.out.println("First");
        }else{
            System.out.println("Second");
        }
        
    }
    }
    
    
}