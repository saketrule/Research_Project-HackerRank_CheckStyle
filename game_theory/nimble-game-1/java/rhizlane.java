import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while(t-->0){
            int n = in.nextInt();
            int k=0, b=0;
            //int ans =0;
            int [] c = new int [n];
            for(int i=0; i<n; i++){
                c[i] = in.nextInt();
                if(c[i]>0){
                    if(c[i]%2==0)
                        k=0;
                    else{
                        k=i;
                        b = b^k;
                    }
                }
                //b = b^k;
                //ans = ans^c[i];
            }
            System.out.println(b==0? "Second":"First");
        }
    }
}