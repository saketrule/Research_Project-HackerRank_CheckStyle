import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public  static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());

        String[] elements = br.readLine().split(" ");
        Set<Integer> values = new HashSet<>(elements.length);
        for(String element : elements){
            values.add(Integer.valueOf(element));
        }

        int size = values.size();
        if(size != n){
            System.out.println("NO");
            return;
        }

        List<Integer> sorted = new ArrayList<>(values);
        Collections.sort(sorted);
        values = null;
        int[] arr = new int[sorted.size()];
        int i=0;
        for(Integer val : sorted){
            arr[i++] = val;
        }
        int[] result = new int[n];

        StringBuilder numbersInLastLevel = new StringBuilder();
        findMinimumArray(arr, 0, arr.length-1, result, 1, 1, numbersInLastLevel);


        List<Integer> givenValues = new ArrayList<>(elements.length);
        for(String element : elements){
            givenValues.add(Integer.valueOf(element));
        }

        List<Integer> resultList = new ArrayList<>();
        boolean valid = true;
        for(int j = 1;j<result.length;j++) {
            resultList.add(result[j]);
        }

        String[] data = numbersInLastLevel.toString().split(" ");
        for(String val : data){
            resultList.add(Integer.valueOf(val));
        }

        Collections.sort(resultList);
        Collections.sort(givenValues);

        for(int j=0; j<resultList.size();j++){
            if(!Objects.equals(resultList.get(j), givenValues.get(j))){
                valid = false;
                break;
            }
        }

        if(!valid){
            System.out.println("NO");
            return;
        }
        System.out.println("YES");
        for(int j = 1;j<result.length;j++){
            System.out.print(result[j]+" ");

        }
        System.out.print(numbersInLastLevel);

    }

    private static int minimumOf(int a, int b){
        if(a>b){
            return b;
        }
        return a;
    }

    private static int findMinimumArray(int[] arr, int s, int e, int[] result, int level, int index, StringBuilder lastLevel){
        if(e-s == 0){
           // System.out.println(level +"->"+ arr[s]);

            if(index<result.length) {
                result[index] = arr[s];
            }else {
                lastLevel.append(arr[s]).append(" ");
            }
            return arr[s];
        }
        int mid = (s + e)/2;

        int left = findMinimumArray(arr, s, mid, result, level+1, 2*index, lastLevel);
        int right = findMinimumArray(arr, mid+1,e, result, level+1, (2*index)+1,lastLevel);



        int minimumOf = minimumOf(left, right);

        if(index<result.length) {
            result[index] = minimumOf;
        }




        //System.out.println(level +"->"+ minimumOf);
       // System.out.println(minimumOf);
        return minimumOf;
    }
}