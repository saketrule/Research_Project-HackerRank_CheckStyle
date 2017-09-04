import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Node
{
    private int index;
    private ArrayList<Path> cesty;
    public boolean[] vzdalenostiOdNuly;
    public int getIndex(){return index;}    
        
    public Node(int index)
    {
        this.index = index;
        cesty = new ArrayList<>();
        vzdalenostiOdNuly = new boolean[10];
    }
    
    public void addPath(Path cesta)
    {
        cesty.add(cesta);
    }
    
    public void hledamKNule(int dosazenaCena)
    {
        dosazenaCena = dosazenaCena % 10;
        if (!vzdalenostiOdNuly[dosazenaCena])
        {
            vzdalenostiOdNuly[dosazenaCena] = true;
            for (int i = 0; i < cesty.size(); i++)
            {
                cesty.get(i).buduCestovate(this, dosazenaCena);
            }
            
        }
    }
    
}



class Path
{
    private Node odkud;
    private Node kam;
    private int cena;
    
    public Path(Node z, Node tam, int cenaTam)
    {
        odkud = z;
        kam = tam;
        cena = cenaTam;
    }
    
    public void buduCestovate (Node volajici, int cenaDoted)
    {
        if (volajici == odkud){kam.hledamKNule(cena+cenaDoted);}
        else {odkud.hledamKNule(10-cena+cenaDoted);}
    }
    
}


public class Solution 
{

    
    
    public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int e = in.nextInt();
        
        Node[] uzly = new Node[n];
        Path[] cesty = new Path[e];
        
        for(int i = 0; i < e; i++)
        {
            int x = in.nextInt()-1;
            int y = in.nextInt()-1;
            int r = in.nextInt()%10;
            
            if (uzly[x] == null){uzly[x] = new Node(x);}
            if (uzly[y] == null){uzly[y] = new Node(y);}
            cesty[i] = new Path(uzly[x], uzly[y], r);
            uzly[x].addPath(cesty[i]);
            uzly[y].addPath(cesty[i]);
        }
        
        uzly[0].hledamKNule(0);
        
        int[] vysledky = new int[10];
        boolean[] pattern;
        
        for (int i = 0; i < n-1; i++)
        {
            for (int j = i+1; j < n; j++)
            {
                pattern = new boolean[10];
                for (int k = 0; k < 10; k++)
                {
                    for (int l = 0; l < 10; l++)
                    {
                        if (uzly[i].vzdalenostiOdNuly[k] && uzly[j].vzdalenostiOdNuly[l])
                        {
                            pattern[(10+k-l)%10] = true;
                            pattern[(10+l-k)%10] = true;
                        }
                    }
                }
                for (int z = 0; z < 10; z++)
                {
                    if (pattern[z]){vysledky[z]++;}
                }
            }
        }
        
        for (int i = 0; i < 10; i++)
        {
            System.out.println(vysledky[i]);    
        }
        
    }
}