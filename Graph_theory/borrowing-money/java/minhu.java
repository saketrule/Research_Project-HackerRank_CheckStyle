import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static int n, m;
    static int[] vs=new int[34];
    static long[] covers;
    static HashMap<Long, Long> ress;
    static HashMap<Long, Long> nums;
    
    static void solve(long mask) {
        if (ress.containsKey(mask))return;
        int i=0;
        long tm = 0;
        boolean found=false;
        for (;i<n;++i) {
         tm=(1L<<i);
            if ((tm & mask)!=0 && vs[i]!=0){
             found=true;
             break;
            }
        }
        long res, num;
        if (!found) {
         i=0;
         for (;i<n;++i) {
          tm=(1L<<i);
             if ((tm & mask)!=0)break;
         }
         
         long cover=covers[i];
         long mask2=(mask & (~cover));
         solve(mask2);

         if (ress.get(mask2)!=0)throw new RuntimeException("err1");  
            res=0;            
            num=nums.get(mask2);         

            mask2=(mask ^ tm);
            solve(mask2);   
            num+=nums.get(mask2); 
        } else {        
         // node i    
         if (vs[i]==0)throw new RuntimeException("err2");    
         long cover=covers[i];
         long mask2=(mask & (~cover));
         solve(mask2);
         
         res=vs[i] + ress.get(mask2);
         num=nums.get(mask2);         
         
            mask2=(mask ^ tm);
            solve(mask2);
            long res2=ress.get(mask2);
            long num2=nums.get(mask2);
            if (res2>res) {
                res=res2;
                num=num2;
            }else if (res2==res) {
                num+=num2;
            }
        }
        ress.put(mask, res);
        nums.put(mask, num);
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        m=sc.nextInt();
        covers=new long[n];
        for (int i=0;i<n;++i){
            vs[i]=sc.nextInt();
            covers[i] = (1L<<i);
        }
        for (int i=0;i<m;++i){
            int from=sc.nextInt()-1;
            int to=sc.nextInt()-1;
            covers[from]|=(1L<<to);
            covers[to]|=(1L<<from);
        }
        ress=new HashMap<Long, Long>();
        nums=new HashMap<Long, Long>();
        
        ress.put(0L, 0L);
        nums.put(0L, 1L);
        long mask=(1L<<n)-1;
        solve(mask);
        String str=ress.get(mask) + " " + nums.get(mask);
        System.out.println(str);
    }
}