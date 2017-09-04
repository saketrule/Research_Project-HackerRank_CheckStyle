import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static class Client {
        int minArea;
        int maxPrice;
        ArrayList<House> houses = new ArrayList<>();

        Client(int minArea, int maxPrice) {
            this.minArea = minArea;
            this.maxPrice = maxPrice;
        }
    }

    static class House {
        int area;
        int minPrice;
        ArrayList<Client> clients = new ArrayList<>();

        House(int area, int minPrice) {
            this.area = area;
            this.minPrice = minPrice;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int clientsCount = scan.nextInt();
        int houseCount = scan.nextInt();

        ArrayList<Client> clients = new ArrayList<>();
        ArrayList<House> houses = new ArrayList<>();

        int sold = 0;

        for(int i = 0; i < clientsCount; i++) {
            clients.add(new Client(scan.nextInt(), scan.nextInt()));
        }
        for(int i = 0; i < houseCount; i++) {
            houses.add(new House(scan.nextInt(), scan.nextInt()));
        }

        for(Client client : clients) {
            for(House house : houses) {
                if(house.area > client.minArea && house.minPrice <= client.maxPrice) {
                    client.houses.add(house);
                    house.clients.add(client);
                }
            }
            if(client.houses.size() == 1) {
                sold++;
                House soldHouse = client.houses.get(0);
                for(Client c : soldHouse.clients) {
                    c.houses.remove(soldHouse);
                }
                houses.remove(soldHouse);
            }
        }

//        for(House house : houses) {
//            System.out.print("House area = " + house.area + " price = " + house.minPrice + " clients: ");
//            for(Client client : house.clients) {
//                System.out.print("(" + client.minArea + ", " + client.maxPrice + ") ");
//            }
//            System.out.println();
//        }
        
        boolean hadChanged = true;
        while(hadChanged) {
            hadChanged = false;
            for(Client client : clients) {
                if(client.houses.size() == 1) {
                    hadChanged = true;
                    sold++;
                    House soldHouse = client.houses.get(0);
                    for(Client c : soldHouse.clients) {
                        c.houses.remove(soldHouse);
                    }
                    houses.remove(soldHouse);
                }
            }
        }

        boolean hadSimpleChange = true;
        boolean hadComplexChange = true;
        while(hadSimpleChange || hadComplexChange) {
            hadSimpleChange = false;
            hadComplexChange = false;
            for(House house : houses) {
                if(house.clients.size() == 1) {
                    sold++;
                    hadSimpleChange = true;
                    Client buyer = house.clients.get(0);
                    for(House h : buyer.houses) {
                        h.clients.remove(buyer);
                    }
                }
            }
            if(hadSimpleChange == false) {
                for (House house : houses) {
                    if(house.clients.size() > 0) {
                        sold++;
                        hadComplexChange = true;
                        Client buyer = house.clients.get(0);
                        for(House h : buyer.houses) {
                            h.clients.remove(buyer);
                        }
                        break;
                    }
                }
            }
        }
        System.out.println(sold);
    }
}