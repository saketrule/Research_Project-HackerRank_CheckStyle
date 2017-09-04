import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static class Point
 {
  public final int x;
  public final int y;
  public boolean used;

  public Point(int x, int y)
  {
   this.x = x;
   this.y = y;
  }

  public static boolean areCollinear(Point p1, Point p2, Point p3)
  {
   return (p2.x - p1.x) * (p3.y - p1.y) == (p3.x - p1.x) * (p2.y - p1.y);
  }
 }

 public static class Line implements Comparable<Line>
 {
  public final Point p1;
  public final Point p2;
  public final Set<Point> points = new HashSet<>();
  public final long m;
  public final long b;

  public Line(Point p1, Point p2)
  {
   this.p1 = p1;
   this.p2 = p2;
   this.points.add(p1);
   this.points.add(p2);

   int mx = p1.x - p2.x;
   int my = p1.y - p2.y;
   int bx = 0;
   int by = 0;
   if (mx == 0)
   {
    my = p1.x;
   }
   else if (my == 0)
   {
    mx = p1.y;
   }
   else
   {
    my = mx < 0 ? -my : my;
    mx = mx < 0 ? -mx : mx;
    int gcd = gcd(Math.abs(mx), Math.abs(my));
    mx /= Math.max(gcd, 1);
    my /= Math.max(gcd, 1);
    bx = mx;
    by = mx * p1.y - my * p1.x;
    gcd = gcd(Math.abs(bx), Math.abs(by));
    bx /= Math.max(gcd, 1);
    by /= Math.max(gcd, 1);
    bx = by == 0 ? 0 : bx;
   }
   this.m = mx | ((long) my) << 32L;
   this.b = bx | ((long) by) << 32L;
  }

  public int unused()
  {
   int count = 0;
   for (Point p : points)
   {
    if (!p.used)
    {
     count++;
    }
   }
   return count;
  }

  @Override
  public int compareTo(Line o)
  {
   return Integer.compare(o.unused(), unused());
  }

  @Override
  public int hashCode()
  {
   long hilo = m ^ b;
   return ((int) (hilo >> 32)) ^ (int) hilo;
  }

  @Override
  public boolean equals(Object obj)
  {
   if (!(obj instanceof Line))
   {
    return false;
   }
   Line l = (Line) obj;
   return m == l.m && b == l.b;
  }
 }

 public static int gcd(int u, int v)
 {
  return u == v || v == 0 ? u
    : u == 0 ? v
      : (~u & 1) == 1 ? ((v & 1) == 1 ? gcd(u >> 1, v) : gcd(u >> 1, v >> 1) << 1)
        : (~v & 1) == 1 ? gcd(u, v >> 1) : u > v ? gcd((u - v) >> 1, v) : gcd((v - u) >> 1, u);
 }

 public static void main(String[] args)
 {
  Scanner sc = new Scanner(System.in);
  int t = sc.nextInt();
  while (t-- > 0)
  {
   int n = sc.nextInt();
   Point[] points = new Point[n];
   for (int i = 0; i < n; i++)
   {
    int x = sc.nextInt();
    int y = sc.nextInt();
    points[i] = new Point(x, y);
   }
   Set<Line> set = new HashSet<>();
   for (int i = 0; i < n; i++)
   {
    Point p1 = points[i];
    boolean[] used = new boolean[n];
    for (int j = i + 1; j < n; j++)
    {
     if (used[j])
     {
      continue;
     }
     Point p2 = points[j];
     Line line = new Line(p1, p2);
     if (set.add(line))
     {
      for (int k = j + 1; k < n; k++)
      {
       Point p3 = points[k];
       if (Point.areCollinear(p1, p2, p3))
       {
        used[k] = true;
        line.points.add(p3);
       }
      }
     }
    }
   }

   List<Line> lines = new ArrayList<>(set);
   minGroups = Integer.MAX_VALUE;
   minTotal = 0;
   remove4(lines, new HashSet<Point>(), n, 0, true);

   System.out.println(minGroups + " " + (minTotal % 1000000007L));
  }
 }

 public static int minGroups = Integer.MAX_VALUE;
 public static long minTotal = 0;

 public static void remove4(List<Line> lines, Set<Point> removed, int n, int depth, boolean recurse)
 {
  Collections.sort(lines);
  if (lines.get(0)
    .unused() <= 2)
  {
   int leftover = n - removed.size();
   int groups = depth + (leftover + 1) / 2;

   long combinations = factorial(leftover) / (2 * factorial(leftover / 2));
   long orders = factorial(groups) / factorial(depth);
   long total = Math.max(1, combinations) * orders;

   if (groups < minGroups)
   {
    minGroups = groups;
    minTotal = total;
   }
   else if (groups == minGroups)
   {
    minTotal += total;
   }
   return;
  }

  if (!recurse)
  {
   return;
  }

  for (int i = 0; i < lines.size(); i++)
  {
   Line line = lines.get(i);
   Set<Point> remove = new HashSet<>(line.points);
   remove.removeAll(removed);
   removed.addAll(remove);
   for (Point p : remove)
   {
    p.used = true;
   }
   remove4(lines, removed, n, depth + 1, remove.size() > 2);
   for (Point p : remove)
   {
    p.used = false;
   }
   removed.removeAll(remove);
   Collections.sort(lines);
  }
 }

 public static long factorial(int n)
 {
  long f = 1;
  for (long i = 1; i <= n; i++)
  {
   f *= i;
  }
  return f;
 }
}