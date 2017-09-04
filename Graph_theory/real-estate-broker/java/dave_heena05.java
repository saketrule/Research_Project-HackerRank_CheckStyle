import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int clients = sc.nextInt();
        int houses = sc.nextInt();
        
        HashMap<Integer,BigInteger> clientAreaMap = new HashMap<Integer,BigInteger>();
        HashMap<Integer,BigInteger> clientPriceMap = new HashMap<Integer,BigInteger>();
        for(int i=0;i<clients;i++){
            clientAreaMap.put(i,new BigInteger(sc.next()));
            clientPriceMap.put(i,new BigInteger(sc.next()));
        }
        
        HashMap<Integer,BigInteger> houseAreaMap = new HashMap<Integer,BigInteger>();
        HashMap<Integer,BigInteger> housePriceMap = new HashMap<Integer,BigInteger>();
        for(int i=0;i<houses;i++){
            houseAreaMap.put(i,new BigInteger(sc.next()));
            housePriceMap.put(i,new BigInteger(sc.next()));
        }
        
        HashMap<Integer,BigInteger> resultMap = new HashMap<Integer,BigInteger>();
        int count = 0;
        for(int i=0;i<houses;i++){
            resultMap.put(i,BigInteger.ZERO);
            
            for(int j=0;j<clients;j++){
                if(clientAreaMap.get(j).compareTo(houseAreaMap.get(i)) == 1 &&
                    clientPriceMap.get(j).compareTo(housePriceMap.get(i)) >=0 ){
                    resultMap.put(i,resultMap.get(i).add(BigInteger.ONE));
                }
            }
            
            if(resultMap.get(i).compareTo(BigInteger.ZERO) == 1){
                count++;
            }
        }
        
        System.out.println(count);
    }
}