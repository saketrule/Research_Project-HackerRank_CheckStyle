import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class WrapperVertexDistance{
 
 public WrapperVertexDistance(long distance, MinPenaltyVertex vertex) {
  super();
  this.distance = distance;
  this.vertex = vertex;
 }
 long distance;
 MinPenaltyVertex vertex;
}
class MinPenaltyVertex {
 
 int color=0;
 int index;
 List<WrapperVertexDistance> adjacents = new ArrayList<WrapperVertexDistance>();
 long distance=Long.MAX_VALUE;
 MinPenaltyVertex parent=null;
 }



public class Solution {
    
 static long cost;
 static boolean isSet=false;
 public static void main(String[] args) {
  Scanner input = null;
  //String fileName = args[0];
  try {
    input = new Scanner(System.in);
   //input = new Scanner(new File(fileName));
  } catch (Exception e) {
   e.printStackTrace();
  }
  int n=input.nextInt();
     int m=input.nextInt();
  List<MinPenaltyVertex> list =new ArrayList<MinPenaltyVertex>(n);
  for(int i=1; i<=n;i++){
   MinPenaltyVertex v = new MinPenaltyVertex();
   v.index=i-1;
   list.add(v);
   }
  for(int i=1;i<=m;i++){
   int from =input.nextInt()-1;
   int to=input.nextInt()-1;
   
   long distance =input.nextLong();
   if(from==to){
    continue;
   }
   MinPenaltyVertex one=list.get(from);
   MinPenaltyVertex two=list.get(to);
   one.adjacents.add(new WrapperVertexDistance(distance,two));
   two.adjacents.add(new WrapperVertexDistance(distance,one));
   }
  int source=input.nextInt()-1;
  int dest= input.nextInt()-1;
  MinPenaltyVertex v= list.get(source);
  v.distance=0;
  calculateSSSP(v,v,dest,0,source);
  System.out.println(list.get(dest).distance !=Long.MAX_VALUE?list.get(dest).distance:-1);
  
 }

 private static void calculateSSSP(MinPenaltyVertex par, MinPenaltyVertex v, int dest,long distance,int source) {
  // TODO Auto-generated method stub
  if(v.index==dest){
   if(v.distance>distance){
   v.distance=distance;
   cost=distance;
   isSet=true;
    return;
   }
  }
  if(isSet && distance>=cost){
   return;
  }
  if(v.distance==distance && v.index !=source){
   return;
  }
  else{
   v.distance=distance;
  }
  v.color=1;
  for(WrapperVertexDistance wd:v.adjacents){
   long dist=wd.distance;
   MinPenaltyVertex ver=wd.vertex;
   if(!(ver.color==1)){
   calculateSSSP(v,ver,dest,(dist|distance),source);
   }
  }
  
  v.color=2;
  
 }


}