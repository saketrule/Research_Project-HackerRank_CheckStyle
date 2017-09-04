import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

 public static void main(String[] rags) throws Exception {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  String s = br.readLine();
  int[] f = new int[26];
  int[] u = new int[26];
  List<Integer> pos[] = new List[26];
  for (int i = 0; i < 26; i++)
   pos[i] = new ArrayList<Integer>();
  int N = s.length();
  for (int i = 0; i < N; i++) {
   int v = s.charAt(i) - 'a';
   f[v]++;
   pos[v].add(i);
  }
  for (int i = 0; i < 26; i++) {
   Collections.sort(pos[i]);
   u[i] = f[i] / 2;
  }

  int chars = N / 2;
  int maxlp = N;
  StringBuilder bob = new StringBuilder();
  while (chars > 0) {

   for (int k = 0; k < 26; k++) {
    if (u[k] == 0)
     continue;
    int lp = -1;
    for(int xx = pos[k].size() - 1; xx >= 0; xx--)
     if(pos[k].get(xx) < maxlp) {
      lp = pos[k].get(xx);
      break;
     }
    boolean ok = true;
    for (int l = k + 1; l < 26; l++) {
     if (u[l] == 0)
      continue;
     int np = u[l];
     if (pos[l].get(np - 1) > lp) {
      ok = false;
      break;
     }
    }
    if (!ok) {
     continue;
    } else {
     bob.append((char) (k + 'a'));
     maxlp = lp;
     u[k]--;
     chars--;
     break;
    }
   }
  }
  System.out.println(bob);
 }
}