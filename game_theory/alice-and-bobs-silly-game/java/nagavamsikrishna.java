import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by vamsi on 12/13/16.
 */
public class SillyGame {
    static List<Integer> primes = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int noOfGames = in.nextInt();
        List<Integer> inputs = new ArrayList<>();
        int max = -1;
        for (int i = 0; i < noOfGames; i++) {
            int n = in.nextInt();
            inputs.add(n);
            if (max < n) max = n;
        }
        buildPrimes(max);
        for (Integer input : inputs) {
            if (noOfPrimes(input) % 2 == 0) System.out.println("Bob");
            else System.out.println("Alice");
        }
    }

    private static void buildPrimes(int max) {
        for (int i = 2; i <= max; i++) {
            boolean isPrime = true;
            for (Integer prime : primes) {
                if (i % prime == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) primes.add(i);
        }
    }

    private static int noOfPrimes(int n) {
        int count = 0;
        for (Integer prime : primes) {
            if (prime <= n) count++;
            else break;
        }
        return count;
    }
}