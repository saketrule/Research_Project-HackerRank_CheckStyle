import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(); int[] arr = new int[2*n-1];
        for(int i = 0; i < 2*n-1; i++){
            arr[i] = s.nextInt();
        }
        Set<Integer> distincts = new LinkedHashSet<Integer>();
        for(int x : arr){
            distincts.add(x);
        }
        int[] distArray = new int[n]; int it = 0;
        for(int q : distincts){
            distArray[it] = q; it++;
        }
        Arrays.sort(distArray);
        int[] orig = new int[2*n-1]; int ct = n;
        if(distincts.size() == n){
            System.out.println("YES");
            for(int t = 2*n-2; t >= 0; t--){
                if(ct > 0){
                    orig[t] = distArray[ct-1]; ct--;
                }
                else {
                    orig[t] = orig[2*t+1];
                }
            }
            for(int p = 0; p < 2*n-1; p++){
                System.out.print(orig[p] + " ");
            }
        }
        else{
            System.out.println("NO");
        }
    }
}