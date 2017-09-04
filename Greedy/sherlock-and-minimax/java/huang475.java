import java.io.*;
import java.util.*;

public class Solution {
    private static long[] parse( String[] strNums, int N ){
        long[] Nums = new long[N];
        for (int i = 0; i < N; i++){
            Nums[i] = Long.parseLong(strNums[i]);
        }
        return Nums;
    }
    
    public static long solvePuzzle(long[] Nums, long[] region, int N){
        sort(Nums, N);
        //printArr(Nums, N);
        long[] CIs = computeCI(Nums, N, region);
        //printArr(CIs, N-1);
        return findYou(Nums, CIs, region, N);
    }
    
    private static long findMax(long[] nums){
        long max = nums[0];
        for( long num: nums ){
            if ( num > max )
                max = num;
        }
        return max;
    }
    
    private static long findYou(long[] Nums, long[] CIs, long[] region, int N){
        long max = findMax(CIs);
        long cand_1 = 0;
        long cand_2 = 0;
        //System.out.println("region[0]:"+Long.toString(region[0])+" Nums[0]-max/2:"+Long.toString(Nums[0]-max/2));
        if( region[0] <= Nums[0]-max/2 || region[1] >= Nums[N-1] + max/2 ){
            //System.out.println("i'm here");
            if ( region[1] - Nums[N-1] > Nums[0] - region[0] ) return region[1];
            else {
                //System.out.println("i'm here");
                return region[0];
            }
        }
        for( int i = 0; i < N-1; i++){
            if( CIs[i] == max ){
                if ( Nums[i] + max/2 >= region[0] && Nums[i] + max/2 <= region[1] )
                    return Nums[i] + max/2;
                else {
                    cand_1 = Nums[i] - region[0];
                    cand_2 = region[1] - Nums[i];
                }
                CIs[i] = 0;
            }
        }
        //printArr(CIs, N-1);
        max = findMax(CIs);
        if ( max/2 < cand_1 || max/2 < cand_2 ){
            if ( cand_1 > cand_2 ) return region[0];
            else return region[1];
        }
        for( int i = 0; i < N-1; i++){
            if( CIs[i] == max ){
                if ( Nums[i] + max/2 >= region[0] && Nums[i] + max/2 <= region[1] )
                    return Nums[i] + max/2;
                CIs[i] = 0;
            }
        }
        
        return -1;
    }
    
    public static long[] computeCI(long[] Nums, int N, long[] region){
        long[] CIs = new long[N-1];
        for( int i = 0; i < N-1; i++){
            if ( Nums[i] < region[0] || Nums[i] > region[1] )
                CIs[i] = 0;
            else {
                if ( i != 0 && CIs[i-1] == 0)
                    CIs[i-1] = Nums[i] - Nums[i-1];
                CIs[i] = Nums[i+1] - Nums[i];
            }
        }
        return CIs;
    }
    
    public static void sort(long[] Nums, int N){
        Qsort(Nums, 0, N-1);
    }
    
    public static void Qsort(long[] Nums, int Lo, int Hi) {
        if ( Hi <= Lo ) return;
        int p = partition(Nums, Lo, Hi); 
            Qsort(Nums, Lo, p-1);
            Qsort(Nums, p+1, Hi);
        if (sorted(Nums, Lo, Hi)) return;
    }
    
    public static boolean sorted(long[] Nums, int Lo, int Hi) {
        for( int i = Lo; i < Hi; i++){
            if ( Nums[i] > Nums[i+1])
                return false;
        }
        return true;
    }
    
    private static int partition( long[] Nums, int Lo, int Hi) {
        int l = Lo;
        int h = Hi+1;
        long t = Nums[Lo];
        while (l < h){
            while (Nums[++l] <= t){
                if ( l >= Hi)
                    break;
            }
            while (Nums[--h] >= t){
                if (h <= Lo)
                    break;
            }
            if (l < h) {
                exchange(Nums, l, h);
            }
        }
        exchange(Nums, Lo, h);
        return h;
    }
            
    private static void exchange(long[] Nums, int i, int j) {
        long temp = Nums[i];
        Nums[i] = Nums[j];
        Nums[j] = temp;
    }
    
    public static void printArr(long[] Nums, int N) {
        for( int i = 0; i < N; i++){
            System.out.printf("% 4d ",Nums[i]);
        }
        System.out.println("");
    }
    
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] nums = parse(br.readLine().split("[\\s+]"),N);
        long[] regions = parse(br.readLine().split("\\s+"),2);
        System.out.println(solvePuzzle(nums, regions, N));
    }
}