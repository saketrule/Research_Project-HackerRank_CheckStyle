import java.util.*;

public class Solution {
 
 public static void main(String[] args) {
  
   Scanner sc = new Scanner(System.in);

    String s = sc.next();
    s = new StringBuilder(s).reverse().toString();
    final int alphaSize = 26;
    int[] count = new int[alphaSize];
    for (int i = 0; i < s.length(); i++)
     ++count[s.charAt(i) - 'a'];
    int needLength = 0;
    for (int i = 0; i < alphaSize; i++) {
     if (count[i] % 2 != 0)
      throw new AssertionError();
     count[i] /= 2;
     needLength += count[i];
    }
    StringBuilder result = new StringBuilder();
    int[][] counts = new int[s.length()][alphaSize];
    for (int i = s.length() - 1; i >= 0; i--) {
     for (int j = 0; j < alphaSize; j++)
      counts[i][j] = (i + 1 == s.length() ? 0 : counts[i + 1][j]);
     counts[i][s.charAt(i) - 'a']++;
    }
    int leftPointer = 0;
    for (int it = 0; it < needLength; it++) {
     int resultIndex = -1;
     for (int i = leftPointer; i < s.length(); i++) {
      if (count[s.charAt(i) - 'a'] > 0) {
       if (resultIndex == -1
         || s.charAt(i) < s.charAt(resultIndex)) {
        if (isOk(count, counts[i]))
         resultIndex = i;
       }
      }
     }
     result.append(s.charAt(resultIndex));
     --count[s.charAt(resultIndex) - 'a'];
     leftPointer = resultIndex + 1;

    }
    System.out.println(result);
   

   sc.close();
 }

 private static boolean isOk(int[] a, int[] b) {
  for (int i = 0; i < a.length; i++)
   if (a[i] > b[i])
    return false;

  return true;
 }
}