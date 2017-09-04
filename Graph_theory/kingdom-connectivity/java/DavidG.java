import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Solution {

    public static class Kingdom {

        public static int MODULO = 1000000000;
        private int end = 0;
        private boolean loop = false;
        private long[] scores;

        public String buildGraph(InputStream stdin) {
            end = 0;
            loop = false;
            Scanner scanner = new Scanner(stdin);
            String parametersLine = scanner.nextLine();
            end = Integer.parseInt(parametersLine.split(" ")[0]);
            scores = new long[end + 1];
            for (int i = 0; i < scores.length; i++) {
                scores[i] = -1;

            }
            int nbRoads = Integer.parseInt(parametersLine.split(" ")[1]);
            HashMap<Integer, String> roads = new HashMap<Integer, String>();
            for (long i = 1; i <= nbRoads; i++) {
                String roadLine = scanner.nextLine();
                String[] road = roadLine.split(" ");
                int src = Integer.parseInt(road[0]);
                if (!roads.containsKey(src)) {
                    roads.put(src, road[1]);
                } else {
                    roads.put(src, roads.get(src) + "|" + road[1]);
                }
            }

            long pathes = explore(1, roads);
            if (pathes > 0) {
                if (loop) {
                    return "INFINITE PATHS";
                } else {
                    return String.valueOf(pathes % MODULO);
                }
            } else {
                return "0";
            }
        }

        public long explore(int root, HashMap<Integer, String> roads) {
            return explore(root, roads, new ArrayList<Integer>());
        }

        public long explore(int root, HashMap<Integer, String> roads, List<Integer> way) {
            boolean isLoop = false;
            long nbPath = 0;
            if (root == this.end) {
                nbPath = 1;
            }
            if (!roads.containsKey(root)) {
                this.scores[root] = nbPath;
                return this.scores[root];
            }
            String[] dests = roads.get(root).split("\\|");
            for (int i = 0; i < dests.length; i++) {
                int dest = Integer.parseInt(dests[i]);
                if (!way.contains(dest)) {
                    List<Integer> newWay = new ArrayList<Integer>(way);
                    newWay.add(root);
                    if (this.scores[dest] != -1) {
                        nbPath = (nbPath + this.scores[dest]) % MODULO;

                    } else {
                        nbPath = (nbPath + explore(dest, roads, newWay) % MODULO);
                    }
                } else {
                    isLoop = true;
                }
            }
            if (isLoop && nbPath > 0) {
                this.loop = true;
            }
            this.scores[root] = nbPath;
            return nbPath;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Kingdom().buildGraph(System.in));
    }
}