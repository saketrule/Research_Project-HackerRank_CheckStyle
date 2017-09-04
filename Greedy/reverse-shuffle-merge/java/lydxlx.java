import java.util.Scanner;


public class Solution {

 public static void main(String[] args) {
  Scanner cin = new Scanner(System.in);
  char s[] = cin.next().toCharArray();
  int a[][] = new int[s.length][128];
  for (int i=0; i<s.length; i++)
   a[i][s[i]] = 1;
  for (int j=0; j<128; j++)
   for (int i=1; i<s.length; i++)
    a[i][j] += a[i - 1][j];
  
  int goal[] = new int[128];
  int total = 0;
  for (int i=0; i<128; i++) {
   goal[i] = a[s.length - 1][i] / 2;
   total += goal[i];
  }
  int i = s.length - 1;
  StringBuilder res = new StringBuilder();
  while(total > 0) {
   int which = -1;
   int index = 0;
   for (char ch='a'; ch<='z'; ch++)
    if (goal[ch] > 0) {
     for (int j=i; j>=0; j--)
      if (s[j] == ch) {
       boolean isok = true;
       for (char cb='a'; cb<='z'; cb++)
        if (a[j][cb] < goal[cb]) {
         isok = false;
         break;
        }
       if (isok) {
        which = ch;
        index = j;
       }
       break;
      }
     if (which != -1) break;
    }
   res.append((char)which);
   goal[which]--;
   i = index - 1;
   total--;
  }
  
  System.out.println(res.toString());
  

  cin.close();
 }

}