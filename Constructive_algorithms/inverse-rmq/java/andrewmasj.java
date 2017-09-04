import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    private static long buildSegTree(long[] segArray, long[] leafArray, int node, int start, int end)
    {
        if(start == end)
        { 
            // Leaf node is the origial value
            segArray[node] = leafArray[start];
           return leafArray[start];
        }
        
            int mid = start + (end-start) / 2;
            // Recurse on the left child
            segArray[node] = Math.min(
                buildSegTree(segArray,leafArray,2*node+1, start, mid),
                      buildSegTree(segArray,leafArray, 2*node+2, mid+1, end)
                                      );
           return segArray[node];
        
    }

//compare if the restored array is same as changed segment array
private static boolean helper(long[] sa,long[] segTree){
    long tmp[] = new long[segTree.length];
    System.arraycopy(segTree,0,tmp,0,tmp.length);
    Arrays.sort(tmp);
    for(int i=0;i<sa.length;i++){
        if(sa[i]!=tmp[i])return false;
    }
    return true;
}
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in  = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        long sa[] = new long[2*n-1];
        
        for(int i=0;i<2*n-1;i++){
            sa[i]=in.nextLong();
        }
        if(in.hasNextLine())in.nextLine();
        in.close();
        Arrays.sort(sa);
        
        //retrieve unique values which as leaf nodes the segTree is built for
        //this is wrong because duplication is allowed
        Set<Long> leafs = new TreeSet<Long>();
        for(int i=0;i<sa.length;i++){
            leafs.add(sa[i]);
        }
        if(leafs.size()!=(sa.length+1)/2){
            System.out.println("NO");//because numbers are distinct
            return;
        }
        long[] originalLeafs = new long[(sa.length+1)/2];
        int cnter =0;
        for(Long i:leafs){
            originalLeafs[cnter] = i;
            cnter++;
        }
      
        long [] segTree=new long[originalLeafs.length*2-1];
        segTree[0] = originalLeafs[0];
        buildSegTree(segTree,originalLeafs,0,0,originalLeafs.length-1);
       
        if(helper(sa,segTree)){
            System.out.println("YES");
            for(int i=0;i<segTree.length;i++){
                System.out.print(segTree[i]+" ");
            }
        }else{
            System.out.println("NO");
                
        }
    }
}