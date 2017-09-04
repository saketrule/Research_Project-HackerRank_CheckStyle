import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Solution of https://www.hackerrank.com/challenges/game-of-throne-ii
 */
public class Solution {
    final String str;
    final int alphabetSize;
    final char[] sym2ch;
    final int[] ch2sym;
    final int[] totalNumbers;
    final StringBuilder result;

    public Solution(String str, char[] id2ch, int[] ch2id, int[] totalNumbers) {
        this.str = str;
        this.alphabetSize = id2ch.length;
        this.sym2ch = id2ch;
        this.ch2sym = ch2id;
        this.totalNumbers = totalNumbers;
        result = new StringBuilder(str.length()/2);
    }

    public String solve() {
        int[] numbersToFind = new int[alphabetSize];
        for (int i = 0; i < alphabetSize; i++)
            numbersToFind[i] = totalNumbers[i] / 2;
        return solveStep(str.length()-1, totalNumbers, numbersToFind, str.length()/2);
    }


    public String solveStep(int endIdx, int[] totalNumbers, int[] numbersToFind, int numberToAllocate) {
        //System.out.println(Arrays.toString(numbersToFind));
        int[] currentNumbers = new int[alphabetSize];
        // Try to select symbols starting from lower
        nextSymbol: for (int symbol=0; symbol<alphabetSize; symbol++) {
            if (numbersToFind[symbol]==0) continue;
            // Try to select one symbol
            System.arraycopy(totalNumbers, 0, currentNumbers, 0, alphabetSize);
            for (int i = endIdx; i >= 0; i--) {
                int sym = ch2sym[str.charAt(i)-'a'];
                if (sym == symbol) {
                    // We will select this symbol
                    result.append(sym2ch[symbol]);
                    currentNumbers[symbol]--;
                    numbersToFind[symbol]--;
                    numberToAllocate--;
                    if (numberToAllocate==0) return result.toString();
                    totalNumbers = null;
                    return solveStep(i-1, currentNumbers, numbersToFind, numberToAllocate);
                }
                currentNumbers[sym]--;
                if (currentNumbers[sym] < numbersToFind[sym]) continue nextSymbol; // further movement isn't possible
            }
        }
        return "";
    }


    public static String solve(String str) {
        final int length = str.length();
        // Find alphabet
        int[] ch = new int[26];
        for (int i = 0; i < length; i++) {
            char chr = str.charAt(i);
            if (chr < 'a' || chr > 'z') throw new IllegalArgumentException();
            ch[chr - 'a']++;
        }
        int alphabetSize = 0;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] > 0) alphabetSize++;
        }
        char[] id2ch = new char[alphabetSize];
        int[] ch2id = new int[26];
        Arrays.fill(ch2id, -1);
        for (int i = 0, j = 0; i < ch.length; i++) {
            if (ch[i] > 0) {
                ch2id[i] = j;
                id2ch[j++] = (char) ('a' + i);
            }
        }
        // Prepare totalNumbers of alphabet chars
        int[] totalNumbers = new int[alphabetSize];
        for (int i=0; i<alphabetSize; i++) {
            totalNumbers[i] = ch[id2ch[i] - 'a'];
        }
        return new Solution(str, id2ch, ch2id, totalNumbers).solve();
    }

    public static void main(String[] args) throws IOException {
        if (true) {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String line = in.readLine();
            System.out.println( solve(line) );
        } else {
            assert "abcab".equals(solve("abaacbabbc"));
            assert "aabbbbcdc".equals(solve("acdcbbbbaabdcbbcba"));
            assert "aaaabbbccbdedcdcece".equals(solve("ececdcdaeedbbccdbcbaccbdaaaaedcbabcbae"));
            String line = "ececdcdaeedbbccdbcbaccbdaaaaedcbabcbae"; //
            System.out.println(solve(line));

        }
    }
}