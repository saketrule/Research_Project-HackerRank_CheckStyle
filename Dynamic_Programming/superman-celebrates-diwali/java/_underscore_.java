import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;


public class Solution {

  public static void main(String[] args) throws IOException {
    Reader.init(System.in);
    int N = Reader.nextInt();
    int H = Reader.nextInt();
    int I = Reader.nextInt();
    
    int[][] people = new int [N][H+1]; 
    for (int i = 0; i < N; i++) {
      int count = Reader.nextInt();
      for (int j = 0; j < count; j++) {
        int floor = Reader.nextInt();
        people[i][floor]++;
      }
    }
    
    int[] max = new int[H+1];
    for (int level = 1; level <= H; level++) {
      for (int building = 0; building < N; building++) {
        int bestBelow = people[building][level-1];
        if (level > I) {
          bestBelow = Math.max(bestBelow, max[level-I]);            
        }
        people[building][level] += bestBelow;
        max[level] = Math.max(max[level],people[building][level]);
      }
    }
    
    System.out.println(max[H]);
  }
 
}



/** Class for buffered reading int and double values */
class Reader {
  static BufferedReader reader;
  static StringTokenizer tokenizer;

  /** call this method to initialize reader for InputStream */
  static void init(InputStream input) {
    reader = new BufferedReader(
        new InputStreamReader(input) );
    tokenizer = new StringTokenizer("");
  }

  /** get next word */
  static String next() throws IOException {
    while ( ! tokenizer.hasMoreTokens() ) {
      //TODO add check for eof if necessary
      tokenizer = new StringTokenizer(
          reader.readLine() );
    }
    return tokenizer.nextToken();
  }

  static int nextInt() throws IOException {
    return Integer.parseInt( next() );
  }

  static double nextDouble() throws IOException {
    return Double.parseDouble( next() );
  }
}