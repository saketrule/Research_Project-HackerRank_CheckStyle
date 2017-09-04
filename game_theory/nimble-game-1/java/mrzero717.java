import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
       Scanner s = new Scanner(System.in);
       int T = s.nextInt();
       while(T-->0){
            int n = s.nextInt();
            int omar=0;
            for(int i =0;i<n;++i){
                int x=s.nextInt();
                x%=2;
                if(x==1)
                    omar^=i;
            }
           System.out.println((omar>0)?"First":"Second");
       }
    }
}