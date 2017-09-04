import java.util.Scanner;
import java.math.BigInteger;
import java.lang.Math;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


//--------------------> Glass Wire à regarder

public class First{
 public static void main(String[] args){
  Scanner sc=new Scanner(System.in);
  String[] texte=sc.nextLine().split(" ");
  int ligne=Integer.parseInt(texte[0]),colonne=Integer.parseInt(texte[1]),k=Integer.parseInt(texte[2]);
  int[][] matrice=new int[ligne][colonne];
  
  for(int i=0;i<ligne;i++){
   String[] temp=sc.nextLine().split(" ");
   for(int j=0;j<colonne;j++)matrice[i][j]=Integer.parseInt(temp[j]);
  }
  
  int l=ligne,c=colonne;
  int deb=0;
  while(l>0 && c>0){
   //----------> prendre
   int m=2*l+2*(c-2);
   int[] tab=new int[m];
   int x=0;
   int i=deb,j=deb;
   for(;i<deb+l-1;i++){
    tab[x]=matrice[i][deb];
    x++;
   }
   for(;j<deb+c-1;j++){
    tab[x]=matrice[deb+l-1][j];
    x++;
   }
   for(;i>deb;i--){
    tab[x]=matrice[i][deb+c-1];
    x++;
   }
   for(;j>deb;j--){
    tab[x]=matrice[deb][j];
    x++;
   }
      
   //---------->tourner
   tab=tourner(tab,k);
   
   //---------->remettre
   x=0;
   i=deb;j=deb;
   for(;i<deb+l-1;i++){
    matrice[i][deb]=tab[x];
    x++;
   }
   for(;j<deb+c-1;j++){
    matrice[deb+l-1][j]=tab[x];
    x++;
   }
   for(;i>deb;i--){
    matrice[i][deb+c-1]=tab[x];
    x++;
   }
   for(;j>deb;j--){
    matrice[deb][j]=tab[x];
    x++;
   }
   
   //-----------> entrer
   deb++;
   l-=2;
   c-=2;
  
  }
  
  for(int i=0;i<ligne;i++)
   for(int j=0;j<colonne;j++)
    System.out.print(matrice[i][j]+""+(j==(colonne-1)?'\n':" "));
  
 
 }
 
 public static int[] tourner(int[] tab,int k){
  int n=tab.length;
  int[] reps=new int[n];
  k=k%tab.length;
  
  for(int x=0;x<n;x++){
   int indice=(x+k)%n;
   reps[indice]=tab[x];
  }
  
  return reps;  
  
  
 }
 
}