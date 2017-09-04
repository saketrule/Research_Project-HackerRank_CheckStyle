import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int tests=in.nextInt();
        for (int t=0; t<tests; t++) {
            int n=in.nextInt();
            System.out.println(n%7<2 ? "Second" : "First");
        }
    }
}