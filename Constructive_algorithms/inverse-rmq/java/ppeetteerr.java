import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) throws Exception{
        Reader sc = new Reader(false);
        int n = sc.nextInt();
        Map<Integer, Integer> occurs = new HashMap<Integer, Integer>();
        
        for(int i=0; i<2*n-1; i++){
            int k = sc.nextInt();
            if(occurs.containsKey(k)) occurs.put(k, occurs.get(k)+1);
            else occurs.put(k,1);
        }
        
        PriorityQueue<Pair> q = new PriorityQueue<Pair>(n, new Comparator<Pair>(){
            public int compare(Pair p1, Pair p2){
                if(p1.occur!=p2.occur) return p2.occur-p1.occur;
                else return p1.value-p2.value;
            }
        });
        
        for(Integer key:occurs.keySet()) q.add(new Pair(key, occurs.get(key)));
        
        int nPower=1;
        int nExp=0;
        while(nPower!=n){
            nPower*=2;
            nExp++; 
        }
        
        int[] tree = new int[2*n-1];
        
        Pair pair = q.poll();
        if(pair.occur!=nExp+1){
            System.out.println("NO");
            return;
        }
        
        tree[0] = pair.value;
        
        int power = 2;
        int exp = 1;
        int index = 1;
        
        while(power<=n){
            TreeSet<Integer> toAdd = new TreeSet<Integer>();
            for(int i=0; i<power/2; i++) {
                pair = q.poll();
                if(pair.occur != nExp+1-exp) {
                    System.out.println("NO");
                    return;
                }
                toAdd.add(pair.value);
            }
                
            for(int i=0; i<power; i++){
                if(index%2==1) {
                    tree[index] = tree[(index-1)>>1];
                } else {
                    Integer next = toAdd.higher(tree[index-1]);
                    if(next==null){
                        System.out.println("NO");
                        return;
                    } else {
                        tree[index] = next;
                        toAdd.remove(next);
                    }
                }
                
                index++;
            }
           
            power*=2;
            exp++;
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<2*n-1; i++) sb.append(tree[i]+" ");
        
        System.out.println("YES\n"+sb);
        
    }
}

class Pair {
    int value;
    int occur;
    
    public Pair(int value, int occur) {
        this.value = value;
        this.occur = occur;
    }
    
    public String toString(){
        return occur + " " + value;
    }
}

class Reader {
 BufferedReader br;
 StringTokenizer tokenizer;

 public Reader(boolean local) {
  int size = 1 << 12;
  try {
   br = new BufferedReader(local ? new FileReader("in.txt") : new InputStreamReader(System.in), size);
   tokenizer = new StringTokenizer(br.readLine());
  } catch (Exception e) {
   System.err.println("CHYBA PRI CITANI VSTUPU");
  }
 }

 public String nextString() {
  if (!tokenizer.hasMoreTokens()) {
   try {
    tokenizer = new StringTokenizer(br.readLine());
   } catch (IOException e) {
    e.printStackTrace();
   }
  }

  return tokenizer.nextToken();
 }

 public int nextInt() {
  if (!tokenizer.hasMoreTokens()) {
   try {
    tokenizer = new StringTokenizer(br.readLine());
   } catch (IOException e) {
    e.printStackTrace();
   }
  }

  return Integer.parseInt(tokenizer.nextToken());
 }

 public long nextLong() {
  if (!tokenizer.hasMoreTokens()) {
   try {
    tokenizer = new StringTokenizer(br.readLine());
   } catch (IOException e) {
    e.printStackTrace();
   }
  }

  return Long.parseLong(tokenizer.nextToken());
 }

 public int[] arrayInt(int n) {
  int[] array = new int[n];
  for (int i = 0; i < n; i++) {
   array[i] = nextInt();
  }

  return array;
 }

 public long[] arrayLong(int n) {
  long[] array = new long[n];
  for (int i = 0; i < n; i++) {
   array[i] = nextLong();
  }

  return array;
 }

 public List<Integer> listInt(int n) {
  List<Integer> list = new ArrayList<Integer>(n);
  for (int i = 0; i < n; i++) {
   list.add(nextInt());
  }

  return list;
 }

 public List<Long> listLong(int n) {
  List<Long> list = new ArrayList<Long>(n);
  for (int i = 0; i < n; i++) {
   list.add(nextLong());
  }

  return list;
 }

}