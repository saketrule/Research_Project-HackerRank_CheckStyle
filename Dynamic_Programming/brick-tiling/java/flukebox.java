import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Scanner;


public class Solution {
  private static long M = 1000000007;
  private static Hashtable<Integer, ArrayList<int[]>> soltable = new Hashtable<Integer, ArrayList<int[]>>();
  private static int[][] base = new int[][]{
    {7,3,4,0},
    {7,3,1,0},
    {1,3,7,0},
    {4,3,7,0},
    {3,2,2,2},
    {3,2,1,1},
    {1,2,1,3},
    {2,2,2,3}
  };
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int tc = sc.nextInt();
    while(tc--!=0){
      int n = sc.nextInt();
      int m = sc.nextInt();                
      int[] rows = new int[n];
      for(int i=0; i<n; i++){
        String row = sc.next();
        for(int j=0; j<m; j++){
          if(row.charAt(j)=='#'){
            rows[i] |= 1<<(m-j-1);
          }
        }
      }
      if(allBlock(rows, n, m)){
        System.out.println(1);
      }else{
        preprocessing(m);
        System.out.println(solve(rows, n));
      }
    }
    sc.close();
  }

  public static boolean allBlock(int[] rows, int n, int m){
    int totalCases = (1<<m)-1;
    for(int i=0; i<n;i++){
      if(rows[i]!=totalCases){
        return false;
      }
    }
    return true;
  }
  
  public static void buildRowSol(int row, int currIdx, int m, int[] cursol, ArrayList<int[]> sols){
    if((row|cursol[0])==((1<<m)-1)){
      sols.add(cursol);
      return;
    }
    if(currIdx>=m){
      return;
    }
    buildRowSol(row, currIdx+1, m, cursol, sols); 
    for(int i=0; i<base.length; i++){
      if(currIdx+base[i][1]<=m){
        if(((row&(base[i][0]<<(m-currIdx-base[i][1])))==0)
            && ((cursol[0]&(base[i][0]<<(m-currIdx-base[i][1])))==0)
            && ((cursol[1]&(base[i][2]<<(m-currIdx-base[i][1])))==0) 
            && ((cursol[2]&(base[i][3]<<(m-currIdx-base[i][1])))==0)){
          buildRowSol(row, currIdx+1, m, 
              new int[]{cursol[0]|(base[i][0]<<(m-currIdx-base[i][1])),
              cursol[1]|(base[i][2]<<(m-currIdx-base[i][1])),
              cursol[2]|(base[i][3]<<(m-currIdx-base[i][1]))},
              sols);
        }
      }
    }
  }
  
  public static void preprocessing(int m){
    int totalCases = (1<<m)-1;
    soltable = new Hashtable<Integer, ArrayList<int[]>>();
    for(int i=0; i<=totalCases; i++){
      ArrayList<int[]> sol = new ArrayList<int[]>();
      buildRowSol(i, 0, m, new int[]{0,0,0}, sol);
      soltable.put(i, sol);
    }
  }
  
  public static Long solve(int[] rows, int n){
    Hashtable<String, Long> masksSol = new Hashtable<String, Long>();
    long totalSol = 0;
    for(int i=0; i<n; i++){
      Hashtable<String, Long> temp = new Hashtable<String, Long>();
      if(i==0){
        ArrayList<int[]> rowsol = soltable.get(rows[i]);
        for(int[] sol:rowsol){
          if(i+1==n && (sol[1]|sol[2])==0){
            totalSol+=1L;
          }else if((rows[i+1]&sol[1])==0){
            if((i+2<n && (sol[2]&rows[i+2])==0)||(i+2==n && sol[2]==0)){
              String key = sol[1]+":"+sol[2];
              if(temp.containsKey(key)){
                temp.put(key, (temp.get(key)+1L)%M);
              }else{
                temp.put(key, 1L);
              }
            }
          }
        }
      }else{
        for(Entry<String, Long> entry:masksSol.entrySet()){
          String[] masks_ = entry.getKey().split(":");
          int[] masks = new int[]{Integer.parseInt(masks_[0]),Integer.parseInt(masks_[1])};
          ArrayList<int[]> rowsol = soltable.get(rows[i]|masks[0]);
          for(int[] sol:rowsol){
            if(i+1==n && (sol[1]|sol[2]|masks[1])==0){
              // this was the last row, we are done here 
              totalSol+=entry.getValue();
            }else if(i+1<n && ((rows[i+1]|masks[1])&sol[1])==0){
              if((i+2<n && (sol[2]&rows[i+2])==0)||(i+2==n && sol[2]==0)){
                String key = (sol[1]|masks[1])+":"+sol[2];
                if(temp.containsKey(key)){
                  temp.put(key, (temp.get(key)+entry.getValue())%M);
                }else{
                  temp.put(key, entry.getValue());
                }
              }
            }
          }
        }
      }
      masksSol=temp;
    }
    return totalSol;
  }
}