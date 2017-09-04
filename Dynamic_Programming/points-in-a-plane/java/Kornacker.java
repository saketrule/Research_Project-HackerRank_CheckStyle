import java.io.* ;
import java.text.DecimalFormat;
import java.util.*;
import static java.lang.Math.* ;
import static java.util.Arrays.* ;

public class Solution {
 
 public static void main(String[] args) {
  
  int n = in.nextInt() ; in.nextLine() ;
  
  while( n-- > 0)
   new Solution().solveProblem();
  
  out.close();
 }

 static Scanner in = new Scanner(new InputStreamReader(System.in));
 static PrintStream out = new PrintStream(new BufferedOutputStream(System.out));
 //static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
 
 ArrayList<Integer> lines = new ArrayList<Integer>() ;
 
 int n ;
 int[] x, y;
 long MOD = 1000000007 ;
 
 int min = 9 ;
 public void solveProblem() {  

  n = in.nextInt() ;
  x = new int[n] ;
  y = new int[n] ;
  
  for( int i = 0 ; i < n ; i++ ){
   x[i] = in.nextInt() ;
   y[i] = in.nextInt() ;
  }
  
  for( int i = 1 ; i < ( 1 << n ) ; i++ ){
   
   boolean goed = true ;
   int f = -1 ;
   int s = -1 ;
   
   for( int j = 0 ; j < 16 && goed ; j++ ){
    if( ((1<<j) & i ) > 0 ){
     if( f == -1 )
      f = j ;
     else if( s == -1 )
      s = j ;
     else
      goed = sameLine( x[f], y[f], x[s], y[s], x[j], y[j]) ;
     
    }
   }
   
   if( goed )
    lines.add(i) ;
   
  }
  
  cache = new E[1<<n] ;
  E res = bt( (1<<n) -1) ;
  
  for( long i = 1 ; i <= res.stappen ; i++ ){
   res.aantal = (res.aantal*i)%MOD ;
  }
  out.println(res.stappen + " " + res.aantal) ;
  
 }
 
 E[] cache ;
 private E bt(int i) {

  if( i == 0 )
   return new E(0,1) ;
  
  if( cache[i] != null)
   return cache[i] ;
  E res = null ;
  
  int m = 1 ;
  while( (m & i) == 0)
   m <<= 1 ;
  
  
  for( int line : lines ){
   if( (line & i) != line || ( m & line ) == 0 )
    continue ;
   E nu = bt( i - line ) ;
   if( res == null || res.stappen > nu.stappen )
    res = new E(nu.stappen,nu.aantal) ;
   else if( res.stappen == nu.stappen ){
    res.aantal = (res.aantal + nu.aantal) % MOD ;   
   }
  }
  res.stappen++ ;
  
  return cache[i] = res ;
  
 }

 class E{
  
  int stappen ;
  long aantal ;
  
  public E(int stappen, long aantal) {
   super();
   this.stappen = stappen;
   this.aantal = aantal;
  }
  
  
 }
 public boolean sameLine( int x1, int y1, int x2, int y2, int x3, int y3){
  return (y3-y1)*(x2-x1) == (y2-y1)*(x3-x1) ;
 }


}