import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
  java.util.Scanner s = new java.util.Scanner(System.in);
  int cont = s.nextInt();
  
  Solution team = new Solution();

  while (cont-- > 0) {
   int N = s.nextInt();
   if (N==0) {
    System.out.println(0);
   } else {
    int[] elements = new int[N];
    while(N-- > 0) {
     elements[N] = s.nextInt();
    }
    System.out.println(team.getMinTeam(elements));
   }
  }
  
  s.close();
 }

 public int getMinTeam(int[] contestantsSkills) {
  List<Contestant> contestants = new ArrayList<Contestant>();
  List<Contestant> heads = new ArrayList<Contestant>();
  HashMap<Integer, Contestant> leafs = new HashMap<Integer, Contestant>();
  
  for (int i = 0; i < contestantsSkills.length; i++) {
   int skill = contestantsSkills[i];
   Contestant c = new Contestant(skill);
   contestants.add(c);
  }
  Collections.sort(contestants);
  
  for (Contestant contestant : contestants) {
   
   Contestant prev = leafs.get(contestant.skill-1);
   if (prev!=null) {
    prev.addLeaf(contestant);
    
   } else {
    Contestant t = leafs.get(contestant.skill);
    if (t!=null) {
     t.nextNeighbord = contestant;
     contestant.previusNeighbord = t;
    }
   }
   leafs.put(contestant.skill, contestant);
   leafs.put(contestant.skill-1, contestant.previus);
   if (contestant.previus == null) {
    heads.add(contestant);
   }
  }
  
  int min = 0;
  
  for (Contestant c : heads) {
   int depth = c.getDepth();
   if (min == 0 || min > depth)
    min = depth;
  }
  return min;
 }

 class Contestant implements Comparable<Contestant> {
  private Integer skill;
  private Contestant next;
  private Contestant previus;
  
  private Contestant nextNeighbord;
  private Contestant previusNeighbord;
  
  public Contestant(int s) {
   this.skill = s;
  }
  
  public void addLeaf(Contestant leaf) {
   Contestant c = this;
   if (c.next == null) {
    while (c.nextNeighbord != null) {
     c = c.nextNeighbord;
    }
    c.next = leaf;
    leaf.previus = c;
    return;
   } else {
    while (c.previusNeighbord != null) {
     c = c.previusNeighbord;
     if(c.next==null) {
      c.next = leaf;
      leaf.previus = c;
      c.nextNeighbord.next.previusNeighbord = leaf;
      leaf.nextNeighbord = c.nextNeighbord.next;
      return;
     }
    }
   }
   Contestant neighbord = this.next;
   while (neighbord.nextNeighbord != null) {
    neighbord = neighbord.nextNeighbord;
   }
   neighbord.nextNeighbord = leaf;
   leaf.previusNeighbord = neighbord;
  }
  
  public int getDepth() {
   int size = 1;
   
   Contestant c = this;
   while (c.next != null) {
    size++;
    c = c.next;
   }
   return size;
  }

  @Override
  public int compareTo(Contestant o) {
   return skill.compareTo(o.skill);
  }
 }
}