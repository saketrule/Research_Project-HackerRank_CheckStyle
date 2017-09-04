import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
 
 // roads
 public static int[] road_j1;
 public static int[] road_j2;
 public static int[] road_toll;
 public static boolean[] road_checked;
 
 // children junctions
 public static int[][] children;
 public static int[][] children_toll;
 public static int[][] children_idx;
 
 public static int[] children_temp;
 
 public static boolean[] checked;
 public static boolean[] checked2;
 
 public static int[] nodes;
 public static int[] nodes2;
 public static int[] parents;
 public static byte[] tolls;
 public static int nodesIdx = 0;
 
 public static long[][] count;
 public static long[][] ctemp;
 public static long[][] ctemp2;
 public static boolean[] count_changed;
 
 public static void init(int n) {
  children = new int[n+1][];
  children_toll = new int[n+1][];
  children_idx = new int[n+1][];
  children_temp = new int[n+1];
  
  checked = new boolean[n+1];
  checked2 = new boolean[n+1];
  count = new long[n+1][10];
  ctemp = new long[n+1][10];
  ctemp2 = new long[n+1][10];
  count_changed = new boolean[n+1];
  
  // tree nodes
  nodes = new int[n+1];
  nodes2 = new int[n+1];
  // tree parents
  parents = new int[n+1];
  // tolls
  tolls = new byte[n+1];
 }
 
 public static int last = 0;
 public static int group = 0;
 
 public static long result0[] = new long[10];
 
 public static long result01[] = new long[10];
 public static long result_temp[] = new long[10];
 
 public static void solve0() {
  Arrays.fill(result0, 0);
  
  Arrays.fill(result_temp, 0);
  
  for(int i=nodesIdx-1; i>=0; i--) {
   // find roads ended in this node
   int node = nodes[i]; 
   if (parents[i]>=0) { 
    /*count[node][tolls[i]]=1;
    for(int j=0; j<10; j++) {
     long c=count[nodes[parents[i]]][j];
     count[node][(j+tolls[i])%10]+=c;
    }*/
    int toll = (10-tolls[i])%10;
    count[nodes[parents[i]]][toll]+=1;
    
    for(int j=0; j<10; j++) {
     long c=count[node][j];
     count[nodes[parents[i]]][(j+toll)%10]+=c;
    }
   }
   
   
   // find roads #2
   
   int m=0;
   for(int j=0; j<children[node].length; j++) {
    if(road_checked[children_idx[node][j]]) {
     if((parents[i]==-1) || (children[node][j]!=nodes[parents[i]])) {
      
      int toll = (10-children_toll[node][j])%10;
      //count[nodes[parents[i]]][toll]+=1;
      Arrays.fill(ctemp[m], 0);
      ctemp[m][toll]+=1;
      for(int k=0; k<10; k++) {
       long c=count[children[node][j]][k];
       ctemp[m][(k+toll)%10]+=c;
      }
      /*
      ctemp[m][0]=count[children[node][j]][0];
      for(int k=1; k<10; k++) {
       ctemp[m][k]=count[children[node][j]][10-k];
      }
      */
      children_temp[m]=children[node][j];
      m++;
     }
     
    }
   }
   
   for(int j=0; j<m; j++) {
    for(int k=0; k<10; k++) {
     if(j == 0) {
      ctemp2[j][k]=ctemp[j][(10-k)%10];
     } else {
      ctemp2[j][k]=ctemp2[j-1][k]+ctemp[j][(10-k)%10];
     }
     
    }
   }
   
   Arrays.fill(result01, 0);
   
   for(int j=0; j<m-1; j++) {
    int idx1=j;
    int idx2=m-1;
    for(int k=0; k<10; k++) {
     result_temp[k]=ctemp2[idx2][k]-ctemp2[idx1][k];
    }
    for(int k=0; k<10; k++) {
     for(int d=0; d<10; d++) {    
      result01[(k+d)%10]+=ctemp[idx1][k]*result_temp[d];
     }
    }
   }
   
   //
   
   result0[0]+=2*count[node][0];
   result0[0]+=2*result01[0];
   for(int j=1; j<10; j++) {
    result0[j]+=count[node][j];
    result0[10-j]+=count[node][j];
    result0[j]+=result01[j];
    result0[10-j]+=result01[j];
   } 
   //System.out.println(Arrays.toString(count[node])); 
  }
  //System.out.println(Arrays.toString(result0));  
 }
 
 public static HashSet<String> connections = new HashSet<String>();
 
 public static void findGroup(int top, boolean resort) {
  nodes[0]=top;
  parents[0]=-1;
  checked[top]=true;
  nodesIdx=1;
  int fromIdx=0;
  int node = 0;
  int child = 0;
  int count=1;
  
  while(count>0) {
   count=0;
   for(int j=fromIdx; j<nodesIdx; j++) {
    node = nodes[j];
    
    //ArrayList<Integer> childs = resort ? children2[node] : children[node];
    
    for(int k=0; k<children[node].length; k++) {
     
     child = children[node][k];
     if(resort && (!road_checked[children_idx[node][k]])) {
      continue;
     }
     if(!checked[child]) {        
      nodes[nodesIdx+count]=child;
      parents[nodesIdx+count] = j;
      tolls[nodesIdx+count] = (byte)children_toll[node][k];
      if (!resort) {
       road_checked[children_idx[node][k]]=true;
      }
      count++;
      checked[child]=true;
     }
     
    }
   }     
      
   fromIdx = nodesIdx;
   nodesIdx+=count;
  }
  
  //Arrays.fill(checked, false);
  
   /*
   group++;
   System.out.println("Group #"+group);
   for(int j=0; j<nodesIdx; j++) {
    System.out.println(nodes[j]);
   }
   System.out.println("-----------");
*/
 }
 
 public static int loopType=-1;
 
 public static void findLoops() {
  int maxLoopsChecks = 1000;
  int loopsChecks = 0; 
  loopType=-1;
  for(int i=0; (i<nodesIdx)  && (loopType<3); i++) {
   int node = nodes[i];
   for(int cc=0; (cc<children[node].length) && (loopType<3); cc++) {
    int child = children[node][cc];
    //String key = child < node ? ""+child+"_"+node : ""+node+"_"+child;
    
    if(!road_checked[children_idx[node][cc]]) {
     //System.out.println("Check loop: "+child+"_"+node);
     // DFS again...
     int[] nodes = nodes2;
     boolean[] checked = checked2;
     nodes[0]=child;
     tolls[0]=0;
     parents[0]=-1;
     checked[child]=true;
     int nodesIdx=1;
     int fromIdx=0;
     int count=1;
     
     int node2=0;
     int child2;
     int toll = -1;
     
     while((count>0) && (toll == -1)) {
      count=0;
      for(int j=fromIdx; (j<nodesIdx) && (toll == -1); j++) {
       node2 = nodes[j];
       
       for(int k=0; (k<children[node2].length) && (toll == -1); k++) {
        
        child2 = children[node2][k];
        
        
        if((!checked[child2]) && (road_checked[children_idx[node2][k]])) {        
         nodes[nodesIdx+count]=child2;
         parents[nodesIdx+count] = j;
         
         tolls[nodesIdx+count] = (byte)((tolls[j]+children_toll[node2][k])%10);
         if(child2 == node) {
          toll = tolls[nodesIdx+count];
          //nodesIdx+=count+1;
          
          
         }
         count++;
         
         checked[child2]=true;
        }
        
       }
      }     
         
      fromIdx = nodesIdx;
      nodesIdx+=count;
     }
     for(int j=0; j<nodesIdx; j++) {
      checked[nodes[j]]=false;
     }
     
     toll=(toll+children_toll[node][cc])%10;
     if(toll == 0) {
      /*System.out.println("<Path>");
      int d=nodesIdx-1;
      while(d>=0) {
       System.out.println(nodes[d]);
       if(!count_changed[nodes[d]]) {
        result0[0]+=2*Solution.count[nodes[d]][0];
        for(int l=1; l<10; l++) {
         result0[l]+=Solution.count[nodes[d]][l];
         result0[10-l]+=Solution.count[nodes[d]][l];
        }
        count_changed[nodes[d]]=true;
       }
       
       d=parents[d];
      }
      System.out.println("</Path>");*/
     }
     
     if (toll>0) {
      if (toll == 5) {
       if (loopType == 2) {
        loopType = 3;
       } else {
        loopType = 1;
       }
      } else {
       if(toll % 2 == 0) {
        if (loopType == 1) {
         loopType = 3;
        } else {
         loopType = 2;
        }
       } else {
        loopType = 3;
       }
      }
     } else {
      loopType = 0;
     }

     road_checked[children_idx[node][cc]]=true;
     loopsChecks++;
     if (loopsChecks == maxLoopsChecks) {
      return;
     }
    }
   }
  }
  
  //System.out.println("Loop type: "+loopType);
 }
 
 
 public static long result1[] = new long[10];
 
 public static void solve1() {
  Arrays.fill(result1, 0);
  if (loopType == 3) {
   for(int i=0; i<10; i++) {
    result1[i]=nodesIdx;
    result1[i]*=nodesIdx-1;
   }
  } else {
   int k=1;
   int step=0;
   if(loopType == 1) {
    k=2;
    step=5;
   }
   if(loopType == 2) {
    k=5;
    step=2;
   }
   
   for(int i=0; i<10; i++) {
    for(int ki=0; ki<k;ki++) {
     result1[(i+ki*step)%10]+=result0[i];
    }
   }
  }
 }
 
 public static long result[] = new long[10];
 
 public static void solve() {
  int last = 0;
  for(int i=last+1; i<checked.length; i++) {
   if(!checked[i]) {
    findGroup(i, false);
    last = nodes[nodesIdx-1];
    //for(int j=0; j<nodesIdx; j++) {
    // checked[nodes[j]]=false;
    //}
    //findGroup(last, true);
    solve0();
    findLoops();
    solve1();
    
    for(int j=0; j<10; j++) {
     result[j]+=result1[j];
    }
   }
  }
  
  for(int j=0; j<10; j++) {
   System.out.println(result[j]);
  }
 }
 
 public static void main(String[] args) throws FileNotFoundException {
  long t = new Date().getTime();
  //Scanner in = new Scanner(new FileInputStream("input.txt"));
  Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        
        init(n);
        
        // load data
        int m = in.nextInt();
        road_j1 = new int[m];
        road_j2 = new int[m]; 
        road_toll = new int[m];
        road_checked = new boolean[m];
        
        for(int i = 0; i < m; i++){
         road_j1[i] = in.nextInt();
         road_j2[i] = in.nextInt();
         road_toll[i] = in.nextInt();
         nodes[road_j1[i]]++;
         nodes[road_j2[i]]++;
        }
        
        for(int i=0; i<=n;i++) {
         children[i]=new int[nodes[i]];
         children_toll[i]=new int[nodes[i]];
         children_idx[i]=new int[nodes[i]];
        }
        
        for(int i = 0; i < m; i++){
         int idx = nodes[road_j1[i]] - 1;
         children[road_j1[i]][idx] = road_j2[i];
         children_toll[road_j1[i]][idx] = road_toll[i] % 10;
         children_idx[road_j1[i]][idx] = i;
         nodes[road_j1[i]]--;
         
         idx = nodes[road_j2[i]] - 1;
         children[road_j2[i]][idx] = road_j1[i];
         children_toll[road_j2[i]][idx] = (1000-road_toll[i]) % 10;
         children_idx[road_j2[i]][idx] = i;
         nodes[road_j2[i]]--;
        }
        //Arrays.fill(nodes, 0);
        
        solve();
        //System.out.println("Total time: "+(new Date().getTime() - t));
    }

}