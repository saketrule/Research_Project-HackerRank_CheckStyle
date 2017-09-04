import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
 
 public static int N; // liczba miast
 public static int[] miasta; // miasta
 public static int K; // liczba listow
 public static int[] adresaci; // miasta docelowe
 public static int[] doreczeni; // miasta docelowe doreczone
 public static path[] paths; // sciezki 
 public static int[] wyniki; // wyniki 
 public static int route;

    public static void main(String[] args) 
    {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
       
        Scanner in = new Scanner (System.in);
          
        N = in.nextInt();
        K = in.nextInt();
        
        adresaci = new int[K];
        doreczeni = new int[K];
        
        miasta = new int[N+1];
        for (int i=0;i<N+1;i++) miasta[i] = 0; // iuniclaluzuje wszystkie miasta jako nieodwiedzone
        
        paths = new path[N-1];
        
        for(int i=0;i<K;i++)
        {
         adresaci[i] = in.nextInt();
         doreczeni[i] = 0;
        }
        
        for(int i=0;i<N-1;i++)
        {
         paths[i] = new path();
         for(int j=0;j<3;j++)
         {
          paths[i].city1 = in.nextInt();
          paths[i].city2 = in.nextInt();
          paths[i].distance = in.nextInt();
         }
        }
        
        wyniki = new int[N];
        
        int[] stos = new int[N*(N-1)];
        int ostatniNaStosie = 0;
        
        route = 0;
 
        //solution
        for(int i=1;i<N+1;i++) // zaczynam z kazdego wierzcholka
        {
         
         //zeruje stos
         for (int z=0;z<stos.length;z++) stos[z]=0;
         ostatniNaStosie=0;
         
         //wkladam wierzcholek na poczatek stosu
         stos[0] = i;
         ostatniNaStosie=0;
         
         do
         {
          //biore miasto ze stosu
          int current = stos[ostatniNaStosie];
          //oznaczam miasto jako odwiedzone
          miasta[current] = 1;
          
          
          //patrze czy miasto jest adresem ktoregokolwiek z listow i oznaczam list jako doreczony
          czyMiastoJestAdresatem(current, adresaci);
          
          
          
          
          
          // wybieram nastepny wierzcholek - najpierw sposrod listy celow, potem najtansza sciezka a jesli nie - o najtanszej sciezce
             //przechodze do nastepnego, zwiekszam moj dydtans o sciezke ktora przeszedlem
          
          
          //szukam ile jest ciezek z obecnego miasta
          int ileSciezek = 0;
          path[] sciezkiZMiasta;
          for(int x=0;x<paths.length;x++)
           if( ( (paths[x].city1) ==current) || ((paths[x].city2) ==current) )
         ileSciezek++;
          
          sciezkiZMiasta = new path[ileSciezek];
          int count = 0;
          
          //wypisuje sobie te sciezki w tablicy "sciezkiZMiasta"
          while (! (count > (ileSciezek-1)) )
          {
           for(int x=0;x<paths.length;x++)
           {
            if( ( (paths[x].city1) ==current) || ((paths[x].city2) ==current) )
            {
         sciezkiZMiasta[count] = paths[x];
            count++;
            }
           }
          }
     
          //wybieram miasto
          //najpierw sprawdzam czy wsrod docelowych miast sa nieodwiedzeni adresaci
          boolean czy = false;
          for (int m = 0;m<sciezkiZMiasta.length;m++)
          {
           if ( zawiera(sciezkiZMiasta[m].city1,adresaci) || zawiera(sciezkiZMiasta[m].city1,adresaci) )
             {
              int city = -1;
              if ( zawiera(sciezkiZMiasta[m].city1,adresaci) && sciezkiZMiasta[m].city1!=current) city = sciezkiZMiasta[m].city1;
              else city = sciezkiZMiasta[m].city2;
              
              int indeks = -1;
              for(int v=0;v<adresaci.length;v++)
              {
               if(adresaci[v] == city) indeks = v;
               v = (adresaci.length)+1;
              }
              if(doreczeni[indeks]==0) czy=true;
              
            
             }
          }
          //jesli sa - eliminuje z listy te miasta, ktore nie sa adresatami
          //jesli nie - nie eliminuje niczego
          if(czy)
          {
           for (int m = 0;m<sciezkiZMiasta.length;m++)
           {
            int temp = sciezkiZMiasta[m].city1;
            if (sciezkiZMiasta[m].city1== i) temp = sciezkiZMiasta[m].city2;  
            if ( !zawiera(temp,adresaci)) sciezkiZMiasta[m] = null;
           }
          }
          
          //eliminuje odwiedzone miasta
          for (int m = 0;m<sciezkiZMiasta.length;m++)
       {
        int temp = sciezkiZMiasta[m].city1;
        if (sciezkiZMiasta[m].city1== i) temp = sciezkiZMiasta[m].city2;  
        if ( miasta[temp] == 1) sciezkiZMiasta[m] = null;
       }
          
          //z tego co zostalo wybieram najkrotsza sciezke
          int choice = 100000;
          path sciezka = null;
          for (int m = 0;m<sciezkiZMiasta.length;m++)
          {
           if(sciezkiZMiasta[m] != null)
           {
            if(sciezkiZMiasta[m].distance < choice) 
             {
              choice = sciezkiZMiasta[m].distance;
              sciezka = sciezkiZMiasta[m];
             }
              
           }
          }
          

          if(sciezka!=null)
          {
           //zwiekszam sciezke            
           route = route + sciezka.distance;
          
           // wybrane miasto wkladam na stos
           int wybrane = sciezka.city1;
           if (wybrane == i) wybrane = sciezka.city2;
           stos[ostatniNaStosie] = wybrane;
           ostatniNaStosie++;
          }
          else //okazuje sie ze nie ma gdzie isc wiec spadam do tylu, cofam stos
          {
           stos[ostatniNaStosie] = 0;
           ostatniNaStosie--;
          }
          
         }
         while(saJeszczeAdresaci(adresaci));
         
         
         wyniki[i] = route;
         
         int answer = 100000;
         for (int w=0;w<wyniki.length;w++) 
          if (wyniki[w]<answer) answer = wyniki[w];
         
         System.out.println(answer);
        }
        
        
        
          
          
    }
    
    
    
    public static class path
    {
     public int city1;
     public int city2;
     public int distance;
    }
    
    public static boolean saJeszczeAdresaci(int[] a)
    {
     boolean result = false;
     for(int i=0;i<a.length;i++) 
     {
      if(a[i]>0) result = true;
     }
      
     return result;
    }
    
    public static void czyMiastoJestAdresatem(int miasto, int[]cele)
    {
     //sprawdzam czy miasto jest adresatem listu, jesli tak, zeruje adres tego listu.
     
     for(int i=0;i<cele.length;i++)
     {
      if(cele[i] == miasto) 
      {
       cele[i] = 1;
      // result = true;
       i = cele.length;
      }
     }
     
     //return result;
    }
    
    
    public static boolean zawiera(int l, int[]tablica)
    {
     //metoda zwraca info czy tablica zawiera licze l
     
     boolean result = false;
     for(int i=0;i<tablica.length;i++)
     {
      if(tablica[i] == l) 
      {
       result = true;
       i = (tablica.length) + 1;
      }
     }
     
     return result;
    }
    
}