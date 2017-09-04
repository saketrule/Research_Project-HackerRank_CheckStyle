import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
   
   Scanner in = new Scanner(System.in);
         
         int n = in.nextInt();
         int m = in.nextInt();
      
         int [] c = new int[n];
         
         Set<Integer>superset = new TreeSet<Integer>();
         Set [] neighbours = new HashSet[n+1];
         
         for(int i=0;i<n;i++) {
       c[i] = in.nextInt();
       superset.add(i+1);      
       neighbours [i+1] = new HashSet<Integer>();      
         }
         
         for(int i=0;i<m;i++) {
       int a = in.nextInt();
       int b = in.nextInt();
       
       neighbours[a].add(b);
       neighbours[b].add(a);      
         }
         int noOfHousesWithZeroMoney=0;
         //Check for Houses with ZERO money and no neighbors
         for(int i=0;i<n;i++) {
          if(c[i]==0 && neighbours[i+1].isEmpty()) {
           noOfHousesWithZeroMoney++;
           superset.remove((i+1));
          }
         }
         
         long startTime = System.currentTimeMillis();
         
         long globalMax =0;
         long noOfWays = 0;
         Result r = null;
         ArrayList<Integer>list = new ArrayList<Integer>(superset);
         for(int i=0;i<superset.size();i++) {
          
          r = subSet(new TreeSet<Integer>(list.subList(i, list.size())), c, neighbours);
          
          if(r.max > globalMax) {
     globalMax = r.max;
     noOfWays=r.noOfWays;
    } else if(r.max == globalMax) {
     noOfWays+=r.noOfWays; 
    }
          
         }
   
         if(noOfHousesWithZeroMoney>0) {
          long nW = (long) Math.pow(2, noOfHousesWithZeroMoney);
          if(noOfWays!=0) {
           noOfWays=noOfWays*nW;
          } else {
           noOfWays = nW-1;
          }
         }
         
   System.out.println(globalMax+" "+noOfWays);
//   System.out.println(System.currentTimeMillis()-startTime);
  }
  
  public static Result subSet(Set<Integer>superset, int [] c, Set [] neighbours) {
   //System.out.println("ss:"+superset.size());
   long max=0;
   int noOfWays=0;
   
   if(superset.size()==0) {
    return new Result();
   }
   
   Result r = new Result();
   if(superset.size()==1) {
    
    Iterator<Integer>it = superset.iterator();
    int id = it.next();
//    System.out.println("id:"+id);
    r = new Result(c[id-1],1);
//    System.out.println("id:"+id); 
   } else {
    //Find Subset
    ArrayList<Integer>list = new ArrayList<Integer>(superset);
    int head = list.get(0);
    
    max = c[head-1];
    noOfWays=1;
//    System.out.println(head+"*"+head+"*"+head+"*"+head+"*"+head+"*"+head+"*"+head+"*"+head+"*");
    for(int i=0;i<list.size()-1;i++) {
     int firstElement = list.get(i+1);   
//     System.out.println("now inspecting:"+firstElement+" out of:"+list.size());
     boolean calculate = true;
     if((i+1)!=list.size()-1) {
//      System.out.println("F E:"+firstElement);
      Set<Integer>rest = new TreeSet<Integer>(list.subList(i+1, list.size()));
      rest.removeAll(neighbours[head]);
          
      if(!rest.contains(firstElement) || rest.isEmpty()) {
//       System.out.println("skipping neighbour : "+firstElement);
       continue;
      }    
      
      r = subSet(rest, c, neighbours);
     } else {
      //Last element in the list
      if(!neighbours[head].contains(firstElement)) {
//       System.out.println("FOUND LAST EL:"+firstElement);
       r.max = c[firstElement-1];
       r.noOfWays=1;
      } else {
       //DO NOTHING
       calculate =false;
//       System.out.println("skipping : "+firstElement);
      }
      
     }    
     
     
     if(calculate) {
      long totalMoney = c[head-1] + r.max;
//      System.out.println("head:"+head);
//      System.out.println("max:"+max+",r.max:"+r.max);
      
      if(totalMoney > max) {
       max= totalMoney;
//       noOfWays=1;
       noOfWays = r.noOfWays;
      } else if(totalMoney == max) {
//       if(r.max==0) {
        noOfWays+=r.noOfWays;
//       } else {
//        noOfWays++;
//       }
        
      }
//      System.out.println("noOfWays:"+noOfWays);
     }
     
    }
//    System.out.println(head+"*"+head+"*"+head+"*"+head+"*"+head+"*"+head+"*"+head+"*"+head+"*");
    r.max = max;
    r.noOfWays = noOfWays;
   }
   
   return r;
  }
  
 }

 class Result {
  long max;
  int noOfWays;
  
  public Result() {
  }
  
  public Result(long max, int noOfWays) {
   this.max = max;
   this.noOfWays = noOfWays;
  }
 }