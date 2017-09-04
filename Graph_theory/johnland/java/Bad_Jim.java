import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try{in = new Scanner(new FileReader("test.txt"));}catch(Exception e){}
        
        int numcities = in.nextInt();
        int numroads = in.nextInt();
        BigInteger answer = new BigInteger("0");
        
        ArrayList<road>[] cities = new ArrayList[numcities];
        for(int i=0; i<numcities; i++){
            cities[i] = new ArrayList();
        }
        
        ArrayList<road> roads = new ArrayList();
        
        for(int i=0; i<numroads; i++){
            road r = new road();
            r.a = in.nextInt()-1;
            r.b = in.nextInt()-1;
            r.dist = in.nextInt();
            r.mark = false;
            cities[r.a].add(r);
            cities[r.b].add(r);
            roads.add(r);
        }
        
        boolean cm[] = new boolean[numcities];
        
        for(int i=0; i<numcities; i++){
            Collections.sort(cities[i]);
            cm[i] = false;
        }
        
        Collections.sort(roads,Collections.reverseOrder());


        //----------------find redundant roads        
        Queue<road> q = new PriorityQueue();
        for(road r : cities[0]){q.add(r);}
        cm[0]=true;
        int citycount = 1;
        while(citycount<numcities){
            road r = q.poll();
            if(false==cm[r.a]){
                cm[r.a] = true;
                for(road r2 : cities[r.a]){q.add(r2);}
                citycount++;
                r.mark = true;
            }
            if(false==cm[r.b]){
                cm[r.b] = true;
                for(road r2 : cities[r.b]){q.add(r2);}
                citycount++;
                r.mark = true;
            }
        }
        //----------------------remove redundant roads
        for(Iterator<road> i = roads.iterator();i.hasNext();){
            road r = i.next();
            if(false==r.mark){i.remove();}
        }
        for(int j=0; j<numcities; j++){
            for(Iterator<road> i = cities[j].iterator();i.hasNext();){
                road r = i.next();
                if(false==r.mark){i.remove();}
            }
        }
        //--------------------------
        calcnodes(roads.get(0),cities,-1);
        
        
        for(road r : roads){
            int cost = r.nodes * (numcities -r.nodes);
            BigInteger cost2 = new BigInteger(String.valueOf(cost));
            cost2 = cost2.shiftLeft(r.dist);
            answer = answer.add(cost2);
        }
        
        //-----------------------
        
        
        System.out.println(answer.toString(2));
    }
    
    static int calcnodes(road r, ArrayList<road>[] cities, int lastcity){
        if(r.a!=lastcity){
            if(1==cities[r.a].size()){
                r.nodes = 1;
            }else{
                r.nodes = 1;
                for(road r2:cities[r.a]){
                    if(r!=r2){
                        r.nodes += calcnodes(r2,cities,r.a);
                    }
                }
            }
        }
        if(r.b!=lastcity){
            if(1==cities[r.b].size()){
                r.nodes = 1;
            }else{
                r.nodes = 1;
                for(road r2:cities[r.b]){
                    if(r!=r2){
                        r.nodes += calcnodes(r2,cities,r.b);
                    }
                }
            }
        }
        return r.nodes;
    }

}

class road implements Comparable<road>{
    int a;
    int b;
    int dist;
    boolean mark;
    int nodes;
    //int journeys;
    
    @Override public int compareTo(road other){
        
        return (this.dist-other.dist);
    }
}