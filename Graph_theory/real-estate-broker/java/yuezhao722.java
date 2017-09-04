import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
class House {
    public int area;
    public int minPrice;
    public House (int area, int minPrice) {
        this.area = area;
        this.minPrice = minPrice;
    }
}

class Client {
    public int minArea;
    public int maxPrice;
    public Client(int minArea, int maxPrice) {
        this.minArea = minArea;
        this.maxPrice = maxPrice;
    }
}
public class Solution {
    
    public static int minCanSell(Client[] clients, House[] houses) {
        HashSet<House> set = new HashSet<>();
        for (Client c : clients) {
            for (House h : houses) {
                if (h.area > c.minArea && h.minPrice <= c.maxPrice) {
                    set.add(h);
                }
            }
        }
        return set.size();
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int clientsNum = in.nextInt();
        int housesNum = in.nextInt();
        Client[] clients = new Client[clientsNum];
        for (int i = 0; i < clientsNum; i++) {
            int minArea = in.nextInt();
            int maxPrice = in.nextInt();
            clients[i] = new Client(minArea, maxPrice);
        }
        House[] houses = new House[housesNum];
        for (int i = 0; i < housesNum; i++) {
            int area = in.nextInt();
            int minPrice = in.nextInt();
            houses[i] = new House(area, minPrice);
        }
        System.out.println(minCanSell(clients, houses));
        
    }
}