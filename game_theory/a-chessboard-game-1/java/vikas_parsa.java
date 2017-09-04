import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in=new Scanner(System.in);
        int t=in.nextInt();
        while(t>0){
            int x=in.nextInt();
            int y=in.nextInt();
            if(x%4==0||x%4==3||y%4==0||y%4==3){
                    System.out.println("First");
            }
            else{
                    System.out.println("Second");
            }
            t--;
        }
    }
}