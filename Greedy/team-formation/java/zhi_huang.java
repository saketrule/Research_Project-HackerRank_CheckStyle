import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

 public static void main(String args[]) throws IOException{
  Scanner scanner = new Scanner(System.in);
  //scanner = new Scanner(new FileInputStream("in.txt"));
  
  int nCase = scanner.nextInt();
  while(nCase-- > 0) {
   int n = scanner.nextInt();
   int a [] = new int[n];
   for(int i = 0; i < n; i++)
    a[i] = scanner.nextInt();
   //Arrays.sort(a);
   getFormation2(a);
//   System.out.println(getProfit(a));
  }
 }
 
 /**
  * We can sort the elements first so that we only have to maintain one hashMap.
  * Also, we do not have to build the list, just get the length will be fine.
  * */
 static void getFormation2(int a[]) {
  Arrays.sort(a);
  if(a.length == 0) {
   System.out.println(0);
   return;
  }
  
  HashMap<Integer, ArrayList<Integer>> tailMap = new HashMap<Integer, ArrayList<Integer>>();
  for(int i = 0; i < a.length; i++) {
   if(tailMap.containsKey(a[i])) {
    //find the minimum and remove it
    ArrayList<Integer> lengthList = tailMap.get(a[i]);
    int minLen = lengthList.get(0);
    int minIndex = 0;
    for(int j = 1; j < lengthList.size(); j++)
     if(lengthList.get(j) < minLen){
      minLen = lengthList.get(j);
      minIndex = j;
     }
    if(! tailMap.containsKey(a[i] + 1))
     tailMap.put(a[i] + 1, new ArrayList<Integer>());
    tailMap.get(a[i] + 1).add(lengthList.get(minIndex) + 1);
    lengthList.remove(minIndex);
    if(lengthList.size() == 0)
     tailMap.remove(a[i]);
   } else {
    if(! tailMap.containsKey(a[i] + 1))
     tailMap.put(a[i] + 1, new ArrayList<Integer>());
    tailMap.get(a[i] + 1).add(1);
   } 
  }
  
  int minLength = Integer.MAX_VALUE;
  Iterator<Map.Entry<Integer, ArrayList<Integer>>> it = tailMap.entrySet().iterator();
  while(it.hasNext()) {
   ArrayList<Integer> list = it.next().getValue();
   for(int i = 0; i < list.size(); i++) {
    minLength = Math.min(minLength, list.get(i));
   }
  }
  System.out.println(minLength);
 }
}