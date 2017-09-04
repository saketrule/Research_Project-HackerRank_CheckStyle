import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int i = 0; i < T; i++){
            int N = in.nextInt();
            int[] array = new int[N];
            for(int n = 0; n < N; n++){
                array[n] = in.nextInt();
            }
            solve(array);
        }
    }

    static class Pair{
        int count;
        int last;
    }

    static class Link{
        Pair pair;
        Link next;
    }

    static void solve(int[] array){
        if(array.length == 0){
            System.out.println(0);
            return;
        }
        if(array.length == 1){
            System.out.println(1);
            return;
        }
        Arrays.sort(array);
        Link link = new Link();
        Pair pair = new Pair();
        pair.count = 1;
        pair.last = array[0];
        link.pair = pair;
        for(int i = 1; i < array.length; i++) {
            if (array[i] - link.pair.last == 1) {
                link.pair.count++;
                link.pair.last = array[i];
            } else if (array[i] == link.pair.last) {
                //check all links before if exists where we can add array[i]
                //if there is no such place we create new link
                Link nextLink = link.next;
                while (nextLink != null && array[i] == nextLink.pair.last){
                    nextLink = nextLink.next;
                }
                if(nextLink == null || array[i] - nextLink.pair.last > 1){
                    Pair pair1 = new Pair();
                    pair1.last = array[i];
                    pair1.count = 1;
                    Link link1 = new Link();
                    link1.pair = pair1;
                    link1.next = link;
                    link = link1;
                } else if(array[i] - nextLink.pair.last == 1) {
                    nextLink.pair.count++;
                    nextLink.pair.last = array[i];
                }
            } else {
                Pair pair1 = new Pair();
                pair1.last = array[i];
                pair1.count = 1;
                Link link1 = new Link();
                link1.pair = pair1;
                link1.next = link;
                link = link1;
            }
        }
        int min = link.pair.count;
        Link currentLink = link.next;
        while (currentLink != null){
            if(currentLink.pair.count < min){
                min = currentLink.pair.count;
            }
            currentLink = currentLink.next;
        }
        System.out.println(min);
    }
}