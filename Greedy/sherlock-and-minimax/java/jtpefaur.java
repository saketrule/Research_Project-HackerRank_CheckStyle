import java.io.*;
import java.util.*;

class Solution
{
    public static void main(String[] arg)
    {
        Scanner in  = new Scanner(System.in);
        int size = in.nextInt();
        int[] array = new int[size+2];
        array[size+1] = 1000000000;
        array[0] = -1000000000;
        
        //para q siempre tenga un elemento mas, y que siempre el primer elemento sea 0 (diff maxima)
        for(int i = 1; i<size+1; i++)
        {
            array[i] = in.nextInt();
        }
        
        int p = in.nextInt();
        int q = in.nextInt();
        
        findMiniMax(array, p, q);
    }
    
    public static void findMiniMax(int[] array, int p, int q)
    {
        Arrays.sort(array);
        int currentMax=p;
        int currentMaxDif=0;
        
        int index = 1;
        while(array[index]<p && index<array.length)
            index++;
        //Hasta aca todo era <p
        if((array[index-1]+array[index])/2 > p)
        {
            currentMaxDif = Math.abs(array[index] - ((array[index-1]+array[index])/2));
            currentMax = (array[index-1]+array[index])/2;
        }
        else
        {
            currentMaxDif = Math.min(Math.abs(array[index]-p),Math.abs(array[index-1]-p));
            currentMax = p;
        }
        //HASTA ACA BIEN
        //desde aca todo es p>= y <=q
        while((index+1)<array.length && array[index+1]<=q)
        {
            int mid = (array[index] + array[index+1])/2;
            if(currentMaxDif< Math.abs(array[index] - mid))
            {
                currentMaxDif = Math.abs(array[index] - mid);
                currentMax = mid;
            }
            index++;
        }
        
        //index<=q y index+1 es >q
        
        if((index+1)<array.length)
        {
            int possibleMaxDif=0;
            int possibleMax=0;
            if((array[index+1]+array[index])/2 < q)
            {
                possibleMaxDif = Math.abs(array[index] - ((array[index+1]+array[index])/2));
                possibleMax = (array[index+1]+array[index])/2;
            }
            else
            {
                possibleMaxDif = Math.min(Math.abs(array[index]-q),Math.abs(array[index+1]-q));
                possibleMax = q;
            }
            
            if(possibleMaxDif>currentMaxDif)
            {
                currentMaxDif = possibleMaxDif;
                currentMax = possibleMax;
            }
        }
        
        System.out.println(currentMax);
    }
}