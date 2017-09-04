import java.util.Scanner;

public class Solution {
 
 static int[] factorielle = {1,1,2,6,24,120,720,5040,40320,362880};
 static boolean[][] banque = new boolean[65536][16];
 static int[] values = {1,2,4,8,16,32,64,128,256,512,1024,2048,4096,8192,16384,32768,65526};
 static int[] tailles = new int[65536];
 static int modulo = 1000000007;
 static int nbPoints;
 static boolean[] colinear = new boolean[65536];
 static int[] X;
 static int[] Y;
 static int[][] aVisiter;

 public static void printColinear(){
  for (int i = 0;i<16;i++){
   System.out.print(colinear[i]);
   System.out.print(" ");
  }
 }
 
 public static void convertNumberSet(int n){
  int temp = n;
  int indice = 0;
  while (temp != 0){
         if (temp % 2 == 1){
             banque[n][indice] = true;
         }
         indice += 1;
         temp = temp>>1 ;
  }
 }
 
 public static void construireTailles(){
  for (int i = 1 ; i < 65536 ; i++){
   if ((i & (i-1)) == 0){
    tailles[i] = tailles[i-1] + 1;
   }
   else{
    tailles[i] = tailles[i-1];
   }
  }
 }
 
 public static void isColinear(int n){
  int l = 0;
  int prem = -1;
  int deuz = -1;
  for (int i = 15; i>=0 ; i--){
   if (banque[n][i]){
    l++;
    deuz = prem;
    prem = i;
   }
  }
  if (l <= 2){
   colinear[n] = true;
   return;
  }
  int u = Y[prem]-Y[deuz];
  int v = X[deuz]-X[prem];
  
  for (int j = deuz+1 ; j<16; j++){
   if (banque[n][j]){
    if (u*(X[j]-X[prem])+v*(Y[j]-Y[prem]) != 0){
     colinear[n] = false;
     return;
    }
   }
  }
  colinear[n] = true;
  
 }

 public static void findAllColinear(){
  int maxi = values[nbPoints];
  for (int i = 1 ; i < maxi ; i++){
   isColinear(i);
  }
 }
 
 public static void firstAttempt(){
  int mini = values[nbPoints-1];
  int remaining = 2*mini-1;
  for (int i = mini ; i < 2*mini ; i++){
      if (colinear[i]){
          aVisiter[remaining - i ][0] = 1;
          aVisiter[remaining - i ][1] = 1;
      }
  }
  int indice = mini-1;
  while (indice > 0){
      if (aVisiter[indice][1] > 0 ){
          visit(indice);
      }
      indice--;
  }
 }
 
 public static void visit(int indice){
        int rank = aVisiter[indice][0];
        int nbWays = aVisiter[indice][1];
        int taille = tailles[indice] - 1;
        int num = values[taille];
        int mask = indice;
        int subMask = mask;
        while (subMask >= num){
            if (colinear[subMask]){
                int reste = mask-subMask;
                int rankB = aVisiter[reste][0];
                if (rank+1 < rankB){
                    aVisiter[reste][0] = rank+1;
                    aVisiter[reste][1] = nbWays;
                }
                else{
                 if (rank+1 == rankB){
                  aVisiter[reste][1] = (aVisiter[reste][1]+nbWays) % modulo;
                 }
                }
            }
            subMask = (subMask-1)&mask;
        }
 }
 
 public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        for (int i = 1 ; i < values[16] ; i++){ 
      convertNumberSet(i);
        }
        construireTailles();
        
        int nbTests = sc.nextInt();
        for (int t = 1 ; t <= nbTests ; t++){
         nbPoints = sc.nextInt();
         int currentMin = (nbPoints+1)/2;
         aVisiter = new int[values[nbPoints]-1][2];
         for (int iter = 0 ; iter < values[nbPoints]-1 ; iter ++){
          aVisiter[iter][0] = currentMin;
         }
         X = new int[nbPoints];
         Y = new int[nbPoints];
         for (int p = 0 ; p < nbPoints ; p++){
          X[p] = sc.nextInt();
          Y[p] = sc.nextInt();
         }
         findAllColinear();
         firstAttempt();
         int mini = aVisiter[0][0];
         int nbWays = aVisiter[0][1];
         long temp = (long) nbWays;
         temp = (temp * factorielle[mini] ) % modulo;
         System.out.print(mini);
         System.out.print(" ");
         System.out.println(temp);
         
        }

 }

}