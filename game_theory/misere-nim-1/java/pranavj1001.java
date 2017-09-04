import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        int numberOfGames;
        Scanner sc = new Scanner(System.in);
        numberOfGames = sc.nextInt();
        for(int i = 0; i < numberOfGames; i++){
            int numberOfPiles;
            numberOfPiles = sc.nextInt();
            int[] pileSize = new int[numberOfPiles];
            for(int j = 0; j < numberOfPiles; j++){
                pileSize[j] = sc.nextInt();
            }
            int s = pileSize[0];
            int c = pileSize[0];
            for(int j = 1; j < numberOfPiles; j++){
                s ^= pileSize[j];
            }
            for(int x : pileSize){
                if(x > 1) c = 0;
            }
            if(s == c) System.out.println("Second");
            else System.out.println("First");
        }
        sc.close();
    }
}