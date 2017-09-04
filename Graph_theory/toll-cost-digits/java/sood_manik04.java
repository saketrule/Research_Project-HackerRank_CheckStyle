import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int e = in.nextInt();
        HashMap<Integer,HashMap<Integer,ArrayList<Integer>>> adj = new                                                                                                            HashMap<Integer,HashMap<Integer,ArrayList<Integer>>>();
        int[][] ans = new int[10][n];
        for(int a0 = 0; a0 < e; a0++){
            int x = in.nextInt();
            int y = in.nextInt();
            int r = in.nextInt();
            HashMap<Integer,ArrayList<Integer>> temp = new HashMap<Integer,ArrayList<Integer>>();
            ArrayList<Integer> temp1 = new ArrayList<Integer>();
            if(adj.containsKey(x)){temp=adj.get(x);
               if(temp.containsKey(y)){temp1=temp.get(y);temp1.add(r);}
               else temp1.add(r);
               temp.put(y,temp1);adj.put(x,temp);}
            if(adj.containsKey(y)){temp=adj.get(y);
               if(temp.containsKey(x)){temp1=temp.get(x);temp1.add(1000-r);}
               else temp1.add(1000-r);
               temp.put(x,temp1);adj.put(y,temp);}
            if(!adj.containsKey(x)&&!adj.containsKey(y)){temp1.add(r);temp.put(y,temp1);adj.put(x,temp);
               temp1.clear();temp.clear();
               temp1.add(1000-r);temp.put(x,temp1);adj.put(y,temp);}
        }
        for(int i=0;i<n;i++){
            int source = i+1;
        }
    }
}