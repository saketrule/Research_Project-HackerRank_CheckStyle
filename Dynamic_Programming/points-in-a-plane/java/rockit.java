import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

class myComparator implements Comparator<int[]>{
 public int compare(final int[] entry1, final int[] entry2) {
  final int time1 = entry1[1];
  final int time2 = entry2[1];
  if(time1<time2)return -1;
  else if(time1==time2)return 0;
  else return 1;
 }
 
}
;
public class Solution {
 static int values[][];
 static int bits[];
 static int mincount;
 static int length;
 static int fact[];
 public static void print(){
  for(int i=0;i<length;i++)System.out.print(values[i][0]+","+values[i][1]+" ");
  System.out.println();
 }
 public static int factorial(int f){
  if(fact[f]>0)return fact[f];
  if(f<2)return 1;
  long lo=1;
  return fact[f]=(int)((lo*f*factorial(f-1))%1000000007);
 }

 public static int func(int remove){
  Arrays.sort(values, new myComparator());
  int include=0,after=0;
  boolean included[]=new boolean[length];
  for(int i=length-1;i>0;i--){
   after=(include|values[i][0]);
   if(after!=include){
    included[i]=true;
    include=after;
    if(after==remove)break;
   }
  }
  include=0;after=0;
  int count=0;
  for(int i=0;i<length;i++){

   if(included[i]){
    after=(include|values[i][0]);
    if(after==include)included[i]=false;
    else {
     count++;
     include=after;
    }
   }
  }
  return count;
 }

 public static int calc(int remove,int count){
  //System.out.println("removing "+remove+" count="+count);
  if(remove==0)return 1;
  if(count==0)return 0;
  int least=1;
  while((least&remove)==0)least=(least<<1);
  int sum=0;
  for(int i=0;i<length;i++){
   if((bits[i]&least)>0){
    for(int j=bits[i]; j>0; j=(j-1)&bits[i]){
     if((j&least)>0&&(j&remove)>least){
      sum=(sum+calc(remove-j,count-1))%1000000007;
     }
    }
   }
  }
  sum=(sum+calc(remove-least,count-1))%1000000007;
  //System.out.println(sum);
  //sum=(int)((lo*factorial(count)*sum)%1000000007);
  //if(sum>0)System.out.println("removing "+remove+" count="+count+" sum="+sum);
  return sum;
 }

 public static void main(String argc[]) throws Exception{
  Scanner sc=new Scanner(new InputStreamReader(System.in));
  int t,n;
  t=sc.nextInt();
  while(t-->0){
   n=sc.nextInt();
   HashMap<String,Integer> map=new HashMap<String,Integer>();
   double points[][]=new double[n+1][2];
   for(int i=1;i<=n;i++){
    points[i][0]=sc.nextInt();
    points[i][1]=sc.nextInt();
   }
   double m,c;
   String key;
   for(int i=1;i<=n;i++){
    for(int j=i+1;j<=n;j++){
     if(points[j][0]==points[i][0]){
      key="INF"+points[i][0];
     }
     else{
      m=(points[j][1]-points[i][1])/(points[j][0]-points[i][0]);
      m = Math.round(m*1000)/1000.0d;
      c=points[i][1]-m*points[i][0];
      c=Math.round(c*1000)/1000.0d;
      //System.out.println("m="+m+" c="+c);
      key=""+m+c;}
     int value=(1<<(i-1)|1<<(j-1));
     if(map.containsKey(key)){
      map.put(key,map.get(key)|value);
     }
     else{
      map.put(key, value);
     }
    }
   }
   int value,sum=0;
   values=new int[map.size()][2];
   int i=0;
   length=map.size();
   bits=new int[length];
   for(String ke:map.keySet()){
    value=map.get(ke);
    values[i][0]=value;
    bits[i]=value;
    sum=0;
    while(value>0){
     sum+=value&1;
     value=value>>1;
    }
    values[i++][1]=sum;
   }
   mincount=func((1<<n)-1);
   fact=new int[mincount+1];
   //print();
   long lo=1;
   int ans=(int)(lo*factorial(mincount)*calc((1<<n)-1,mincount))%1000000007;
   System.out.println(mincount+" "+ans);
  }
 }
}