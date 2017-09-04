import java.util.Scanner;

public class Solution {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int N = sc.nextInt();
            long ways = 1;
            long prod = 1;
            int sum = 0;

            for (int j = 0; j < N; j++) {
                int b = sc.nextInt();

                // only applies for 2+ components
                // s1 * .. * sk
                if (N > 1)
                    prod = (prod * (long) b) % 1000000007;

                // also multiply with number of different trees this component
                // can have
                for (int k = 0; k < b - 2; k++) {
                    prod = (prod * (long) b) % 1000000007;
                }

                // n = s1 + .. + sk
                sum = (sum + b) % 1000000007;
            }

            // only applies for 3+ components
            for (int j = 0; j < N - 2; j++) {
                // n^k-2
                ways = (ways * (long) sum) % 1000000007;
            }
            ways = (ways * prod) % 1000000007;

            System.out.println(ways);
        }
        sc.close();
    }

}