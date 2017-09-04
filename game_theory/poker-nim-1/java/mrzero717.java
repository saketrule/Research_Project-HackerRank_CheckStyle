import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        while(T-- > 0){
            int n = s.nextInt();
            int k = s.nextInt();
            int omar = 0;
            while(n-- >0){
                int x = s.nextInt();
                omar^=x;
            }
            System.out.println((omar>0)?"First":"Second");
        }
    }
}