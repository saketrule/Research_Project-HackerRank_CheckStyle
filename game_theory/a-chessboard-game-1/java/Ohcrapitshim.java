import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 <= t; a0++){
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            if (x%4 <= 1 && y%4 <= 1){
                System.out.println("Second");
            }else {
                System.out.println("First");
            }
        }
    }
}