import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while(t-->0){
            int x= in.nextInt();
            int y= in.nextInt();
            x=x%4; 
            y=y%4;
            if((y==0)||(y==3)||(x==0)||(x==3)) 
                System.out.println("First");
            else
                System.out.println("Second");
        }
        
    }
}