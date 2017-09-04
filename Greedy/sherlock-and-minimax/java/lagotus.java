import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
   
    public static void main(String[] args) throws NumberFormatException, IOException {
  int N = Integer.parseInt(in.readLine());
        
        String[] str = in.readLine().split(" ");
        TreeSet<Integer> set = new TreeSet<Integer>();
            
        for (int n=0 ; n<N ; ++n) {
            set.add( Integer.parseInt( str[n] ) );
        }
        
        str = in.readLine().split(" ");
        int P = Integer.parseInt( str[0] );
        int Q = Integer.parseInt( str[1] );
        
        Iterator<Integer> iterator = set.iterator();  
        int val = -1;
        int lastVal = -1;
        
        
        int status = 0;
        int max = 0;
        int erg = -1;
        
        while (iterator.hasNext()){
            val = iterator.next();
            int min = Integer.MAX_VALUE;
            
            switch (status) {
                case 0:
                    // P
                    if (P < val) {
                        if (lastVal != -1) {
                            min = P-lastVal;
                        }   
                        
                        min = Math.min(min, val-P);                        
                        if (min > max) {
                            max = min;  
                            erg = P;
                        }
                        
                         min = (val-lastVal) / 2;       
                
                        if (min > max && lastVal + min > P && lastVal != -1) {
                            max = min;
                            erg = lastVal + min;
                        }
                        
                        status = 1;
                    }
                break;
                case 1:
                    // Q?
                    if (Q < val) {
                        min = Q-lastVal;
                        min = Math.min(min, val-Q);
                                                
                        if (min > max) {
                            max = min;
                            erg = Q;
                        }
                        
                        status = 2;
                    }
                
                    min = (val-lastVal) / 2;       
                
                    if (min > max && lastVal + min < Q) {
                        max = min;
                        erg = lastVal + min;
                    }
                break;
                case 2:
                    // done     
                break;
            }
            
            lastVal = val;
        }
        
        if (status == 0) {
            erg = P;
        } else if (status == 1) {
            int min = Q-lastVal;
            
            if (min > max) {
                erg = Q;
            }
        }
        
        System.out.println(erg);        
    }   
 
}