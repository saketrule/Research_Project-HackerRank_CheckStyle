import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class client{
 private int a;
 private int p;
 public int getA() {
  return a;
 }
 public void setA(int a) {
  this.a = a;
 }
 public int getP() {
  return p;
 }
 public void setP(int p) {
  this.p = p;
 }
 
}

class house{
 int x;
 int y;
 public int getX() {
  return x;
 }
 public void setX(int x) {
  this.x = x;
 }
 public int getY() {
  return y;
 }
 public void setY(int y) {
  this.y = y;
 }
 
}

public class WC5 {
 InputStream is;
 PrintWriter out;
 String INPUT = "";
// X j > A i  and Y i  <= P i
 void solve() {
  int clients = ni();
  int housess = ni();
  List<client> cl = new ArrayList<client>();
  List<client> clTemp = new ArrayList<client>();
  List<house> hs = new ArrayList<house>();
  for(int i=0;i<clients;i++){
   client c = new client();
   c.setA(ni());
   c.setP(ni());
   cl.add(c);
   clTemp.add(c);
  }
  for(int i=0;i<housess;i++){
   house h = new house();
   h.setX(ni());
   h.setY(ni());
   hs.add(h);
  }
  
  Map<house,List<client>> mp = new HashMap<house,List<client>>();
  Map<client,List<house>> mpc = new HashMap<client,List<house>>();
  
  for(house h:hs){
   for(client c:cl){
    if(h.getX() > c.getA() && h.getY() <= c.getP()){
     if(mp.containsKey(h)){
          mp.get(h).add(c);
     }else{
      List<client> ac = new ArrayList<client>();
      ac.add(c);
      mp.put(h, ac);
     }
    }
   }
  }
  
  
     for(client c:cl){
    for(house h:hs){
    if(h.getX() > c.getA() && h.getY() <= c.getP()){
     if(mpc.containsKey(c)){
          mpc.get(c).add(h);
     }else{
      List<house> acb = new ArrayList<house>();
      acb.add(h);
      mpc.put(c, acb);
     }
    }
   }
  }

  int total = 0;
  Iterator<Map.Entry<client,List<house>>> iterc = mpc.entrySet().iterator();
  while (iterc.hasNext()) {
   Map.Entry<client,List<house>> entry = iterc.next();
   if(entry.getValue()!=null && entry.getValue().size()==1){
    total=total+1;
    mp.put(entry.getValue().get(0), null);
   }
  }
  
  
  while(true){
   Iterator<Map.Entry<house,List<client>>> iter = mp.entrySet().iterator();
   boolean b = false;
   while (iter.hasNext()) {
       Map.Entry<house,List<client>> entry = iter.next();
       if(!clTemp.isEmpty()){
       if(entry.getValue()!=null && entry.getValue().size()>0){
        if(entry.getValue().size()==1 && clTemp.contains(entry.getValue())){
         total=total+1;
         clTemp.remove(entry.getValue());
         entry.setValue(null);
         iter.remove(); 
         b=true;
        }
        if(entry.getValue().size()>1){
         List<client> temp;
         temp=entry.getValue();
         boolean exst = false;
         client tem = null;
         for(client f : temp){
          if(clTemp.contains(f)){
           exst = true;
           tem= f;
           break;
          }
         }
         if(exst){
          clTemp.remove(tem);
          iter.remove();
          total=total+1;
          b=true;
         }
        }
       
         }else{
          iter.remove();
         }
       }
   }
   
   if(!b){
    break;
   }
  }
  out.print(total);
  
 }




 void run() throws Exception {
  is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(
    INPUT.getBytes());
  out = new PrintWriter(System.out);
  long s = System.currentTimeMillis();
  solve();
  out.flush();
 }

 public static void main(String[] args) throws Exception {
  new WC5().run();
 }

 private byte[] inbuf = new byte[1024];
 public int lenbuf = 0, ptrbuf = 0;

 private int readByte() {
  if (lenbuf == -1)
   throw new InputMismatchException();
  if (ptrbuf >= lenbuf) {
   ptrbuf = 0;
   try {
    lenbuf = is.read(inbuf);
   } catch (IOException e) {
    throw new InputMismatchException();
   }
   if (lenbuf <= 0)
    return -1;
  }
  return inbuf[ptrbuf++];
 }

 private boolean isSpaceChar(int c) {
  return !(c >= 33 && c <= 126);
 }

 private int skip() {
  int b;
  while ((b = readByte()) != -1 && isSpaceChar(b))
   ;
  return b;
 }

 private double nd() {
  return Double.parseDouble(ns());
 }

 private char nc() {
  return (char) skip();
 }

 private String ns() {
  int b = skip();
  StringBuilder sb = new StringBuilder();
  while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b !=
         // ' ')
   sb.appendCodePoint(b);
   b = readByte();
  }
  return sb.toString();
 }

 private char[] ns(int n) {
  char[] buf = new char[n];
  int b = skip(), p = 0;
  while (p < n && !(isSpaceChar(b))) {
   buf[p++] = (char) b;
   b = readByte();
  }
  return n == p ? buf : Arrays.copyOf(buf, p);
 }

 private char[][] nm(int n, int m) {
  char[][] map = new char[n][];
  for (int i = 0; i < n; i++)
   map[i] = ns(m);
  return map;
 }

 private int[] na(int n) {
  int[] a = new int[n];
  for (int i = 0; i < n; i++)
   a[i] = ni();
  return a;
 }

 private int ni() {
  int num = 0, b;
  boolean minus = false;
  while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
   ;
  if (b == '-') {
   minus = true;
   b = readByte();
  }

  while (true) {
   if (b >= '0' && b <= '9') {
    num = num * 10 + (b - '0');
   } else {
    return minus ? -num : num;
   }
   b = readByte();
  }
 }

 private long nl() {
  long num = 0; 
  int b;
  boolean minus = false;
  while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
   ;
  if (b == '-') {
   minus = true;
   b = readByte();
  }

  while (true) {
   if (b >= '0' && b <= '9') {
    num = num * 10 + (b - '0');
   } else {
    return minus ? -num : num;
   }
   b = readByte();
  }
 }

 private static void tr(Object... o) {
  System.out.println(Arrays.deepToString(o));
 }
}