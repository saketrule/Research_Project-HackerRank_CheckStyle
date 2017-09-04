import java.util.*;

public class Solution {
 public static void main(String[] args) {
  
  Scanner in = new Scanner(System.in);
  StringBuilder line = new StringBuilder(in.nextLine());

  solve(line.reverse().toString());
 }

 public static void solve(String s) {
  int[] letters = new int[26];

  tally(s, letters);

  for (int i = 0; i < letters.length; i++) {
   letters[i] /= 2;
  }

  char c = 'a';

  for (int i = 0; i < letters.length; i++) {
   if (letters[i] > 0) {
    c = (char)('a' + i);
    break;
   }
  }

  solve(s, letters, c, 0, "");
 }

 public static void tally(String s, int[] letters) {
  for (int i = 0; i < s.length(); i++) {
   letters[s.charAt(i) - 'a']++;
  }
 }

 public static boolean solve(String s, int[] letters, char letter, int index, String a) {
  int loc = s.indexOf(letter, index);
  int half = s.length() / 2;

  if (loc == -1) {
   return false;
  }

  if (s.length() - loc < half - (a.length() + 1)) {
   return false;
  }

  int[] rem = new int[26];
  tally(s.substring(loc), rem);

  for (int i = 0; i < letters.length; i++) {
   if (rem[i] < letters[i]) {
    return false;
   }
  }

  if (a.length() + 1 == half) {
   System.out.println(a + letter);
   return true;
  }

  letters[letter - 'a']--;

  for (int i = 0; i < letters.length; i++) {
   if (letters[i] > 0) {
    if (solve(s, letters, (char)('a' + i), loc + 1, a + letter)) {
     return true;
    }
   }
  }

  letters[letter - 'a']++;

  return false;
 }
}