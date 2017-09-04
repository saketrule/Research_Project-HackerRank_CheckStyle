/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/
import java.util.Scanner;
import java.io.IOException;


import java.util.PriorityQueue;

/**
 *
 * @author joshuapixla
 */
public class Solution {
    
    private static int[] billboards;
    private static long[] counts;
    private static int K;
    private static int N;
    private static PriorityQueue<Pair> qu;
    
    
    
    private static void fillCounts(int[] bill){
        counts[0] = 0;
        
        for(int i = 1; i <= bill.length; i++){
            
            if(i == 1){
            counts[i] = bill[0]+counts[0];
            }
            else{
            counts[i] = bill[i-1]+counts[i-1];
            }
      }
    }
    
   
    private static void calculate(){
    
    long mx = 0;
    qu.add(makePair(0, 0));
    qu.add(makePair(-counts[1],1));   
    mx = counts[1];
        
    for(int i = 1; i <= N; i++){
       
        while(qu.peek().value < (i - K)){
           qu.poll();
        }
        
        mx = counts[i] + qu.peek().key;
        qu.add(makePair((mx-counts[i+1]), (i+1)));
    }
        
        System.out.println(mx);
  }
    
    
    
    
    public static void main(String[] args)throws IOException{
        
        Scanner scan = new Scanner(System.in);
        
        N = scan.nextInt();
        K = scan.nextInt();
        billboards = new int[N];
        counts = new long[billboards.length+3];
        
        for(int i = 0; i < N; i++){
     billboards[i] = scan.nextInt();
        }
        
        fillCounts(billboards);
        qu = new PriorityQueue<Pair>();
        calculate();
         
    }
    
    
    private static class Pair implements Comparable<Pair>{
        
        long key;
        int value;
        
        public Pair(long k, int v){
           
            key = k;
            value = v;
        }

        @Override
       public int compareTo(Pair o) {
         final int BEFORE = -1;
         final int EQUAL = 0;
         final int AFTER = 1;
            
         long val = this.key - o.key;
         
         if(val > 0) return BEFORE;
         if(val < 0) return AFTER;
         
         return EQUAL;
        }
    }
    
    
    private static Pair makePair(long k, int v){
        return new Pair(k,v);
    }
    
}