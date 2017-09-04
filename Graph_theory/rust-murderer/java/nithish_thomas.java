import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class RustMurderer {
 static String INPUT="";
 public static void main(String[] args) throws IOException {
  Reader.init(INPUT.isEmpty()?System.in:new ByteArrayInputStream(INPUT.getBytes(StandardCharsets.UTF_8)));
  
  final StringBuilder out=new StringBuilder();
  
  final int T=Reader.nextInt();
  for(int counter=0;counter<T;counter++){
   final int N=Reader.nextInt();
   final int M=Reader.nextInt();
   final int[] numPathsAtCity=new int[N];
   final int paths[][]=new int [M][2];
   
   final int[] sol=new int[N];
   final Queue<Integer> queue=new LinkedList<Integer>();
   final Set<Integer> notVisitedCities=new HashSet<Integer>(N);
   
   
   for(int path=0;path<M;path++){
    final int x=paths[path][0]=Reader.nextInt()-1;
    final int y=paths[path][1]=Reader.nextInt()-1;
    numPathsAtCity[x]++;
    numPathsAtCity[y]++;
   }
   
   final int S=Reader.nextInt()-1;
   
   final int[][] cityConnectionsGraph=new int[N][];
   for (int i = 0; i < N; i++) {
    cityConnectionsGraph[i]=new int[numPathsAtCity[i]];
    notVisitedCities.add(i);
   }
   
   for(int path=0;path<M;path++){
    final int x=paths[path][0];
    final int y=paths[path][1];
    cityConnectionsGraph[x][--numPathsAtCity[x]]=y;
    cityConnectionsGraph[y][--numPathsAtCity[y]]=x;
   }
   //System.out.println(Arrays.deepToString(cityConnectionsGraph));
   
   
   //solving
   int cost =0;
   queue.add(S);
   sol[S]=cost;
   notVisitedCities.remove(S);
   while(!queue.isEmpty()){
    int currentCity=queue.poll();
    cost=sol[currentCity]+1;
    Iterator<Integer> itr = notVisitedCities.iterator();
    Set<Integer> pathsFromCurrentCity=new HashSet<Integer>(cityConnectionsGraph[currentCity].length);
    
    for(int i=0; i<cityConnectionsGraph[currentCity].length; i++){
     pathsFromCurrentCity.add(cityConnectionsGraph[currentCity][i]);
    }
    
    while(itr.hasNext()){
     int notVisitedCity = itr.next();
     boolean hasPath=pathsFromCurrentCity.contains(notVisitedCity);     
     if(hasPath) continue;
     sol[notVisitedCity]=cost;
     queue.add(notVisitedCity);
     itr.remove();
    }
   }
   for(int i=0; i<sol.length; i++){
    int x=sol[i];
    if(x==0)continue;
    out.append(x).append(" ");
   }
   out.replace(out.length()-1, out.length(), "\n");
   
  }
  System.out.println(out);
 }
 
 
 static public class Reader{
  static BufferedReader reader;
  static StringTokenizer tokenizer;

  /** call this method to initialize reader for InputStream */
  static void init(InputStream input) {
   reader = new BufferedReader(
     new InputStreamReader(input) );
   tokenizer = new StringTokenizer("");
  }

  /** get next word */
  static String next() throws IOException {
   while ( ! tokenizer.hasMoreTokens() ) {
    //TODO add check for eof if necessary
    tokenizer = new StringTokenizer(
      reader.readLine() );
   }
   return tokenizer.nextToken();
  }

  static int nextInt() throws IOException {
   return Integer.parseInt( next() );
  }

  static double nextDouble() throws IOException {
   return Double.parseDouble( next() );
  }
 }

}