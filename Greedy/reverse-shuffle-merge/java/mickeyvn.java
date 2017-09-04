import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static int[] getHist(String s, boolean div2) {
        int[] result = new int[26];
        for (int i=0; i<s.length(); i++)
            result[s.charAt(i)-'a']++; 
        
        if (div2) {
            for (int i=0; i<26; i++)
                result[i] /= 2;
        }
        
        return result;
    }
    
    public static void printHist (int[] Hist) {
        for (int i=0; i<26; i++) {
            if (Hist[i] > 0) {
                char c = (char) (i + 'a');
                System.out.print(c + " (" + Hist[i] + "); ");
            }
        }
        System.out.println("");
    }
    
    public static List<Character> getSortedCharList (String s) {
        List<Character> list = new ArrayList<> ();
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (!list.contains(new Character(c)))
                list.add(new Character(c));
        }
        Collections.sort(list);
        
        return list;
    }
    
    public static List<Character> getSortedCharList (int[] Hist) {
        List<Character> list = new ArrayList<> ();
        for (int i=0; i<26; i++) {
            if (Hist[i] > 0)
                list.add(new Character((char) (i + 'a')));
        }
        
        return list;
    }
    
    public static String reverseString (String s) {
        String r = "";
        for (int i=s.length()-1; i>=0; --i)
            r += s.charAt(i);
        
        return r;
    }
    
    public static boolean isValid (int[] H, int[] h) {
        for (int i=0; i<26; i++) {
            if (h[i] > H[i])
                return false;
        }
        
        return true;
    }
    
    public static int[] subtractHist (int[] H1, int[] H2) {
        int[] H = new int[26];
        for (int i=0; i<26; i++)
            H[i] = H1[i] - H2[i];
        
        return H;
    }
    
    public static String removeFirst(String s, char c) {
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i) == c)
                return s.substring(0, i) + s.substring(i+1);
        }
        
        return s;
    }
    
    public static String removeLast(String s, char c) {
        for (int i=s.length()-1; i>=0; --i) {
            if (s.charAt(i) == c)
                return s.substring(0, i) + s.substring(i+1);
        }
        
        return s;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String M = scanner.next();
        //String M = "bdabaceadaedaaaeaecdeadababdbeaeeacacaba";
        String s = reverseString(M);
       
        String result = "";
        
        int[] shuffleHist = getHist (s, true);
        int[] stringHist  = getHist (s, true);
        int length = s.length()/2;       
        
        while (length > 0) {            
            List<Character> list = getSortedCharList (stringHist);
            if (list.isEmpty())
                break;

            for (int i=0; i<list.size(); i++) {
                char c = list.get(i).charValue();
                int k = s.indexOf(c);
                
                String shuffle = s.substring(0, k);
                int[] h = getHist(shuffle, false);
                
                if (isValid(shuffleHist, h) && k <= length) {
                    shuffleHist = subtractHist(shuffleHist, h);                    
                    stringHist[c-'a']--;
                    result += c;                    
                    s = s.substring(k+1);
                    length -= k;
                    
                    break;
                }
            }
        }        
        
        if (length == 0)
            result += s;
        
        System.out.println(result);
        //System.out.println("Expect = aaaaaabaaceededecbdb");
    }
}