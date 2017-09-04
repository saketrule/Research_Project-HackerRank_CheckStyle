import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    static class Pair{
        int value;
        int frequency;
        
        public Pair(int value, int frequency){
            this.value = value;
            this.frequency = frequency;
        }
        
        @Override
        public boolean equals(Object o) {

            if (o == this) return true;
            if (!(o instanceof Pair)) {
                return false;
            }

            Pair pair = (Pair) o;

            return pair.value == this.value;
        }
        
        @Override
        public int hashCode(){
            return value;
        }
    }
    
    static void printList(ArrayList<Pair> l){
        for (Pair p : l){
            System.out.println(p.value + " " + p.frequency);
        }
    }
    
    static ArrayList<Pair> buildFrequencyList(int[] elems){
        ArrayList<Pair> list = new ArrayList<Pair>();
        
        int index = 0;
        int counter = 1;
        Pair pair;
        while (index < elems.length - 1){
            if (elems[index] == elems[index + 1]){
                counter++;
            } else {
                pair = new Pair(elems[index], counter);
                list.add(pair);
                counter = 1;
            }
            index++;
        }    
        pair = new Pair(elems[index], counter);
        list.add(pair);
        
        return list;
    }
    
    static Pair pickMinElementOfFreq(ArrayList<Pair> list, int frequency){
        for (int i = 0; i < list.size(); i++){
            Pair p = list.get(i);
            if (p.frequency >= frequency){
                p.frequency -= frequency;
                return p;
            }
        }
        
        return null;
    }
    
    static boolean comparePairs(Pair p1 , Pair p2){
        if (p2.value >= p1.value){
            return true;
        }
        
        return false;
    }
    
    static ArrayList<ArrayList<Pair>> checkFrequencies(ArrayList<Pair> list, int depth){
        ArrayList<ArrayList<Pair>> lists = new ArrayList<ArrayList<Pair>>();
        
        Pair p1 = pickMinElementOfFreq(list, depth);
        Pair p2 = pickMinElementOfFreq(list, depth - 1);
        
        if (p1 != null && p2 != null){
            ArrayList<Pair> root = new ArrayList<Pair>();
            root.add(p1);
            lists.add(root);
            
            if (comparePairs(p1, p2)){
                ArrayList<Pair> children = new ArrayList<Pair>();
                children.add(p1);
                children.add(p2);
                lists.add(children);
                
                if (checkFrequenciesHelper(list, children, lists, depth - 2)){
                    return lists;
                }
            }
        }
        
        return null;
    }
    
    static boolean checkFrequenciesHelper(ArrayList<Pair> list, ArrayList<Pair> parents, 
                                          ArrayList<ArrayList<Pair>> lists, int depth){
        if (depth == 0){
            if (checkUniqueness(parents)){
                return true;
            }
            return false;
        }
        
        ArrayList<Pair> children = new ArrayList<Pair>();
        
        for (Pair parent : parents){
            //System.out.println("Parent is: " + parent.value);
            children.add(parent);
            
            //printList(list);
            //System.out.println("Depth: " + depth);
            Pair child = pickMinElementOfFreq(list, depth);
            //System.out.println("Child is: " + child.value);
            
            if (parent != null && child != null){
                if (comparePairs(parent, child)){
                    children.add(child);
                } else {
                    //System.out.println("Catch1");
                    return false;
                }
            } else {
                //System.out.println("Catch2");
                return false;
            }
        }
        lists.add(children);
        
        return checkFrequenciesHelper(list, children, lists, depth - 1);
    }
    
    static boolean checkUniqueness(ArrayList<Pair> list){
        HashSet<Pair> set = new HashSet<Pair>(list);
        
        return list.size() == set.size();
    }
    
    static void printSegmentTree(ArrayList<ArrayList<Pair>> lists){
        for (ArrayList<Pair> al : lists){
            for (Pair p : al){
                System.out.print(p.value + " ");
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arSize = scanner.nextInt();
        int stSize = arSize * 2 - 1;
        
        int[] elems = new int[stSize];
        for (int i = 0; i < stSize; i++){
            elems[i] = scanner.nextInt(); 
        }
        Arrays.sort(elems);
        
        
        ArrayList<Pair> list = buildFrequencyList(elems);
        Collections.sort(list, new Comparator<Pair>(){
            @Override
            public int compare(Pair p1, Pair p2){
                return Integer.valueOf(p2.frequency).compareTo(Integer.valueOf(p1.frequency));
            }
        });
        
        
        int depth = (int) (Math.log(elems.length + 1) / Math.log(2));
        ArrayList<ArrayList<Pair>> lists = checkFrequencies(list, depth);
        
        if (lists != null){
            System.out.println("YES");
            printSegmentTree(lists);
        } else {
            System.out.println("NO");
        }
    }
}