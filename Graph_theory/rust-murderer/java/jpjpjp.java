import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    class Road {
        public int x, y;
        public Road(int x, int y) {
            this.x = (y>x) ? y : x;
            this.y = (y>x) ? x : y;
        }
        boolean find(int a) {
            return (a==x || a==y);
        }
        public boolean equals(Object a) {
            Road b = (Road) a;
            return (this.x==b.x) && (this.y==b.y);
        }
        public int hashCode() {
            return (x << 20) | y;
        }
        public String toString() {
            return "" + x + ":" + y;
        }
    }

    public Set<Road> roads;
    int cities;

    public Solution() {
        roads = new HashSet<>();
    }

    public void addRoad(Integer x, Integer y) {
        roads.add(new Road((int) x, (int) y));
    }

    public String toString() {
        String str = "";
        for(Road r : roads) {
            str += r + "\n";
        }
        return str;
    }

    public boolean check(int r, int s) {
        return !roads.contains(new Road(r, s));
    }

    public String solve(int s) {
        int[] dist = new int[cities+1];
        List<Integer> unsolved = new ArrayList<Integer>();
        List<Integer> solved = new ArrayList<Integer>();

        for(int i = 1;i<=cities;i++) {
            if(check(i, s)) {
                dist[i] = 1;
                solved.add(i);
            }
            else
                unsolved.add(i);
        }

        while(!unsolved.isEmpty()) {
            List<Integer> al = new ArrayList<Integer>();
            List<Integer> rl = new ArrayList<Integer>();
            for(Integer cx : unsolved) {
                for(Integer cy : solved) {
                    if(check(cx, cy)) {
                        dist[cx] = dist[cy] + 1;
                        rl.add(cx);
                        al.add(cx);
                        break;
                    }
                }
                solved.addAll(al);
                al.clear();
            }
            unsolved.removeAll(rl);
            rl.clear();
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 1; i<=cities; i++) {
            if(s==i) continue;
            sb.append(""+dist[i]+" ");
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int i=0;i<t;i++) {
            int n = in.nextInt(); // cities
            int m = in.nextInt(); // roads
            Solution sol = new Solution();
            sol.cities = n;
            for (int j=0;j<m;j++) {
                sol.addRoad(in.nextInt(), in.nextInt());
            }
            int s = in.nextInt();
            System.out.println(""+sol.solve(s));
        }
        
    }
}