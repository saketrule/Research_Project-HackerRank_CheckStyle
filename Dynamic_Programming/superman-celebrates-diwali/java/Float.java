import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class SupermanDiwali {
 
 public static void main(String[] args) throws IOException
 {
  BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
  String line = bfr.readLine();
  StringTokenizer stkr = new StringTokenizer(line);
  final int N = Integer.parseInt(stkr.nextToken());
  final int H = Integer.parseInt(stkr.nextToken());
  final int D = Integer.parseInt(stkr.nextToken());
  //Number of person in nth building, at height h
  int[][] countPerson = new int[N][H];
  for(int n = 0; n < N; ++n)
  {
   line = bfr.readLine();
   stkr = new StringTokenizer(line);
   int P = Integer.parseInt(stkr.nextToken());
   for(int p = 0; p < P; ++p)
   {
    int h = Integer.parseInt(stkr.nextToken()) - 1;
    countPerson[n][h] ++;
   }
  }
  
  int[][] val = new int[N][H];
  int[] maxVal = new int[H];
  for(int h = 0; h < H; ++h)
  {
   int maxH = 0;
   for(int n = 0; n < N; ++n)
   {
    int max = 0;
    if((h-1) >= 0 && val[n][h - 1] > max)
     max = val[n][h-1];
    if((h-D) >= 0 && maxVal[h-D] > max)
     max = maxVal[h-D];
    max += countPerson[n][h];
    val[n][h] = max;
    if(max > maxH)
     maxH = max;
   }
   maxVal[h] = maxH;
  }
  System.out.println(maxVal[H-1]);
 }
}