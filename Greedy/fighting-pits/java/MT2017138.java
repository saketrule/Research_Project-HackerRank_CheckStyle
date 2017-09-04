import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
 static void add(ArrayList<Integer> team,int x){
 // System.out.println(team.size());
  int high=0,low=0,mid;
  int p=0;
  high=team.size()-1;
 // System.out.println(high);
  while(true){
   
   if(low+1==high){
    if(team.get(low)<=x&&team.get(high)>=x){
     p=high;
     break;
    }
   }
  mid=(high+low)/2;
  //System.out.println("low="+low+" mid="+mid+" high "+high);
  //System.out.println();
  //Scanner sc=new Scanner(System.in);
  
  //System.out.println(team.get(mid)+" "+x);
  if(team.get(mid)<x){
   //System.out.println("greater");
   low=mid;
  }
  else if(team.get(mid)>x){
   //System.out.println("lower");
   high=mid;
  }
  else{
   //System.out.println("equal");
   p=mid+1;
   break;
  }
  //sc.nextInt();
  }
  team.add(x);
  for(int i=team.size()-1;i>p;i--){
   team.set(i,team.get(i-1));
  }
  team.set(p,x);
  
 }
    public static void main(String[] args) throws Exception {
     BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
     String input[]=br.readLine().split(" ");
     int n=Integer.parseInt(input[0]);
     int k=Integer.parseInt(input[1]);
     int q=Integer.parseInt(input[2]);
     ArrayList<Integer> team[]=new ArrayList[k];
     
     for(int i=0;i<n;i++){
      input=br.readLine().split(" ");
      int s=Integer.parseInt(input[0]);
      int t=Integer.parseInt(input[1]);
      if(team[t-1]==null){
       team[t-1]=new ArrayList();
      }
      team[t-1].add(s);
      
     }
     for(int i=0;i<k;i++){
     // System.out.println(team[i].size());
      Collections.sort(team[i]);
     }
     for(int i=0;i<q;i++){
     
      input=br.readLine().split(" ");
         int q1=Integer.parseInt(input[0]);
         int q2=Integer.parseInt(input[1]);
         int q3=Integer.parseInt(input[2]);
         if(q1==1){
         
          add(team[q3-1],q2);
         }
         else{
          boolean turn=true;
          int x,y,ix=0,iy=0,st=0,p;
          ix=team[q2-1].size()-1;
          iy=team[q3-1].size()-1;
          while(true){
           
           if(turn){
            x=q2-1;
           
            st=team[x].get(ix);
               iy-=st;
               p=iy;
              
           }
           else{
            x=q3-1;
            st=team[x].get(iy);
            ix-=st;
            p=ix;
           }
           
           if(p<0){
            System.out.println((int)(x+1));
            break;
           }
           turn=!turn;
          }
         }
     }
    }
}