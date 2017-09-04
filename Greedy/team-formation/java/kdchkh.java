import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

// https://www.hackerrank.com/challenges/team-formation
public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            String[] in = br.readLine().split(" ");
            int n = Integer.parseInt(in[0]);
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(in[i + 1]);
            }
            Arrays.sort(arr);
            System.out.println(teamSize(arr));
        }
    }

    private static int teamSize(int[] arr) {
        int n = arr.length;
        if (n == 0) {
            return 0;
        }
        int globalMin = Integer.MAX_VALUE;
        ArrayList<Integer> parallelList = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            if (i>=1 &&  arr[i] == arr[i-1] + 1) {
                int count = 1;
                while ((i+1)<n && arr[i] == arr[i+1]) {
                    count++;
                    i++;
                }
                int x = count - parallelList.size();
                if (x == 0) {
                    incrementList(parallelList);
                } else if (x < 0) {
                    x = -1 * x;
                    int temp = Integer.MIN_VALUE;
                    for (int z = 0; z < x; z++) {
                        temp = parallelList.remove(0);
                    }
                    incrementList(parallelList);
                    globalMin = Math.min(globalMin, temp);

                } else if (x > 0) {
                    incrementList(parallelList);
                    for (int z = 0; z < x; z++) {
                        parallelList.add(new Integer(1));
                    }
                }
            } else {
                if (i != 0) {
                    globalMin = Math.min(globalMin, parallelList.get(parallelList.size() - 1));
                    for (int z = 0; z < parallelList.size(); z++) {
                        parallelList.remove(0);
                    }
                }
                parallelList.add(new Integer(1));
                while ((i+1)<n && arr[i] == arr[i+1]) {
                    parallelList.add(new Integer(1));
                    i++;
                }
            }
        }
        globalMin = Math.min(globalMin, parallelList.get(parallelList.size() - 1));
        return globalMin;
    }

    private static void incrementList(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i) + 1);
        }
    }
}