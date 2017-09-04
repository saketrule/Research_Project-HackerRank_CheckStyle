import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static final BigInteger two = new BigInteger("2"); 
    public static final BigInteger marker = new BigInteger("-1"); 

    public static void main(String[] args) throws IOException{
        new Solution().run();
    }
    
    public void run() throws IOException{
        Scanner in = new Scanner(System.in);
        BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = in.nextInt();
        int m = in.nextInt();
        
        int i,j,k,c;
        int[][] w = new int[n][n];
        
        BigInteger[][] d = new BigInteger[n][n];
        for(i=0;i<n;i++){
            for(j=0;j<n;j++){
                d[i][j] = marker;
            }
            d[i][i] = BigInteger.ZERO;
        }
        
        for(k=0;k<m;k++){
            i = in.nextInt()-1;
            j = in.nextInt()-1;
            c = in.nextInt();
            w[i][j] = c;
            d[i][j] = two.pow(c);
            d[j][i] = d[i][j];
        }
        
        for(i=0;i<n;i++){
            for(j=0;j<n;j++){
                for(k=0;k<n;k++){
                    if(!d[j][i].equals(marker) && !d[i][k].equals(marker) && 
                       (d[j][k].equals(marker) || d[j][k].compareTo(d[j][i].add(d[i][k]))>0)){
                           d[j][k] = d[j][i].add(d[i][k]);
                       }
                }
            }
        }
        BigInteger sum = BigInteger.ZERO;
        for(i=0;i<n;i++){
            for(j=i+1;j<n;j++){
                sum = sum.add(d[i][j]);
            }
        }
        
        log.write("" +sum.toString(2)+"\n"); 
        
        log.flush();
    }
}