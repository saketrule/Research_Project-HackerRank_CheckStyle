import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
 
 

 public static void main(String[] args) throws IOException {

  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  String s = br.readLine();
  int n = Integer.parseInt(s.split(" ")[0]);
  int k = Integer.parseInt(s.split(" ")[1]);
  
  int a[] = new int[n];
  for(int i=0; i<n; i++)
   a[i] = Integer.parseInt(br.readLine());
  
  System.out.println(billboard(n,a,k));
  
  
  

 }

 private static long oldbillboard(int n, int[] a, int k) {
  long[] history = new long[n];
  int occupancy = 0;
  long best = 0;
  
  for(int i=0; i<n; i++) {
   if(occupancy < k) {
    best+=a[i];
    history[i]=best;
    occupancy++;
   } else {
    int offer = a[i];
    for(int j=0; j<k; j++) {
     long localContribution = offer;
     for(int l=0; l<j;l++)
      localContribution+=a[i-1-l];
     
     
     long localOffer = localContribution;
     if(i-j-2 >=0)
      localOffer += history[i-j-2];
    // System.out.println("Comparing " + localOffer + " against " + best);
     if(localOffer > best) {
      best=localOffer;
      occupancy = j+1;
     }
    
    }
    history[i]=best;
   }
   //System.out.println("At " + a[i] + "  Best = " + best + " : " + occupancy);
  }

  return best;
 }
 

 private static long billboard(int n, int[] a, int k) {
  long[] history = new long[n];
  int occupancy = 0;
  long best = 0;
  
  for(int i=0; i<n; i++) {
   if(occupancy < k) {
    best+=a[i];
    history[i]=best;
    occupancy++;
   } else {
    int offer = a[i];
    long localContribution = offer;
    for(int j=0; j<k; j++) {
     
      
     long localOffer = localContribution;
     if(i-j-2 >=0)
      localOffer += history[i-j-2];
    // System.out.println("Comparing " + localOffer + " against " + best);
     if(localOffer > best) {
      best=localOffer;
      occupancy = j+1;
     }
     localContribution+=a[i-j-1];
    
    }
    history[i]=best;
   }
   //System.out.println("At " + a[i] + "  Best = " + best + " : " + occupancy);
  }

  return best;
 }


}