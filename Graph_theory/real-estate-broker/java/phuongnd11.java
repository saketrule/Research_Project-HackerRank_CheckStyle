import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

 static class Client implements Comparable<Client>{
        int area;
        int price;
        
        public Client(int area, int price){
            this.area = area;
            this.price = price;
        }
        
        @Override
        public int compareTo(Client o) {
            if(this.price == o.price) return 0;
            else return this.price > o.price ? 1 : -1;
        }
    }
    
    static class House implements Comparable<House>{
        int area;
        int price;
        
        public House(int area, int price){
            this.area = area;
            this.price = price;
        }
        
        @Override
        public int compareTo(House o) {
            if(this.price == o.price) return 0;
            else return this.price > o.price ? 1 : -1;
        }
    }
    
    public static void main(String[] args) {
    
        Scanner scan = new Scanner(System.in);
        
        int n = scan.nextInt();
        int m = scan.nextInt();
        Client [] clients = new Client[n];
        House [] houses = new House[m];
        int clientBuy [] = new int[n];
        int avail [] = new int[m];
        int total = 0;
        int clientTotal = 0;
        for(int i = 0 ; i < n; i++){
            clients[i] = new Client(scan.nextInt(), scan.nextInt()); 
        }
        
        for(int i = 0 ; i < m; i++){
            houses[i] = new House(scan.nextInt(), scan.nextInt()); 
        }
        
        //Arrays.sort(houses);
        for(int i = 0 ; i < n; i++){
            for(int j = 0; j < m; j++){
                if(clients[i].area <= houses[j].area && clients[i].price >= houses[j].price){
                    avail[j]++;
                    if(avail[j] == 1){
                        total++;
                    }
                    clientBuy[i]++;
                    if(clientBuy[i] == 1){
                        clientTotal++;
                    }
                }
            }
        }
        
        System.out.println(total <= clientTotal ? total : clientTotal);
    }
}