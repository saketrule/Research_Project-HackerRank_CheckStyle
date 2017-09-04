import java.io.*;
import java.util.TreeSet;
class Solution {
    static final long M = 1000000007;
    static final int LARGE_SIZE = 17;
    static int turns[] = new int[1 << 16];
    static long ways[] = new long[1 << 16];
    static int lines[] = new int[(16*(16-1)) / 2];

    static class Vector {
        int x, y;
        Vector (int x, int y) {
            this.x = x;
            this.y = y;
        }
        int cross (Vector b) {
            return this.x * b.y - this.y * b.x;
        }
        Vector sub (Vector b) {
            return new Vector (this.x - b.x, this.y - b.y);
        }

        public String toString () {
            return x + " " + y;
        }
    }

    static void min_covers (final int n, int maxsets[]) {
        int prospects[] = new int [maxsets.length];
        int s = 0;
        for (int i = maxsets.length - 1; i >= 0; --i) {
            prospects[i] = s;
            s |= maxsets[i];
        }

        final int universe = (1 << n) - 1;

        ways[0] = 1;
        for (int i = 1; i <= universe; ++i)
            ways[i] = 0;
        turns[0] = 0;
        for (int i = 1; i <= universe; ++i)
            turns[i] = LARGE_SIZE;

        int bits_seen = 0;
        for (int m = 0; m < maxsets.length; ++m) {
            final int line = maxsets[m];

            for (int src = universe - 1; src >= 0; --src) {
                if (ways[src] == 0) continue;

                final int settable = line & ~src;
                final int choosable = settable & prospects[m];
                final int must_sel = settable & ~prospects[m];
                final int base = src | must_sel;
              if (must_sel == 0) {
                  for (int choice = 0; choice != choosable; ) {
                      choice = choosable & ((choice | ~choosable) + 1);
                      if (((choice & (1 + ~choice)) == choice) && (choice & bits_seen) != 0)
                          continue;
                      do { int _src = (src); int _dest = (base | choice); if (turns[_src] + 1 == turns[_dest]) ways[_dest] = (ways[_dest] + ways[_src]) % M; else if (turns[_src] + 1 < turns[_dest]) { turns[_dest] = turns[_src] + 1; ways[_dest] = ways[_src]; } } while (false);
                  }
              } else {
                  if (! (((must_sel & (1 + ~must_sel)) == must_sel) && (must_sel & bits_seen) != 0))
                      do { int _src = (src); int _dest = (base); if (turns[_src] + 1 == turns[_dest]) ways[_dest] = (ways[_dest] + ways[_src]) % M; else if (turns[_src] + 1 < turns[_dest]) { turns[_dest] = turns[_src] + 1; ways[_dest] = ways[_src]; } } while (false);
                  for (int choice = 0; choice != choosable; ) {
                      choice = choosable & ((choice | ~choosable) + 1);
                      do { int _src = (src); int _dest = (base | choice); if (turns[_src] + 1 == turns[_dest]) ways[_dest] = (ways[_dest] + ways[_src]) % M; else if (turns[_src] + 1 < turns[_dest]) { turns[_dest] = turns[_src] + 1; ways[_dest] = ways[_src]; } } while (false);
                  }
              }

            }
            bits_seen |= line;
        }

        long w = ways[universe];
        for (long k = turns[universe]; k > 1; --k)
            w = (w * k) % M;
        System.out.printf ("%d %d\n", turns[universe], w);
    }

    static int [] maximal_sets_sort_by_size (Vector points[]) {
        TreeSet<Integer> buf = new TreeSet<Integer> ();
        for (int i = 0; i < points.length; ++i) {
            for (int j = i + 1; j < points.length; ++j) {
                Vector pi_pj = points[i].sub (points[j]);
                int set = 0;
                for (int k = 0; k < points.length; ++k) {
                    if (pi_pj.cross (points[i].sub (points[k])) == 0)
                        set |= 1 << k;
                }
                buf.add (set);
            }
        }
        int ret[] = new int [buf.size ()];
        int i = 0;
        for (int set : buf) {
            ret[i++] = set;
        }
        return ret;
    }

    public static void main (String args[]) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s;
        try {
            s = in.readLine ();
            long t = new Long (s);
            Vector points[] = null;
            while (t-- > 0) {
                int n;
                n = new Integer (in.readLine ());
                points = new Vector[n];
                for (int i = 0; i < n; ++i) {
                    char input[] = in.readLine ().toCharArray ();
                    int x = 0, y = 0;
                    int si = 0;
                    for (; si < input.length && Character.isDigit (input[si]);
                         ++si) {
                        x = x * 10 + (int)(input[si] - '0');
                    }
                    while (! Character.isDigit (input[si])) ++si;
                    for ( ; si < input.length && input[si] != ' '; ++si)
                        y = y * 10 + (int)(input[si] - '0');
                    points[i] = new Vector (x, y);
                }
                if (n == 1) {
                    System.out.println ("1 1");
                    continue;
                }

                int lines[] = maximal_sets_sort_by_size (points);

                min_covers (n, lines);
            }
        } catch (Exception e) {
            System.err.println ( e.toString ());
        }
    }
}