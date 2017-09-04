import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int T = sc.nextInt();
        
        for(int i=0;i<T;i++){
            int N = sc.nextInt();
            int M = sc.nextInt();
            
            if(M==1||N%2==0) {
                System.out.println("2");
            } else {
                System.out.println("1");
            }
        }
    }
}