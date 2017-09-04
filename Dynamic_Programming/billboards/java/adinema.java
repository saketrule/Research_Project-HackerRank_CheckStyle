import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


public class Solution {
 static int N;
 static int K;
 
 public static void main(String args[]) throws IOException{
  
  
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  String ip = br.readLine().trim();
  String inp[] = ip.split(" ");
  N= Integer.parseInt(inp[0].trim());
  K= Integer.parseInt(inp[1].trim());
  
  Map<Long, Integer> map = new HashMap<Long, Integer>();

  
  PriorityQueue<Long> pq = new PriorityQueue<Long>();
  
  List<Long> li = new ArrayList<Long>();
  
  Long total=0l;
  
  for(int i=0; i <N; i++){
   Long temp = Long.parseLong(br.readLine().trim());
   li.add(temp);
   total = total + temp;
   
   
  }
  
  Long[] meta = new Long[li.size()];
  for(int i=0 ; i<K+1 ; i++){
   meta[i] = li.get(i);
   pq.add(li.get(i));
  }
  
  for(int i=K+1 ; i<li.size() ; i++){
   meta[i] = li.get(i)+pq.peek();
   
   if(map.containsKey(meta[i-K-1])){
    map.put(meta[i-K-1], map.get(meta[i-K-1])+1);
   }else{
    map.put(meta[i-K-1], 1);
   }
   
   while(map.containsKey(pq.peek())){
    map.put(pq.peek(), map.get(pq.peek()) - 1);
    if(map.get(pq.peek())==0){
     map.remove(pq.peek());
    }
    pq.remove();
   }
   
   pq.add(meta[i]);
   
  }
  
  Long tempMin=meta[li.size()-1];
  for(int i=li.size()-1; i>=li.size()- K -1; i-- ){
   if(tempMin>meta[i]){
    tempMin = meta[i];
   }
  }
  
  System.out.println(total-tempMin);
 
 }

}