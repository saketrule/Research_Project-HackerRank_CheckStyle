import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt(); // size of the array
        int maxlen = 2 * N - 1;
        int depth = (int) (Math.log(N) / Math.log(2)) + 1;
        
        int[] inputTree = new int[maxlen];
        int[] outputTree = new int[N];
        
        // Read the shuffled segment tree values
        for (int n = 0; n<maxlen; n++) {
            inputTree[n] = scan.nextInt();
        }
        scan.close();
        
        // Sort the values
        Arrays.sort(inputTree);
        
        // Track how full a given tree depth is (depth from leaf)
        int[] xpos = new int[depth+1];  // +1 for simplicity
        int[] xposJump = new int[depth+1];
        xpos[1] = 1;
        xposJump[1] = 2;
        for (int i=2; i<=depth; i++) {
            xpos[i] = xposJump[i-1];
            xposJump[i] = xposJump[i-1] * 2;
        }
        xpos[depth] = 0;  // Root treated specially
        
        // Determine freq of each value & place in output tree 
        boolean success = true;
        int pos = 1;
        int freq = 1;
        int value = inputTree[0];
        while ((pos < inputTree.length) && success) {
            if (inputTree[pos] == value) {
                freq++;
            } else {
                // If we have room to save value with this freq.
                if ((freq <=depth) && (xpos[freq] < N)) {
                    outputTree[xpos[freq]] = value;
                    xpos[freq] += xposJump[freq];
                    // Reset freq for the new value
                    freq = 1;
                    value = inputTree[pos];
                } else {
                    success = false;
                }                
            }
            pos++;
        }
        // Store final value
        if ((freq <=depth) && (xpos[freq] < N) && success) {
            outputTree[xpos[freq]] = value;
            xpos[freq] += xposJump[freq];
        } else {
            success = false;
        }
    
        // Output the tree
        StringBuilder sb = new StringBuilder(20 * N);

        pos = 0;
        int level = depth;  // how many levels to output.
        while ((level > 0) && success) {
            while (pos < N) {
                sb.append(outputTree[pos]);
                sb.append(" ");
                pos += (xposJump[level] / 2);
            }
            level--;
            pos = 0;
        }

        if (success) {
            System.out.println("YES");
            System.out.println(sb);
        } else {
            System.out.println("NO");
        }
    }

}