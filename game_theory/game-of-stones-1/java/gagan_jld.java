import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int i=0;i<T;i++){
            int n = in.nextInt();
            if (n%7==0||n%7==1)
                System.out.println("Second");
            else
                System.out.println("First");
        }
    }
}