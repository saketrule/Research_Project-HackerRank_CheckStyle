import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        String word = scan.nextLine();
        word = new StringBuilder(word).reverse().toString();
        int[] dict = new int[26];
        Arrays.fill(dict, 0);
        
        // counting the chars
        for (int i = 0; i < word.length(); i++) {
            dict[word.charAt(i) - 'a']++;
        }
        
        // divide by 2
        for (int i = 0; i < 26; i++) {
            dict[i] /= 2;
        }
        
        int bottleneck = -1;
        int[] needTofind = dict.clone();
        int targetNum = word.length() / 2;
        // find bottleneck
        for (int i = word.length() - 1; i >= 0; i--) {
            if (needTofind[word.charAt(i) - 'a'] > 0) {
                needTofind[word.charAt(i) - 'a']--;
                targetNum--;
            }
            
            if (targetNum == 0) {
                bottleneck = i;
                break;
            }
            
        }
        
        int[] after_bottleneck = new int[26];
        Arrays.fill(after_bottleneck, 0);
        for (int i = bottleneck; i < word.length(); i++) {
            after_bottleneck[word.charAt(i) - 'a']++;
        }
        // find the target word
        String res = "";
        targetNum = word.length() / 2;
        int start = 0;
        //System.out.println(word);
        for (int i = 0; i < targetNum; i++) {
            int minChar = 123;
            int index = -1;
            for (int j = start; j <= bottleneck; j++) {
                if (minChar > word.charAt(j) && dict[word.charAt(j) - 'a'] > 0) {
                    minChar = word.charAt(j);
                    index = j;
                }
            }
            char[] cur = Character.toChars(minChar);
            res += cur[0];
            //System.out.println(res + " " + bottleneck + " " + index);
            dict[cur[0] - 'a']--;
            
            // check need to move bottle neck
            //System.out.println(index);
            while(bottleneck < word.length() && dict[word.charAt(bottleneck) - 'a'] < after_bottleneck[word.charAt(bottleneck) - 'a']) {
                after_bottleneck[word.charAt(bottleneck) - 'a']--;
                bottleneck++;
            }
            
            
            start = index + 1;

            
        }
        
        System.out.println(res);
        
    }
}