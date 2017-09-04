import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); 
  
  int T = scan.nextInt();
        for (int i=0; i<T; i++) {
            int x = scan.nextInt() % 4;
            int y = scan.nextInt() % 4;
            if (x > 0 && x < 3 && y > 0 && y < 3) {
                System.out.println("Second");
            }
            else {
                System.out.println("First");
            }
        }
  scan.close();
    }
}