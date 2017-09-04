import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
    private static void Solve() throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<Integer> nm=Ia(Integer.class);
        n=nm.get(0);
        int m = nm.get(1);
        graph=new Node[n+1];
        sol=new String[n+1][n+1];
        GetGraph(graph, m);
        String ans="0";
        for(int i=1;i<=n;i++){
            Get(i);
            for(int j=i+1;j<=n;j++){
                ans=AddB(ans,sol[i][j]);
            }
        }
        On(ans.toString());
    }

    private static void Get(int root) {
        boolean[] visited = new boolean[n + 1];
        sol[root][root]="0";
        visited[root]=true;

        PriorityQueue<Edge> pq=new PriorityQueue<>();
        pq.add( new Edge("0",graph[root]));

        while(pq.size()>0){
            Node c=pq.poll().node;
            visited[c.n]=true;

            for (Edge v:c.edges) {
                String newd=AddB(sol[root][c.n],v.weight);
                if(!visited[v.node.n] &&( sol[root][v.node.n]==null || CompareString(sol[root][v.node.n],newd)>0)){
                    sol[root][v.node.n]=newd;
                    pq.add(v);
                }
            }
        }
    }

    private static String AddB(String a, String b) {
        BigInteger temp=new BigInteger(a,2);
        BigInteger temp2=new BigInteger(b,2);
        BigInteger ans=temp.add(temp2);
        return ans.toString(2);
    }

    private static int n;
    private static String[][] sol;
    private static Node[] graph;
    private static void GetGraph(Node[] graph,int m) throws NoSuchMethodException, IOException, IllegalAccessException, InvocationTargetException {
        for (int i=0;i<m;i++){
            List<Integer> temp=Ia(Integer.class);
            int a =temp.get(0);
            int b= temp.get(1);
            int c=temp.get(2);
            StringBuilder sb=new StringBuilder(c+1);
            for(int j=0;j<=c;j++){sb.append("0");}
            sb.setCharAt(0,'1');
            if(graph[a]==null){
                graph[a]=new Node(a);
            }
            if(graph[b]==null){
                graph[b]=new Node(b);
            }
            graph[a].edges.add(new Edge(sb.toString(),graph[b]));
            graph[b].edges.add(new Edge(sb.toString(),graph[a]));
        }
    }

    private static class Node{
        int n;
        List<Edge> edges;
        Node(int n){
            this.n=n;
            edges=new ArrayList<>();
        }
    }
    static class Edge implements Comparable<Edge>{
        String weight;
        Node node;
        Edge(String weight, Node node){
            this.weight=weight;
            this.node=node;
        }

        @Override
        public int compareTo(Edge o) {
            return CompareString(this.weight,o.weight);
        }


    }
    private static int CompareString(String weight, String o) {
        if(weight.length()>o.length()){
            return 1;
        }
        else if(weight.length()<o.length()){
            return -1;
        }
        for(int i=0;i<weight.length();i++){
            if(weight.charAt(i)=='1' && o.charAt(i)=='0'){
                return 1;
            }
            else if(weight.charAt(i)=='0' && o.charAt(i)=='1'){
                return -1;
            }
        }
        return 0;
    }
    public static void main(String args[]) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException {
        Solve();
        sw.close();
        sr.close();
    }

    private static BufferedReader sr =new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter sw =new BufferedWriter(new OutputStreamWriter(System.out));


    private static String S() throws IOException {
        return sr.readLine();
    }
    private static <T> T I(Class<T> clazz) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {
        String s= sr.readLine().trim();
        Method m=clazz.getMethod("valueOf",String.class);
        return (T)m.invoke(null,(Object) s);
    }
    private static <T> ArrayList<T> Ia(Class<T> clazz) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {
        ArrayList<T> t = new ArrayList<T>();
        String[] s= sr.readLine().trim().split(" ");
        Method m=clazz.getMethod("valueOf",String.class);
        for (String n:s) {
            t.add((T)m.invoke(null,(Object) n));
        }
        return t;
    }
    private static <T> void On(T s) throws IOException {
        sw.write(String.valueOf(s));
        sw.newLine();
    }
    private static <T> void Swap(List<T> x,int a,int b){
        T temp=x.get(a);
        x.set(a,x.get(b));
        x.set(b,temp);
    }
    private static <T extends Comparable<T>> T Min(List<T> inp) {
        T min=inp.get(0);
        for(int i=1;i<inp.size();i++){
            if(inp.get(i).compareTo(min)<0){
                min=inp.get(i);
            }
        }
        return min;
    }
    private static <T extends Comparable<T>> T Max(List<T> inp) {
        T max=inp.get(0);
        for(int i=1;i<inp.size();i++){
            if(inp.get(i).compareTo(max)>0){
                max=inp.get(i);
            }
        }
        return max;
    }
    private static long Sum(List<Integer> inp) {
        long ans = 0;
        for (Integer anInp : inp) {
            ans += anInp;
        }
        return ans;
    }
    private static int[] convertIntegers(List<Integer> integers) {
        int[] ret = new int[integers.size()];
        for (int i=0; i < ret.length; i++)
        {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }
}