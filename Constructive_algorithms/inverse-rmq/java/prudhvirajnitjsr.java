import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    
    static class T implements Comparable<T>{
        int n,c;
        T(int n,int c){
            this.n = n;
            this.c = c;
        }

        @Override
        public int compareTo(T o) {            
            return o.n-this.n;
        }
        
        @Override
        public String toString(){
            return "("+n+" , "+c+")";
        }
        
    }
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        InputReader ir = new InputReader();
        int n = ir.readInt();
        int A[] = ir.readIntArray(2*n-1);
        Arrays.sort(A);
        TreeSet<Integer> ts = new TreeSet();
        HashMap<Integer,Integer> hm = new HashMap();
        for(int i=0;i<A.length;i++){
            ts.add(A[i]);
            if(!hm.containsKey(A[i])){
                hm.put(A[i], 0);
            }
            hm.put(A[i], hm.get(A[i])+1);
        }
        if(ts.size()!=n){
            System.out.println("NO");
            return;
        }        
        T t[] = new T[hm.size()];
        int ind = 0;
        Set<Integer> key = hm.keySet();
        for(int k : key){
            t[ind++] = new T(k,hm.get(k));
        }
        int SIZE = n;
        int levels = (int)(Math.log(n)/Math.log(2)) + 1;
        TreeSet<Integer> l[] = new TreeSet[levels];
        for(int i=0;i<levels;i++){
            l[i] = new TreeSet();
        }
        for(int i=0;i<t.length;i++){
            T temp = t[i];
            for(int j=levels-1;j>=levels-temp.c;j--){
                l[j].add(temp.n);
            }            
        }
//        for(int i=0;i<levels;i++){
//            System.out.println(l[i]);
//        }
        int ans[] = new int[2*n-1];
        ind = 0;
        int prev[] = new int[1];
        prev[0] = l[0].first();
        //System.out.println("Temp "+Arrays.toString(prev));
        ans[ind++] = prev[0];
        for(int i=1;i<levels;i++){
            int temp[] = new int[(int)Math.pow(2, i)];
            int in = 0;
            for(int j=0;j<prev.length;j++){
                temp[in] = prev[j];
                l[i].remove(prev[j]);
                in+=2;
            }
            in = 1;
            for(int x : l[i]){
                temp[in] = x;
                in+=2;
            }
            for(int j=0;j<temp.length;j++){
                ans[ind++] = temp[j];
            }
            //System.out.println("Temp "+Arrays.toString(temp));
            prev = temp;
        }
        System.out.println("YES");
        for(int i=0;i<ans.length;i++){
            System.out.print(ans[i]+" ");
        }
        System.out.println("");
    }
    final static class InputReader {

        byte[] buffer = new byte[8192];
        int offset = 0;
        int bufferSize = 0;
        final InputStream in = System.in;

        public int readInt() throws IOException {
            int number = 0;
            int s = 1;
            if (offset == bufferSize) {
                offset = 0;
                bufferSize = in.read(buffer);
            }
            if (bufferSize == -1) {
                throw new IOException("No new bytes");
            }
            for (; buffer[offset] < 0x30 || buffer[offset] == '-'; ++offset) {
                if (buffer[offset] == '-') {
                    s = -1;
                }
                if (offset == bufferSize - 1) {
                    offset = -1;
                    bufferSize = in.read(buffer);
                }
            }
            for (; offset < bufferSize && buffer[offset] > 0x2f; ++offset) {
                number = (number << 3) + (number << 1) + buffer[offset] - 0x30;
                if (offset == bufferSize - 1) {
                    offset = -1;
                    bufferSize = in.read(buffer);
                }
            }
            ++offset;
            return number * s;
        }

        public int[] readIntArray(int n) throws IOException {
            int[] ar = new int[n];
            for (int i = 0; i < n; i++) {
                ar[i] = readInt();
            }

            return ar;
        }

        public int[] readIntArray1(int n) throws IOException {
            int[] ar = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                ar[i] = readInt();
            }

            return ar;
        }

        public long readLong() throws IOException {
            long res = 0;
            int s = 1;
            if (offset == bufferSize) {
                offset = 0;
                bufferSize = in.read(buffer);
            }
            for (; buffer[offset] < 0x30 || buffer[offset] == '-'; ++offset) {
                if (buffer[offset] == '-') {
                    s = -1;
                }
                if (offset == bufferSize - 1) {
                    offset = -1;
                    bufferSize = in.read(buffer);
                }
            }
            for (; offset < bufferSize && buffer[offset] > 0x2f; ++offset) {
                res = (res << 3) + (res << 1) + buffer[offset] - 0x30;
                if (offset == bufferSize - 1) {
                    offset = -1;
                    bufferSize = in.read(buffer);
                }
            }
            ++offset;
            if (s == -1) {
                res = -res;
            }
            return res;
        }

        public long[] readLongArray(int n) throws IOException {
            long[] ar = new long[n];

            for (int i = 0; i < n; i++) {
                ar[i] = readLong();
            }

            return ar;
        }

        public String read() throws IOException {
            StringBuilder sb = new StringBuilder();
            if (offset == bufferSize) {
                offset = 0;
                bufferSize = in.read(buffer);
            }

            if (bufferSize == -1 || bufferSize == 0) {
                throw new IOException("No new bytes");
            }

            for (;
                    buffer[offset] == ' ' || buffer[offset] == '\t' || buffer[offset]
                    == '\n' || buffer[offset] == '\r'; ++offset) {
                if (offset == bufferSize - 1) {
                    offset = -1;
                    bufferSize = in.read(buffer);
                }
            }
            for (; offset < bufferSize; ++offset) {
                if (buffer[offset] == ' ' || buffer[offset] == '\t'
                        || buffer[offset] == '\n' || buffer[offset] == '\r') {
                    break;
                }
                if (Character.isValidCodePoint(buffer[offset])) {
                    sb.appendCodePoint(buffer[offset]);
                }
                if (offset == bufferSize - 1) {
                    offset = -1;
                    bufferSize = in.read(buffer);
                }
            }
            return sb.toString();
        }

        public String read(int n) throws IOException {
            StringBuilder sb = new StringBuilder(n);
            if (offset == bufferSize) {
                offset = 0;
                bufferSize = in.read(buffer);
            }

            if (bufferSize == -1 || bufferSize == 0) {
                throw new IOException("No new bytes");
            }

            for (;
                    buffer[offset] == ' ' || buffer[offset] == '\t' || buffer[offset]
                    == '\n' || buffer[offset] == '\r'; ++offset) {
                if (offset == bufferSize - 1) {
                    offset = -1;
                    bufferSize = in.read(buffer);
                }
            }
            for (int i = 0; offset < bufferSize && i < n; ++offset) {
                if (buffer[offset] == ' ' || buffer[offset] == '\t'
                        || buffer[offset] == '\n' || buffer[offset] == '\r') {
                    break;
                }
                if (Character.isValidCodePoint(buffer[offset])) {
                    sb.appendCodePoint(buffer[offset]);
                }
                if (offset == bufferSize - 1) {
                    offset = -1;
                    bufferSize = in.read(buffer);
                }
            }
            return sb.toString();
        }
    }
}