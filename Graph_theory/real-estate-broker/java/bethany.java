import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        
        int client_house_area[] = new int[n];
        int client_house_price[] = new int[n];
        for (int i = 0; i < n; i++) {
            client_house_area[i] = scan.nextInt();
            client_house_price[i] = scan.nextInt();
        }
        
        int house_area[] = new int[m];
        int house_price[] = new int[m];
        for (int i = 0; i < m; i++) {
            house_area[i] = scan.nextInt();
            house_price[i] = scan.nextInt();
        }
        
        boolean house_taken[] = new boolean[m];
        int housesTaken = 0;
        for (int i = 0; i < m; i++) {
            if (!house_taken[i]) {
                for (int j = 0; j < n; j++) {
                    if (house_area[i] >= client_house_area[j] && house_price[i] <= client_house_price[j]) {
                       house_taken[i] = true;
                       housesTaken++;
                       break;
                    }
                }
            }
        }
        
        System.out.println(housesTaken);
        // System.out.println(Arrays.toString(client_house_area));
        // System.out.println(Arrays.toString(client_house_price));
        // System.out.println(Arrays.toString(house_area));
        // System.out.println(Arrays.toString(house_price));
        
        
    }
}