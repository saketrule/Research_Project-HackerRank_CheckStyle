import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int tcs = in.nextInt();
        
        Game g = new Game();
        
        for (int tc = 0; tc < tcs; tc++) {
            int n = in.nextInt();
            if (g.winner(n)) {
                System.out.println("First");
            } else {                
                System.out.println("Second");
            }
        }
    }
}

class Game {
    private Map<Integer, Boolean> states;
    private int known;
    
    Game() {
        states = new HashMap<Integer, Boolean>();
        states.put(0, false);
        states.put(1, false);
        known = 1;
    }
    public boolean winner(int n) {
        if (known < n) {
            getMoreStates(n);
        } 
        return states.get(n);
    }
    private void getMoreStates(int n) {
        while (known < n) {
            known++;
            
            boolean res = false;
            if (known > 0) {
                res = res || !states.get(known - 2);
            }
            if (known - 2 > 0) {
                res = res || !states.get(known - 3);
            }
            if (known - 4 > 0) {
                res = res || !states.get(known - 5);
            }
            
            states.put(known, res);
        }
    }
}