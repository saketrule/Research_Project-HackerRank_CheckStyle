import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static class Info {
        int area;
        int price;
        
        public Info(int a, int p) {
            area = a;
            price = p;
        }
    }
    
    private static ArrayList<ArrayList<Integer>> adjList;
    private static boolean[] visited;
    private static int[] assigned;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numClients = sc.nextInt();
        int numHouses = sc.nextInt();
        Info[] clients = new Info[numClients];
        for (int i = 0; i < numClients; i++) {
            clients[i] = new Info(sc.nextInt(), sc.nextInt());
        }
        Info[] houses = new Info[numHouses];
        for (int i = 0; i < numHouses; i++) {
            houses[i] = new Info(sc.nextInt(), sc.nextInt());
        }
        
        adjList = new ArrayList<>(); // house -> who can buy it
        for (int i = 0; i < numHouses + numClients; i++) {
            adjList.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < numHouses; i++) {
            for (int j = 0; j < numClients; j++) {
                if (clients[j].area < houses[i].area && clients[j].price >= houses[i].price) {
                    adjList.get(i).add(j + numHouses);
                }
            }
        }

        int ans = 0;
        assigned = new int[numHouses+numClients];
        visited = new boolean[numHouses+numClients];
        Arrays.fill(assigned, -1);
        for (int i = 0; i < numHouses; i++) {
            if (assigned[i] == -1) {
                ans += dfs(i);
                Arrays.fill(visited, false);
            }
        }
        System.out.println(ans);
    }
    
    private static int dfs(int p) {
        if (visited[p]) {
            return 0;
        }
        visited[p] = true;
        for (int v : adjList.get(p)) {
            if (assigned[v] == -1 || dfs(assigned[v]) == 1) {
                assigned[p] = v;
                assigned[v] = p;
                return 1;
            }
        }
        return 0;
    }
}