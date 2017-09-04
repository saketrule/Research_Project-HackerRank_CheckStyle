import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
 

 public static void main(String[] args) throws IOException,NumberFormatException {
  
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  int _n = Integer.parseInt(br.readLine());
  String str[] =new String[_n];
  str = br.readLine().split(" ");
  
  int ar[] = new int[_n];
 
  for (int i = 0; i < _n; i++) {

   ar[i] = Integer.parseInt(str[i]);;
  }
  String s[] =  br.readLine().split(" ");
  int low = Integer.parseInt(s[0]);
  int high = Integer.parseInt(s[1]);
  Arrays.sort(ar);
  int ans =0;
  int max=-1;
  /*for (int i = low; i <= high; i++) {
   int n = getDiff(i, ar);
   System.out.println(n);
   if(n>max){
    max=n;
    ans=i;
   }
  }*/
        max = getDiff(low, ar);
        ans = low;
  int newMax   = getDiff(high, ar);
        if(newMax>max){
            ans = high;
            max=newMax;
        }
        for(int i=0;i<_n;i++){
   for(int j = i+1;j<_n;j++){
    int no = (ar[i]+ar[j])/2;
    if(no<low || no>high){continue;}
    int n = getDiff(no, ar);
    if(n>max){
     max=n;
     ans=no;
    }
   }
  }
  
  
  System.out.println(ans);
 }

 static int getDiff(int i, int ar[]) {
  
  int low = 0;
  int high = ar.length-1;
  while (low <= high) {
   int mid = low + ((high - low) / 2);
   if (i < ar[mid]) {
    high = mid - 1;
   } else if (i > ar[mid]) {
    low = mid + 1;
   } else {
    return 0;
   }
  }
  if(high>-1 ){
   if(low<ar.length){
    return Math.min(Math.abs(ar[low]-i),Math.abs(ar[high]-i));
   }else{
    return Math.abs(ar[high]-i);
    
   }
  }else{
   return Math.abs(ar[low]-i);
   
  }
  

  
 }

}