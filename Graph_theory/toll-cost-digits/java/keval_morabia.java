import java.util.*;

class Vertex{
    LinkedList<Vertex> adj;
    int data;
    int color;//0=white, 1=gray, 2=black
    int distance;//from start vertex

    public Vertex(int data){
        color=0;
        distance=-1;
        this.data = data;
        adj = new LinkedList<>();
    }
}

public class Solution {
    int v;//no of vertices
    Vertex[] vertex;
    int[][] w;
    int[] count;

    public Solution(int v){
        this.v=v;
        vertex = new Vertex[v];
        count = new int[10];
        for(int i = 0; i<v; i++){
            vertex[i] = new Vertex(i);
        }
        w = new int[v][v];
    }

    void addEdge(int a, int b,int r){
        vertex[a].adj.add(vertex[b]);
        vertex[b].adj.add(vertex[a]);
        w[a][b] = r;
        w[b][a] = 1000-r;
    }

    void reset(){
        for(int i = 0; i<v; i++){
            vertex[i].distance=-1;
            vertex[i].color=0;
        }    
    }

    void dfs(){
        for(int i = 0; i<v; i++){
            for(Vertex v: vertex[i].adj){
                reset();
                dfsvisit(i,w[i][v.data],v.data);
            }
        }
    }

    void dfsvisit(int start, int sum, int curr){
        //System.out.println(sum);
        count[sum%10]++;
        for(Vertex v:vertex[curr].adj){
            if(v.data!=start && v.color==0){
                v.color=1;
                dfsvisit(start, sum+w[curr][v.data], v.data);
            }
        }
    }

    void print(){
        for(int i = 0; i<10; i++)
            System.out.println(count[i]);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Solution s = new Solution(in.nextInt());
        int e = in.nextInt();
        for(int a0 = 0; a0 < e; a0++){
            int x = in.nextInt()-1;
            int y = in.nextInt()-1;
            int r = in.nextInt();
            s.addEdge(x,y,r);
            s.count[r%10]--;
            s.count[(1000-r)%10]--;
        }
        s.dfs();
        s.print();
    }
}