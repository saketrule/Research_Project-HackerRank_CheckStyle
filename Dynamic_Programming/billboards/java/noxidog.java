import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Solution {

 @SuppressWarnings("deprecation")
 private static int getNumber(DataInputStream in) {
  try { return Integer.parseInt(in.readLine()); } catch (Exception e) {}
  return 0;
 }

 @SuppressWarnings("deprecation")
 private static String getString(DataInputStream in) {
  try {
   return in.readLine();
  } catch (IOException e) {
   e.printStackTrace();
   return null;
  }
 }

 public static void main(String[] args)
 {
//  @SuppressWarnings("unused")
//  long strt = System.currentTimeMillis();
  
  DataInputStream in = new DataInputStream(new BufferedInputStream(System.in));
  PrintStream out = new PrintStream(new BufferedOutputStream( System.out));

  String[] vals = getString(in).split(" ");
  int N = Integer.parseInt(vals[0]);
  int K = Integer.parseInt(vals[1]);
  
  final long[] opt = new long[N];
  final int[] nums = new int[N];
  long value = 0;
  
  int last_hit = -1, last_hit_si = 0;
  long last_hit_val = 0;
  for (int i = 0; i < nums.length; ++i)
  {
   nums[i] = getNumber(in);
   value += nums[i];
   if (i>=K)
   {
    value -= nums[i-K];
    long accum = 0;
    long v = value;
    if (last_hit >= (i-K))
    {
     for (int j = i; j > 0 && j > last_hit_si; --j)
      accum += nums[j];
     accum += last_hit_val;
     if (v < accum)
     {
      v = accum;
      last_hit_val = v;
      last_hit_si = i;
     }
    }
    else
    {
     int lh = -1;
     for (int j = i; j > 0 && j >= (i-K); --j)
     {
      if (j<last_hit)
       break;
      if (v < accum + opt[j-1])
      {
       v = accum + opt[j-1];
       lh = j-1;
       last_hit_val = v;
      }
      accum += nums[j];
     }
     if (lh >= 0)
     {
      last_hit_si = i;
      last_hit = lh;
     }
    }
    opt[i] = v;
   }
   else
   {
    opt[i] = value;
   }
  }
  out.println("" + opt[opt.length-1]);
  out.flush();
//  System.out.println("brute : " + brute(nums, 0, K));
//  out.flush();
 }

}