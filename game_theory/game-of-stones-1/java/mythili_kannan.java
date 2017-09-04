import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
         Scanner in = new Scanner(System.in);
         int t = in.nextInt();
         for(int i=1;i<=t;i++){
             int inpt = in.nextInt();
             if(inpt == 1 || inpt%7 == 0 || (inpt-1)%7 == 0){
                 System.out.println("Second");
             }
             else
                 System.out.println("First");
         }
    }
}