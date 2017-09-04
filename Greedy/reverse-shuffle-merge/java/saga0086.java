import java.io.*;
import java.util.*;

public class Solution {

  public static void main(String[] args) {
    Scanner sc=new Scanner(System.in);
    String S=sc.nextLine();
    if(S.length()==0)return;
    if(S.length()<4){
      System.out.print(S.charAt(0));
      return;
    }
    int[] cc=new int[26];
    for(int i=0;i<S.length();i++)cc[S.charAt(i)-97]++;
    for(int i=0;i<26;i++)cc[i]=cc[i]/2;
    Stack<Integer>[] latest=(Stack<Integer>[])java.lang.reflect.Array.newInstance(Stack.class,26);
    for(int i=0;i<S.length();i++)
      if(latest[S.charAt(i)-97]!=null && latest[S.charAt(i)-97].size()<cc[S.charAt(i)-97])latest[S.charAt(i)-97].push(i);
      else if(latest[S.charAt(i)-97]==null){
        latest[S.charAt(i)-97]=new Stack<Integer>();
        latest[S.charAt(i)-97].push(i);
       }
    int[] rr=new int[S.length()/2];
    int p=-1,start=S.length();
    while(p<rr.length-1){
      int max=0;
      for(int i=0;i<26;i++)
        if(latest[i]!=null && latest[i].peek().intValue()>max)max=latest[i].peek().intValue();
      char c=S.charAt(max);
      char min;
      while(start>max+1){
        min=c;
        for(int i=max+1;i<start;i++)
          if(min>S.charAt(i) && latest[S.charAt(i)-97]!=null)min=S.charAt(i);
        if(min==c)break;
        for(int i=start-1;i>max;i--){
          if(S.charAt(i)==min){
            rr[++p]=i;
            start=i;
            latest[min-97].pop();
            if(latest[min-97].size()==0){
              latest[min-97]=null;
              break;
            }
          }
        }
      }
      for(int i=start-1;i>=max;i--)
        if(S.charAt(i)==c){
         rr[++p]=i;
         start=i;
         latest[c-97].pop();
         if(latest[c-97].size()==0)latest[c-97]=null;
         break;
       }
    }
    char[] rr2=new char[rr.length];
 for(int i=0;i<rr2.length;i++)rr2[i]=S.charAt(rr[i]);
    String result=new String(rr2);
      
    System.out.print(result);
    
  }
  
}