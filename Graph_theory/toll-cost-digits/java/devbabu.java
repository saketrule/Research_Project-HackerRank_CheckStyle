import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Graph{
    int v;
    List<Integer> list;
    Graph(){
        
    }
    Graph(int v){
        this.v = v;
        list = new ArrayList<Integer>();
    }
}

public class Solution {

    Map<String,Integer> map;
    Graph []graph;
    int []arr;
    public static void main(String[] args) {
        Solution s = new Solution();
        s.input();
    }
    
    void input(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int e = in.nextInt();
        graph = new Graph[n+1];
        arr = new int[10];
        map = new HashMap<String,Integer>();
        for(int a0 = 0; a0 < e; a0++){
            int x = in.nextInt();
            int y = in.nextInt();
            int r = in.nextInt();
            String s = x+"-"+y;
            String d = y+"-"+x;
            map.put(s,r);
            map.put(d,1000-r);
            if(graph[x] == null){
                Graph g = new Graph(x);
                g.list.add(y);
                graph[x] = g;
            }
            else if(graph[x] != null){
                graph[x].list.add(y);
            }
            if(graph[y] == null){
                Graph g = new Graph(y);
                g.list.add(x);
                graph[y] = g;
            }
            else if(graph[y] != null){
                graph[y].list.add(x);
            }            
        } 
        process(n);
        print();
    }
    
    void process(int n){
        for(int i = 1; i <= n; i++){
            if(graph[i] != null){
                for(int j = i+1; j <= n; j++){
                    if(graph[j] != null){
                        dfs(graph[i].v, graph[j].v, new boolean[n+1], new int[n+1], 0);
                    }
                }
            }
        }
    }
    
    void dfs(int s, int d, boolean []visited, int []path, int index){
        visited[s] = true;
        path[index] = s;
        index++;
        if(s == d){
            if(path != null && path.length > 1)
                processPath(path);
            //printPath(path);
        }
        else{
            List<Integer> list = graph[s].list;
            if(list != null && list.size() > 0){
                for(int i = 0; i < list.size(); i++){
                    int nv = list.get(i);
                    if(!visited[nv]){
                        dfs(nv,d,visited,path,index);
                    }
                }
            }
        }
        path[index] = 0;
        index--;
        visited[s] = false;
    }
    
    void processPath(int []path){
        //System.out.println("Length : "+(path != null ? path.length : 0));
        int sum1 = 0;
        int sum2 = 0;
        for(int i = 0; i < path.length; i++){            
            if((i+1) < path.length && path[i] > 0 && path[i+1] > 0){
                sum1 = sum1 + map.get(path[i]+"-"+path[i+1]);
                //System.out.println(path[i]+" "+path[i+1]);
            }
        }
        for(int i = path.length-1; i >= 0; i--){
            if((i-1) >= 0 && path[i] > 0 && path[i-1] > 0){
                sum2 = sum2 + map.get(path[i]+"-"+path[i-1]);
                //System.out.println(path[i]+" "+path[i+1]);
            }
        }        
        String temp1 = String.valueOf(sum1);
        String temp2 = String.valueOf(sum2);
        try{
            int d1 = Integer.parseInt(""+temp1.charAt(temp1.length()-1));
            int d2 = Integer.parseInt(""+temp2.charAt(temp2.length()-1));
            arr[d1]++;
            arr[d2]++;
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    void print(){
        for(int i = 0; i < 10; i++){
            System.out.println(arr[i]);
        }
    }
    
    void printPath(int []path){
        for(int i = 0; i < path.length; i++){
            if(path[i] > 0)
                System.out.print(path[i]+" ");
        }
        System.out.println();
    }
}