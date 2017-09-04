import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String[] args) {
        Scanner stdin=new Scanner(System.in);
        int n=stdin.nextInt();//,k=stdin.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++) arr[i]=stdin.nextInt();
        int P=stdin.nextInt();
        int Q=stdin.nextInt();
        
        
        int answer = Solve(arr,P,Q);
        // Compute the final answer from n,k,prices 
        System.out.println(answer);
    }
    
    private static int Solve(int [] arr,int p, int q){
        Arrays.sort(arr);
        int start=0;
        int end=0;
        boolean foundPPos=false;
        boolean foundQPos=false;
        for (int i=0;i<arr.length;i++){
            if (!foundPPos){
                if(arr[i]>=p){
                    foundPPos=true;
                    start=i;
                }
            }
            else if(foundPPos && (!foundQPos)){
                if (arr[i]>q){
                    foundQPos=true;
                    end=i-1;
                    break;
                }
            }
            
        }
        
        if (!foundQPos){
            end=arr.length-1;
        }
        
        //handle start, end corner cases
        //if (start==end-)
        int MaxMin=0;
        int prev;
        int mid;
        int min;
        int m=0;
        int prevMax;
        for(int i=start;i<=end+1;i++){
            if(i==0) {
                MaxMin=arr[i]-p;
                m=p;
            }
            else if(i==arr.length) 
             { 
                 prevMax=MaxMin;
                 MaxMin=Math.max(MaxMin, Math.abs(q-arr[i-1]));
                 if(prevMax<MaxMin){m=q;}
                    
             }
            else{
                prev=arr[i-1];
                mid=(prev + arr[i])/2;
                
                if((i-1)<start){
                    if (mid<p){mid=p;}
                }
                if(i==end+1){
                    if(mid>q){mid=q;}
                }
                
                min=Math.min(Math.abs(mid-prev), Math.abs(arr[i]-mid));
                prevMax=MaxMin;
                MaxMin=Math.max(MaxMin, min);
                //System.out.println("maxmin "+MaxMin);
                if(MaxMin>prevMax){
                    m= mid;
                }
                
            }
            
        }
        
        return m;
    }
}