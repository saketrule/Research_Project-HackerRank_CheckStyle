import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        String reverse = new StringBuilder(s).reverse().toString();
        int[] count = new int[26];
        for(int i = 0; i < s.length(); i++)
            count[s.charAt(i) - 'a'] += 1;
        
        int[] need = new int[26];
        for(int i = 0; i < 26; i++)
            need[i] = count[i]/2;
        
        StringBuilder solution = new StringBuilder();
        int i = 0;
        while(solution.length() < s.length()/2){
            int first_char_at = -1;
            // Find the first character in the solution string that we need
            while(true){
                char c = reverse.charAt(i);
                if(need[c - 'a'] > 0 && (first_char_at < 0 || c < reverse.charAt(first_char_at)))
                    first_char_at = i;
                count[c - 'a'] -= 1;
                if(count[c - 'a'] < need[c - 'a'])
                    break;
                i++;
            }
            for(int j = first_char_at + 1; j < i + 1; j++)
                count[reverse.charAt(j) - 'a']++;
            need[reverse.charAt(first_char_at) - 'a']--;
            solution.append(reverse.charAt(first_char_at));
            
            i = first_char_at + 1;
        }
        System.out.println(solution.toString());

    }
}