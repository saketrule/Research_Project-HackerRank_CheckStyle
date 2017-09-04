import java.util.*;

public class Solution {
    static long pos = 0;
    static long [] ar;
    static int p , q ;
    public static void main(String[] args)
    {
        int i ;
        Scanner obj = new Scanner(System.in);
        int size = obj.nextInt();
        ar = new long[size];
        for(i = 0 ; i < ar.length ; i++)
            ar[i] = obj.nextLong();
        p = obj.nextInt();
        q = obj.nextInt();
       // p = 70283784;
       // q = 302962359;
       // CLASS(ar);
       // long start = System.currentTimeMillis();
        pos = p;
        test(p);
        test(q);
        quickSort(ar, 0, ar.length-1);
        for(i = 0 ; i < ar.length ; i++)
        {
            for(int j = i+1 ; j < ar.length ; j++)
            {
                long find = (ar[i]+ar[j])/2;
                if(find > p && find < q)
                {
                    test(find);
                }
            }
        }
        System.out.println(pos);
        //long stop = System.currentTimeMillis();
        //System.err.println(stop-start);
    }
    public static void quickSort(long[] arr, int left, int right) {
        int i = left;
        int j = right;
        long temp;
        long pivot = arr[(left+right)/2];
        while (i <= j) {
            while (arr[i] < pivot) 
               i++;
            while (arr[j] > pivot)
               j--;
            if (i <= j) {
               temp = arr[i];
               arr[i] = arr[j];
               arr[j] = temp;
               i++;
               j--;
            }
        }
        if (left < j)
            quickSort(arr, left, j);
        if (i < right)
            quickSort(arr, i, right);
    }
    public static void test(long j)
    {
        if(findMax(j) > findMax(pos))
            pos = j ;
    }
    private static long findMax(long i) {

        long sum = 0;
        long min = Long.MAX_VALUE;
        for(int j = 0 ; j < ar.length ; j++)
        {
            sum = Math.abs(i - ar[j]);
            min = Math.min(min,sum);
        }
        return min ;
    }
}