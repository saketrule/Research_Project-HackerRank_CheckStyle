import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        for(int i=0; i<a; i++){
            
            int n = scan.nextInt();
            int m = scan.nextInt();
            
            if(n == 1 || m%2 == 0){
            System.out.println("2");
            }
            else {
            System.out.println("1");
        }
    }
    }
}