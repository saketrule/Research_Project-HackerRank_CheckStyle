import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Solution {
 
 private static final int modulo = 1000000007;

 HashMap<String, Long> map = new HashMap<String, Long>();
 
 public long getNumOfWays(char[] grid, int N, int M, int next, int remaining){
  
  if(remaining == 0){
   return 1;
  }
  
  String s = new String(grid);
  if(map.containsKey(s)){
   return map.get(s);
  }
  
  Long count = 0L;
  
  // case 1:
  if( next % M < M-1 && next / M < N-2 && grid[next + M] == '.' && grid[next+2*M]=='.' && grid[next+2*M+1]=='.'){
   grid[next]='#'; grid[next+M]='#'; grid[next+2*M]='#'; grid[next+2*M+1]='#';
   long count1 = getNumOfWays(grid, N, M, getNextEmpty(grid, next), remaining-4);
   grid[next]='.'; grid[next+M]='.'; grid[next+2*M]='.'; grid[next+2*M+1]='.';
   count =  (count + (count1 % 1000000007)) % 1000000007;
  }
  
  if( next % M < M-2 && next / M < N-1 && grid[next + 1] == '.' && grid[next+2]=='.' && grid[next+2+M]=='.'){
   grid[next]='#'; grid[next+1]='#'; grid[next+2]='#'; grid[next+2+M]='#';
   long count1 = getNumOfWays(grid, N, M, getNextEmpty(grid, next), remaining-4);
   grid[next]='.'; grid[next+1]='.'; grid[next+2]='.'; grid[next+2+M]='.';
   count =  (count + (count1 % 1000000007)) % 1000000007;
  }
  
  if(  next % M < M-2  && next /M < N-1 && grid[next + 1] == '.' && grid[next+2]=='.' && grid[next+M]=='.'){
   grid[next]='#'; grid[next+1]='#'; grid[next+2]='#'; grid[next+M]='#';
   long count1 = getNumOfWays(grid, N, M, getNextEmpty(grid, next), remaining-4);
   grid[next]='.'; grid[next+1]='.'; grid[next+2]='.'; grid[next+M]='.';
   count =  (count + (count1 % 1000000007)) % 1000000007;
  }
  
  if(  next % M < M-1 && next /M < N-2 && grid[next + 1] == '.' && grid[next+M]=='.' && grid[next+2*M]=='.'){
   grid[next]='#'; grid[next+1]='#'; grid[next+M]='#'; grid[next+2*M]='#';
   long count1 = getNumOfWays(grid, N, M, getNextEmpty(grid, next), remaining-4);
   grid[next]='.'; grid[next+1]='.'; grid[next+M]='.'; grid[next+2*M]='.';
   count =  (count + (count1 % 1000000007)) % 1000000007;
  }
  
  if(  next % M < M-1 && next /M < N-2 && grid[next + 1] == '.' && grid[next+1+M]=='.' && grid[next+1+2*M]=='.'){
   grid[next]='#'; grid[next+1]='#'; grid[next+M+1]='#'; grid[next+1+2*M]='#';
   long count1 = getNumOfWays(grid, N, M, getNextEmpty(grid, next), remaining-4);
   grid[next]='.'; grid[next+1]='.'; grid[next+M+1]='.'; grid[next+1+2*M]='.';
   count =  (count + (count1 % 1000000007)) % 1000000007;
  }
  
  if( next % M < M-2 && next /M < N-1 && grid[next + M] == '.' && grid[next+1+M]=='.' && grid[next+2+M]=='.'){
   grid[next]='#'; grid[next+M]='#'; grid[next+M+1]='#'; grid[next+M+2]='#';
   long count1 = getNumOfWays(grid, N, M, getNextEmpty(grid, next), remaining-4);
   grid[next]='.'; grid[next+M]='.'; grid[next+M+1]='.'; grid[next+M+2]='.';
   count =  (count + (count1 % 1000000007)) % 1000000007;
  }
  
  if( next % M > 0  && next /M < N-2 && grid[next + M] == '.' && grid[next+2*M]=='.' && grid[next+2*M-1]=='.'){
   grid[next]='#'; grid[next+M]='#'; grid[next+M*2]='#'; grid[next+M*2-1]='#';
   long count1 = getNumOfWays(grid, N, M, getNextEmpty(grid, next), remaining-4);
   grid[next]='.'; grid[next+M]='.'; grid[next+M*2]='.'; grid[next+M*2-1]='.';
   count =  (count + (count1 % 1000000007)) % 1000000007;
  }
  
  
  if( next % M > 1  && next /M < N-1 && grid[next + M] == '.' && grid[next+M-1]=='.' && grid[next+M-2]=='.'){
   grid[next]='#'; grid[next+M]='#'; grid[next+M-1]='#'; grid[next+M-2]='#';
   long count1 = getNumOfWays(grid, N, M, getNextEmpty(grid, next), remaining-4);
   grid[next]='.'; grid[next+M]='.'; grid[next+M-1]='.'; grid[next+M-2]='.';
   count =  (count + (count1 % 1000000007)) % 1000000007;
  }
  
  
  if(remaining > N*M / 12){
   map.put(new String(grid), count% 1000000007);
   
  }
  
  return count% 1000000007;
  
  
 }
 
 public static int getNextEmpty(char[] grid, int next){
  for(int i = next; i < grid.length; i++){
   if(grid[i]=='.')
    return i;
  }
  return -1;
 }
 
 public static int countOccurrences(String haystack, char needle)
 {
     int count = 0;
     for (int i=0; i < haystack.length(); i++)
     {
         if (haystack.charAt(i) == needle)
         {
              count++;
         }
     }
     return count;
 }
 
 public static void main(String[] args) throws IOException{
  
  
  
  
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         String cases = br.readLine();
         
         Long[] result = new Long[Integer.parseInt(cases)];
         
         
         for(int i = 0; i < Integer.parseInt(cases); i++){
          Solution bt = new Solution();
          String line = br.readLine();
          String[] a = line.split(" ");
          int N = Integer.parseInt(a[0]);
          int M = Integer.parseInt(a[1]);
          String s = "";
          
          for(int j = 0; j <N; j++){
           s+=br.readLine();
          }
          result[i]=bt.getNumOfWays(s.toCharArray(), N, M, getNextEmpty(s.toCharArray(), 0),countOccurrences(s, '.') );
          
         }
       
 
         for(Long x : result){
          System.out.println(x);
         }
 
//  bt.getNumOfWays(sb, N, M, next, remaining)
 }

}