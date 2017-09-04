import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        //try{in = new Scanner(new FileReader("test.txt"));}catch(Exception e){}
        
        int numfighters = in.nextInt();
        int numteams = in.nextInt();
        int queries = in.nextInt();
        
        ArrayList<Integer>[] teams = new ArrayList[numteams+1];
        
        for(int i=0; i<=numteams; i++){
            teams[i] = new ArrayList<>();
        }
        
        for(int i=0; i<numfighters; i++){
            int str = in.nextInt();
            int team = in.nextInt();
            teams[team].add(str);
        }
        
        for(int i=0; i<=numteams; i++){
            Collections.sort(teams[i]);
        }
        
        for(int q=0; q<queries; q++){
            int type = in.nextInt();
            if(1== type){
                int p = in.nextInt();
                int x = in.nextInt();
                teams[x].add(p);
            }
            if(2== type){
                int x = in.nextInt();
                int y = in.nextInt();
                
                int fx = teams[x].size()-1;
                int fy = teams[y].size()-1;
                
                while((0<=fx)){
                    fy -= teams[x].get(fx);
                    if(0>fy){break;}
                    fx -= teams[y].get(fy);
                }
                
                if(0>fx){System.out.println(y);}
                if(0>fy){System.out.println(x);}
                
            }
            
        }
        
        
    }
}