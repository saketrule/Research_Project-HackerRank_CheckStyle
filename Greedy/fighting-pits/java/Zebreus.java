import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static LinkedList<Integer>[] teams;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int fighters = s.nextInt();
        int teamsl = s.nextInt();
        int querys = s.nextInt();
        teams = new LinkedList[teamsl];
        for(int x = 0;x<teams.length;x++){
            teams[x] = new LinkedList<Integer>();
        }
        for(int i = 0;i<fighters;i++){
            int pow = s.nextInt();
            int team = s.nextInt()-1;
            add(team,pow);
        }
        for(int i = 0;i<querys;i++){
            int q = s.nextInt();
            switch(q){
                case 1:
                    int pow = s.nextInt();
                    int team = s.nextInt()-1;
                    add(team,pow);
                    break;
                case 2:
                    int a = s.nextInt()-1;
                    int b = s.nextInt()-1;
                    int ac = teams[a].size();
                    int bc = teams[b].size();
                    int turn = 0;
                    while(ac>0&bc>0){
                        if(turn==0){
                            int power = teams[a].get(ac-1);
                            bc-=power;
                            turn = 1;
                        }else{
                            int power = teams[b].get(bc-1);
                            ac-=power;
                            turn = 0;
                        }
                        
                    }
                    if(ac<=0){
                        System.out.println(b+1);
                    }else{
                        System.out.println(a+1);
                    }
                    break;
            }
        }
    }
    public static void add(int team, int pow){
            teams[team].add(pow);
            Collections.sort(teams[team]);
    }
}