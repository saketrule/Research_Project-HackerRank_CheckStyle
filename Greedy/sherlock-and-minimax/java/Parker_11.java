import java.io.*;
import java.util.*;

public class Solution{
    
    static int minDiff(List<Integer> l1, int M, int start, int end){
        int mid = ( start + end )/2;
        if(start==end || start>end){
            int diff = Math.abs(l1.get(start) - M);
            if( (start+1)<l1.size() && Math.abs(l1.get(start+1) - M)<diff)
                diff =  Math.abs(l1.get(start+1) - M);
            else if( (start-1)>=0 && Math.abs(l1.get(start-1) - M)<diff)
                diff =  Math.abs(l1.get(start-1) - M);
            return diff;    
        }
        else if(l1.get(mid)>M)
            return minDiff(l1, M, 0, mid-1);
        else if(l1.get(mid)<M)
            return minDiff(l1, M, mid+1, end);
        else
            return -1;
    }
    public static void main(String args[]) throws IOException{
        Scanner scn = new Scanner(System.in);
        
        int n;
        n = scn.nextInt();
        List<Integer> l1 = new ArrayList<Integer>();
        while(n>0){
            l1.add(scn.nextInt());
            n--;
        }
        int P = scn.nextInt();
        int Q =scn.nextInt();
        Collections.sort(l1);
        int maxDiff = minDiff(l1, P, 0, l1.size()-1);
        int M;
        int g = minDiff(l1, Q, 0, l1.size() -1);
        if(g > maxDiff){
            M = Q;
            maxDiff = g;
        }
        else
            M = P;
        
        for(int i=0; i<l1.size()-1; i++){
            int mid = ( l1.get(i+1) - l1.get(i) )/2; 
            int num = l1.get(i) + mid;
            if(mid>maxDiff && num>=P && num<=Q){
                M = num;
                maxDiff = mid;
            }
        }
        System.out.print(M);
        
        
    }
}