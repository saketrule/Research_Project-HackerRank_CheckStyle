import java.util.*;

/**
 * Created by PZhang2 on 5/27/2015.
 */
public class Solution {

    private static int[] count = new int[26];       // how many occurrences is needed for each letter
    private static int[] needToWrite = new int[26]; // how many occurrences still need to write at one moment

    // indexed with source string.
    // Let's say M: needed occurrences in target string (the same as count array)
    // minPresentedSoFar: for each alpha in the source indexed at i, when reach index i (after this index), how many (N) of this alpha needs
    // to be presented in the partially completed target string. It means after index i (exclusive),
    // there are only (M-N) occurrences of the alpha.
    private static int[] minPresentedSoFar;

    private static char[] str;                      // source string

    private static char[] dst;
    private static int dstIndex = 0;

    private static int previousPos = 0;
    private static int nextBottleNeck = 0;
    
    // For each alpha, list its positions in the str
    private static Map<Integer,List<Integer>> positions = new HashMap<Integer, List<Integer>>();
    private static int[] nextPos = new int[26];
    
    private static int targetCharIndex = 0;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        str = s.nextLine().toCharArray();
        minPresentedSoFar = new int[str.length];

        reverse(str);

        dst = new char[str.length/2];

        preCounting();

        while(dstIndex < dst.length) {
            findNextBottleNeck();
            findNextTargetChar();
            
            int pos = positions.get(targetCharIndex).get(nextPos[targetCharIndex]);
            while (previousPos <= pos) {
                nextPos[indexOfChar(str[previousPos])]++;
                previousPos++;
            }
                      
            writeDst(targetCharIndex);
        }

        System.out.println(new String(dst));
    }
  
    // Return true: if the needed occurrences have been written.
    private static void writeDst(int index) {
        dst[dstIndex++] = getChar(index);
        --needToWrite[index];
    }

    private static void preCounting() {
        for (char c : str) {
            count[indexOfChar(c)]++;
        }

        int[] temp = new int[26];
        for (int i = 0; i < 26; i++) {
            count[i] /= 2;
            needToWrite[i] = count[i];
            temp[i] = count[i];
        }

        for (int i = 0; i < str.length; i++) {
            char c = str[str.length - i - 1];
            minPresentedSoFar[str.length - i - 1] = temp[indexOfChar(c)];

            if (temp[indexOfChar(c)] > 0)
                temp[indexOfChar(c)]--;
            
            char c1 = str[i];
            List<Integer> posList = positions.get(indexOfChar(c1));
            if (posList == null) {
                posList = new ArrayList<Integer>();
                positions.put(indexOfChar(c1), posList);
            }
            posList.add(i);
        }
    }

    private static int indexOfChar(char c) {
        return c-97;
    }

    private static char getChar(int index) {
        return (char) (index+97);
    }

    private static boolean isBottleNeck(int index) {
        int charIndex = indexOfChar(str[index]);
        return minPresentedSoFar[index] + needToWrite[charIndex] > count[charIndex];
    }

    private static void findNextBottleNeck() {
        for (int i = nextBottleNeck; i < str.length; i++) {
            if (isBottleNeck(i)) {
                nextBottleNeck = i;
                return;
            }
        }
    }

    // Find the next letter that needs to be written (in lexicographical order and could be the same as previous)
    // start search index (previous target index)
    private static void findNextTargetChar() {
        for (int index = 0; index < count.length; index++) {
            if (needToWrite[index] > 0 && positions.get(index).get(nextPos[index]) <= nextBottleNeck) {
                targetCharIndex = index;
                return;
            }
        }
    }

    private static void reverse(char[] S) {
        int len = S.length;
        for (int i = 0; i < len/2; i++) {
            char temp = S[i];
            S[i] = S[len - 1 - i];
            S[len - 1 -i] = temp;
        }
    }
}