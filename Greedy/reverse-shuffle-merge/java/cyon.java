import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {

 private static void debug(Object... args) {
  //System.out.println(Arrays.deepToString(args));
 }

 public static void main(String[] rags) throws Exception {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  PrintWriter pw = new PrintWriter(System.out);
  String s = br.readLine();
  int[]f=new int[26];
  int[]u=new int[26];
  List<Integer>pos[] = new List[26];
  for(int i=0;i<26;i++)pos[i]=new ArrayList<Integer>();
  int N=s.length();
  for(int i=0;i<N;i++) {
   int v = s.charAt(i)-'a';
   f[v]++;
   pos[v].add(i);
  }
  for(int i=0;i<26;i++) {
   Collections.sort(pos[i]);
   u[i]=f[i]/2;
  }
  //egfezgzfgg
  int chars = N/2;
  int maxlp = N;
  StringBuilder bob = new StringBuilder();
  while(chars > 0) {
   
   for(int i=0;i<26;i++) {
    if(!pos[i].isEmpty()) debug(pos[i]);
   }
   
   for(int k=0;k<26;k++) {
    if(u[k]==0) continue;
    int lp = pos[k].get(pos[k].size()-1);
    debug(lp, (char)(k+'a'));
    boolean ok=true;
    for(int l=k+1;l<26;l++) {
     if(u[l]==0) continue;
     int np = u[l];
     if(pos[l].get(np-1) > lp) {
      ok = false;
      break;
     }
    }
    debug(lp, (char)(k+'a'),ok);
    if(!ok) {
     continue;
    } 
    else {
     bob.append((char)(k+'a'));
     maxlp=lp;
     f[k]--;
     u[k]--;
     chars--;
     for(int h=0;h<26;h++) {
      while(!pos[h].isEmpty() && pos[h].get(pos[h].size()-1) >= maxlp) {
       pos[h].remove(pos[h].size()-1);
       f[h]--;
      }
     }
     break;
    }
   }
  }
  pw.println(bob.toString());
  pw.flush();
 }
}