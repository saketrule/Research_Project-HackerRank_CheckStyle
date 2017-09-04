import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        
        int n = in.nextInt();
        int log2n = 1;
        while(1<<log2n < n) log2n++;
        
        int[] tree = new int[2*n];
        for(int i=0; i<(2*n)-1; i++)
            tree[i] = in.nextInt();
        tree[(2*n)-1] = Integer.MAX_VALUE;
        
        int[] indices = new int[20];
        int[] newtree = new int[(2*n)-1];
        
        int i2 = 0;
        Arrays.sort(tree);
        newtree[0] = tree[0];
        
        for(int i=1; i<2*n; i++){
            if(tree[i] != tree[i2]){
                int ni = (n >> (i-i2-1));
                if(i-i2 > log2n+1) cantBeDone();
                if(ni+indices[i-i2] >= ni+ni) cantBeDone();
                
                int i3 = ni + indices[i-i2];
                while(i3 < n+n-1){
                    newtree[i3] = tree[i2];
                    i3 += i3 + 1;
                }
                
                indices[i-i2] += 2;
                i2 = i;
            }
        }
        
        
        System.out.println("YES");
        for(int i=0; i<2*n-1; i++){
            System.out.print(newtree[i]);
            System.out.print(' ');
            
        }
        
        
    }
    
    static void cantBeDone(){
        System.out.println("NO");
        System.exit(0);
    }
}