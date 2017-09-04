import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


public class Solution {
    
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt(), M = scan.nextInt();
        BigInteger[][] roads = new BigInteger[N + 1][N + 1];
        BigInteger negOne = new BigInteger("-1");
        for(BigInteger[] road : roads) Arrays.fill(road, negOne);
        for(int i = 0; i < M; i++){
            int c1 = scan.nextInt(), c2 = scan.nextInt(), l = scan.nextInt();
            BigInteger tmp = new BigInteger("1");
            
            tmp = tmp.shiftLeft(l);
            roads[c2][c1] = (roads[c2][c1].equals(negOne))? tmp : tmp.min(roads[c2][c1]);
            roads[c1][c2] = (roads[c1][c2].equals(negOne))? tmp : tmp.min(roads[c1][c2]);
        }
        for(int i = 1; i <= N; i++)
        {
        Set<Integer> nodeSet = new HashSet<Integer>();
        for(int j = 1; j <= N; j++) nodeSet.add(j);
        int pre = i;
        BigInteger dis = new BigInteger("0");
        nodeSet.remove(pre);
        while(!nodeSet.isEmpty()){
            int candidate = -1;
            for(int next : nodeSet){
                if(!roads[pre][next].equals(negOne)){
                    if(candidate == -1 || roads[pre][next].compareTo(roads[pre][candidate]) < 0) candidate = next;
                }
            }
            dis = roads[pre][candidate];
            nodeSet.remove(candidate);
            for(int j : nodeSet){
                if(!roads[candidate][j].equals(negOne)){
                    if(roads[pre][j].equals(negOne)|| roads[pre][j].compareTo(dis.add(roads[candidate][j])) > 0){
                        roads[pre][j] = roads[candidate][j].add(dis);
                        roads[j][pre] = roads[candidate][j].add(dis);
                    }
                }
            }
        }
        }
        BigInteger dis = new BigInteger("0");
        for(int i = 1; i <= N; i++){
            for(int j = i + 1; j <= N; j++){
                
                dis = dis.add(roads[i][j]);
                
            }
        }
       System.out.println(dis.toString(2));
        
        
    }
}