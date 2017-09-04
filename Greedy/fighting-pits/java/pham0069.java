import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int fighters = sc.nextInt();
        int teams = sc.nextInt();
        int queries = sc.nextInt();
        ArrayList<ArrayList<Integer>> strengths = new ArrayList<ArrayList<Integer>>();
        int s, t, q;
        for (int i = 0; i < teams; i++){
            strengths.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < fighters; i++){
            s = sc.nextInt();
            t = sc.nextInt() - 1;
            strengths.get(t).add(s);
        }
        for (int i = 0; i < teams; i++){
            Collections.sort(strengths.get(i));
        }
        for (int i = 0; i < queries; i++){
            q = sc.nextInt();
            if (q == 1){
                int p = sc.nextInt();
                t = sc.nextInt()-1;
                //no need to re-sort because p is ensured to be bigger than max strength of team t
                strengths.get(t).add(p);
            }
            else{
                int x = sc.nextInt() - 1;
                int y = sc.nextInt() - 1;
                int rounds = fight(new ArrayList<Integer> (strengths.get(x)), 
                                    new ArrayList<Integer> (strengths.get(y)));
                if (rounds%2 == 1)
                    System.out.println(x+1);
                else
                    System.out.println(y+1);
            }
        }
    }
    //teamX and teamY are sorted in ascending order of strength
    public static int fight(ArrayList<Integer> teamX, ArrayList<Integer> teamY){
        if (teamX.isEmpty() || teamY.isEmpty())
            return 0;
        
        int s = teamX.get(teamX.size()-1);
        int last = teamY.size()-1;
        int i = 0;
        while (!teamY.isEmpty() && i < s){
            teamY.remove(last-i);
            i++;
        }
        
        return 1+fight(teamY, teamX);
    }
}