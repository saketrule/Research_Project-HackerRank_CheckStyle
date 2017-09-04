import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
         int sold=0;
Scanner in=new Scanner(System.in);
    int n=in.nextInt();
    int m= in.nextInt();
    TreeMap<Integer, Integer>clients=new TreeMap<>();
    TreeMap<Integer, Integer>houses=new TreeMap<>();
    for(int i=0;i<n;i++){
     int p=in.nextInt();
     int c=in.nextInt();
     clients.put(p, c);
     
     
    }
    for(int i=0;i<m;i++){
     int p=in.nextInt();
     int c=in.nextInt();
     houses.put(p, c);
      
    }
    boolean []bool=new boolean[n];
    
    for(Map.Entry<Integer,Integer> entry : houses.entrySet()) {
       Integer keyH = entry.getKey();
       Integer valueH = entry.getValue();
       int count=0;
         for(Map.Entry<Integer,Integer> entry1 : clients.entrySet()) {
            Integer key = entry1.getKey();
            Integer value = entry1.getValue();
           
            if(keyH>key){
             if(valueH<=value){
              if(!bool[count]){
              sold++;
              bool[count]=true;
             
              break;
              }
             }
             
            }else break;
              count++;
          }
     }
    System.out.println(sold);
    }
}