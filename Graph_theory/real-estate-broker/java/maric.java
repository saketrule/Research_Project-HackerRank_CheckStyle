import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nClients = scanner.nextInt();
        int nHouses = scanner.nextInt();

        HouseDetails[] clients = new HouseDetails[nClients];
        HouseDetails[] houses = new HouseDetails[nHouses];

        for (int i = 0; i < nClients; i++) {
            long ai = scanner.nextLong();
            long pi = scanner.nextLong();
            clients[i] = new HouseDetails(ai, pi);
        }

        for (int i = 0; i < nHouses; i++) {
            long xj = scanner.nextLong();
            long yj = scanner.nextLong();
            houses[i] = new HouseDetails(xj, yj);
        }

        Arrays.sort(clients);
        Arrays.sort(houses);

        int count = countHousesWithBuyers(houses, clients);

        System.out.println(count);

    }

    private static int countHousesWithBuyers(HouseDetails[] houses, HouseDetails[] clients) {
        int count = 0;
        for (int i = 0; i < houses.length; i++) {
            for (int j = clients.length-1; j>=0; j--) {
                HouseDetails client = clients[j];
                if (isClientInterested(client, houses[i])) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    private static boolean isClientInterested(HouseDetails client, HouseDetails house) {
        if (house.area > client.area) {
            return house.price < client.price;
        }
        return false;
    }

    public static class HouseDetails implements Comparable<HouseDetails> {
        long area;
        long price;

        public HouseDetails(long area, long price) {
            this.area = area;
            this.price = price;
        }

        @Override
        public int compareTo(HouseDetails o) {
            if (o.area < area) {
                return 1;
            } else if (o.area == area) {
                if (o.price < price) {
                    return 1;
                } else if (o.price == price) {
                    return 0;
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        }

        @Override
        public String toString() {
            return String.format("(%s, %s)", area, price);
        }
    }
}