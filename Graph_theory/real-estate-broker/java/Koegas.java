import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int clientCt = in.nextInt();
        int houseCt = in.nextInt();
        int area = 0;
        int price = 0;
 
        Map<Integer,Integer> clients = new HashMap<Integer,Integer>();
        for(int i=0; i < clientCt; i++){
            area = in.nextInt();
            price = in.nextInt();
            clients.put(area,price);
        }
        Map<Integer,Integer> houses = new HashMap<Integer,Integer>();
        for(int i=0; i < houseCt; i++){
            area = in.nextInt();
            price = in.nextInt();
            houses.put(area,price);
        }
        int houseIndex = 0;
        int houseSold = 0;
        List<Integer> indexes = new ArrayList<Integer>();
        for (Map.Entry<Integer,Integer> client : clients.entrySet()){
//            System.out.println(client.getKey() + " / " + client.getValue());
            houseIndex = 0;
            for (Map.Entry<Integer,Integer> house : houses.entrySet()){
                if (client.getKey()>house.getKey() && house.getValue()<client.getValue()){
                    if (!indexes.contains(houseIndex)){
                        indexes.add(houseIndex);
                        houseSold++;
                    }
//                    System.out.println(house.getKey() + " :: " + house.getValue());                    
                }
                houseIndex++;
//                System.out.println(house.getKey() + " / " + house.getValue());
                
            }
        }


        System.out.println(houseSold);
    }
}