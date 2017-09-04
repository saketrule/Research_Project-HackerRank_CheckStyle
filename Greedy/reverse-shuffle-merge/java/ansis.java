import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    

    static void rev(char[] a) {
        int lo=0, hi=a.length-1;
        while (lo<hi) {
            char tmp = a[lo];
            a[lo++] = a[hi];
            a[hi--] = tmp;
        }
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        char[] S = in.next().toCharArray();
        rev(S);
        
        int[] alph = new int[26];
        for (char c : S) {
            alph[c-97]++;
        }
        
        // A will have exactly half of the characters
        for (int i=0; i<26; i++) alph[i] /= 2;
        
        int[] firstOccurrence = new int[26];
        for (int i=0; i<26; i++) firstOccurrence[i] = -1;
        
        int[][] toTheRight = new int[S.length][26];
        
        int[] nextToTheRight = new int[S.length];
        
        for (int i=S.length-2; i>=0; i--) {
            for (int j=0; j<26; j++) toTheRight[i][j] = toTheRight[i+1][j];
            toTheRight[i][S[i+1]-97]++;
        }

        for (int i=S.length-1; i>=0; i--){
            nextToTheRight[i] = firstOccurrence[S[i]-97];
            firstOccurrence[S[i]-97] = i;
        }
        
        //System.out.println(Arrays.toString(alph));
        
        
        int thisLetter = 0;
        int startLetter = 0;
        int totalLetters = S.length/2;
        int globalFirstOccurrence = -1;
        String A = "";
        while (totalLetters > 0) {
            if (alph[thisLetter]==0) {
                //System.out.println("thisLetter (" + (char)(thisLetter + 97) + ") is not being searched");
                if (startLetter == thisLetter) startLetter++;
                thisLetter++;
                continue;
            }
            //System.out.println("looking for " + (char)(thisLetter + 97) + ", first occurrence at " + firstOccurrence[thisLetter] + " whereas global is " + globalFirstOccurrence);
            while (firstOccurrence[thisLetter] < globalFirstOccurrence)
                firstOccurrence[thisLetter] = nextToTheRight[firstOccurrence[thisLetter]];
            boolean canUse = true;
            for (int i=0; i<26; i++) {
                int lookingFor = i==thisLetter ? alph[i]-1 : alph[i];
                if (lookingFor==0) continue;
                if (firstOccurrence[i] <= firstOccurrence[thisLetter]) {
                    if (toTheRight[firstOccurrence[thisLetter]][i] < lookingFor) {
                        canUse = false;
                        break;
                    }
                }
                else {
                    lookingFor--; // exclude the first occurrence itself
                    if (toTheRight[firstOccurrence[i]][i] < lookingFor) {
                        canUse = false;
                        break;
                    }
                }
            }
            if (canUse) {
                alph[thisLetter]--;
                totalLetters--;
                globalFirstOccurrence = firstOccurrence[thisLetter];
                firstOccurrence[thisLetter] = nextToTheRight[firstOccurrence[thisLetter]];
                A += (char) (thisLetter + 97);
                thisLetter = startLetter;
            }
            else thisLetter++; //System.out.println("can't use it");
        }
        System.out.println(A);
        
    }
}