import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static int compute(int[] arr){
        boolean p1 = true;
        boolean p2 = false;
        boolean[] value = new boolean[arr.length];
        Arrays.fill(value, Boolean.TRUE);
        //for(boolean i : value)
        //    System.out.println("Value of i is :" + i);
        int counter = arr.length;
        boolean change = false;
        while(counter > 0){
            if(p1 == true){
                for(int i = 0; i < arr.length; i++){
                    if(arr[i] > 1){
                        if(value[i]!= false){
                            value[i] = false;
                            p2 = true;
                            p1 = false;
                            change = true;
                            break;
                        }
                    }
                    else
                        value[i] = false;
                }
                
                if(change == true){
                    change = false;
                    continue;
                }
                else
                    return 2;

            }
            
            if(p2 == true){
                for(int i = 0; i < arr.length; i++){
                    if(arr[i] > 1){
                    //    System.out.println("Value of i for player 2 : " + value[i]);
                        if(value[i]!= false){
                            value[i] = false;
                            p1 = true;
                            p2 = false;
                            change = true;
                            break;
                        }
                    }
                    else
                        value[i] = false;
                       
                }
                
                if(change == true){
                    change = false;
                    continue;
                }
                else
                    return 1;

            }
            
           counter --;
            
        }
        
        return -1;
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int tests = in.nextInt();
        int[][] data = new int[tests][];
        int[] results = new int[tests];
        
        for(int i = 0; i < tests; i++){
            int noTowers = in.nextInt();
            data[i] = new int[noTowers];
            for(int j  = 0; j < noTowers; j++){
               data[i][j] = in.nextInt();
            }
        }
        
        for(int i = 0; i < data.length; i++){
            results[i] = compute(data[i]);
            System.out.println(results[i]);
        }
    }
}