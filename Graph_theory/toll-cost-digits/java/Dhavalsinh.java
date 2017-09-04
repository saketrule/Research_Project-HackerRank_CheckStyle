import java.util.*;

/**
 * Created by Dhaval on 29-Jan-17.
 */
class Node{
    int end;
    int cost;
    Node(int e , int c){
        end = e;
        cost = c;
    }
}
public class Toll_Tax {
    public static ArrayList<Node> data[];
    public static HashMap<Integer,HashSet<String>> map = new HashMap<Integer, HashSet<String>>();
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int nodes = sc.nextInt();
        data = new ArrayList[nodes];
        int edges = sc.nextInt();
        int count = 0;
        for (int i = 0 ; i < nodes ; i++)
            data[i] = new ArrayList<Node>();
        while (count < edges){
            int s = sc.nextInt();
            int e = sc.nextInt();
            int cost = sc.nextInt();
            s--; e--;
            data[s].add(new Node(e,cost));
            data[e].add(new Node(s,1000-cost));
            count ++;
        }
        HashSet<Integer> set = new HashSet<Integer>();
        set.add(0);
        for (int i = 0 ; i < nodes ; i++) {
            boolean visit[] = new boolean[nodes];
            DFS(i, visit, 0);
           // System.out.println("****************");
        }
       /* Iterator it = map.keySet().iterator();
        while (it.hasNext()){
            int key = (int)it.next();
            System.out.println(key+":- ");
            Iterator ht = map.get(key).iterator();
            while (ht.hasNext()){
                System.out.println(ht.next());
            }
        }*/

        for (int i = 0 ; i < 10 ; i++){
            System.out.println(map.containsKey(i) ? map.get(i).size() : 0);
        }

        sc.close();
    }

    public static int DFS(int start ,boolean visit[], int cost){
        visit[start] = true;
        int c = 0;
        for (int i = 0 ; i < data[start].size() ; i++){
            boolean visited[] = Arrays.copyOf(visit,data.length);
            Node get = data[start].get(i);
            int loc = get.end;
            int cost1 = get.cost;
            if (visited[loc])
                continue;
            // int c = cost+cost1;
            int a = start + 1;
            int b = loc + 1;
            // System.out.println(a+" - "+b);
            c = cost1 + DFS(loc, visited, cost);
            if (map.containsKey(c%10)){
               // System.out.println(a+" * "+b+" * "+c);
                map.get(c%10).add(a+" "+b);
            }else {
                HashSet<String> set = new HashSet<String>();
               // System.out.println(a+" ** "+b+" ** "+c);
                set.add(a+" "+b);
                map.put(c%10,set);
            }
        }
        return c;
    }
}