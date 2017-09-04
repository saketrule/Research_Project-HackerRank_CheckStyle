import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class RMQPerf {
 static int n = 0;
 static int l = 0;
 //static int[] rmqArr;
 static int[] rmqArrFinal;
 static int max = 0;
 static Scanner sc;
 static Map<Integer,Integer> rmqMap = new TreeMap<Integer,Integer>();
 static Map<Integer,List<Integer>> rmqIMap = new HashMap<Integer,List<Integer>>();
 static int frstUnOcc = 0;
 
 
 public static void main(String[] args) {
  sc = new Scanner(System.in);
  n = sc.nextInt();
  max = 2*n-1;
  for(int i = 0; i < max; i++){
   int key = sc.nextInt();
   Integer val = rmqMap.get(key);
   if(val != null){
    rmqMap.put(key, val+1);
   }else{
    rmqMap.put(key, 1);
   }
  }
  
  for(int i = 1; i <= n; i *= 2){
   l++;
  }
  
  rmqArrFinal = new int[max];

  for (Map.Entry<Integer, Integer> entry : rmqMap.entrySet()) {
   List<Integer> value = rmqIMap.get(entry.getValue());
   if(value!=null){
    value.add(entry.getKey());
   }else{
    List<Integer> list = new LinkedList<Integer>();
    list.add(entry.getKey());
    rmqIMap.put(entry.getValue(), list);
   }
  }
  Set<Integer> keySet = rmqIMap.keySet();
  Integer[] array = keySet.toArray(new Integer[keySet.size()]);
  
  Arrays.sort(array, Collections.reverseOrder());

  int[] index = new int[max];

  boolean valid = true;
  int nu = 0;
  x: for(int i : array){
   if(i > l){
    valid = false;
    break x;}
   List<Integer> list = rmqIMap.get(i);
   ////System.out.print(i+" : ");
   for (Integer integer : list) { 
    int j = 0;
    ////System.out.print(integer+" ");
    frstUnOcc = getNextUnOcc(frstUnOcc, index);
    int m = frstUnOcc;
    for(int k = m; j < i; j++){
     //System.out.println(k);
     if(k < max && (k == 0 || rmqArrFinal[(k-1)/2] <= integer )){ 
      rmqArrFinal[k] = integer;
      index[k]=-1;
      //System.out.println("removed : "+k+" size remaining : "+index.size());
      nu++;
     }else{
      m = getNext(m, index);
      if(m < max && j == 0){
       k = m;
       j--;
       continue;
      }else{
       valid = false;
       break x;
      }
      
     }

     k = 2*k + 1;          
    }
    if(j != i){
     valid = false;
     break x;
    }
     
   }
   //System.out.println();
  }
  if(valid && nu == max ){
   System.out.println("YES");
   for(int r : rmqArrFinal){
    System.out.print(r+" ");
   }
  }else{
   System.out.println("NO");
  }
  
  
 }
 
 private static boolean printTree(){
  boolean isValid = true;
  int i = 0;
  int j = 0;
  Set<Integer> set;
  while(i < max){
   j = 2*i+1;
   set = new HashSet<Integer>();
   while(i < j){
    if(!set.contains(rmqArrFinal[i])){
     set.add(rmqArrFinal[i]);
    }else{
     isValid = false;
     return isValid;
    }
    
    //System.out.print(rmqArrFinal[i]+" ");
    i++;
   }
   set.clear();
   //System.out.println();
  }  
  //System.out.println();
  
//  for(int x = (max)-1; x >=2; x=x-2){
//   int y = (x - 2)/2;
//   if(!(rmqArrFinal[y] <= rmqArrFinal[x] && rmqArrFinal[y] <= rmqArrFinal[x-1])){
//    isValid = false;
//    break;
//   }
//  }
  
  return isValid;
 }
 
 private static int getNextUnOcc(int i,int[] arr){
  for(int k = i; k < arr.length; k++){
   if(arr[k] == 0)
    return k;
   else
    continue;
  }
  return max;
 }
 
 private static int getNext(int i,int[] arr){
  for(int k = i+1; k < arr.length; k++){
   if(arr[k] == 0)
    return k;
   else
    continue;
  }
  return max;
 }
}