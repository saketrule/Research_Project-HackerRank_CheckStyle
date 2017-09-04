import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


import java.util.*;

public class Solution {
       
    static void quickSort(int[] ar, int start, int end){

     if(start >= end){
            return;
        }
        
        int pivot = ar[start];
        ar[start] = ar[end];
        ar[end] = pivot;
        
        int i = start-1;
        int j = end;
        int temp;
        
        while(true){
            while(ar[++i] < pivot){
                if(i == end){
                    break;
                }
            }
            
            while(ar[--j] > pivot){
                if(j == start){
                    break;
                }
            }
            
            if(i < j){
                temp = ar[i];
                ar[i] = ar[j];
                ar[j] = temp;
            }
            else{
                break;
            }
        }
        
        temp = ar[i];
        ar[i] = pivot;
        ar[end] = temp;
        
        quickSort(ar, start, i-1);
        quickSort(ar, i+1, end);
        
        //printArray(ar, start, end);
    }
    
    static void quickSort(int[] ar) {
        
        quickSort(ar, 0, ar.length-1);        
    }   
 
    
    static void printArray(int[] ar, int start, int end) {
         for(int n = start;n <= end;n++){
            System.out.print(ar[n]+" ");
         }
         System.out.println("");
    }
       
 public static void main(String[] args) {
     Scanner in = new Scanner(System.in);
     int n = in.nextInt();
     int[] ar = new int[n];
        double sum = 0;
     for(int i=0;i<n;i++){
        ar[i]=in.nextInt();
     }
     int P = in.nextInt();
        int Q = in.nextInt();
        
        quickSort(ar);
        int low = 0;
        int high = 0;
        for(low = 0;low < n;low++){
            if(ar[low] >= P){
                break;
            }
        }
     
        for(high = low;high < n;high++){
            if(ar[high] >= Q){
                break;
            }
        }
        
        int point = -1;
        int minmax = -1;
        
        if(low == n){
            point = Q;
        }
        else if(high == 0){
            point = P;
        }
        else{
            
            if(low == 0){
                point = P;
                minmax = ar[0] - P;
            }
            else{
                int temp = (ar[low-1] + ar[low])/2;
                if(temp > P){
                    if(temp <= Q){
                        point = temp;
                        minmax = temp - ar[low-1];
                    }
                    else{
                        point = Q;
                        minmax = Q - ar[low-1];
                    }
                }
                else{
                    point = P;
                    minmax = ar[low] - P;
                }
            }
            
            if(high == n){
                if(Q - ar[n-1] > minmax){
                    minmax = Q - ar[n-1];
                    point = Q;
                }
            }
            else{
                int temp = (ar[high-1] + ar[high])/2;
                if(temp < Q){
                    if(temp >= P){
                        if(temp - ar[high-1] > minmax){
                            point = temp;
                            minmax = temp - ar[high-1];
                        }
                    }
                    else{
                        if(ar[high] - P > minmax){
                            point = P;
                            minmax = ar[high] - P;
                        }
                    }
                }
                else{
                    if(Q - ar[high-1] > minmax){
                        point = Q;
                        minmax = Q - ar[high-1];
                    }
                }
            }
            
            for(int i = low;i < high-1;i++){
                int temp = (ar[i] + ar[i+1])/2;
                if(temp - ar[i] > minmax){
                    point = temp;
                    minmax = temp - ar[i];
                }
            }
            
        }
        
        

        
        System.out.println(point);
        
        
 }    
}