import java.util.*;

class Solution
{
 static void Adjust( int a[] , int i , int size )
 {
  int j = (2 * i) + 1 , rt = 0 ;
  int item = a[i] ;
  
  while( j < size )
  {
   rt = 0;
   if( j < size - 1 && a[j] < a[j+1] )
   {
    j++ ;
    rt = 1 ;
   }
   if( item >= a[j] )
    {
     break;
    }
   else 
   {
    if( rt == 1 )
    {
     a[j/2 - 1] = a[j] ;
     rt = 0;
    }
    else
    {
     a[j/2] = a[j] ;
    }
   }
   j = (j * 2) + 1 ;
  }

   if( rt == 1 )
   {
    a[j/2 - 1] = item ;
   }
   else
   {
    a[j/2] = item ;
   }
  
 }
 
 static void Heapify( int a[] , int size )
 {
  for( int j = ((size)/2 - 1) ; j >= 0 ; j-- )
   Adjust( a , j , size ) ;
  
 }
 
 static void Heap_Sort( int a[] , int size ) 
 {
  int temp ;
  Heapify( a , size ) ;
  for( int q = size - 1 ; q > 0 ; q-- )
  {
   temp = a[0] ;
   a[0] = a[q] ;
   a[q] = temp ;
   Adjust( a , 0 , q ) ;
  }
  
 }

 public static void main( String ...$ )
 {
  Scanner in = new Scanner( System.in ) ;
  int n = in.nextInt();
  
  int a[] = new int[n] ;
  for( int i = 0 ; i < n ; i++ )
   a[i] = in.nextInt();
  
  int p = in.nextInt();
  int q = in.nextInt();
  
  ArrayList<Integer> ar = new ArrayList<Integer>() ;
  
  Heap_Sort( a , n ) ;
  
  for( int i = 0 ; i < n-1 ; i++ )
  {
   
    if( (a[i]+a[i+1])%2 == 0 )
    {
     ar.add( (a[i]+a[i+1])/2 ) ;
    }
    else
    {
     ar.add( (a[i]+a[i+1])/2 ) ;
     ar.add( (a[i]+a[i+1])/2 + 1 ) ;
    }
   
  }
  ar.add(p) ;
  ar.add(q) ;
  
  int max = Integer.MIN_VALUE ;
  int ans = 0 ;
  
        Collections.sort(ar) ;
  
        for( int j : ar)
  {
   int min = Integer.MAX_VALUE ;
   
   if( j >= p && j <= q )
   {
    for( int k : a )
     min = Math.min( min , Math.abs(j-k) ) ;
   
    if( max < min )
    {
     max = min ;
     ans = j ;
    }
   }
  }
  System.out.println(ans);
  
  
  
 }
 
}