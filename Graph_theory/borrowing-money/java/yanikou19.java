import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;



public class Solution {
    
    public Solution()
    {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int M = scan.nextInt();
        ArrayList<Vertex> vertices = new ArrayList<>();
        for(int i = 0; i < N; i++){
            Vertex v = new Vertex(i,scan.nextInt());
            vertices.add(v);
        }
        
        for(int j = 0; j < M; j++){
            int roadS = scan.nextInt();
            int roadE = scan.nextInt();
            
            vertices.get(roadS-1).addNB(vertices.get(roadE-1));
            vertices.get(roadE-1).addNB(vertices.get(roadS-1));
        }
        
        // Try them all !
        long current = 0;
        int maxMoney = -2;
        int nbPaths = 0;
        while(current < Math.pow(2L,N))
        {
            int money = 0;
            long val = current;

            for(Vertex v : vertices)
            {
                int indV = v.index;
                for(Vertex n : v.neighbours)
                {
                    int ind2 = n.index;

                    if((val >> indV) % 2 == 1)
                    {
                        val = val & (~(1 << ind2));
                    }
                }
            }
            
            if(val < current)
            {
                current++;
                continue;
            }
            
            int index = 0;
            while(val > 0)
            {
                if(val % 2 == 1)
                {
                    money += vertices.get(index).money;
                }
                index = index+1;
                val = val >> 1;
            }
            if(money > maxMoney)
            {
                nbPaths = 1;
                maxMoney = money;
            }
            else if(money == maxMoney)
            {
                nbPaths++;
            }
            
            current++;
            
        }
        
        System.out.println(maxMoney+" "+nbPaths);
        
    }

    private class Vertex implements Comparable<Vertex> {
    
        public int money;
        public int index;
        public int weight;

        public LinkedList<Vertex> neighbours;

        public Vertex(int index, int money)
        {
            this.index = index;
            this.money = money;
            this.weight = 0;
            this.neighbours = new LinkedList<>();
        }

        public void addNB(Vertex vert)
        {
            this.neighbours.add(vert);
        }
        
        public int compareTo(Vertex v)
        {
            return (v.money - this.money)*100+(v.index-this.index);
        }
    } 
    
    public static void main(String[] args) {
        
        Solution s = new Solution();
        
        
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}