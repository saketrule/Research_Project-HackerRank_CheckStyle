import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Sergey.Pak
 *         Date: 4/8/2015
 *         Time: 11:52 AM
 */
public class Solution {

  private static final int MIN_INDEX = 'a';
  private static final int MAX_INDEX = 'z';

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    final String word = sc.next();
    //final String word = "baaaaabbbbaaab";
    int baseCharMap[] = new int[256];
    final char[] charsRev = word.toCharArray();
    int wordLength = charsRev.length;
    final char[] wordChars = new char[charsRev.length];
    for (int i = 0; i < charsRev.length; i++) {
      wordChars[wordLength-i-1] = charsRev[i];
    }
    //System.out.println(new String(wordChars));

    for (char ch : wordChars) {
      baseCharMap[ch]++;
    }

    int totalCharsCount=0;
    for (int i = MIN_INDEX; i <= MAX_INDEX; i++) {
      baseCharMap[i] /= 2;
      totalCharsCount += baseCharMap[i];
    }
    int charMapCopy[] = Arrays.copyOf(baseCharMap, 256);
    StringBuilder baseWord = new StringBuilder();
    int position = 0;
    for (int charsLeft = totalCharsCount; charsLeft > 0; charsLeft--){
      // pick up the best
      for (char ch=MIN_INDEX; ch <=MAX_INDEX; ch++){
        if (charMapCopy[ch] > 0){
          charMapCopy[ch]--;
          final int charPos = findFirstOccurence(wordChars, position, ch);
          if (charPos >=0 && restFits(wordChars, charPos+1, charMapCopy)){
            position = charPos+1;
            baseWord.append(ch);
            break;
          } else {
            charMapCopy[ch]++;
          }
        }
      }
    }
    System.out.println(baseWord.toString());
  }

  private static int findFirstOccurence(char[] array, int startIndex, char ch){
    for (int i=startIndex; i<array.length; i++){
      if (ch == array[i])
        return i;
    }
    return -1;
  }

  private static boolean restFits(char[] array, int startIndex, int[] leftCharsMap){
    int[] result = new int[256];
    for (int i=startIndex; i<array.length; i++){
      result[array[i]]++;
    }
    for (int i = MIN_INDEX; i <= MAX_INDEX; i++) {
      if (leftCharsMap[i] > result[i]){
        return  false; // required more such symbols than actually exists
      }
    }
    return true;
  }
}