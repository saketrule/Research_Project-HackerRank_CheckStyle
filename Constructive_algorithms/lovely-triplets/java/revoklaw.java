import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {



    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int cases = 1;//in.nextInt();
        for (int testcase = 0; testcase < cases; testcase++) {
            int n = in.nextInt();
            int m = in.nextInt();
            lovelyTriplets(n, m);
        }
    }

    public static void lovelyTriplets(int n, int q) {
        if (q == 2) {
            lovelyTripletsTwo(n);
            return;
        }
        if (q == 3) {
            lovelyTripletsThree(n);
            return;
        }
        int best = Integer.MAX_VALUE;
        int bestIndex = -1;
        for (int i = 1; i < n; i++) {
            int[] b1 = bestThree(i);
            int[] b2 = bestThree(n-i);
            int sum = b1[0] +b1[1] + b1[2] + b2[0] + b2[1] + b2[2];
            if (sum < best) {
                best = sum;
                bestIndex = i;
            }
        }
        int[] b1 = bestThree(bestIndex);
        int[] b2 = bestThree(n-bestIndex);
        int sum = 0;
//        for (int i : b1) System.out.print(i + " ");
//        for (int i : b2) System.out.print(i + " ");
//        System.out.println();
        for (int i : b1) sum+=i;
        for (int i : b2) sum+=i;
        int nodes = sum+3*(q-2);
        System.out.println(nodes + " " + nodes);
        int node = 7;
        for (int i = 0; i < b1[0]; i++) {
            System.out.println("1 " + node); node++;
        }
        for (int i = 0; i < b1[1]; i++) {
            System.out.println("2 " + node); node++;
        }
        for (int i = 0; i < b1[2]; i++) {
            System.out.println("3 " + node); node++;
        }
        for (int i = 0; i < b2[0]; i++) {
            System.out.println("4 " + node); node++;
        }
        for (int i = 0; i < b2[1]; i++) {
            System.out.println("5 " + node); node++;
        }
        for (int i = 0; i < b2[2]; i++) {
            System.out.println("6 " + node); node++;
        }
        System.out.println("1 4");
        System.out.println("2 5");
        System.out.println("3 6");
        int[] lasts = new int[]{4,5,6};
        for (int  i = 5; i <= q; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println(lasts[j] + " " + node);
                lasts[j] = node;
                node++;
            }
        }
        System.out.println(lasts[0] + " 2");
        System.out.println(lasts[1] + " 3");
        System.out.println(lasts[2] + " 1");

    }

    static void lovelyTripletsThree(int n) {
        int best = Integer.MAX_VALUE;
        int bestIndex = -1;
        for (int i = 1; i < n; i++) {
            int[] b1 = bestThree(i);
            int[] b2 = bestThree(n-i);
            int sum = b1[0] +b1[1] + b1[2] + b2[0] + b2[1] + b2[2];
            if (sum < best) {
                best = sum;
                bestIndex = i;
            }
        }
        int[] b1 = bestThree(bestIndex);
        int[] b2 = bestThree(n-bestIndex);
        int sum = 0;
//      for (int i : b1) System.out.print(i + " ");
//      for (int i : b2) System.out.print(i + " ");
//      System.out.println();
      for (int i : b1) sum+=i;
      for (int i : b2) sum+=i;
      int nodes = sum+6;
      System.out.println(nodes + " " + nodes);
      int node = 7;
        for (int i = 0; i < b1[0]; i++) {
            System.out.println("1 " + node); node++;
        }
        for (int i = 0; i < b1[1]; i++) {
            System.out.println("2 " + node); node++;
        }
        for (int i = 0; i < b1[2]; i++) {
            System.out.println("3 " + node); node++;
        }
        for (int i = 0; i < b2[0]; i++) {
            System.out.println("4 " + node); node++;
        }
        for (int i = 0; i < b2[1]; i++) {
            System.out.println("5 " + node); node++;
        }
        for (int i = 0; i < b2[2]; i++) {
            System.out.println("6 " + node); node++;
        }
        System.out.println("1 2");
        System.out.println("2 3");
        System.out.println("3 1");
        System.out.println("4 5");
        System.out.println("5 6");
        System.out.println("6 4");

    }

    static int[] bestThree(int n) {
        int cube = (int)Math.pow(n, 1.0/3.0);
        for (int i = cube+1; i >= 1; i--) {
            if ((n%i) == 0) {
                int[] bt = bestTwo(n/i);
                return new int[]{i, bt[0], bt[1]};
            }
        }
        return new int[]{n, 1, 1};
    }

    static int[] bestTwo(int n) {
        int square = (int)Math.sqrt(n);
        for (int i = square+1; i >= 1; i--) {
            if ((n%i) == 0) {
                return new int[]{i, n/i};
            }
        }
        return new int[]{n, 1};
    }

    public static void lovelyTripletsTwo(int p) {
        int[] chooses = new int[34];
        for (int i = 3; i < 34; i++) {
            int val = i*(i-1)*(i-2)/6;
            chooses[i] = val;
        }



        int total = 0;
        List<Integer> flowers = new ArrayList<Integer>();
        while (total < p) {
            for (int i = 33; i >= 3; i--) {
                if (total + chooses[i] <= p) {
//                    System.out.println("size " + i + " flower");
                    total += chooses[i];
                    flowers.add(i);
                    i++;
                }
            }
        }
        int numFlowers = flowers.size();
        int numLeaves = 0;
        for (int flower : flowers) numLeaves += flower;
        System.out.println((numFlowers + numLeaves) + " " + numLeaves);

        int nodeCount = 0;
        for (int flower : flowers) {
            int root = nodeCount+1;
            for (int i = 0; i < flower; i++) {
                System.out.println(root + " " + (root+i+1));
            }
            nodeCount = root + flower;
        }
    }

}