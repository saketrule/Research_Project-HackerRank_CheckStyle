import java.util.*;

public class Solution {

    public static void main(String[] args) 
    {
     Scanner s = new Scanner(System.in);
     ArrayList<Long> beads;
        int T = s.nextInt();
        int N = 0;
        long total;
        for (int i = 0; i < T; i++)
        {
            beads = new ArrayList<Long>();
         N = s.nextInt();
         total = 0l;
         for (int j = 0; j < N; j++)
         {
          beads.add(s.nextLong());
          total += beads.get(j)%1000000007;
         }
         total = pow(total%1000000007, (N > 1 ? N - 2 : 0));
         for (int k = 0; k < beads.size(); k++)
         {
                total *= pow(beads.get(k), (N > 1 ? beads.get(k) - 1 : beads.get(k)-2));
                total %= 1000000007;
         }
         System.out.println(total%1000000007);
        }
    }
    
    public static long pow(long bead, long pewr)
    {
        if (pewr == 0)
            return 1;
     long total = 1;
  for (int m = 0; m < pewr; m++)
        {
   total *= bead % 1000000007;
            total %= 1000000007;
        }
        
  return total % 1000000007;
    }
}