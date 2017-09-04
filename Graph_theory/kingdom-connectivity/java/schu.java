import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

//
//  DFS
//
class Solution {

    private int _n;
    private ArrayList<Integer>[] _adjMatrix;
    private ArrayList<Integer> _loop;
    private Map<Integer,Long> _pathCount;
    private long MODULO = 1000000000;
    private boolean _loopfound;
    
    private void Debug() {
     int i;
     for (i=0; i<_adjMatrix.length-1; i++) {
      System.out.printf("%d:",i);
      for (int j=0; j<_adjMatrix[i].size(); j++) {
       System.out.printf("%d ", _adjMatrix[i].get(j));
      }
      System.out.printf("\n");
     }
    }

    public Solution(int n, ArrayList<Integer>[]adjMatrix) {
        _n = n;
        _adjMatrix = adjMatrix;
        _pathCount = new HashMap<Integer,Long>();
        _loop = new ArrayList<Integer>();
        _loopfound = false;
        
        //Debug();
        ArrayList<Integer> visited = new ArrayList();
        
        long count = nbrPaths(1,visited,false);
        if (_loopfound) {
         System.out.printf("INFINITE PATHS");
        }
        else {
         System.out.printf("%d\n", count);
        }
    }
    
    //
    //  isLoopPath carries the value if the current path has been detected as a loop 
    //
    public long nbrPaths(int currnode, ArrayList<Integer> visited, Boolean isLoopPath) {
     int j;
     long currcount=0;
     long childcount;
     if (_adjMatrix[currnode].size() == 0) {
      return 0;
     }
  visited.add(currnode);
     for (j=0; j<_adjMatrix[currnode].size(); j++) {
      int child = _adjMatrix[currnode].get(j);
      if (child == _n) {
       if (isLoopPath) {
        _loopfound = true;
        return -1;
       }
       else {
        currcount++;
       }
      }
      else if (visited.contains(child)){
       isLoopPath = true;
      }
      else {
       if (_pathCount.containsKey(new Integer(child))) {
        childcount = _pathCount.get(new Integer(child));
       }
       else {
        childcount = nbrPaths(child, visited,isLoopPath);
       }
       if (childcount < 0) {
        isLoopPath = true;
       }
       else {
        currcount += childcount;
       }
      }
     }
     visited.remove((Object)currnode);
     //save computed score
     currcount = currcount % MODULO;
     _pathCount.put(new Integer(currnode), currcount);
     return currcount;
    }
    
    //  Need to detect infinite loop
    //  keep track of count

    public static void main (String args[]) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String s = br.readLine();
            String[] tmp = s.split(" ");
            int n = Integer.parseInt(tmp[0]);
            int m = Integer.parseInt(tmp[1]);
            
            int i;
            int x,y;
            ArrayList<Integer> []adjMatrix = new ArrayList[n+1];
            
            
            for (i=0; i<n; i++) {
             ArrayList<Integer> a = new ArrayList();
             adjMatrix[i] = a;
            }
            
            for (i=0; i<m; i++) {
             s = br.readLine();
             tmp = s.split(" ");
             x = Integer.parseInt(tmp[0]);
             y = Integer.parseInt(tmp[1]);
             adjMatrix[x].add(y);
            }

            Solution soln = new Solution(n,adjMatrix);

            
        } catch (Exception e) {
            System.out.println("IO Exception: " + e.getMessage());
        }
    }
}