import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int lines = sc.nextInt();
        for(int i=0; i<lines; i++) {
            char command = sc.next().charAt(0);
            switch(command) {
                case 'A':
                    int a = sc.nextInt();
                    createSet(a);
                    break;
                case 'B':
                    int b1 = sc.nextInt();
                    int b2 = sc.nextInt();
                    connectSet(b1, b2);
                    break;
                case 'C':
                    int c = sc.nextInt();
                    combineSet(c);
                    break;
            }
        }
        
        System.out.print(findMax());
    }
    
    public static List<nodeSet> space = new ArrayList<nodeSet>();
    
    public static void createSet(int a) {
        nodeSet ns = new nodeSet(a, space.size());
        space.add(ns);
    }
    
    public static void connectSet(int b1, int b2){
        nodeSet ns1 = space.get(b1-1);
        nodeSet ns2 = space.get(b2-1);
        ns1.connect(ns2);
        ns2.connect(ns1);
    }
    
    public static void combineSet(int c) {

    }
    
    public static int findMax() {
        return 0;
    }
}

class nodeSet {
    int independentNode;
    int K;
    List<nodeSet> links = new ArrayList<nodeSet>();
    public nodeSet(int i, int K) {
        this.independentNode = i;
        this.K = K;
    }
    public void connect(nodeSet ns) {
        links.add(ns);
    }
    public boolean[] allLinked(List<nodeSet> space) {
        boolean[] result = new int[space.size()];
        
    }
}