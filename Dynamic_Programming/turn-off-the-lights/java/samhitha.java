import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int n= in.nextInt();
        int k= in.nextInt();
        int[] c = new int[n];
        int[] s = new int[n];
        int min=0,cost=0;
        for(int i=0;i<n;i++){
            s[i]=1;
            c[i]=in.nextInt();
            if(c[min]>c[i])
                min=i;
        }
        if(k>=n-1){
            System.out.println(c[min]);
            System.exit(0);
        }
        if(k==0){
            for(int i=0;i<n;i++){
                cost+=c[i];
            }
            System.out.println(cost);
            System.exit(0);
        }
        int sum = 0;
        while(sum!=0){
            
        }
        
        in.close();
        
    }
}