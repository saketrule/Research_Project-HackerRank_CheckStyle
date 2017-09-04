import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] clientsAreas = new int[n];        
        int[] clientsPrices = new int[n];                
        int m = in.nextInt();
        int[] housesAreas = new int[m];        
        int[] housesPrices = new int[m];                        
        for (int i = 0; i<n; i++) {
            clientsAreas[i] = in.nextInt();
            clientsPrices[i] = in.nextInt();
        }
        for (int j = 0; j<m; j++) {
            housesAreas[j] = in.nextInt();
            housesPrices[j] = in.nextInt();
        }
        
        System.out.print(getMaxSells(n, m, clientsAreas, clientsPrices, housesAreas, housesPrices));
    }
    
    private static int getMaxSells(int n, int m, int[] clientsAreas, int[] clientsPrices, int[] housesAreas, int[] housesPrices) {
        ArrayList<LinkedList<Integer>> possibleClientsHouses = new ArrayList<LinkedList<Integer>>();
        LinkedList<Integer> ableClients = new LinkedList<>();
        for (int idxClient=0; idxClient < n; idxClient++) {
            LinkedList<Integer> listOfHouses = new LinkedList<Integer>();
            possibleClientsHouses.add(new LinkedList<Integer>());            
            for (int idxHouse=0; idxHouse < m; idxHouse++) {
                if (clientsAreas[idxClient] < housesAreas[idxHouse] && clientsPrices[idxClient] >= housesPrices[idxHouse]) {
                    possibleClientsHouses.get(idxClient).add(idxHouse);                    
                    ableClients.add(idxClient);
                }
            }
            possibleClientsHouses.add(listOfHouses);
        }
        
        boolean allClientsCompleted = false;
        int countClientsCompleted = 0;
        int totalClientsCompleted = n;
        int maxSells = Integer.MIN_VALUE;
        //while (!allClientsCompleted) {
        while (ableClients.size() > 0) {
        
            boolean[] availableHouses = new boolean[m];
            for (int h=0; h < m;h++) {
                availableHouses[h] = true;
            }
            
            LinkedList<Integer> newAbleClients = new LinkedList<>();

            int countIssuedHouse = 0;    
            for (Integer idxClient : ableClients) {
            //for (int idxClient=0; idxClient < n; idxClient++) {
                LinkedList<Integer> possibleHouses = possibleClientsHouses.get(idxClient);
                ListIterator<Integer> iterator = possibleHouses.listIterator();
                
                //for (int i=0; i < possibleHouses.size() ;i++) {
                while (iterator.hasNext()) {
                    int idxPossibleHouse = iterator.next();
                    if (availableHouses[idxPossibleHouse]) {
                        availableHouses[idxPossibleHouse] = false;
                        
                        iterator.remove();
                        if (possibleHouses.size() == 0) {
                            countClientsCompleted++;
                        } else {
                            newAbleClients.add(idxClient);
                        }
                        
                        countIssuedHouse++;
                        break;
                    }
                }
            }
            
            if (countIssuedHouse > maxSells) {
                maxSells = countIssuedHouse;
            }            
            
            if (countClientsCompleted == totalClientsCompleted) {
                allClientsCompleted = true;
            }            
            
            ableClients = new LinkedList<>();
            ableClients.addAll(newAbleClients);
            //totalClientsCompleted = ableClients.size();
            
        }
        
        return maxSells;
    }
}