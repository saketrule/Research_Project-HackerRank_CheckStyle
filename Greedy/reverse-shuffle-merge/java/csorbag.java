import java.util.Scanner;

public class Solution {

 private final static int alphas = 'z' - 'a' + 1;

 public static void main(String[] args) {

  try (Scanner scanner = new Scanner(System.in)) {
   System.out.println(solve(scanner.next()));

  }

 }

 private static String search(int[] searchChars,
   int[][] charsLeftAtPosition, String inputReveresed) {

  char[] result = new char[inputReveresed.length() / 2];

  int lastPosSelected = -1;

  for (int i = 0; i < result.length; i++) {

   for (int j = 0; j < alphas; j++) {

    if (searchChars[j] == 0) {
     // Char not needed
     continue;
    }

    int charIndex = inputReveresed.indexOf(j + 'a',
      lastPosSelected + 1);
    if (charIndex < 0) {
     // Char not found in remaining string
     continue;
    }

    // Check if all needed characters are present from the given position --> found next char
    boolean allCharsPresent = true;
    for (int k = 0; k < searchChars.length; k++) {
     if (charsLeftAtPosition[charIndex][k] < searchChars[k]) {
      allCharsPresent = false;
      break;
     }
    }
    if (allCharsPresent) {
     result[i] = (char) (j + 'a');
     searchChars[j]--;
     lastPosSelected = charIndex;
     break;
    }
   }

  }

  return new String(result);
 }

 private static String solve(String input) {

  String inputReversed = new StringBuilder(input).reverse().toString();

  // Number of each chars needed in the result
  int[] charCounts = new int[alphas];

  for (char c : inputReversed.toCharArray()) {
   charCounts[c - 'a']++;
  }

  // Count of each alphabet left from a given position in the input
  int[][] charCountsAfterPosition = new int[inputReversed.length()][alphas];

  charCountsAfterPosition[0] = charCounts.clone();

  for (int i = 1; i < inputReversed.length(); i++) {
   charCountsAfterPosition[i] = charCountsAfterPosition[i - 1].clone();
   charCountsAfterPosition[i][inputReversed.charAt(i - 1) - 'a']--;
  }

  for (int i = 0; i < charCounts.length; i++) {
   charCounts[i] /= 2;
  }

  return search(charCounts, charCountsAfterPosition, inputReversed);
 }

}