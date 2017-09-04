import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        int T = scan.nextInt();
        for(int t=0; t<T; t++) {
            
            int s = scan.nextInt();
            
            System.out.println(play(s, Player.First));
        }
    }
    
    // method returns who won the game
    public static Player play(int s, Player player) {
        
        //System.out.println(String.format("%s playing: state %d", player.name(), s));
        Player nextPlayer = (player == Player.First) ? Player.Second : Player.First;
        
        // if state is 0 or 1, the next player wins
        if(s == 0 || s == 1)
            return nextPlayer;
        
        if(isWinMove(s, 5) || isWinMove(s, 3) || isWinMove(s, 2))
            return player;
        
        // if no win moves, check the non lose moves to see if w ecna win
        if(!isLoseMove(s, 5)) {
            Player winner = play(s-5, nextPlayer);
            if(winner == player)
                return player;
        }
        
        if(!isLoseMove(s, 3)) {
            Player winner = play(s-3, nextPlayer);
            if(winner == player)
                return player;
        }
        
        if(!isLoseMove(s, 2)) {
            Player winner = play(s-2, nextPlayer);
            if(winner == player)
                return player;
        }

        
        
        // if no moves are win moves and all moves are lose moves, the next player wins
        return nextPlayer;
    }
    
    public static boolean isWinMove(int s, int m) {
        int futureState = s - m;
        
        if(futureState == 8 || futureState == 7 || futureState == 0 || futureState == 1)
            return true;
        
        return false;
    }
    
    public static boolean isLoseMove(int s, int m) {
        int futureState = s - m;
        
        if(isWinMove(s, 5))
            return true;
        
        if(isWinMove(s, 3))
            return true;
        
        if(isWinMove(s, 2))
            return true;
        
        return false;
    }
        
    public static enum Player {
        First,
        Second
    }
}