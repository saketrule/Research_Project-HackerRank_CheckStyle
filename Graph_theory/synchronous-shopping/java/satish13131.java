import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int N = 0;
        int M = 0;
        int K = 0;
        int ti1 = -1;
        int ti2 = -1;
        int ti3 = -1;
        Graph g = null;
        N = in.nextInt();
        g = new Graph(N);
        M = in.nextInt();
        K = in.nextInt();
        for (int i = 0;i < N;i++)
        {
            ti1 = in.nextInt();
            for (int j = 0;j < ti1;j++)
            {
                g.addFish(i + 1, in.nextInt());
            }
        }
        for (int i = 0;i < M;i++)
        {
            ti1 = in.nextInt();
            ti2 = in.nextInt();
            ti3 = in.nextInt();
            g.addEdge(ti1, ti2, ti3);
        }

        g.travMinTime(K);
        
        in.close();
 }
 
 static class Graph
 {
  Node[] nodeAr = null;
  int size = 0;
  
  NodeTraverseInfo nti = new NodeTraverseInfo();
  
  public Graph(int capacity)
  {
   size = capacity;
   nodeAr = new Node[size];
   for (int i = 0;i < size;i++)
         {
             nodeAr[i] = new Node();
             nodeAr[i].idx = i + 1;
         }
  }
  
  void addEdge(int idx1, int idx2, int time)
  {
   nodeAr[idx1 - 1].conList.add(nodeAr[idx2 - 1]);
            nodeAr[idx1 - 1].timeList.add(time);
            nodeAr[idx2 - 1].conList.add(nodeAr[idx1 - 1]);
            nodeAr[idx2 - 1].timeList.add(time);
  }
  
  void addFish(int idx1, int type)
  {
   nodeAr[idx1 - 1].fishList |= (1 << (type - 1));
  }
  
  void travMinTime(int K)
  {
   long initTime = System.currentTimeMillis();
   long curTime = -1;
   PriorityQueue<Path> q = new PriorityQueue<Path>(1, new PathComparator());
         Iterator<Node> it1 = null;
         Iterator<Integer> it2 = null;
         Node tn = null;
         Path path1 = null;
         Path path2 = null;        
         int time = -1;
         int itCnt = 0;
         int itCnt2 = 0;
         
         tn = nodeAr[0];
         path1 = new Path();         
         path1.fishList = tn.fishList;
         path1.nodeNV = false;
         path1.nodeNL = false;
         path1.time = 0;
         path1.last = tn;
         path1.last2 = null;
         path1.inc = true;
         q.add(path1);
         
         nti.info = new Object[size];
            nti.maxFishList = new int[size];
         for (int i = 0;i < size;i++)
         {
          nti.info[i] = new LinkedList<FishListTime>();
                nti.maxFishList[i] = 0;
         }
         
         nti.K = K;
         nti.fullFishList = ((int)Math.pow(2, K)) - 1;
         while (!q.isEmpty())
         {
          itCnt2++;
          path1 = q.poll();
          
          //print(path1);
          
          
                if (path1.nodeNL)
          {
                    if (path1.fishList != nti.fullFishList)
                    {
                updateMinTimeN2(path1, nti, path1.last.idx - 1);
                if (path1.isMinReached)
                {
                break;
                }
                if (!path1.required)
                {
                continue;
                }
                    }
                    else
                    {
                        updateMinTime(nti, path1.time);
                        break;
                    }
          }
          else
          {
                    if (path1.fishList != nti.fullFishList)
                    {
                        updateMinTimeOtherN2(path1, nti, path1.last.idx - 1);
               if (!path1.required)
               {
                 continue;
               }
                    }
                      
          }
          
           
          it1 = (Iterator<Node>)path1.last.conList.iterator();
          it2 = (Iterator<Integer>)path1.last.timeList.iterator();
          while (it1.hasNext())
          {
           tn = (Node)it1.next();
           time = (Integer)it2.next();
           itCnt++;
           
           if (tn == path1.last2 && !path1.inc)
           {
            continue;
           }
           
           path2 = new Path();
           if (isSub(path1.fishList, tn.fishList))
           {
            path2.fishList = path1.fishList;
            path2.inc = false;
           }
           else
           {
            path2.fishList = mergeSorted(path1.fishList, tn.fishList);
            path2.inc = true;
           }
           if (tn == nodeAr[size - 1])
           {
            path2.nodeNV = true;
            path2.nodeNL = true;
           }
           else
           {
            path2.nodeNL = false;
           }
           
           path2.last = tn;
           path2.last2 = path1.last;
           path2.time = path1.time + time;
           
           q.add(path2);
           
          } 
          
          
         }
         
         q.clear();
         curTime = System.currentTimeMillis();
         //System.out.println((curTime - initTime) + " ms");
            //System.out.println(itCnt);
            //System.out.println(itCnt2); 
         System.out.println(nti.minTime);
  }
  
  
        
        void updateMinTimeN2(Path path, NodeTraverseInfo nti, int idx)
  {
      List<FishListTime> fishListTimeList = (LinkedList<FishListTime>)nti.info[idx];
      Iterator<FishListTime> it = fishListTimeList.iterator();
      FishListTime fishListTime = null;
      int fishList1 = path.fishList;
      int fishList2 = 0;
      int fishList3 = 0;
      boolean isFishListTime = false;
      while (it.hasNext())
      {
        
       fishListTime = (FishListTime)it.next();
       fishList2 = fishListTime.fishList;
                
                
                if (isSub(fishList2, fishList1))
                {
                        path.required = false;
                  isFishListTime = true;
                  break;
                }
                else if (!isSub(fishList1, fishList2))
                {
                    fishList3 = mergeSorted(fishList1, fishList2);
           if (fishList3 == nti.fullFishList)
           {                
                updateMinTime(nti, path.time);
                         path.required = false;
                         path.isMinReached = true;
                         break;
              }
                }
                else if (fishList1 == nti.fullFishList)
                {
                    updateMinTime(nti, path.time);
                    path.required = false;
                    path.isMinReached = true;
                    break;
                }    
      }
      
      if (!isFishListTime)
      {
       addFishList(fishListTimeList, fishList1, path.time);
      }   
  }
  
  
  
        void updateMinTimeOtherN2(Path path, NodeTraverseInfo nti, int idx)
  {
      List<FishListTime> fishListTimeList = (LinkedList<FishListTime>)nti.info[idx];
      Iterator<FishListTime> it = fishListTimeList.iterator();
      FishListTime fishListTime = null;
      int fishList1 = path.fishList;
      int fishList2 = 0;
      boolean isFishListTime = false;
            /*if (fishList1.size() > nti.maxFishList[idx])
            {
                addFishList(fishListTimeList, fishList1, path.time);
                nti.maxFishList[idx] = fishList1.size();
                return;
            } */   
                
      while (it.hasNext())
      {
        
       fishListTime = (FishListTime)it.next();
       fishList2 = fishListTime.fishList;
       if (isSub(fishList2, fishList1))
       {
        path.required = false;
                    isFishListTime = true;
                    break;
       }
      }
      
      if (!isFishListTime)
      {
       addFishList(fishListTimeList, fishList1, path.time);
      }       
  }
        
  void addFishList(List<FishListTime> fishListTimeList, int fishList, int time) {
   FishListTime fishListTime = new FishListTime();
      fishListTime.fishList = fishList;
      fishListTime.minTime = time;         
      fishListTimeList.add(0, fishListTime);   
  }

     void updateMinTime(NodeTraverseInfo nti, int minTime)
  {
   if (nti.minTime == -1)
   {
    nti.minTime = minTime;
    //System.out.println(nti.minTime);
   }
   else if (minTime < nti.minTime)
   {
    nti.minTime = minTime;
    //System.out.println(nti.minTime);
   }
   
  }
  
  void updateMinTime(FishListTime fishListTime, int minTime)
  {
   if (minTime < fishListTime.minTime)
      {
    fishListTime.minTime = minTime;
      }
  } 
  
  
  
  int mergeSorted(int fishList1, int fishList2)
  {
         return (fishList1 | fishList2);
     }
  
  void print(Path path1) {
   
   if (path1.last2 != null)
   {
    System.out.print(path1.last2.idx);
   }
   System.out.print(" " + path1.last.idx + "\n");
   printFishList(path1.fishList);
   System.out.println("inc - " + path1.inc + " time - " + path1.time);
   System.out.print("\n");
  } 
  
  void printFishList(int fishList)
  {
   for (int i = 9;i >= 0;i--)
   {
    System.out.print((1 & (fishList >> i)));
   }
   System.out.print("\n");
  }
 
  
  boolean isSub(int fishList1, int fishList2)
     {
   int res = 0;
   res = fishList1 & fishList2;
   if (res == fishList2)
   {
    return true;
   }
   return false;
     }
  
  
  
 }
 
 static class Node
    {
        int idx;
        int fishList = 0;
        List<Node> conList = new LinkedList<Node>();
        List<Integer> timeList = new LinkedList<Integer>();
    }
 
 static class Path
    {
        int fishList = 0;
        boolean nodeNV = false;
        boolean nodeNL = false;
        boolean inc = false;
        boolean required = true;
        boolean isMinReached = false;
        int time = 0;
        Node last = null;
        Node last2 = null;
    }
 
 static class NodeTraverseInfo
    {
     Object[] info = null;
     int minTime = -1;
     int K = 0;
        int[] maxFishList = null;
        int fullFishList = 0;
    }
    
    static class FishListTime
    {
     int fishList = 0;
     int minTime = -1;
    }

    static class PathComparator implements Comparator<Path>
    {      
     public int compare(Path path1, Path path2)
     {
                
      if (path1.time < path2.time)
      {
       return -1;
      }
      else if (path1.time > path2.time)
      {
       return 1;
      }
      else if (path1.nodeNL && !path2.nodeNL)
      {
       return -1;
      }
      else if (!path1.nodeNL && path2.nodeNL)
      {
       return 1;
      }
      else if (getCnt(path1.fishList) > getCnt(path2.fishList))
   {
    return -1;
   }
   else if (getCnt(path1.fishList) < getCnt(path1.fishList))
   {
    return 1;
   }
   return -1;
     }
     
     int getCnt(int fishList)
  {
   int cnt = 0;
   while (fishList != 0)
   {
    if ((fishList & 1) == 1)
    {
     cnt++;
    }
    fishList = fishList >> 1;
   }
   return cnt;
  }
    }
 
}