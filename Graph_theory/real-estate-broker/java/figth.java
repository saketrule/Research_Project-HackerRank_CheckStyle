import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        TreeMap<Integer, Integer> clientMap = new TreeMap<Integer, Integer>();
        for( int i = 0 ; i < n; i++){
            int a = scan.nextInt();
            int p = scan.nextInt();
            clientMap.put(a, p);
        }
        TreeMap<Integer, Integer> houseMap = new TreeMap<Integer, Integer>();
        for( int i = 0 ; i < m; i++){
            int x = scan.nextInt();
            int y = scan.nextInt();
            houseMap.put(x, y);
        }

        int lastKey = clientMap.firstKey(); // lowest key in map of clients
        // reduce the set of houses according to key 
        // this makes the houses to look at number smaller. 
        Iterator it = houseMap.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pairOf = (Map.Entry)it.next();
            if((int)pairOf.getKey() < lastKey) it.remove();
        }

        // go through clients, and find a mapping in order. 
        // if match found, increment counter. 
        int counter = 0;
        for( Integer key : clientMap.keySet()){
            for(Integer keyHouse : houseMap.keySet()){
                if(houseMap.get(keyHouse) <= clientMap.get(key) && keyHouse >= key){
                    counter++;
                    houseMap.remove(keyHouse);
                    break;
                }
            } 
        }
        System.out.println(counter);
    }
}