import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        long[][] clients = new long[n][2];
        long[][] houses = new long[m][2];

        for(int i= 0 ;i<n ;i++) {
            clients[i][0] = sc.nextLong();
            clients[i][1] = sc.nextLong();
        }

        for(int i =0; i<m; i++) {
            houses[i][0] = sc.nextLong();
            houses[i][1] = sc.nextLong();
        }

        HashSet<Integer> houseset = new HashSet(m);

        for(int i =0; i<n ; i++) {
            long[] client = clients[i];
            long client_area = client[0];
            long client_value = client[1];

            for(int j =0; j<m; j++) {
                if(houses[j][0] > client_area && houses[j][1] <= client_value) {
                    houseset.add(j);
                }
            }


        }

        System.out.println(houseset.size());
    }
}