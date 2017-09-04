import java.util.*;
public class Solution {
  private static class MaxHeapComparator implements Comparator<Integer>{
    @Override
    public int compare(Integer a, Integer b){
      return b-a;
    }
  }
  
  public static void printWinningTeam(PriorityQueue<Integer> team1, PriorityQueue<Integer> team2, int team1ID, int team2ID){
    Iterator<Integer> attacker = team1.iterator();
    Iterator<Integer> other = team2.iterator();
    Iterator<Integer> w;
    int attackerID = team1ID;
    int otherID = team2ID;
    int wID;
    
    // team1 always starts
    while(attacker.hasNext() && other.hasNext()){
      int attackerStrenght = attacker.next();
      for(int i=0; i<attackerStrenght; i++){
        if(other.hasNext()){
          other.next();
        } else {
          System.out.println(attackerID); return;
        }
      }
      wID = attackerID;
      attackerID=otherID;
      otherID=wID;
      
      w = attacker;
      attacker=other;
      other=w;
    }
    
    if(attacker.hasNext()){
      System.out.println(attackerID);
    } else {
      System.out.println(otherID);
    }
  }
  
  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    int fighters = in.nextInt();
    int teams = in.nextInt();
    int queries = in.nextInt();
    Comparator<Integer> comparator = new MaxHeapComparator();
    ArrayList<PriorityQueue<Integer>> qList = new ArrayList<>();
    for(int i=0; i<teams; i++){
      qList.add(new PriorityQueue<Integer>(10,comparator));
    }
    for(int i=0; i<fighters; i++){
      int s = in.nextInt();
      int t = in.nextInt();
      qList.get(t-1).add(s);
    }
    for(int i=0; i<queries; i++){
      int op = in.nextInt();
      int val1 = in.nextInt();
      int val2 = in.nextInt();
      switch(op){
        // add a new fighter of strenght val1 to team val2
        case 1:
          qList.get(val2-1).add(val1);
          break;
        // Match team val1 vs val2
        case 2:
          printWinningTeam(qList.get(val1-1),qList.get(val2-1),val1,val2);
          break;
        default:
          throw new IllegalArgumentException("Unknown operation: "+op);
      }
    }
  }
}