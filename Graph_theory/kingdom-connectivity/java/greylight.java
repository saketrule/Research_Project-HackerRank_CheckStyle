import java.io.*;
import java.util.*;
public class Solution {
 static HashMap<Integer, Integer>[] con;
 static HashSet<Integer>[] coned;
 static boolean[] pending;
 static long[] cnt;
 static boolean[] connected;
 static int cities;
 static int paths;
 static long result;
 static final long mod = 1000000000;
 static boolean ended; 
 public static void main(String[] arg) throws FileNotFoundException{ 
  /*int ii = Integer.MAX_VALUE;
  long jj = Integer.MAX_VALUE;
  System.out.println((ii*jj)%100);*/
  // Scanner sc = new Scanner(new FileInputStream(new File("input")));
  Scanner sc = new Scanner(System.in);
  cities = sc.nextInt();
  paths = sc.nextInt();
  cnt = new long[cities+1];
  pending = new boolean[cities+1];
  connected = new boolean[cities+1];
  for(int i=0;i<=cities;i++)
   cnt[i] = -1;
  con = new HashMap[cities+1];
  coned = new HashSet[cities+1];
  for(int i=0; i<paths;i++){
   int from = sc.nextInt();
   int to =sc.nextInt();
   if(coned[to] == null)
    coned[to] = new HashSet<Integer>();
   coned[to].add(from);
   
   if(con[from] == null)
    con[from] = new HashMap<Integer, Integer>();
   if(con[from].containsKey(to))
    con[from].put(to, con[from].get(to)+1);
   else
    con[from].put(to, 1);
  }
  setCon(cities);
  result = getSum(1);
  if(ended == true){
   System.out.println("INFINITE PATHS");
  }
  else
   System.out.println(result);
 }
 public static void setCon(int i){
  if(coned[i] == null)
   return;
  for(int c: coned[i]){
   if(connected[c] == false){
    connected[c] = true;
    setCon(c);
   }
  }  
 }
 public static long getSum(int city){
  if(ended == true)
   return 0;
  if(cnt[city] >= 0)
   return cnt[city];
  else if(pending[city] == true){
   if(connected[city] == true)
    ended = true;
   return 0;
  }
  else{
   long tmp = 0;
   if(con[city] == null){
    cnt[city] = 0;
    return 0;
   }
   pending[city] = true;
   Iterator iter = con[city].entrySet().iterator(); 
   while(iter.hasNext()){
       Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) iter.next(); 
       int c = entry.getKey(); 
       int ways = entry.getValue();    
    // System.out.println("checking "+city+" to "+c);
    if(c == cities)
     tmp = (tmp+ways)%mod;
    else
     tmp = (tmp + (getSum(c)*ways)%mod)%mod;    
   }
   // System.out.println("city "+city+" has "+tmp);
   cnt[city] = tmp;
   return tmp;
  }   
 }
}