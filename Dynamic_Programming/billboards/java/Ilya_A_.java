import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

    private static class Entry {
        private long value;
        private int index;

        public Entry(long value, int index) {
            this.value = value;
            this.index = index;
        }
    }

//    private static class EntryComparator implements Comparator<Solution.Entry> {
//
//        @Override
//        public int compare(Solution.Entry o1, Solution.Entry o2) {
//            return (int) (o1.value - o2.value);
//        }
//
//    }

    public static void main(String args[]) throws IOException {
        try {
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            String firstLine = stdin.readLine();
            Scanner valuesScanner = new Scanner(firstLine);
            valuesScanner.useDelimiter(" ");
            int n = Integer.valueOf(valuesScanner.next());
            int k = Integer.valueOf(valuesScanner.next());
            long arr[] = new long[n];
            for (int i = 0;i < n;i++) {
                String nextLine = stdin.readLine();
                arr[i] = Long.valueOf(nextLine);
            }
            solve2(n, k, arr);
        } catch (Exception e) {
            System.err.println("Error:" + e.getMessage());
        }
    }

    private static void solve1(int n, int k, long[] arr) {
        long sum[] = new long[n];
        sum[0] = arr[0];
        long totalSum = arr[0];
        for (int i = 1;i < n;i++) {
            totalSum += arr[i];
            long minSum = Long.MAX_VALUE;
            if (i <= k) {
                minSum = arr[i];
            }
            for (int j = i-1;j >= 0 && (j >= i - k - 1);j--) {
                if (arr[i] + sum[j] < minSum) {
                    minSum = arr[i] + sum[j];
                }
            }
            sum[i] = minSum;
        }
        long minSum = sum[n-1];
        for (int i = n-1;i >= 0 && i >= n-k-1;i--) {
            if (sum[i] < minSum) {
                minSum = sum[i];
            }
        }
        if (n <= k) {
            minSum = 0;
        }
        System.out.println(totalSum - minSum);
    }

    private static void solve2(int n, int k, long[] arr) {
        Queue<Entry> orderedSums = new PriorityQueue<Entry>(n, new Comparator<Solution.Entry>() {
            public int compare(Entry o1, Entry o2) {
                return (int) (o1.value - o2.value);
            }
            
        });
        long totalSum = arr[0];
        orderedSums.add(new Entry(arr[0], 0));
        for (int i = 1;i < n;i++) {
            totalSum += arr[i];
            if (i <= k) {
                orderedSums.add(new Entry(arr[i], i));
//                minSum = arr[i];
            } else {
                Entry minSum = orderedSums.peek();
                while (i - minSum.index > k + 1) {
                    orderedSums.poll();
                    minSum = orderedSums.peek();
                }
                orderedSums.add(new Entry(minSum.value + arr[i], i));
            }
        }
        Entry minSumEntry = orderedSums.peek();
        long minSum = minSumEntry.value;
        while (n - minSumEntry.index > k + 1) {
            orderedSums.poll();
            minSumEntry = orderedSums.peek();
        }
        minSum = minSumEntry.value;
        if (n <= k) {
            minSum = 0;
        }
        System.out.println(totalSum - minSum);
    }

}