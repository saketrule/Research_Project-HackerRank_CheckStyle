import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

import java.util.Scanner;

/**
 *
 * @author ayush
 */
public class Hackerrank {
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int g = scanner.nextInt();
        int numOfGames = 0;
        while(numOfGames < g){
            int n = scanner.nextInt();
            if(n == 1)
                System.out.println("Bob");
            else if(n == 2)
                System.out.println("Alice");
            else{
                boolean[] isPrimeNumber = new boolean[n+1];
                  for (int i = 2; i <= n; i++) {
                      isPrimeNumber[i] = true;
                  }
                  int num = 2;
                  while (true) {
                      for (int i = 2;; i++) {
                          int multiple = num * i;
                          if (multiple > n) {
                              break;
                          }else {
                              isPrimeNumber[multiple] = false;
                          }
                      }
                      boolean nextNumFound = false;
                      for (int i = num + 1; i < n + 1; i++) {
                          if (isPrimeNumber[i]) {
                              num = i;
                              nextNumFound = true;
                              break;
                          }
                      }
                      if (!nextNumFound) {
                          break;
                      }
                  }
                  int count = 0;
                  for(int i = 0; i < isPrimeNumber.length; i++){
                      if(isPrimeNumber[i]){
                          count++;
                      }
                  }
                if(count%2 == 0)
                    System.out.println("Bob");
                else
                    System.out.println("Alice");
            }
            numOfGames++;
        }
    }
}