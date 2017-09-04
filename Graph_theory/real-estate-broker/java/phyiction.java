import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] clientArea = new int[n];
        int[] clientPrice = new int[n];
        for(int i = 0; i < n; i++){
            clientArea[i] = scanner.nextInt();
            clientPrice[i] = scanner.nextInt();
        }
        int[] houseArea = new int[m];
        int[] housePrice = new int[m];
        for(int i = 0; i < m; i++){
            houseArea[i] = scanner.nextInt();
            housePrice[i] = scanner.nextInt();
        }
        
        Map<Integer,List<Integer>> clientHouseMap = new HashMap<Integer,List<Integer>>();
        Map<Integer,List<Integer>> houseClientMap = new HashMap<Integer,List<Integer>>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(houseArea[j] >= clientArea[i] && housePrice[j] < clientPrice[i]){
                    if(!houseClientMap.containsKey(j)){
                        houseClientMap.put(j,new LinkedList<Integer>());
                    }
                    houseClientMap.get(j).add(i);
                    if(!clientHouseMap.containsKey(i)){
                        clientHouseMap.put(i,new LinkedList<Integer>());
                    }
                    clientHouseMap.get(i).add(j);
                }
            }
        }
        
        int result = (int)Math.min(clientHouseMap.keySet().size(), houseClientMap.keySet().size());
        System.out.println(result);
    }
}