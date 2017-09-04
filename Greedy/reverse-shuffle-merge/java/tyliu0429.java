import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(findSmall(s));
    }

    private static String findSmall(String s) {
        String revS = new StringBuffer(s).reverse().toString();
        Map<Character, Integer> dict = new HashMap<Character, Integer>();
        for (int i = 0; i < revS.length(); i++) {
            if (dict.containsKey(revS.charAt(i))) {
                dict.put(revS.charAt(i), dict.get(revS.charAt(i)) + 1);
            } else {
                dict.put(revS.charAt(i), 1);
            }
        }
        for (Character key : dict.keySet()) {
            dict.put(key, dict.get(key) / 2);
        }
        int[] needs = buildNeeds(revS, dict);
        Map<Character, List<Integer>> indexes = buildIndex(revS);
        StringBuilder sb = new StringBuilder();
        int index = 0;
        int lastIndex = -1;
        Map<Character, Integer> used = new HashMap<Character, Integer>();
        for (Character key : dict.keySet()) {
            used.put(key, 0);
        }

        while (sb.length() < revS.length() / 2) {
            while (needs[index] <= used.get(revS.charAt(index))) {
                index++;
            }
            char now = revS.charAt(index);
            
            for (char i = 'a'; i < now; i++) {
                if (used.containsKey(i) && used.get(i) < dict.get(i)) {
                    List<Integer> poses = indexes.get(i);
                    for (int pos : poses) {
                        if (used.get(i) < dict.get(i) && pos < index &&
                                (lastIndex == -1 || pos > lastIndex)) {
                            sb.append(i);
                            used.put(i, used.get(i) + 1);
                            lastIndex = pos;
                        }
                    }
                }
            }
            List<Integer> poses = indexes.get(now);
            for (int pos : poses) {
                if (pos <= index && (lastIndex == -1 || pos > lastIndex)) {
                    sb.append(now);
                    used.put(now, used.get(now) + 1);
                    lastIndex = pos;
                    break;
                }
            }
        }

        return sb.toString();
    }

    private static Map<Character, List<Integer>> buildIndex(String s) {
        Map<Character, List<Integer>> indexes = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!indexes.containsKey(c)) {
                indexes.put(c, new ArrayList<Integer>());
            }
            indexes.get(c).add(i);
        }
        return indexes;
    }

    private static int[] buildNeeds(String s, Map<Character, Integer> dict) {
        Map<Character, Integer> map = new HashMap<>(dict);
        int[] needs = new int[s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            needs[i] = map.get(c);
            if (needs[i] > 0) {
                map.put(c, map.get(c) - 1);
            }
        }

        return needs;
    }
}