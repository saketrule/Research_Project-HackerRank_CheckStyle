import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int i = 0; i < T; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            x = x%4;
            y = y%4;
            System.out.println(x==0||x==3||y==0||y==3 ? "First" : "Second");
        }
    }
}