import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<Game> games = new ArrayList<Game>();
        for (int i = 0; i < n; i++) {
            Game g = new Game(in.nextInt());
            games.add(g);
            for (int j = 0; j < g.p; j++) {
                g.pSizes[j] = in.nextInt();
            }
        }
        
        for (Game g : games) {
            System.out.println(findWinner(g));
        }
    }
    
    private static String findWinner(final Game g) {
        int score = g.pSizes[0];
        int allOnes = 1;
        for (int i = 1; i < g.p; i++) {
            score = score ^ g.pSizes[i];
            if (g.pSizes[i] > 1)
                allOnes = 0;
        }
        String winner = score == allOnes ? "Second" : "First";
        return winner;
    }
    
    static class Game {
        int p;
        int[] pSizes;
        
        Game(final int p) {
            this.p = p;
            this.pSizes = new int[p];
        }
    }
}