import java.io.*;
import java.util.*;
import java.util.Collections;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
 
    static int getValue(List<Integer> A, int M) {
        int res = Math.abs(A.get(0)-M);
        for (int i = 1; i < A.size(); ++i) {
            res = Math.min(res, Math.abs(A.get(i)-M));
        }
        return res;
    }
    
    static int solve(int N, List<Integer> A, int P, int Q) {
        Collections.sort(A);
        int bestM = -1;
        int bestNum = -1;
        for (int i = 1; i < A.size(); ++i) {
            int num = (A.get(i) + A.get(i-1)) / 2;
            int M = num - A.get(i-1);
            if (num >= P && num <= Q && M > bestM) {
                bestM = M;
                bestNum = num;
            }
        }
        
        int Pval = getValue(A, P);
        int Qval = getValue(A, Q);       
        if (Pval >= bestM && Pval >= Qval) {
            return P;
        } else if (bestM >= Qval) {
            return bestNum;
        } else {
            return Q;
        }
    }

   
 public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        List<Integer> A = new ArrayList<Integer>(N);
        for (int i = 0; i < N; ++i) {
            A.add(in.nextInt());
        }
        int P = in.nextInt();
        int Q = in.nextInt();
        int M = solve(N, A, P, Q);
        System.out.println(M);
   }
}