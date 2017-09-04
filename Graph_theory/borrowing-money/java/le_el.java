import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static House[] houses;
    public static int[] components;
    public static boolean[] searched;

    public static void main(String[] args) {
        Solution s = new Solution();
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        houses = new House[N];
        components = new int[N];
        searched = new boolean[houses.length];
        for (int i = 0; i < N; i++) {
            houses[i] = s.new House(in.nextInt());
        }
        for (int i = 0; i < M; i++) {
            int first = in.nextInt();
            int second = in.nextInt();
            houses[first - 1].addNeighbor(second - 1);
            houses[second - 1].addNeighbor(first - 1);
        }
        for (int i = 0; i < N; i++) bfs(i);
        calculate();   
    }
    
    public static void bfs(int index) {
        if (searched[index]) return;
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(index);
        components[index] = index;
        while (!q.isEmpty()) {
            Integer n = q.remove();
            if (!searched[n]) {
                searched[n] = true;
                components[n] = index;
                for (Integer i : houses[n].neighbors()) {
                    if (!searched[i]) q.add(i);
                }
            }           
        }        
    }
    
    public static void calculate() {        
        int max = 0;
        int n = 1;

        ArrayList<Integer> left;
        int N = houses.length;
        for (int i = 0; i < N; i++) {
            if (components[i] == i) {
                int[] solution = new int[2];
                left = new ArrayList<Integer>();
                for (int j = 0; j < N; j++) {
                    if (components[j] == i) left.add(j);
                }
                
                demandMoney(solution, -1, left);
                max += solution[0];
                if (solution[0] == 0) solution[1]++; // we can visit this part of the graph, or not
                n *= solution[1];
            }            
        }
        
        System.out.println(max + " " + n);
    }
    
    public static void demandMoney(int[] solution, int MAX, ArrayList<Integer> left) {
        int N = left.size();
        if (N < 1) {
            if (MAX > solution[0]) {
                solution[0] = MAX;
                solution[1] = 1;
            }
            else if (MAX == solution[0]) solution[1]++;
            return;
        }
              
        ArrayList<Integer> leftNew = removeNeighbors(left);
        
        int tmpMax = MAX;
        if (MAX < 0) tmpMax = 0;
        
        int max = houses[left.get(0)].getC() + tmpMax;
        left.remove(0);
            
        demandMoney(solution, max, leftNew);
        demandMoney(solution, MAX, left);      
    }
    
    public static ArrayList<Integer> removeNeighbors(ArrayList<Integer> left) {
        int N = left.size();
        ArrayList<Integer> leftNew = new ArrayList<Integer>();
        
        House h = houses[left.get(0)];
        for (int i = 1; i < N; i++) {
            if (!h.neighbors().contains(left.get(i))) {
                leftNew.add(left.get(i));
            }
        }
        return leftNew;
    }
    
    private class House {
        private int C;
        private HashSet<Integer> neighbors;
        public House(int C) {
            this.neighbors = new HashSet<Integer>();
            this.C = C;
        }
        public int getC() {
            return C;
        }
        public void addNeighbor(int i) {
            neighbors.add(i);
        }
        public HashSet<Integer> neighbors() {
            return neighbors;
        }
    }
}