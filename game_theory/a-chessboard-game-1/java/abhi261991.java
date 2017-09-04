import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        for(int i=0;i<t;i++){
            int x=sc.nextInt();
            int y=sc.nextInt();
            if((y%4==0)||(y%4==3)||(x%4==0)||(x%4==3)){
                System.out.println("First");
            }else{
                System.out.println("Second");
            }
            
        }
    }
}