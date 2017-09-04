import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tests = sc.nextInt();
        for(int i = 0 ; i < tests ; i++) {
            int towers = sc.nextInt();
            boolean win = false;
            for(int j = 0 ; j < towers ; j++ ) {
                int height = sc.nextInt();
                if(height == 1) {
                    continue;
                }
                else {
                    win = !win;
                }
            }
            
            if(win) {
                System.out.println("1");
            }
            else {
                System.out.println("2");
            }
        }
    }
}