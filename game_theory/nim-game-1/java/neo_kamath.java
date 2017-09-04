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
            int tot = 0;
            for(int j=0;j<N;j++){
                tot ^= sc.nextInt();
            }
            if(tot == 0) {
                System.out.println("Second");
            } else {
                System.out.println("First");
            }
        }
    }
}