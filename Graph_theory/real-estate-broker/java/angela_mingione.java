import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

        public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int clientLen = in.nextInt();
        int houseLen = in.nextInt();

        ArrayList<Client> cList = new ArrayList<Client>();
        ArrayList<House> hList = new ArrayList<House>();


        int count = 0;

        for(int i = 0; i< clientLen; i++){
            Client curr = new Client();
            curr.money = in.nextInt();
            curr.area = in.nextInt();

            cList.add(curr);
        }

        for(int i = 0; i < houseLen; i++){
            House curr = new House();
            curr.cost = in.nextInt();
            curr.area = in.nextInt();

            hList.add(curr);
        }

        Collections.sort(cList, getCompClient());
        Collections.sort(hList, getCompHouse());

        for(Client c : cList){
            if(c.active){
                for(House h : hList){
                    if(h.active){
                        if(h.cost <= c.money && h.area <= c.area){
                            count++;
                            c.active = false;
                            h.active = false;
                            continue;
                        }
                    }

                }
            }

        }

        System.out.println(count);
    }

    public static Comparator<Client> getCompClient() {
        Comparator comp = new Comparator<Client>(){
            @Override
            public int compare(Client s1, Client s2) {
                if (s1.money > s2.money) {
                    return -1;
                } else if (s1.money < s2.money) {
                    return 1;
                } else {
                    if (s1.area > s2.area) {
                        return -1;
                    } else if (s1.area < s2.area) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            }
        };
        return comp;
    }

    public static Comparator<House> getCompHouse() {
        Comparator comp = new Comparator<House>(){
            @Override
            public int compare(House s1, House s2) {
                if(s1.cost > s2.cost){
                    return -1;
                }
                else if(s1.cost < s2.cost){
                    return 1;
                }
                else{
                    if(s1.area > s2.area){
                        return -1;
                    }
                    else if(s1.area < s2.area){
                        return 1;
                    }

                    else{
                        return 0;
                    }
                }
            }
        };
        return comp;
    }

}
class Client {
    int money;
    int area;
    boolean active = true;

}

class House {
    int cost;
    int area;
    boolean active = true;

}