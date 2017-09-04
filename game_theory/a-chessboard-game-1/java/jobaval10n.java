import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
        
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.nextLine();//discard the first line
        while (sc.hasNext()) {
            String [] strCoords = sc.nextLine().split(" ");
            int x = Integer.parseInt(strCoords[0]);
            int y = Integer.parseInt(strCoords[1]);
            if ( ((x % 4 == 1) || (x % 4 == 2)) && ((y % 4 == 1) || (y %4 == 2)) ) {
                System.out.println("Second");
            } else {
                System.out.println("First");
            }
        }
        sc.close();
    }
} 