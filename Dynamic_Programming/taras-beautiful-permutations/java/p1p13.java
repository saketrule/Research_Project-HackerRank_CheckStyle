import java.util.*; 
    import java.math.*;
    import java.io.*;
    import java.text.DecimalFormat;
    import java.math.BigInteger;
    public class Main{
        //static int d=20;
        static long mod=1000000007 ;
        static HashMap<String,Double> hm=new HashMap<>(); 
        static ArrayList<ArrayList<Integer>> arr;
        static long[][] color;
        static int[] a;
        static int[] vis,num;
        static double val;
        public static void main(String[] args)  throws IOException {
            boolean online =false;
            String fileName = "C-large-practice";
            PrintWriter out;
            if (online) {
                s.init(new FileInputStream(new File(fileName + ".in")));
                 out= new PrintWriter(new FileWriter(fileName + "out.txt"));
            }
             else {
                s.init(System.in);   
            
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
            }
            
            //out.println(ans);
           long[] fac=new long[2005];
           fac[0]=1;
           for(int i=1;i<=2004;i++)
            fac[i]=(fac[i-1]*i)%mod;
            long[][] dp=new long[2005][2005];
            for(int i=0;i<=2004;i++){
                for(int j=0;j<=i;j++){
                    if(j==0 || j==i)
                        dp[i][j]=1;
                    else
                        dp[i][j]=(dp[i-1][j-1]+dp[i-1][j])%mod;
                }
            }
           int q=s.ni();
           while(q--!=0){
            int n=s.ni();
            int[] a=new int[n];
            ArrayList<Integer> arr=new ArrayList<>();
            HashSet<Integer> hs=new HashSet<>();
            for(int i=0;i<n;i++){
                a[i]=s.ni();
                if(hs.contains(a[i]))
                    arr.add(a[i]);
                else
                    hs.add(a[i]);
            }
            if(arr.size()*2==n){
                if(n==1)
                    out.println(0);
                else
                    out.println(2);
                continue;
            }

            int m=n-arr.size()*2;
            
            //System.out.println(hs);
            long ans=fac[m];
            
            for(int i=1;i<=arr.size();i++){

                ans=(ans*dp[m+2][2])%mod;
                m+=2;
            }
            out.println(ans);

           }
           
            out.close();

        }
   
        public static long pow2(long a,long b){
                long ans=1;
                while(b>0){
                    if(b%2==1)
                        ans=(a*ans);
                    a=(a*a);
                    b/=2;
                }
                return  ans;
        }
        public static double pow3(double a,long b){
                double ans=1;
                while(b>0){
                    if(b%2==1)
                        ans=(a*ans);
                    a=(a*a);
                    b/=2;
                }
                return  ans;
        }

         static class name implements Comparable<name> {
            int l,r,id,sz;
            public name(int  x,int y,int z){
                l=x;
                r=y;
                id=z;
                sz=r-l;
            }
            public int compareTo(name o){
               
                    return r-o.r;
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