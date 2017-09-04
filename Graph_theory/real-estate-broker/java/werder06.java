import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static long calc(int[][] map, int[][] houses, int index, int m, int n) {
        boolean[] buy = new boolean[houses.length];
        int count = 0;
        for(int i=0; i< n; i++)  {
            int el = map[i][0];
           for(int j=0; j<m; j++) {
               if(!buy[j]) {
                   int p = houses[j][0];
                   if(index == 0 ? el < p : el >= p) {
                       if(index == 0 ? map[i][1] >= houses[j][1] : map[i][1] < houses[j][1]) {
                        //   System.out.println("Cl: "+ el+" "+map[i][1]+" "+houses[j][0]+"  "+houses[j][1]);
                           count++;
                           buy[j] = true;
                           j = m;
                       }
                   }
               }
           }
        }

        return count;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] areaToPrice = new int[n][2];
        int[][] priceToArea = new int[n][2];

        int[][] areaToPriceH = new int[m][2];
        int[][] priceToAreaH = new int[m][2];


        for (int i = 0; i < n; i++) {
            int area = scanner.nextInt();
            int price = scanner.nextInt();
            areaToPrice[i] = new int[]{area, price};
            priceToArea[i] = new int[]{price, area};
        }
      //  int[][] houses = new int[m][2];

        for (int i = 0; i < m; i++) {
            int area = scanner.nextInt();
            int price = scanner.nextInt();
            areaToPriceH[i] = new int[]{area, price};
            priceToAreaH[i]= new int[]{price, area}; ;
        }
        Arrays.sort(areaToPrice, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                int com = Integer.compare(a[0], b[0]);
                if (com == 0) {
                    return Integer.compare(a[1], b[1]);
                } else {
                    return com;
                }
            }
        });
        Arrays.sort(priceToArea, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                int com = Integer.compare(a[0], b[0]);
                if (com == 0) {
                    return Integer.compare(a[1], b[1]);
                } else {
                    return com;
                }
            }
        });
        Arrays.sort(areaToPriceH, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                int com = Integer.compare(a[0], b[0]);
                if (com == 0) {
                    return Integer.compare(a[1], b[1]);
                } else {
                    return com;
                }
            }
        });
        Arrays.sort(priceToAreaH, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                int com = Integer.compare(a[0], b[0]);
                if (com == 0) {
                    return Integer.compare(a[1], b[1]);
                } else {
                    return com;
                }
            }
        });
        long max2 =  Math.max(calc(areaToPrice, areaToPriceH, 0, m, n),
                calc(priceToArea, priceToAreaH, 1, m, n));
        Arrays.sort(areaToPriceH, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                int com = Integer.compare(a[1], b[1]);
                if (com == 0) {
                    return Integer.compare(a[0], b[0]);
                } else {
                    return com;
                }
            }
        });
        Arrays.sort(priceToAreaH, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                int com = Integer.compare(a[1], b[1]);
                if (com == 0) {
                    return Integer.compare(a[0], b[0]);
                } else {
                    return com;
                }
            }
        });
        long max1 =  Math.max(calc(areaToPrice, areaToPriceH, 0, m, n),
                calc(priceToArea, priceToAreaH, 1, m, n));

        System.out.println(Math.max(max2,max1));
    }
}