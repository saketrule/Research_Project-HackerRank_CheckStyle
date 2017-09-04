import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;


public class Solution {

 public static String reverseShuffleMerge( String str ) {
  int count[] = new int[256];
  int seen[] = new int[256];
  int added[] = new int[256];
  for( char c: str.toCharArray() )
   count[c]++;
  
  Stack<Character> st = new Stack<Character>();
  for( int i = str.length() - 1; i >= 0; --i ) {
   char c = str.charAt(i);
   seen[c]++;
   if( added[c] >= count[c] / 2 )
    continue;
   while( st.size() > 0 && st.peek() > c && count[st.peek()] - seen[st.peek()] >= ( count[st.peek()] / 2 - added[st.peek()] + 1 ) ) {
    added[st.peek()]--;
    st.pop();
   }
   st.push( c );
   added[c]++;
  }
  StringBuilder builder = new StringBuilder();
  for( char c: st ) 
   builder.append(c);
  return builder.toString();
 }
 public static void main(String[] args) throws Exception {
  BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
        String line = null;
        while ( ( line = reader.readLine() ) != null ) {

         System.out.println( reverseShuffleMerge( line ) );
        }

 }

}