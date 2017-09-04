import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int g = s.nextInt();
        
        for(int i = 0; i < g; i++){
            int piles = s.nextInt();
            int result = s.nextInt();
            boolean higher = false;
            
            for(int j = 0; j < piles - 1; j++){
                int t = s.nextInt();
                result = result ^ t;
                if(t > 1 && !higher) higher = true;
            }
            System.out.println((result == 0 && higher) || (result == 1 && !higher) ? "Second" : "First");
        }
        
    }
}