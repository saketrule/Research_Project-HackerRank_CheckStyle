import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        System.out.println(solve(str.toCharArray()));
    }

    public static String solve(char[] str) {
        int n = str.length / 2;
        for (int i = 0; i < n; i++) {
            char tmp = str[i];
            str[i] = str[str.length - i - 1];
            str[str.length - i - 1] = tmp;
        }

        List<List<Integer>> charIndex = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            charIndex.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < str.length; i++) {
            charIndex.get(str[i] - 'a').add(i);
        }

        int[] index = new int[26];
        int[] next = new int[26];
        for (int i = 0; i < 26; i++) {
            index[i] = charIndex.get(i).size() / 2;
            next[i] = 0;
        }
        StringBuilder sb = new StringBuilder();
        int cur = 0;
        while (sb.length() < n) {
            int minLastIndex = str.length;
            int minLastChar = 25;
            for (int i = 0; i < 26; i++) {
                if (index[i] < charIndex.get(i).size()) {
                    if (charIndex.get(i).get(index[i]) < minLastIndex) {
                        minLastChar = i;
                        minLastIndex = charIndex.get(i).get(index[i]);
                    }
                }
            }


            while (cur <= minLastIndex) {
                int i;
                for (i = 0; i <= minLastChar; i++) {
                    while (next[i] < charIndex.get(i).size() && charIndex.get(i).get(next[i]) < cur) {
                        next[i]++;
                    }
                    if (index[i] < charIndex.get(i).size()
                        && next[i] < charIndex.get(i).size()
                        && charIndex.get(i).get(next[i]) <= minLastIndex) {
                        cur = charIndex.get(i).get(next[i]) + 1;
                        next[i]++;
                        index[i]++;
                        sb.append((char)('a' + i));
                        break;
                    }
                }
                if (i == minLastChar) {
                    break;
                }
            }
        }
        return sb.toString();
    }


}