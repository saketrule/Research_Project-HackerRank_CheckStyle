import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
        try {
            String firstLine=bi.readLine();
            int arraySize=Integer.parseInt(firstLine);
            String inputArray[]=bi.readLine().split(" ");
            double result=0;
            Map<Long,Long> values=new HashMap<>();
            TreeMap<Long,ArrayList<Long>> countsToValueListMap=new TreeMap<Long,ArrayList<Long>>();
            for(int i=0;i<2*arraySize-1;i++){
                long multValue=Long.parseLong(inputArray[i]);
                Long count=values.get(multValue);
                if(count==null){
                    values.put(multValue,1L);
                }
                else{
                    values.put(multValue,count+1);
                }
            }
            if(values.size()!=arraySize){
                System.out.println("NO");
                return;
            }
            List<Long> originalArray=new ArrayList();
            values=sortByValue(values);
            long maxCount=(long)(Math.log(arraySize)/Math.log(2))+1;
            int index=0;
            for(Map.Entry<Long,Long> entry:values.entrySet()){
                if(index==0){
                    if(entry.getValue()!=maxCount){
                        System.out.println("NO");
                        return;
                    }
                }else{
                    double subVal=(long)(Math.log(index)/Math.log(2))+1;
                    long expectedCount=maxCount-(long)subVal;
                    if(entry.getValue()!=expectedCount){
                        System.out.println("NO");
                        return;
                    }
                }
                index++;
            }

            List<Long> sortedByCount=new ArrayList<Long>(values.keySet());

            for(int i=0;i<maxCount;i++){
                placeLevel(originalArray,i,sortedByCount);
            }
            System.out.println("YES");
            for(int i=0;i<originalArray.size();i++){
                System.out.print(originalArray.get(i)+" ");
            }
            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void placeLevel(List<Long> originalArray,int level,List<Long> sortedByCount){
        if(level==0){
            originalArray.add(sortedByCount.get(0));
        }else{
            int itemCountToBePlaced=originalArray.size()+1;
            List<Long> sortedSubList=getSortedSublist(sortedByCount,itemCountToBePlaced/2);
            List <Long> newList=new ArrayList<Long>();
            for(int i=0;i<itemCountToBePlaced;i++){
                if(i%2==0){
                    newList.add(originalArray.get(originalArray.size()-itemCountToBePlaced/2+i/2));
                }else{
                    newList.add(sortedSubList.get(i/2));
                }
            }
            originalArray.addAll(newList);
        }

    }

    private static List<Long> getSortedSublist(List<Long> sortedByCount, int itemCountToBePlaced) {
        List<Long> subList=new ArrayList<Long>();
        for(int i=0;i<itemCountToBePlaced;i++){
            subList.add(sortedByCount.get(itemCountToBePlaced+i));
        }
        Collections.sort(subList);
        return subList;
    }

    public static <K, V extends Comparable<? super V>> Map<K, V>
    sortByValue( Map<K, V> map )
    {
        List<Map.Entry<K, V>> list =
                new LinkedList<Map.Entry<K, V>>( map.entrySet() );
        Collections.sort( list, new Comparator<Map.Entry<K, V>>()
        {
            public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list)
        {
            result.put( entry.getKey(), entry.getValue() );
        }
        return result;
    }


}