import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.PriorityQueue;


public class Solution {
 
 private static int getMinGroupSize(List<Integer> members) {
  
  Map<Integer, PriorityQueue<Integer> > teamMap = new TreeMap<Integer, PriorityQueue<Integer> >();
  if(members.size() <= 0)
   return 0;
  
  Collections.sort(members);
  
  for(int ind : members) {
   int newTeamSize = 0;
   if (teamMap.containsKey(ind-1)) {
    PriorityQueue<Integer> q = teamMap.get(ind-1);
    if (!q.isEmpty()) {
     newTeamSize = q.remove();
    }
   }
   
   //add it...and create a queue if nessecary
   newTeamSize++;
   if (teamMap.containsKey(ind)) {
    teamMap.get(ind).add(newTeamSize);
   } else {
    PriorityQueue<Integer> q = new PriorityQueue<Integer>();
    q.add(newTeamSize);
    teamMap.put(ind, q);
   }
  }
  
  //get the min
  int groupMin = members.size();
  for(PriorityQueue<Integer> q : teamMap.values()) {
   if (!q.isEmpty()) {
    int sizeMin = q.remove();
    groupMin = sizeMin < groupMin ? sizeMin : groupMin;
   }
  }
  
  return groupMin;
 }

 /**
  * @param args
  */
 public static void main(String[] args) {
  Scanner in = new Scanner(System.in);
  int numTestCases = in.nextInt();
  
  for(int testCase = 0; testCase < numTestCases; testCase++) {
   int size = in.nextInt();
   ArrayList<Integer> members = new ArrayList<Integer>(size);
   for(int i = 0; i < size; i++) {
    members.add(in.nextInt());
   }
   System.out.println(getMinGroupSize(members));
  }

 }

}