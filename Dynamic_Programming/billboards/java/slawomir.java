import java.util.*;

public class Solution {
    private int n, k;
    int[] values;

    public void read() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        values = new int[n];
        for (int i = 0; i < n; i++)
            values[i] = sc.nextInt();
    }

    private Long biggest;
    private long [] subsum;

              
    public void compute2(){
        subsum = new long[n];
        subsum[n-1] = values[n-1];
        for (int i = n - 2; i>=0; i--)
            subsum[i] = values[i] + subsum[i+1];
        compute2Int(0, 0);
        System.out.println(biggest);
    }

    private void compute2Int(long sum, int start){
        if (start >= n){
            if (biggest == null)
                biggest = sum;
            else
                if (biggest < sum)
                    biggest = sum;
            return;
        }
        
        if (biggest != null && sum + subsum[start] <= biggest)
            return;
        int end = start + k >= n ? n : start + k;
        for (int i = start; i < end; i++)
            sum += values[i];
        for (int i = end - 1; i >= start; i--){
            compute2Int(sum, i + 2);
            sum -= values[i];
        }
    }


    public void compute() {
        long[] columnLeft = new long[k + 1];
        long[] columnRight = new long[k + 1];

        long kFirst = 0;
        for (int i = 0; i < k; i++)
            kFirst += values[i];

        for (int i = 1; i <= k; i++)
            columnLeft[i] = kFirst - (k - i - 1 >= 0 ? values[k - i - 1] : 0);

        columnLeft[0] = kFirst - values[k - 1];
        int skip = k;

        columnRight[0] = columnLeft[1];
        for (int i = 1; i <= k; i++)
            if (columnLeft[i] > columnRight[0])
                columnRight[0] = columnLeft[i];


        for (int i = k; i < n; i++) {
            int nextValue = values[i];
            int skipMin = 1;
            long nextMax = columnLeft[0] + nextValue;
            for (int j = 1; j <= skip; j++) {
                long nextVal = columnLeft[j - 1] + nextValue;
                if (nextVal > nextMax) {
                    nextMax = nextVal;
                    skipMin = j;
                }
                columnRight[j] = nextVal;
            }
            skip = skipMin + 1 > k ? k : skipMin + 1;
            long[] tmp = columnRight;
            columnRight = columnLeft;
            columnLeft = tmp;
            columnRight[0] = nextMax;
        }

        System.out.println(columnRight[0]);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.read();
        //long time = System.currentTimeMillis();
        if (s.k < 10000)
            s.compute();
        else
            s.compute2();
        //System.out.println(System.currentTimeMillis() - time);
    }

}