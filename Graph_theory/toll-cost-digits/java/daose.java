import java.util.*;
import java.awt.Point;

public class Solution {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int[] report = new int[10];

        Node[] map = new Node[in.nextInt()];
        for(int i = 0; i < map.length; i++){
            map[i] = new Node(i);
        }

        int roads = in.nextInt();
        //TODO:: use arraylist for multiple tolls
        HashMap<Point, Integer> tollMap = new HashMap<Point, Integer>();

        for(int i = 0; i < roads; i++){
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int toll = in.nextInt();
            map[from].children.add(map[to]);
            map[to].children.add(map[from]);
            tollMap.put(new Point(from, to), toll);
            tollMap.put(new Point(to, from), 1000-toll);
        }

        for(Node root : map){
            Stack<Frame> s = new Stack<Frame>();
            for(Node to : root.children){
                int toll = tollMap.get(new Point(root.key, to.key));
                s.push(new Frame(root, to, toll));
            }

            while(!s.empty()){
                Frame f = s.pop();
                Node from = f.from;
                Node cur = f.to;
                if(cur == root) continue;

                int toll = f.price;
                report[toll % 10]++;

                for(Node to : cur.children){
                    if(to == from) continue;
                    toll += tollMap.get(new Point(cur.key, to.key));
                    s.push(new Frame(cur, to, toll));
                }
            }
        }

        for(int freq : report){
            System.out.println(freq);
        }
    }

    public static class Frame {
        public Node from;
        public Node to;
        public int price;
        public Frame(Node from, Node to, int price){
            this.from = from;
            this.to = to;
            this.price = price;
        }

        @Override
        public String toString(){
            return "From: " + from.key + " To: " + to.key + " Toll: " + price;
        }
    }

    public static class Node {
        public int key;
        public ArrayList<Node> children;

        public Node(int key) {
            this.key = key;
            children = new ArrayList<Node>();
        }

        public void add(Node node){
            children.add(node);
        }
    }
}