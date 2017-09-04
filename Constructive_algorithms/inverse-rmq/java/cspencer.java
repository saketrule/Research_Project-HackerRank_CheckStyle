import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    // round up to next power of 2 - 1
    static int roundUp(int num) {
        while (num != (num | (num >>> 1))) {
            num = num | (num >>> 1);
        }
        return num;
    }
    
    static int leftMost(int parent, int n) {
        int size = 2*n - 1;
        int left = 2*parent + 1;
        while (left < size) {
            parent = left;
            left = 2*parent + 1;
        }
        return parent;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Map<Integer, Integer> values = new TreeMap<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < 2*n - 1; ++i) {
            int num = in.nextInt();
            queue.add(num);
            int count = values.containsKey(num) ? values.get(num) : 0;
            values.put(num, count + 1);
        }
        
        int[] result = new int[2*n - 1];
        int[] depth = new int[2*n - 1];
        int maxDepth = 0;
        for (int i = 0; i < result.length; ++i) {
            result[i] = Integer.MAX_VALUE;
            depth[i] = depth[(i-1)/2] + 1; // root has depth one
            if (depth[i] > maxDepth) 
                maxDepth = depth[i];
        }
        
        List<PriorityQueue<Integer>> depthQueues = new ArrayList<>(maxDepth);
        for (int i = 0; i < maxDepth; ++i) {
            depthQueues.add(new PriorityQueue<Integer>());
        }
        
        if (values.keySet().size() != n) {
            // invalid
            System.out.println("NO");
        }
        else {
            int firstLeafDepth = depth[depth.length - n];
            depthQueues.get(firstLeafDepth - 1).add(depth.length - n);
            if (firstLeafDepth != maxDepth) {
                int lowest = roundUp(depth.length - n);
                //System.out.println("lowest: " + lowest);
                depthQueues.get(maxDepth - 1).add(lowest);
            }
                //System.out.println("firstLeafDepth: " + firstLeafDepth);
            
            //while (!queue.isEmpty()) {
            //    int value = queue.peek();
            boolean valid = true;
            for (Integer value : values.keySet()) {
                //System.out.println("value " + value);
                int targetDepth = values.get(value);
                
                if (targetDepth > maxDepth) {
                    valid = false;
                    break;
                }
                
                PriorityQueue<Integer> depthQueue = depthQueues.get(targetDepth - 1);
                if (depthQueue.isEmpty()) {
                    // bail out, failure, not enough of this value to make a path
                   // System.out.println("bailout1");
                    valid = false;
                    break;
                }
                int leaf = depthQueue.remove();
                int node = leaf;
                while (true) {
                    int parent = (node - 1) / 2;
                    
                    if (result[node] > value)
                        result[node] = value;
                    else
                        break;
                    
                    depth[node] = 0;
                    
                    // if even, this is a left node, so put right node in depthQueues
                    if ((node & 1) == 1 && node < depth.length - 1) {
                        int right = node + 1;
                        if (depth[right] != 0) {
                            int leftMost = leftMost(right, n);
                            int rightDepth = depth[leftMost] - depth[parent];
                            //depth[right] = rightDepth;
                            
                            //int newNode = 
                            depthQueues.get(rightDepth - 1).add(leftMost);
                            //System.out.println("rightDepth " + rightDepth + " value " + value + " leftmost "
                            //                   + leftMost(right, n));
                        }
                    }
                    // if this is the right node
                    else if ((node & 1) == 0 && node > 0) {
                        int left = node - 1;
                        if (depth[left] != 0) {
                            int leftMost = leftMost(left, n);
                            int leftDepth = depth[leftMost] - depth[parent];
                            //depth[left] = leftDepth;
                            depthQueues.get(leftDepth - 1).add(leftMost);
                        }
                    }
                    if (node == 0) 
                        break;
                    node = parent;
                }
            }
            
            
            
            
            /*
            //List<Integer> extras = new ArrayList<>();
            //List<Integer> inners = new ArrayList<>();
            
            for (Integer value : values.keySet()) {
                if (values.get(value) > 1) {
                    int newAmount = values.get(value) - 1;
                    for (int i = 0; i < newAmount; ++i) {
                        //inners.add(value);
                    }
                }
                else {
                    //extras.add(value);
                }
            }
            //Collections.sort(inners);
            //Collections.sort(extras);
            //inners.addAll(extras);
            
            
            boolean valid = true;
            int result_i = -1;
            //for (int i = 0; i < inners.size(); ++i) {
            outer:
            while (!queue.isEmpty()) {
                //int value = inners.remove(i);
                int value = queue.peek();
                
                ++result_i;
                while (result[result_i] != Integer.MAX_VALUE) {
                    ++result_i;
                }
                
                // trickle down the left nodes
                int node = result_i;
                while (node < result.length) {
                    int next = queue.remove();
                    if (next == value) {
                        result[node] = next;
                        node = 2*node + 1;
                    }
                    else {
                        valid = false;
                        break outer;
                    }
                }
            }
            */
            
            if (valid)
            {
                System.out.println("YES");
                for (int i = 0; i < result.length; ++i) {
                    System.out.print(result[i] + " ");
                }
                System.out.println();
            }
            else {
                System.out.println("NO");
            }
        }
        
        
    }
}