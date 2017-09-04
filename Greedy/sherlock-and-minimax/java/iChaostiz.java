import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {

 static Scanner in = new Scanner(System.in);

 static int quiCount = 0;

 public static void main(String args[]) {

  PriorityQueue<Box> PQBox = new PriorityQueue<Box>();

  int N = in.nextInt();
  long[] Set = new long[N];
  for (int i = 0; i < N; i++) {
   Set[i] = in.nextLong();
  }
  long Min = in.nextLong();
  long Max = in.nextLong();
  Arrays.sort(Set);

  int i = 0;
  while (Set[i] <= Min)
   i++;

  int tmp = 0;

//  System.out.println(Set[i - 1]);
//  System.out.println(Set[i]);

//  PQBox.add(new Box(Min, Math.abs(Set[i] - Min)));

  for (; i < N && Set[i] <= Max; i++) {
   if (i == 0) {
    PQBox.add(new Box(Min, Math.abs(Min - Set[i])));
   } else {
    tmp = (int) ((Set[i] + Set[i - 1]) / 2);
    if (tmp >= Min && tmp <= Max) {
     PQBox.add(new Box(tmp, Math.abs(tmp - Set[i - 1])));
    }
   }
  }

  if (i == N) {
   PQBox.add(new Box(Max, Math.abs(Max - Set[i-1])));
  } else {
   tmp = (int) ((Set[i] + Set[i - 1]) / 2);
   if (tmp >= Min && tmp <= Max) {
    PQBox.add(new Box(tmp, Math.abs(Set[i - 1] - Max)));
   }else{
    PQBox.add(new Box(Max, Math.abs(Max - Set[i-1])));
   }
  }

   System.out.println(PQBox.poll().val);

//  for (Box X : PQBox) {
//   System.out.println(X.val + " " + X.max);
//  }

 }

 public static class Box implements Comparable<Box> {

  long val = 0;
  long max = 0;

  public Box(long min, long l) {
   this.val = min;
   this.max = l;
  }

  @Override
  public int compareTo(Box o) {
   if (this.max == o.max)
    return (int) (this.val - o.val);
   else
    return (int) (o.max - this.max);
  }

 }

}