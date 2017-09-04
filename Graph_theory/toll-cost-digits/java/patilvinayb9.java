import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
 static int[] d;
 
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int e = in.nextInt();
        d = new int[10];
        
        MyNode[] nodes = new MyNode[n+1];
        
        for(int i=1;i<=n;i++){
         nodes[i] = new MyNode(i);
        }
        
        for(int a0 = 0; a0 < e; a0++){
         int x = in.nextInt();
            int dest = in.nextInt();
            int toll = in.nextInt();
            
            nodes[x].addLink(new Link(toll, nodes[dest]));
            nodes[dest].addLink(new Link(1000 - toll, nodes[x]));
        }
        
        for(MyNode node : nodes){
         if(node != null){
             addToD(node, 0, "-" + node.nodeNo);
         }
        }
        
        for(int i = 0; i<10; i++){
         System.out.println(d[i]);
        }
    }

 private static void addToD(MyNode node, int sum, String path) {
  for(Link link: node.links){
   if(!path.contains("-" + link.dest.nodeNo + "-")){
    String currPath = path + "-" + link.dest.nodeNo;
    int total = sum + link.toll;
    
    //System.out.println("Setting for " + currPath + ":" + total);
    d[total % 10]++;
    addToD(link.dest, total, currPath);
   }else{
    //System.out.println("Skipped for " + path);
   }
  }
 }

}

class Link{
 int toll;
 MyNode dest;
 
 public Link(int toll, MyNode dest) {
  super();
  this.toll = toll;
  this.dest = dest;
 }
 
}

class MyNode{
 
 int nodeNo;
 
 List<Link> links = new ArrayList<>();
 
 public MyNode(int nodeNo) {
  super();
  this.nodeNo = nodeNo;
 }

 @Override
 public int hashCode() {
  final int prime = 31;
  int result = 1;
  result = prime * result + nodeNo;
  return result;
 }


 @Override
 public boolean equals(Object obj) {
  if (this == obj)
   return true;
  if (obj == null)
   return false;
  if (getClass() != obj.getClass())
   return false;
  MyNode other = (MyNode) obj;
  if (nodeNo != other.nodeNo)
   return false;
  return true;
 }


 public void addLink(Link link) {
  links.add(link);
 }
}