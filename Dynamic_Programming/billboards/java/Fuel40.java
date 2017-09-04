/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/
//package solution;

import java.io.*;

/**
 *
 * @author Fuel
 */
public class Solution {

    int n, k, lastindex;
    long sum = 0l;
    int[] values;
    long[] results, results2;
    int[] min;

    public void init() throws IOException {
        String[] a;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        a = bf.readLine().split(" ");
        n = Integer.parseInt(a[0]);
        k = Integer.parseInt(a[1]);
        values = new int[n];
        //
        min = new int[n + 1];
        //
        results = new long[n];
        results2 = new long[n];
        lastindex = n - k - 1;
        for (int i = 0; i < lastindex; i++) {
            sum += values[i] = min[i] = Integer.parseInt(bf.readLine());
            results[i] = -1l;
        }
        for (int i = lastindex; i < n; i++) {
            sum += results[i] = results2[i] = values[i] = min[i] = Integer.parseInt(bf.readLine());
            //
            min[i] = n;
            //
        }
        min[lastindex] = n;
        results2[lastindex] = values[lastindex];
    }

    public void init(String fname) throws IOException {
        String[] a;
        BufferedReader bf = new BufferedReader(new FileReader(new File(fname)));
        a = bf.readLine().split(" ");
        n = Integer.parseInt(a[0]);
        k = Integer.parseInt(a[1]);
        values = new int[n];
        //
        min = new int[n + 1];
        //
        results = new long[n];
        results2 = new long[n];
        lastindex = n - k - 1;
        for (int i = 0; i < lastindex; i++) {
            sum += values[i] = min[i] = Integer.parseInt(bf.readLine());
            results[i] = -1l;
        }
        for (int i = lastindex; i < n; i++) {
            sum += results[i] = results2[i] = values[i] = min[i] = Integer.parseInt(bf.readLine());
            //
            min[i] = n;
            //
        }
        min[lastindex] = n;
        results2[lastindex] = values[lastindex];

        //min[lastindex] = minInRange(lastindex + 1, n - 1);
        //results2[lastindex] = values[min[lastindex]] + values[lastindex];
    }

    int minInRange(int start, int end) {
        long minresult = Long.MAX_VALUE;
        int minindex = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            if (results2[i] < minresult) {
                minindex = i;
                minresult = results2[i];
            }
        }
        return minindex;
    }

    long findMin(int index) {
        for (int i = index; i >= 0; i--) {
            if (min[i + 1] - i <= k + 1) {
                if (results2[min[i + 1]] < results2[i + 1]) {
                    min[i] = min[i + 1];
                } else {
                    min[i] = i + 1;
                }
            } else {
                min[i] = minInRange(i + 1, i + k + 1);
            }
            //System.out.println("min: " + i + " = " + min[i]);
            results2[i] = results2[min[i]] + values[i];
        }
        return results2[minInRange(0, k)];
    }

    long parse(int index) {

        long tempresult, result = Long.MAX_VALUE;
        for (int i = index + k + 1; i > index; i--) {
            tempresult = results[i] == -1 ? parse(i) : results[i];
            if (result > tempresult) {
                result = tempresult;
            }
        }
        if (index == -1) {
            return result;
        }
        return results[index] = result += values[index];
    }
    static final String INPUT_FILE_NANE = "billboards4.txt";

    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        solution.init();
        //solution.init(INPUT_FILE_NANE);
        //
        System.out.print(solution.sum - solution.findMin(solution.lastindex - 1));
        //
        //System.out.print(solution.sum - solution.parse(-1));
    }
}