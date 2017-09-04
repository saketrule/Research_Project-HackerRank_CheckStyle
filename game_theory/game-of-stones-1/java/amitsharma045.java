import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
       Scanner scan=new Scanner(System.in); 
  int n=scan.nextInt();
        while(n!=0){
            int m=scan.nextInt();
            if(m%7==0 || m%7==1){
                System.out.println("Second");
            }
            else{
                System.out.println("First");
            }
        }
    }
}