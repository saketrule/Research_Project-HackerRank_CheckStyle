import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static String lexSeq(String str, int from, int to, char minChar) {
  String ans = "";//System.out.println("input "+str.substring(from, to+1));
  StringBuilder tempSeq = new StringBuilder();
  int lastCountOfToUsed = -1;
  char finalChar = str.charAt(to);
  for (int i = from; i <= to; i++) {
   char ch = str.charAt(i);
   if (tempSeq.length() == 0 && ch > minChar) {
    if (ch < finalChar) {
     tempSeq.append(ch);
    } else if (ch == finalChar) {
     tempSeq.append(ch);
     lastCountOfToUsed = i;
    }
    continue;
   }else if(tempSeq.length() == 0 && ch <= minChar)
    continue;

   for (int k = 0; k <= tempSeq.length(); k++) {
    if (k == tempSeq.length() && tempSeq.charAt(tempSeq.length()-1)!=finalChar) {
     tempSeq.append(ch);
     if (ch == finalChar)
      lastCountOfToUsed = i;
     break;
    }else if(k == tempSeq.length())
     break;

    if (ch > minChar) {
     if (ch < finalChar && ch < tempSeq.charAt(k)) {
      tempSeq.delete(k, tempSeq.length());
      tempSeq.append(ch);
      lastCountOfToUsed = -1;
      break;
     } else if (ch == finalChar && ch < tempSeq.charAt(k)) {
      tempSeq.delete(k, tempSeq.length());
      tempSeq.append(ch);
      lastCountOfToUsed = i;
      break;
     }
    }
   }

  }
  ans = tempSeq.toString() + "@" + lastCountOfToUsed;//System.out.println("output "+ans+" "+minChar);
  return ans;
 }

 public static void main(String[] args) {

//  System.out.print(lexSeq("vqq", 0, 2, 'a'));
  Scanner sc = new Scanner(System.in);
  String str = sc.nextLine();
  StringBuilder strTemp = new StringBuilder(str);
  str = strTemp.reverse().toString();
//  System.out.println(str);
  StringBuilder ans = new StringBuilder();
  int[] charCount = new int[26];
  int[] charCount2 = new int[26];
  for (int j = 0; j < charCount.length; j++) {
   charCount[j] = 0;
  }

  for (int j = 0; j < str.length(); j++) {
   char ch = str.charAt(j);
   charCount[ch - 97]++;
  }
  for (int j = 0; j < charCount.length; j++) {
   charCount[j] >>= 1;
   charCount2[j] = charCount[j];
  }
  char highestCh = highestChar(charCount2);
  int lastChange = -1;
  for (int j = 0; j < str.length(); j++) {

   char ch = str.charAt(j);
   if (ch == highestCh) {
    ans.append(ch);
    lastChange = j;
    charCount2[ch - 97]--;
    highestCh = highestChar(charCount2);
   } else if (ch > highestCh) {
    if (charCount[ch - 97] > 0) {
     charCount[ch - 97]--;

    } else {//System.out.println("output C count "+charCount2[1]);
     String strSequence[] = lexSeq(str, lastChange + 1, j,
       highestCh).split("@");
     int strSeqIndex = Integer.parseInt(strSequence[1]);
     for (int index = 0; index < strSequence[0].length(); index++) {
      if (index == (strSequence[0].length()-1)
        && strSeqIndex != j)
       charCount[ch - 97]++;
      else if (index < (strSequence[0].length()-1)){
       if(charCount2[strSequence[0].charAt(index) - 97]<=0){
        StringBuilder teStringBuilder = new StringBuilder(strSequence[0]);
        teStringBuilder.deleteCharAt(index);
        strSequence[0]=teStringBuilder.toString();
        index--;
       }else{
        charCount[strSequence[0].charAt(index) - 97]++;
        charCount2[strSequence[0].charAt(index) - 97]--;
       }
      }
     }
     ans.append(strSequence[0]);
     if (strSeqIndex != j){
      for(int kl=strSeqIndex+1; kl<j;kl++){
       charCount[str.charAt(kl) - 97]++;
      }
      j = strSeqIndex;      
     }
     lastChange = j;
     charCount2[ch - 97]--;
    }
   }
  }

  System.out.print(ans);
  System.out.println();

  sc.close();
 }

 public static char highestChar(int[] charCount) {
  char ans = 'a';
  for (int i = 0; i < charCount.length; i++) {
   if (charCount[i] > 0) {
    ans += i;
    break;
   }
  }
  return ans;
 }
}