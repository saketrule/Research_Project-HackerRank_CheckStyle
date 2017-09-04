import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static int findWinner(int t1,int t2,ArrayList<Integer> p1,ArrayList<Integer> p2){
        
        if(p1.size() == 0) return t2;
        if(p2.size() == 0) return t1;
        Collections.sort(p1);
        Collections.sort(p2);
        
        ArrayList<Integer> tempTeam1 = ( ArrayList<Integer>)p1.clone();
        ArrayList<Integer> tempTeam2 = ( ArrayList<Integer>)p2.clone();
       // System.out.println(t1 +" = "+p1);
        //System.out.println(t2+" = "+p2);
        int high = tempTeam1.get(tempTeam1.size()-1);
        while(high != 0){
            tempTeam2.remove(tempTeam2.size()-1);
            high--;
        }
       // System.out.println(t1 +" = "+p1);
        //System.out.println(t2+" = "+p2);
        return findWinner(t2,t1,tempTeam2,tempTeam1);
    }    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int f,teams,q;
        f = sc.nextInt();
        teams = sc.nextInt();
        q = sc.nextInt();
        int i;
        ArrayList<Integer>[] teamArr = new ArrayList[teams+1];
        for( i = 1 ; i <= teams ; i++){
            teamArr[i] = new ArrayList<Integer>();
        }
        for( i = 0 ; i < f ; i++){
            int s = sc.nextInt();
            int t = sc.nextInt();
            teamArr[t].add(s);
        }
        for(i = 0 ; i < q ; i++){
            int c = sc.nextInt();
            if(c == 1){
                int stren = sc.nextInt();
                int tempTeam = sc.nextInt();
                teamArr[tempTeam].add(stren);
            }
            else{
                /*for( i = 0 ; i <= teams ; i++){
                    System.out.println("team = "+teamArr[i]);
                }*/
                int team1 = sc.nextInt();
                int team2 = sc.nextInt();
                System.out.println(findWinner(team1,team2,teamArr[team1],teamArr[team2]));
            }
        }
         
        
    }
}