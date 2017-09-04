import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    static int N;
    static int K;
    static int K_goal;
    static int N_pos[];
    
    private static class Road {
        Shopping nextShopping;
        long time;
        Road nextRoad;
    }
    
    private static class Shopping {
        int num;
        int types;
        Road roads = null;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        N = s.nextInt(); // # shoppings
        N_pos = new int[N];
        Arrays.fill(N_pos, -1);
        int M = s.nextInt(); // # roads
        K = s.nextInt(); // # types of fish
        K_goal = 0; //(1 << K) - 1;
        Shopping [] shops = new Shopping[N];
        for (int i=0; i<N; i++) {
            shops[i] = new Shopping();
            shops[i].num = i + 1;
            int k = s.nextInt();
            shops[i].types = 0;
            for (int j=0; j<k; j++) {
                shops[i].types |= 1 << (s.nextInt() - 1);
            }
            K_goal |= shops[i].types;
        }
        for (int i=0; i<M; i++) {
            int a = s.nextInt() - 1;
            int b = s.nextInt() - 1;
            int t = s.nextInt();
            Road ra = new Road();
            ra.nextShopping = shops[b];
            ra.time = t;
            addRoad(shops[a], ra);
            Road rb = new Road();
            rb.nextShopping = shops[a];
            rb.time = t;
            addRoad(shops[b], rb);
        }
        path = new int[M];
        hist = new int[M];
        link = new int[M];
        followShopping(0, shops[0], shops[0].types, 0);
        System.out.println(maxTime);
    }
    
    static void addRoad(Shopping s, Road r) {
        if (s.roads == null || r.time < s.roads.time) {
            r.nextRoad = s.roads;
            s.roads = r;
        } else {
            Road r0 = s.roads;
            while (r0.nextRoad != null) {
                if (r.time < r0.nextRoad.time) {
                    r.nextRoad = r0.nextRoad;
                    r0.nextRoad = r;
                    return;
                }
                r0 = r0.nextRoad;
            }
            r0.nextRoad = r;
        }
    }
    
    static class Way {
        int []path;
        long time;
        int types;
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(Integer.toBinaryString( (1 << K) | (types & ((1 << K) - 1)) ).substring( 1 ));
            sb.append(' ');
            sb.append(Arrays.toString(path));
            sb.append(' ');
            sb.append(time);
            return sb.toString();
        }
    }
    static long maxTime = Long.MAX_VALUE;
    static List<Way> respostas = new ArrayList<Way>();
    static int []path;
    static int []hist;
    static int []link;
    
    private static void followShopping(int posPath, Shopping shopping, int types, long sumTime) {
        if (posPath == path.length) return;
        if (sumTime > maxTime) return;
        link[posPath] = N_pos[shopping.num - 1];
        N_pos[shopping.num - 1] = posPath;
        path[posPath] = shopping.num;
        hist[posPath] = types;
        //System.out.println("*" + Arrays.toString(Arrays.copyOfRange(path, 0, posPath + 1)));
        //System.out.println(" " + Arrays.toString(Arrays.copyOfRange(hist, 0, posPath + 1)));
        //System.out.println(" " + Arrays.toString(Arrays.copyOfRange(N_pos, 0, N)));
        //System.out.println(" " + Arrays.toString(Arrays.copyOfRange(link, 0, posPath + 1)));
        if (shopping.num == N) {
            Way w = new Way();
            w.path = Arrays.copyOfRange(path, 0, posPath + 1);
            w.time = sumTime;
            w.types = types;
            if (types == K_goal && sumTime < maxTime) {
                respostas.add(w);
                maxTime = sumTime;
                for (Iterator it = respostas.iterator(); it.hasNext();) {
                    Way w0 = (Way) it.next();
                    if (w0.time > sumTime) it.remove();
                }
                N_pos[shopping.num - 1] = link[posPath];
                path[posPath] = 0;
                return;
            }
            long max = maxTime;
            for (Way w0 : respostas) {
                long major = Math.max(w0.time, w.time);
                if (major < max && ((w0.types | w.types) == K_goal)) {
                    max = major;
                }
            }
            if (max == Long.MAX_VALUE) {
                respostas.add(w);
            } else if (max <= maxTime) {
                respostas.add(w);
                maxTime = max;
                for (Iterator it = respostas.iterator(); it.hasNext();) {
                    Way w0 = (Way) it.next();
                    if (w0.time > max) it.remove();
                }
                N_pos[shopping.num - 1] = link[posPath];
                path[posPath] = 0;
                return;
            }
        }
        Road r = shopping.roads;
        List<Road> lr = new ArrayList<Road>();
        while (r != null) {
            if (!findCycle(posPath, r.nextShopping.num, types | r.nextShopping.types)) {
                if ((types | r.nextShopping.types) != types) {
                    followShopping(posPath + 1, r.nextShopping, types | r.nextShopping.types, sumTime + r.time);
                } else {
                    lr.add(r);
                }
            }
            r = r.nextRoad;
        }
        for (Road r0 : lr) {
            followShopping(posPath + 1, r0.nextShopping, types | r0.nextShopping.types, sumTime + r0.time);
        }
        N_pos[shopping.num - 1] = link[posPath];
        path[posPath] = 0;
    }
    
    private static boolean findCycle(int posPath, int num, int types) {
        if (posPath == 0) {
            return false;
        }
        int p = N_pos[num - 1];
        while (p != -1) {
            if (hist[p] == types) return true;
            p = link[p];
        }
        return false;
    }
}