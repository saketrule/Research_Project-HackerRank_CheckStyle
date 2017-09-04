import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
 static int[] money;
 static int maxValue=-9090909;
 static ArrayList<String> store = new ArrayList<String>();
  static Hashtable<Integer,String> neighbor = new Hashtable<Integer,String>();
    public static void main(String[] args) {
     
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        money= new int[N+1];
        for(int i=0;i<N;i++){
         money[i+1]=in.nextInt();
        }
        for(int i=0;i<M;i++){
         int n1=in.nextInt();
         int n2=in.nextInt();
         if(neighbor.containsKey(n1)){
          neighbor.put(n1,neighbor.get(n1)+"\t"+n2);
         }
         else{
          neighbor.put(n1, ""+n2);
         }
         if(neighbor.containsKey(n2)){
          neighbor.put(n2,neighbor.get(n2)+"\t"+n1);
         }
         else{
          neighbor.put(n2, ""+n1);
         }
        }
        Hashtable<Integer,String> all_candi = new Hashtable<Integer,String> ();
        for(int i=1;i<=N;i++){
         all_candi.put(i, "");
        }
        for(int i=1;i<=N;i++){
         Hashtable<Integer,String> temp_candi = new Hashtable<Integer,String>();
      for(Integer ele:all_candi.keySet()){
       temp_candi.put(ele, "");
      }
         temp_candi.remove(i);
         
         dfs(i,temp_candi,0,"");
        }
        
        Hashtable<String,String> finalStore = new Hashtable<String,String>();
        for(String ele:store){
         String temp[]=ele.split(":");
         if(Integer.parseInt(temp[0])==maxValue){
          String[] member=temp[1].split("\t");
          ArrayList<Integer> yo = new ArrayList<Integer>();
          for(String el:member){
           yo.add(Integer.parseInt(el));
          }
          Collections.sort(yo);
          String pat="";
          for(int i=0;i<yo.size();i++){
           pat=pat+" "+yo.get(i);
         
          }
          finalStore.put(pat, "");
          
         }
        }
        
        in.close();
        System.out.println(maxValue+" "+finalStore.size());
}

    
    public static void dfs(int curNode,Hashtable<Integer,String> candi,int curGain,String path){
     if(path.equals("")){
      path=String.valueOf(curNode);
     }
     else{
      path=path+"\t"+String.valueOf(curNode);
     }
     curGain+=money[curNode];
     if(neighbor.containsKey(curNode)){
      String []neigh=neighbor.get(curNode).split("\t");
      for(String ele:neigh){
       if(candi.containsKey(Integer.parseInt(ele))){
        candi.remove(Integer.parseInt(ele));
       }
      }
     }
     int leftMoney=0;
     for(Integer remain:candi.keySet()){
      leftMoney+=money[remain];
     }
     if(candi.size()==0){
      if(curGain>maxValue){
       maxValue=curGain;
      }
      store.add(String.valueOf(curGain)+":"+path);
      return;
     }
     else if(leftMoney==0){
      if(curGain>maxValue){
       maxValue=curGain;
      }
      store.add(String.valueOf(curGain)+":"+path);
     }
     for(Integer next:candi.keySet()){
      Hashtable<Integer,String> temp_candi = new Hashtable<Integer,String>();
      for(Integer ele:candi.keySet()){
       temp_candi.put(ele, "");
      }
     
       temp_candi.remove(next);
      dfs(next,temp_candi,curGain,path);
     }
    }
}