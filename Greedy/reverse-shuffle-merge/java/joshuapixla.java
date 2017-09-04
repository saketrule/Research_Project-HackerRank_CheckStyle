import java.util.*;
import java.io.*;

public class Solution {
 
 private static String S;
 private static String sRev;
 private static char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
 private static Map<Character,Integer> mapping = new HashMap<Character,Integer>();
 private static Map<Integer,Character> revmapping = new HashMap<Integer,Character>();
 private static Map<Integer,Integer> counts = new TreeMap<Integer,Integer>();
 private static char[] charr;
 private static int[] stint;
 private static int[] requir = new int[26];
 private static int[] total = new int[26];
 private static int toproc;
 private static int previous = Integer.MIN_VALUE;
 private static List<ArrayList<Integer>> index = new ArrayList<ArrayList<Integer>>();
 private static int[][] todist;
 private static List<Integer> reslet = new ArrayList<Integer>();
 
 
 private static void fillMappings(){
  for(int i = 0; i < alphabet.length; i++){
   mapping.put(alphabet[i],i);
   revmapping.put(i,alphabet[i]);
  }
 }
 
 private static void fillStint(String input){
  charr = sRev.toCharArray();
  stint = new int[input.length()];
  for(int i = 0; i < charr.length; i++){
   stint[i] = mapping.get(charr[i]);
  }
 }
 
 private static void doFinal(List<Integer> input){
  StringBuilder sb = new StringBuilder();
  for(int i = 0; i < input.size(); i++){
   sb.append(revmapping.get(input.get(i)));
  }
  System.out.println(sb.toString());
 }
 
 
 private static void fillToDist(int[] input){
  for(int i = 0; i < input.length; i++){
   todist[i][input[i]]++;
   if(i < input.length-1){
   for(int j = 0; j < 26; j++){
    todist[i+1][j] += todist[i][j];
   }
    }
  }
 }
 
 private static void count(int[] input){
  int temp;
  for(int i = 0; i < input.length; i++){
   if(!counts.containsKey(input[i])){
    counts.put(input[i],1);
   }
   else{
    temp = counts.get(input[i])+1;
    counts.put(input[i], temp);
   }
  }
  Set<Integer> keyset = counts.keySet();
  Iterator<Integer> it = keyset.iterator();
  int next;
  int val;
  int half;
  while(it.hasNext()){
   next = it.next();
   val = counts.get(next);
   half = (val/2);
   requir[next] = half;
   total[next] = val;
  }
 }
 
 private static void fillIndexes(int[] input){
  for(int i = 0; i < 26; i++){
   index.add(new ArrayList<Integer>());
  }
  for(int i = 0; i < input.length; i++){
   index.get(input[i]).add(i);
  }
 }
 
 
 private static void reverseString(String input){
  char[] arr = input.toCharArray();
  StringBuilder sb = new StringBuilder();
  for(int i = arr.length-1; i >= 0; i--){
   sb.append(arr[i]);
  }
  sRev = sb.toString();
 }
 
 private static void doCycle(){
  int totest;
  int success = 0;
  for(int i = 0; i < index.size(); i++){
   if(index.get(i).isEmpty()) continue;
   if(requir[i] == 0) continue;
   totest = index.get(i).get(0);
   if(totest < previous) {
    index.get(i).remove(0);
    return;
   }
   int one;
   for(int j = 0; j < requir.length; j++){
    one = total[j]-todist[totest][j];
    if(one < requir[j]){
     if(j == i){
      if(one < requir[j]-1){
       success = 0;
       break;
      }
     }
     else{
     success = 0;
     break;
     }
    }
    success++;
   }
   
   if(success == 26){
    reslet.add(i);
    index.get(i).remove(0);
    requir[i]--;
    toproc--;
    previous = totest;
    return;
   }
  } 
 }
 
 private static void compute(String input){
  fillMappings();
  reverseString(input);
  fillStint(sRev);
  count(stint);
  fillToDist(stint);
  fillIndexes(stint);
  while(toproc > 0){
   doCycle(); 
  }
  doFinal(reslet);
 }
 
 
 public static void main(String[] args) throws IOException {
  
  String line = "";
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        line = br.readLine();
        S = line;
        int len = S.length();
        toproc = len/2;
        todist = new int[len][26];
  compute(S); 
 }
}