import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Solution by recursion and memoization.
 * The state of the board is stored in a BitSet and the algorithm
 * tries to add an element to the upper-left corner
 * @author fra
 */
public final class Solution {
    public static int MOD = 1000000007;
    int n; //number of rows
    int m; //number of columns
    BitSet start;
    HashMap<BitSet,Long> ways = new HashMap<BitSet, Long>();
    

    /**
     * All arrangement that can be added from the upper-left corner.
     * (x is the upper left corner, r=right,l=left,d=down,u=up
     */
    static int pieces[][][] = new int[][][]{
            {{0,0,0,1},{0,1,2,2}},  //xrrd
            {{0,0,1,2},{0,1,1,1}},  //xrdd
            {{0,1,1,1},{0,0,1,2}},  //xdrr
            {{0,1,2,2},{0,0,0,1}},  //xddr
            {{0,1,2,2},{0,0,0,-1}}, //xddl
            {{0,1,1,1},{0,0,-1,-2}},//xdll
            {{1,0,0,0},{0,0,1,2}},  //dxrr
            {{2,1,0,0},{0,0,0,1}}   //ddxr
    };

    public Solution(int n, int m) {
        this.n = n;
        this.m = m;
        start = new BitSet();
        ways = new HashMap<BitSet, Long>();
    }

    /**
     * Sets the element at [row,col]
     * @param b the board
     * @param row the row
     * @param col the column
     */
    public void set(BitSet b, int row, int col){
        int idx = row*m + col;
        b.set(idx);
    }

    /**
     * Gets the element at [row,col]
     * @param b the board
     * @param row the row
     * @param col the column
     * @return if the element is occupied
     */
    public boolean get(BitSet b, int row, int col){
        int idx = row*m + col;
        return b.get(idx);
    }


    /**
     * Returns if piece p can be added to the board from the upper-left corner
     * @param b the board
     * @param p the piece to add
     * @return if the piece can be added
     */
    public boolean canAdd(BitSet b, int p){
        int idx = b.nextClearBit(0);
        int row = idx / m;
        int col = idx % m;
        for (int i = 0; i < pieces[p][0].length; i++) {
            int nr = row + pieces[p][0][i];
            int nc = col + pieces[p][1][i];
            if(nr <0 || nr >=n || nc<0 || nc >=m) return false; //out of bounds
            if(get(b, nr, nc)) return false;                    //already occupied
        }
        return true;
    }

    /**
     * Adds piece p to the board
     * @param b the board
     * @param p the piece to add
     * @return the board after addition
     */
    public BitSet add(BitSet b, int p){
        BitSet nb = (BitSet) b.clone();
        int idx = b.nextClearBit(0);
        int row = idx / m;
        int col = idx % m;
        for (int i = 0; i < 4; i++) {
            int nr = row + pieces[p][0][i];
            int nc = col + pieces[p][1][i];
            set(nb, nr, nc);
        }
        return nb;
    }

    /**
     * Returns if the board is full
     * @param b the board
     * @return if the board is complete
     */
    public boolean isComplete(BitSet b){
        return b.nextClearBit(0) == n*m;
    }

    /**
     * Returns the possible boards after adding one piece at the
     * next free upper-left corner
     * @param b the board
     * @return the next boards
     */
    public ArrayList<BitSet> getNextMove(BitSet b){
        ArrayList<BitSet> r = new ArrayList<BitSet>();
        for (int i = 0; i < pieces.length; i++) {
            if(canAdd(b, i)) r.add(add(b, i));
        }
        return r;
    }

    /**
     * Returns the number of way b can be completed
     * @param b a board
     * @return the number of ways to complete a board
     */
    public long getWays(BitSet b){
        if(ways.containsKey(b)){
            return ways.get(b);
        }
        long r =0;
        if(isComplete(b)) r = 1;
        for (BitSet nb : getNextMove(b)) {
            r += getWays(nb);
        }
        r %= MOD;
        ways.put(b, r);
        return r;
    }

    /**
     * Prints the board to a string
     * @param b the board
     * @return a string
     */
    public String toString(BitSet b){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(get(b, i, j)? '#': '.');
            }
            sb.append("\n");
        }
        return sb.toString();
    }



    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int ntest = 0; ntest < t; ntest++) {
            //if(ntest>0)return;
            int n = in.nextInt();
            int m = in.nextInt();

            Solution s = new Solution(n, m);
            for (int i = 0; i < n; i++) {
                String line = in.next();
                for (int j = 0; j < m; j++) {
                    if(line.charAt(j)=='#') s.set(s.start,i,j);
                    
                }
            }
            System.out.println(s.getWays(s.start));
        }
    }
}