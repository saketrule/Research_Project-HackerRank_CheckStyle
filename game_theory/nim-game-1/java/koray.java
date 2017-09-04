import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int t = sc.nextInt() ; t > 0 ; t--)
            {
            int n = sc.nextInt();
            int xor = 0;
            for(int i = 0 ; i < n ; i ++){
                xor ^= sc.nextInt();
            }
            System.out.println(xor ==0 ? "Second" : "First");
        }
    }
}