import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
class SetForNodes{
 ArrayList<String> nodes;
 SetForNodes(){
  nodes=new ArrayList<String>();
 }
 public String toString(){
  return nodes.toString();
 }
}
public class Solution {
static Stack<Integer> stk;
 static ArrayList<SetForNodes> sets=new ArrayList<SetForNodes>();
 static ArrayList<String> nodes=new ArrayList<String>();
 static HashMap<String,ArrayList<String>> relations=new HashMap<String,ArrayList<String>>();
 static int max;
 public static void main(String args[]){
  stk=new Stack<Integer>();
  HashMap<Integer,ArrayList<Integer>> setRelations=new HashMap<Integer,ArrayList<Integer>>();
  Scanner in=new Scanner(System.in);
  int T=Integer.parseInt(in.nextLine());
  for(int t=0;t<T;t++)
  {
  String given[]=in.nextLine().split(" ");
  switch(given[0]){
   case "A":
    int n=Integer.parseInt(given[1]);
    SetForNodes set=new SetForNodes();
    int sno=sets.size();
    for(int i=0;i<n;i++){
     String st="s"+sno+"n"+i;
     set.nodes.add(st);
     nodes.add(st);
    }
    sets.add(set);
    break;
   case "B":
    SetForNodes set1,set2;
    int s1,s2;
    s1=Integer.parseInt(given[1])-1;
    s2=Integer.parseInt(given[2])-1;
    set1=sets.get(s1);
    set2=sets.get(s2);
    
    if(!setRelations.containsKey(s1)){
     setRelations.put(s1,new ArrayList<Integer>());
    }
    setRelations.get(s1).add(s2);
    if(!setRelations.containsKey(s2)){
     setRelations.put(s2,new ArrayList<Integer>());
    }
    setRelations.get(s2).add(s1);
    
    
    for(String node:set1.nodes){
     if(relations.containsKey(node)){
      relations.get(node).addAll(set2.nodes);
     }else{
      relations.put(node,new ArrayList<String>(set2.nodes));
     }
    }  
    break;
   case "C":
    SetForNodes newSet=new SetForNodes();
    int sorceSet=Integer.parseInt(given[1])-1;
    
    newSet.nodes.addAll(sets.get(sorceSet).nodes);
    ArrayList<Integer> list=setRelations.get(sorceSet);
    for(Integer i:list){
     newSet.nodes.addAll(sets.get(i).nodes);
    }
    sets.add(newSet);
    break;
  }
  }
  /* System.out.println(sets);
  System.out.println(nodes);
  System.out.println(setRelations);
  System.out.println(relations);
  System.out.println(relations.size()); */
  max=0;
  comb(-1);
  System.out.println(max);
 }
 public static void comb(int start){
  //if(stk.size()>0){
  if(true){
   List<Integer> tarr=stk;
   HashSet<String> set=new HashSet<String>();
   /* for(int i=0;i<nodes.size();i++){
    if(!tarr.contains(i)){
     set.add(nodes.get(i));
    }
   } */
   
   for(Integer i:tarr)
    set.add(nodes.get(i));
   
   boolean isSolution=true;
   tag:
   for(String st:set){
    ArrayList<String> vals=relations.get(st);
    if(vals==null)
     continue;
    for(String val:vals){
     if(set.contains(val)){
      isSolution=false;
      break tag;
     }
    }
   }
   if(isSolution){
    //int cur=nodes.size()-tarr.size();
    int cur=tarr.size();
    if(cur>max){
     //System.out.println(set+":"+cur);
     max=cur;
    }
    //System.exit(0);
   } 
  }
  for(int i=start+1;i<nodes.size();i++){
   stk.push(i);
   comb(i);
   stk.pop();
  }
 }
 
}