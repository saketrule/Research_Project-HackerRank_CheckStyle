import java.util.*;
import java.lang.*;
import java.io.*;
class AliceBobby
{    //*****************************************    VARIABLE DECLERATION SECTION   ********************************************************************;
         //static ArrayList<Integer> al;
         //static PriorityQueue<Integer> pq;
         //static TreeMap<Integer,Integer> tm;
         //static TreeSet<Integer> ts;
      //static ArrayList<ArrayList<Integer>>  graph;
         // static int[][] dp;
 //*****************************************    VARIABLE DECLERATION ENDS   ********************************************************************;

    //************************************************   MAIN FUNCTION  *************************************************************************; 
 public static void main(String[] args) 
 {   //al=new ArrayList<Integer>();
  //graph=new ArrayList<ArrayList<Integer>>();op
  //pq=new PriorityQueue<Integer>();
  //ts=new TreeSet<Integer>();
  //tm=new TreeMap<Integer,Integer>();
        //dp=new int[][];
        //System.out.println("");
   FastReader scan=new FastReader();
         int t=scan.nextInt();
         while(t--!=0)
         {
            int n=scan.nextInt();
            if(sieve(n)%2==0)System.out.println("Bob");
            else System.out.println("Alice");
         }
          

 }
 //***********************************************  MAIN FUNCTION ENDS   *********************************************************************;

 //**********************************************   AUXILIARY FUNCTIONS  **********************************************************************;
 static int sieve(int N) 
    {
        boolean[] isPrime=new boolean[N+1];
       for(int i = 0; i <= N;++i) 
       {
        isPrime[i] = true;
       }
        isPrime[0] = false;
        isPrime[1] = false;
        for(int i = 2; i * i <= N; ++i) 
         {
           if(isPrime[i] == true) 
           {
             for(int j = i * i; j <= N ;j += i)
                 isPrime[j] = false;
           }
         }int res=0;
         for(int i=0;i<=N;i++)
         {
            if(isPrime[i])res++;
         }
         return res;
    }
 //**********************************************   AUXILIARY FUNCTIONS  ENDS  **********************************************************************;

 //**********************************************   INPUT FUNCTIONS     ******************************************************************************;
       static class FastReader
    {   BufferedReader br;   StringTokenizer st;
        public FastReader()
        {  br = new BufferedReader(new  InputStreamReader(System.in)); }
        String next()
        {    while (st == null || !st.hasMoreElements())
            {    try
                {  st = new StringTokenizer(br.readLine()); }
                catch (IOException  e)
                {  e.printStackTrace(); }
            }
            return st.nextToken();
        }
        int nextInt()
        {   return Integer.parseInt(next()); }
        long nextLong()
        {   return Long.parseLong(next()); }
        double nextDouble()
        {  return Double.parseDouble(next()); }
        String nextLine()
        { String str = "";
            try
            {     str = br.readLine(); }
            catch (IOException e)
            {     e.printStackTrace(); }
            return str;
        }
    }
 //**********************************************   INPUT FUNCTIONS     ******************************************************************************;
}