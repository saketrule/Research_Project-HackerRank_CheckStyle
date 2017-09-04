import java.io.*; 
import java.util.*;

public class Solution{
    static class Node{
        public int node;
        public int mask;
        public Node(int d, int m){
            this.node = d;
            this.mask = m;
        }
        public void setNode(int node) {
            this.node = node;
        }

        public int getNode() {
            return node;
        }

        public void setMask(int mask) {
            this.mask = mask;
        }

        public int getMask() {
            return mask;
        }
    }
    public static void main(String[] args) throws IOException{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line1 = br.readLine().split(" ");

        int N = Integer.parseInt(line1[0]);
        int M = Integer.parseInt(line1[1]);
        int K = Integer.parseInt(line1[2]);
        int[][] matS =  new int[N+1][N+1];
        int[] matK = new int[N+1];
        for(int x1 = 0; x1<N+1;x1++){
            for(int x2=0;x2<N+1;x2++){
                matS[x1][x2] = 1000000000;
            }
        }
        for(int _i=1;_i<=N;_i++){
            String[] line = br.readLine().split(" ");
            int Ti = Integer.parseInt(line[0]);
            for(int _j=1;_j<=Ti;_j++){
                matK[_i] |= 1<<(Integer.parseInt(line[_j])-1);
            }
        }
        for(int _y=0;_y<M;_y++){
            String[] line2 = br.readLine().split(" ");
            matS[Integer.parseInt(line2[0])][Integer.parseInt(line2[1])] = Integer.parseInt(line2[2]);
            matS[Integer.parseInt(line2[1])][Integer.parseInt(line2[0])] = Integer.parseInt(line2[2]);
        }
        boolean[] visited = new boolean[N+1];
        int[][] dist = new int[N+1][1024];
        for(int i=0;i<N+1;i++){
            for(int j=0;j<1024;j++){
                dist[i][j] = 1000000000;            
            }}
        visited[1] = true;
        int lastVisited = 1;
        int curVisiting = 1;
        int minVisiting = 0;
        int curd = 0;
        int curMask = 0;
        int minMask = 0;
        dist[1][matK[1]] = 0;

        TreeMap<Integer,List<Node>> map= new TreeMap<Integer,List<Node>>();
        ArrayList<Node> list = new ArrayList<Node>();
        list.add(new  Node(1,matK[1]));
        map.put(0,list);
        while(map.size()!=0){

            Map.Entry topEntry = (Map.Entry) map.firstEntry();
            curd = (Integer)topEntry.getKey();
            ArrayList<Node> mList = (ArrayList<Node>)topEntry.getValue();
            Node cur = null;
            if(mList.size()!=0)
                cur = mList.get(0);
            else
                break;

            curVisiting = cur.getNode();
            curMask = cur.getMask();
            mList.remove(0);
            if(mList.size()==0){
                map.remove(curd);
            }

            for(int q=1;q<N+1;q++){
                if(matS[curVisiting][q]!=1000000000){
                    int toMask = 0;

                    toMask = curMask | matK[q];

                    if(curd+matS[curVisiting][q] < dist[q][toMask]){
                        if(map.containsKey(dist[q][toMask])){
                            ArrayList<Node> tList = (ArrayList<Node>)map.get(dist[q][toMask]);    
                            Iterator it = tList.iterator();
                            Node toRemove = null;
                            while(it.hasNext()){
                                Node x = (Node)it.next();

                                if(x.getMask()==toMask && x.getNode()==q){
                                    toRemove = x;
                                }

                            }
                            if(toRemove!=null){
                                tList.remove(toRemove);
                            }

                        }
                        dist[q][toMask] = curd+matS[curVisiting][q];
                        ArrayList<Node> tdList = (ArrayList<Node>)map.get(dist[q][toMask]);    
                        if(tdList==null){
                            tdList = new ArrayList<Node>();
                        }
                        tdList.add(new Node(q,toMask));
                        map.put(dist[q][toMask],tdList);
                    }

                }


            }    


        }

        int ans = 1000000000;
        for (int w=0;w<(1<<K);w++){
            for(int r=0;r<(1<<K);r++)
                if((w|r)==(1<<K)-1 )
                    ans = Math.min(ans,Math.max(dist[N][w],dist[N][r]));
        }
        System.out.println(ans);
    }
}