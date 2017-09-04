import java.util.*; 
import java.math.*;
import java.io.*;
import java.text.DecimalFormat;
import java.math.BigInteger;
public class Solution{
    //static int d=20;
    static long mod=1000000007 ;
    //static HashMap<String,Integer> hm=new HashMap<>(); 
    static ArrayList<ArrayList<Integer>> arr;
    static int size;
    static long[][] fac;
    static long[][] dp;
    static int num,max;
    static int ans=0;
    static HashMap<Integer,Integer> hm;
    public static void main(String[] args)  throws IOException {
        boolean online =false;
        String fileName = "C-large";
        PrintWriter out;
        if (online) {
            s.init(new FileInputStream(new File(fileName + ".in")));
             out= new PrintWriter(new FileWriter(fileName + "out.txt"));
        }
         else {
            s.init(System.in);   
        
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        }
        
        int n=s.ni();
        int k=s.ni();
        long[] c=new long[n];
        for(int i=0;i<n;i++)
            c[i]=s.nl();
        bulb[] b=new bulb[n];
        for(int i=0;i<n;i++){
            long add=c[i];
            int x=1;
            
            for(int j=Math.max(0,i-k);j<=Math.min(n-1,i+k);j++){
                if(i!=j){
                    add-=c[j];
                    x++;
                }
            }
            b[i]=new bulb(c[i],add,i,x);

        }
        Arrays.sort(b);
        long ans=0;
        int ct=0;
        while(ct<n){
            int id=0;
            while(id<n && b[id].on==false)
                id++;
            if(id>=n)
                break;
            ans+=b[id].cost;
            b[id].on=false;
            ct+=b[id].count;
            for(int j=Math.max(0,id-k);j<=Math.min(n-1,id+k);j++){
                if(j==id)
                    continue;
                if(b[j].on==true){
                    b[j].on=false;
                    for(int u=Math.max(0,j-k);u<=Math.min(n-1,j+k);u++){
                        b[u].ecost+=b[j].cost;
                        b[u].count--;
                    }
                }
            }
            Arrays.sort(b);


        }
        out.println(ans);

        out.close();
        
    }
 
    static class bulb implements Comparable<bulb>{
        long cost,ecost;
        int id;
        boolean on;
        int count;
        public bulb(long x,long y,int idd,int c){
            cost=x;
            ecost=y;
            id=idd;
            count=c;
            on=true;
        }
        public int compareTo(bulb o){
            return (int)(Math.signum(ecost-o.ecost));
        }
    }
 
    
    public static class s {
        static BufferedReader reader;
        static StringTokenizer tokenizer;
    
        /** call this method to initialize reader for InputStream */
        static void init(InputStream input) {
            reader = new BufferedReader(
                        new InputStreamReader(input) );
            tokenizer = new StringTokenizer("");
        }   
    
        /** get next word */
        static String ns() throws IOException {
            while ( ! tokenizer.hasMoreTokens() ) {
                //TODO add check for eof if necessary
                tokenizer = new StringTokenizer(
                    reader.readLine() );
            }
            return tokenizer.nextToken();
        }
    
        static int ni() throws IOException {
            return Integer.parseInt( ns() );
        }
    
        static double nd() throws IOException {
            return Double.parseDouble( ns() );
        }
        static long nl() throws IOException {
            return Long.parseLong( ns() );
        }
        
    }
}       