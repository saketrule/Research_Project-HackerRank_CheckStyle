import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int H = sc.nextInt();
        int I = sc.nextInt();
        sc.nextLine();
        int[][] people = new int[N+1][H+1];
        int[][] solution = new int[N+1][H+1];
        for(int i=1;i<=N;i++){            
            String floors = sc.nextLine();
            //System.out.println(floors);
            String[] arr = floors.split(" ");
            for(int j=1;j<arr.length;j++){
                int temp = Integer.parseInt(arr[j]);
                if(temp >0)
                    people[i][temp]++;
            }
        }
        for(int i=1;i<=H;i++){  //floor
            int max = 0;
            for(int j=1;j<=N;j++){  //building
                if(i<=I){
                    solution[j][i] = solution[j][i-1]+people[j][i];                                     
                }
                else{                    
                    solution[j][i] = Math.max(solution[j][i-1],solution[0][i-I]) + people[j][i];
                }                
                if(solution[j][i]>max)
                    max = solution[j][i];
            }   
            solution[0][i] = max;
        }
        int max =0;
        for(int j=1;j<=N;j++) 
            max = Math.max(max, solution[j][H]);                
        System.out.println(max);                
    }
}