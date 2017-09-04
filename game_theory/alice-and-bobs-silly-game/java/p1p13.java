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
            int[] prime=new int[100001];
            for(int i=2;i*i<=100000;i++){
                if(prime[i]==0){
                    for(int j=2*i;j<=100000;j+=i){
                        prime[j]=1;
                    }
                }
            }
            int t=s.ni();
            while(t--!=0){
                int  n=s.ni();
                int ans=0;
                for(int i=2;i<=n;i++){
                    if(prime[i]==0)
                        ans++;
                }
                if(ans%2==0)
                    out.println("Bob");
                else
                    out.println("Alice");
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
            int id,p,g;
            public name(int  x,int y,int z){
                id=x;
                p=y;
                g=z;
            }
            public int compareTo(name o){
               
                    return id-o.id;
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