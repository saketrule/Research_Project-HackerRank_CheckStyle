import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
       Scanner in = new Scanner(System.in);
        int test = in.nextInt();
        for(int i=0;i<test;i++){
            int j = in.nextInt();
            if(j%7==1 || j%7==0){
                System.out.println("Second");
            }
            else
                System.out.println("First");
        }
    }
}