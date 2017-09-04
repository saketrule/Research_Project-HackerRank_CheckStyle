import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    // S = merge(reverse(A), shuffle(A)).  Find smallest lexical A.
    // Reversing S, gives S = merge(A, shuffle(A))
    private static String smallestSource(String sIn) {
        String s = new StringBuffer(sIn).reverse().toString();
        
        // An array of all of the character frequencies in A.
        //  Only need 26 values here, but avoids adding/subtracting 'a' later.
        int[] freqA = new int[128];
        
        // An array with the counts of characters remaining in S.
        //  If s[3] = 'a', charCount[3] = count of 'a's with index >= 3.
        int[] remainingCount = new int[s.length()];

        for (int i=s.length()-1;  i >= 0;  i--) {
            char c = s.charAt(i);
            freqA[c]++;
            remainingCount[i] = freqA[c];
        }
        for (int i='a';  i <= 'z';  i++) freqA[i] /= 2;

        StringBuffer A = new StringBuffer();

        int smallestChar = 'a';  // The smallest lexical value remaining for A
        int pos = 0;
        while (A.length() < s.length()/2) {
            for (;  smallestChar <= 'z' && freqA[smallestChar] == 0;  smallestChar++) {}
            char smallestFound = '~';
            int smallestPos = pos;
            
            // Starting at the current position, scan forward until we find a character we can no longer ignore...
            while (true) {
                char c = s.charAt(pos);
                // Is this character one that's remaining for A?
                if (freqA[c] > 0) {
                    // Check if this char is lexically smaller than the last char found...
                    if (c < smallestFound) {
                        smallestFound = c;
                        smallestPos = pos;
                    }
                    
                    // If this char is the smallest lexical character remaining for A,
                    //  OR we can't ignore the character, because there are exactly N left, and we need them all for A
                    if (c == smallestChar || remainingCount[pos] == freqA[c]) {
                        // but there might have been a smaller char found earlier take it, back-up, and resume scan
                        A.append((char)(smallestFound));
                        freqA[smallestFound]--;
                        pos = smallestPos + 1;
                        break;
                    }
                }
                pos++;
            }            
        }
        
        return A.toString();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        System.out.println(smallestSource(line));
    }
}