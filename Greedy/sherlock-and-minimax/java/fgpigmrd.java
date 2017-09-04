import java.util.*;

public class Solution {
        
    static void answer(int[] ar, int p, int q) {
        Arrays.sort (ar);
        int x=p;
        int count= Math.abs(ar[0]-p);
        for(int i=0;i<ar.length;i++){
            if(Math.abs(ar[i]-p)<count){
                count=Math.abs(ar[i]-p);
            }
        }
        
        for (int i=0;i<ar.length-1;i++){
            if(((ar[i]+ar[i+1])/2)>=p && ((ar[i]+ar[i+1])/2)<=q){
            if(  ((ar[i+1]-ar[i])/2)>count){
                count=(ar[i+1]-ar[i])/2;
                x=(ar[i]+ar[i+1])/2;
            }
        }
        }
        int count2=Math.abs(ar[0]-q);
        for(int i=0;i<ar.length;i++){
            if(Math.abs(ar[i]-q)<count2){
                count2=Math.abs(ar[i]-q);
               
            }
        }
        if(count2>count){x=q;}
        System.out.println(x);
}
                    
    
    
    static void printArray(int[] ar) {
         for(int n: ar){
            System.out.print(n+" ");
         }
           System.out.println("");
      }
    
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] ar = new int[n];
        for(int i=0;i<n;i++){
            ar[i]=in.nextInt();
        }
        int p = in.nextInt();
        int q = in.nextInt();
        answer(ar,p,q);
       }    
   }