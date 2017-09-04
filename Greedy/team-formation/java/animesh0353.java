import java.io.*;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.text.*;
import java.math.*;
import java.util.regex.*;





public class Solution {

 static void quickSort(int[] arr, int p, int q)
 {
  if(p<q)
  {
   int pivot = partition(arr, p, q);
   quickSort(arr, p, pivot-1);
   quickSort(arr, pivot+1, q);
  }
 }

 static int partition(int[] arr, int p, int q)
 {
  int key = arr[q];
  int j = p-1;

  for (int i = p; i < q; i++) {
   if(arr[i] < key)
   {
    j++;
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
   }
  }
  j++;
  arr[q] = arr[j];
  arr[j] = key;
  return j;
 }


 public static void main(String[] args) {
  /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
  Scanner in = new Scanner(System.in);
  String string = in.nextLine();
  int t = Integer.parseInt(string);
  for (int j = 0; j < t; j++) {
   string = in.nextLine();
   String[] str = string.split(" ");
   int n = Integer.parseInt(str[0]);
   int[] arr = new int[n];
   for (int i = 0; i < n; i++) {
    arr[i] = Integer.parseInt(str[i+1]);
   }

   quickSort(arr, 0, n-1);

   if(n==0 || n==1)
    System.out.println(n);
   else {
    
  

   Map<Integer, ArrayList<Integer>> map= new HashMap<Integer, ArrayList<Integer>>();
//   Entry<Integer, ArrayList<Integer>> entry =map.get(1);
//   arrayList.add(entry);
   ArrayList<Integer> arrayList = new ArrayList<Integer>();
   arrayList.add(1);
    map.put(arr[0], arrayList);
   
   for (int i = 1; i < n; i++) {
    int pos = -1;
    

    if(map.containsKey(arr[i]-1))
    {
     arrayList = map.get(arr[i]-1);
     int min = Integer.MAX_VALUE;
     for (int k = 0; k < arrayList.size(); k++) {


      if( min > arrayList.get(k) )
      {
       pos = k;
       min = arrayList.get(k);

      }

     }
     int value = arrayList.get(pos);
     arrayList.remove(pos);
     if(arrayList.size() == 0)
     {
      map.remove(arr[i]-1);
      arrayList = new ArrayList<Integer>();
      arrayList.add(value+1);
      if(map.containsKey(arr[i]))
      {
       arrayList = map.get(arr[i]);
       arrayList.add(value+1);
       
      }
      else {
       map.put(arr[i], arrayList);
      }
     }
     else
     {
      if(map.containsKey(arr[i]))
      {
       arrayList = map.get(arr[i]);
       arrayList.add(value+1);
      }
      else
      {
       arrayList = new ArrayList<Integer>();
       arrayList.add(value+1);
       map.put(arr[i], arrayList);
      }
     }

     
    }
    else {
     if(map.containsKey(arr[i]))
     {
      arrayList = map.get(arr[i]);
      arrayList.add(1);
     }
     else {
      arrayList = new ArrayList<Integer>();
      arrayList.add(1);
      map.put(arr[i], arrayList);
     }
    }
    
   }
   Iterator itr = map.entrySet().iterator();
   int min = Integer.MAX_VALUE;
   
   while (itr.hasNext()) {
    Map.Entry<Integer, ArrayList<Integer>> pairs = (Map.Entry<Integer, ArrayList<Integer>>)itr.next();
    arrayList = (ArrayList<Integer>) pairs.getValue();
    Iterator<Integer> it = arrayList.iterator();
    while (it.hasNext()) {
     int temp = (Integer) it.next();
     if(min > temp)
      min = temp;
     
    }
    
    
    
   }
   System.out.println(min);
   }
   
  }


 }
}