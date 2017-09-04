import java.util.Scanner;

public class Solution {

  static String reverse(String s) {
    String res = "";
    for (int i = s.length() - 1; i >= 0; i--) {
      res = res + s.charAt(i);
    }
    return res;
  }

  static boolean isValidSubstring(String s, int i, int[] count) {
    int[] newCount = new int[26];
    for (int j = i; j < s.length(); j++) {
      newCount[s.charAt(j) - 97]++;
    }
    for (int j = 0; j < 26; j++) {
      if (newCount[j] < count[j]) {
        return false;
      }
    }
    return true;
  }

  static void reverseShuffleMerge(String s) {
    s = reverse(s);
    int[] charCount = new int[26];
    for (int i = 0; i < s.length(); i++) {
      charCount[s.charAt(i) - 97]++;
    }
    int tar = 0;
    int[] needed = new int[26];
    for (int i = 0; i < 26; i++) {
      needed[i] = charCount[i] / 2;
      tar += needed[i];
    }
    boolean[] choosed = new boolean[s.length()];
    int current = -1;
    int length = 0;
    boolean chooseflag = false;
    while (length < tar) {
      for (int i = 0; i < 26 && !chooseflag; i++) {
        if (needed[i] > 0) {
          for (int j = current + 1; j < s.length(); j++) {
            if (s.charAt(j) == i + 97) {
              if (isValidSubstring(s, j, needed)) {
                choosed[j] = true;
                current = j;
                needed[i]--;
                length++;
                chooseflag = true;
              }
              break;
            }
          }
        }
      }
      chooseflag = false;
    }
    for (int i = 0; i < choosed.length; i++) {
      if (choosed[i])
        System.out.print(s.charAt(i));
    }
  }

  public static void main(String[] args) {
    /*
     * Enter your code here. Read input from STDIN. Print output to STDOUT. Your
     * class should be named Solution.
     */
    Scanner in = new Scanner(System.in);
    reverseShuffleMerge(in.nextLine());
  }
}