import java.util.*;

public class Solution {

    static int n;
    static int m;
    static int k;
    static int t[];
    static Map<Integer,Integer> Fish;
    static int g[][];
    static Map<Integer,List<Integer>> grathMap;
    static List<Way> ways;
    static int tmplt;

    static int max = Integer.MAX_VALUE;

    static int daextra[][];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        grathMap = new HashMap<>();
        n = in.nextInt();
        m = in.nextInt();
        k = in.nextInt();
        t = new int[n];
        Fish = new HashMap<>();
        g = new int[n][n];
        for(int a0 = 0; a0 < n; a0++){
            t[a0] = in.nextInt();
            int fish = 0;
            for(int b0 = 0; b0 < t[a0]; b0++) {
                int bit = 1 << in.nextInt()-1;
                fish = fish + bit;
            }
            Fish.put(a0+1,fish);
        }
        for(int a1 = 0; a1 < n; a1++) {
            for(int b1 = 0; b1 < n; b1++) {
                g[a1][b1] = -1;
            }
        }
        for (int a1 = 0; a1 < n; a1++) grathMap.put(a1+1, new ArrayList<Integer>());
        for(int a2 = 0; a2 < m; a2++) {
            int xi = in.nextInt();
            int xj = in.nextInt();
            int xt = in.nextInt();
            g[xi-1][xj-1] = xt;
            g[xj-1][xi-1] = xt;
            grathMap.get(xi).add(xj);
            grathMap.get(xj).add(xi);
        }
        tmplt = (2 << (k-1)) - 1;

        daextra = new int[n][tmplt+1];
        for(int a1 = 0; a1 < n; a1++) {
            for(int b1 = 0; b1 < tmplt+1; b1++) {
                daextra[a1][b1] = Integer.MAX_VALUE;
            }
        }
        if (Fish.containsKey(0)) {
            daextra[0][Fish.get(0)] = 0;
        } else {
            daextra[0][0] = 0;
        }

        ways = new ArrayList<>();

        for(Integer mep : grathMap.get(1)) {
            Way newWay = new Way(mep);
            ways.add(newWay);
            daextra[newWay.endPoint()-1][newWay.fish.fish] = newWay.time;
        }

        WayComparador comparador = new WayComparador();
        PriorityQueue<Way> openWays = new PriorityQueue<Way>(ways.size(),comparador);
//        PriorityQueue<Way> openWays = new PriorityQueue<Way>();
//        Queue<Way> openWays = new ArrayDeque<Way>();
        openWays.addAll(ways);
//        int i=0;

        while(!openWays.isEmpty()) {
//            i++;
            Way way = openWays.remove();
//            if (i%1000 == 0) {
////                System.out.println("quue size: "+ openWays.size()+". shortest way: "+way.time+". Found ways: " + daextraFoundWays() );
//            }
            openWays.addAll(daextra(way));
        }

        daextraShortestWays();
//
//        for(int a1 = 0; a1 < tmplt+1; a1++) {
//            System.out.println(""+ String.format("%" + k + "s", Integer.toBinaryString(a1)).replace(' ', '0') + " " + a1+ " " + daextra[n-1][a1]);
//        }

        System.out.println(max);
    }

    static int daextraFoundWays() {
        int s= 0;
        for (int i = 0; i<tmplt+1;i++) {
            if (daextra[n-1][i]< Integer.MAX_VALUE) s++;
        }
        return s;
    }


    static class WayComparador implements Comparator<Way> {

        @Override
        public int compare(Way x, Way y) {
            return Integer.compare(x.time, y.time);

        }
    }

    static List<Way> daextra(Way way) {
        if (way==null) return null;
        List<Way> ways = new ArrayList<>();
        int sp = way.endPoint();
        for (Integer mep : grathMap.get(sp)) {
            Way newWay = new Way(way, way.endPoint(), mep);
            if (daextra[mep-1][newWay.fish.fish] > newWay.time &&
                    !(sp == newWay.endPoint() && way.fish.fish == newWay.fish.fish) ){
//                    && daextra[n-1][newWay.fish.fish] > newWay.time) {
                daextra[mep-1][newWay.fish.fish] = newWay.time;
                ways.add(newWay);
            }
        }
        return ways;
    }

    public static void daextraShortestWays() {
        for (int i = 0; i<tmplt+1;i++) {
            for (int j = i; j<tmplt+1;j++) {
                int sumFish = i | j;
//                String sumfish = String.format("%" + k + "s", Integer.toBinaryString(sumFish)).replace(' ', '0');
                if (sumFish == tmplt && daextra[n-1][i] < Integer.MAX_VALUE && daextra[n-1][j] < Integer.MAX_VALUE ) {
//                    int thismin = min(daextra[n-1][i], daextra[n-1][j]);
                    int thismax = max(daextra[n-1][i], daextra[n-1][j]);
                    if (thismax < max) {
                        max = thismax;
                    }
                }
            }


        }
    }

    public static int max(int a, int b) {
        return a>b?a:b;
    }

    public static class Fish {
        public int fish;
        public Fish(int p) {
            fish = fish | Fish.get(p) | Fish.get(1);
        }
        public Fish(Fish nf, int p) {
            fish = nf.fish | Fish.get(p);
        }
        public String toString() {
            return String.format("%"+k+"s", Integer.toBinaryString(fish)).replace(' ', '0');
        }
    }

    public static class Way {

//        public String toString() {
//            StringBuilder str = new StringBuilder( "" + (path.b) + "-");
//
//            for (Way wp = prev; wp!=null; wp=wp.prev) {
//                str.append(wp.path.b);
//                if (prev!=null ) {
//                    str.append("-");
//                }
//            }
//            str.append("1");
//            return str.toString();
//        }

        public Way(int ep) {
            this.path = new Pair(1,ep);
            this.fish = new Fish(ep);
            this.time = g[0][ep-1];
//            this.prev = null;
            this.size = 1;
        }

        public Way(Way way, int sp, int ep) {
            this.path = new Pair(sp, ep);
            this.fish = new Fish(way.fish, ep);
            this.time = way.time + g[sp-1][ep-1];
//            this.prev = way;
            this.size = way.size+1;
        }

        public int endPoint() {
            return path.b;
        }

        public Pair path;
//        public Way prev;
        public Fish fish;
        public int time;
        public int size;
    }

    public static class Pair {

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object other) {
            if (other==null) return false;
            if (other==this) return true;
            if (!(other instanceof Pair)) return false;
            Pair pair = (Pair) other;
            return this.a == pair.a && this.b == pair.b;
        }


        public int a;
        public int b;

        public String toString() {
            return ""+ a + "-" + b;
        }
    }
}