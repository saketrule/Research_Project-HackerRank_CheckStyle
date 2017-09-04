import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Solution {

    public static class InfiniteLoopException extends Exception {

        private static final long serialVersionUID = 1L;
    }
    public static final String SEPARATOR = "|";
    public static final int MODULO = 1000000000;
    public static final String INFINITE_PATHS = "INFINITE PATHS";
    public static String EXPECTED_RESULT = null;
    private List<Solution> roads;
    private int ways;
    private boolean loop;
    private final boolean end;
    private final int me;
    private boolean explored;

    public Solution(int me) {
        this(me, false);
    }

    public Solution(int me, boolean end) {
        this.roads = new ArrayList<Solution>();
        this.ways = 0;//end ? 1 : 0;
        this.loop = false;
        this.end = end;
        this.me = me;
        this.explored = false;
    }

    public static void main(String[] args) {
        Solution root = Solution.parseInput(System.in);
        try {
            System.out.println(root.explore());
        } catch (Solution.InfiniteLoopException x) {
            System.out.println(INFINITE_PATHS);
        }
    }

    public static Solution parseInput(InputStream input) {
        Scanner sc = new Scanner(input);
        Map<String, Solution> cities = new HashMap<String, Solution>();
        int roads = 0;
        while (sc.hasNextLine()) {
            if (cities.size() == 0) {
                String[] values = sc.nextLine().split(" ");
                int limit = Integer.parseInt(values[0]);
                for (int i = 1; i <= limit; i++) {
                    cities.put(Integer.toString(i), new Solution(i, limit == i));
                }
                roads = Integer.parseInt(values[1]);
            } else if (roads > 0) {
                String[] values = sc.nextLine().split(" ");
                cities.get(values[0]).addRoute(cities.get(values[1]));
                roads--;
            } else {
                if (sc.hasNextLine()) {
                    EXPECTED_RESULT = sc.nextLine().replace("#", "");
                }
                return cities.get("1");
            }
        }
        return cities.get("1");
    }

    public void addRoute(Solution child) {
        this.roads.add(child);
    }

    public int explore() throws InfiniteLoopException {
        return this.explore(
                Integer.toString(this.me));
    }

    protected int explore(String branch) throws InfiniteLoopException {
        for (Solution node : this.roads) {
            if (node.end) {
                // TODO is A -> B -> C -> B -> C a correct path ?
                if (node.explore(branch + Solution.SEPARATOR + node.me + Solution.SEPARATOR) != 0) {
                    throw new InfiniteLoopException();
                }
                this.ways = (this.ways + 1) % MODULO;
            } else if (node.explored) {
                this.ways = (this.ways + node.ways) % MODULO;
            } else if (branch.contains(Solution.SEPARATOR + node.me + Solution.SEPARATOR)) {
                node.loop = true;
            } else {
                this.ways = (this.ways + node.explore(branch + Solution.SEPARATOR + node.me + Solution.SEPARATOR)) % MODULO;
            }
            if (this.ways > 0 && this.loop) {
                throw new InfiniteLoopException();
            }
        }
        this.explored = true;
        return this.ways;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Solution other = (Solution) obj;
        if (this.me != other.me) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.me;
        return hash;
    }
}