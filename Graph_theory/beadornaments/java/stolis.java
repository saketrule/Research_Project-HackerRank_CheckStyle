import java.util.Scanner;

public class Solution {
    static long mod = 1000000007;

    static long pow(long base, int exp) {
        long result = 1;
        for (int i=1; i<=exp; i++) {
            result *= base;
            result %= mod;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        for (int t=0; t<T; t++) {
            int N = input.nextInt();
            int[] A = new int[N];
            for (int n=0; n<N; n++) {
                A[n] = input.nextInt();
            }
            long result = 1;
            if (N == 1) {
                result = A[0] == 1 ? 1 : pow(A[0], A[0]-2);
            } else {
                long sum = 0;
                for (int n=0; n<N; n++) {
                    result *= pow(A[n], A[n]-1);
                    result %= mod;
                    sum += A[n];
                }
                result *= pow(sum, N-2);
                result %= mod;
            }
            System.out.println(result);
        }
    }
    
}