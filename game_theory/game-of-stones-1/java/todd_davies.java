import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    private static final HashMap<Integer, Boolean> cache = new HashMap<>();
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println(doesWin(sc.nextInt()) ? "First" : "Second");
        }
    }
    
    private static boolean doesWin(int i) {
        if (i == 0 || i == 1) return false;
        else if (i <= 6) return true;
        else {
            if (cache.containsKey(i)) return cache.get(i);
            boolean ans = !doesWin(i - 5) || !doesWin(i - 3) || !doesWin(i - 2);
            if (i % 50 == 0) cache.put(i, ans);
            return ans;
        }
    }
}