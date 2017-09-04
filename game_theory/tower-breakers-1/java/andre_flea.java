import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCase = in.nextInt();
        for(int i=0; i<testCase; i++) {
            int towers = in.nextInt();
            int height = in.nextInt();
            boolean firstWin = true;
            if(height == 1 || towers %2 == 0)
                firstWin = false;
            System.out.println(firstWin ? 1 : 2);
        }
    }
}