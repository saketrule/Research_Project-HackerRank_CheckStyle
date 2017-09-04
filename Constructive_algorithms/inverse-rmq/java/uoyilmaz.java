import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution
{
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void main(String[] args)
    {
        Scanner scan = new Scanner( System.in);
        int n = scan.nextInt();
        int power = (int)(Math.log(n) / Math.log(2));
        HashSet[] levels = new HashSet[power+1];
        boolean canBeReconstructed = true;
        
        for( int i = 0; i <= power; i++)
        {
            levels[i] = new HashSet<Integer>( (int)(Math.pow( 2, i)));
        }
        
        int value, level;
        outerloop:
        for( int i = 0; i < 2*n-1; i++)
        {
            value = scan.nextInt();
            level = power;
            while( levels[level].contains( value))
            {
                --level;
                
                if( level < 0)
                {
                    canBeReconstructed = false;
                    break outerloop;
                }
            }
            levels[level].add( value);
        }
        scan.close();
        
        if( !canBeReconstructed)
        {
            System.out.println( "NO");
        }
        else
        {
            for( int i = 0; i <= power; i++)
            {
                if( levels[i].size() != (int)(Math.pow( 2, i)))
                {
                    canBeReconstructed = false;
                }
            }
            
            if( !canBeReconstructed)
            {
                System.out.println( "NO");
            }
            else
            {
                int[] segmentTree = new int[2*n-1];
                segmentTree[0] = (Integer)(levels[0].toArray())[0];

                int index = 1;
                TreeSet<Integer> levelAsSet;
                Integer rightChild;
                outerloop2:
                for( int i = 1; i <= power; i++)
                {
                    int size = levels[i].size();

                    // Fill left children using parent's value
                    for( int offset = 0; offset < size; offset += 2)
                    {
                        segmentTree[index+offset] = segmentTree[(index+offset)/2];
                        levels[i].remove( (Object)segmentTree[index+offset]);
                    }

                    // Fill right children using the smallest remaining number
                    levelAsSet = new TreeSet<Integer>( (HashSet<Integer>)levels[i]);
                    for( int offset = 1; offset < size; offset += 2)
                    {
                        rightChild = levelAsSet.ceiling( segmentTree[index+offset-1]);
                        if( rightChild == null)
                        {
                            canBeReconstructed = false;
                            break outerloop2;
                        }
                        else
                        {
                            segmentTree[index+offset] = rightChild;
                            levelAsSet.remove( rightChild);
                        }
                    }

                    index += size;
                }
                
                if( !canBeReconstructed)
                {
                    System.out.println( "NO");
                }
                else
                {
                    StringBuilder sb = new StringBuilder();
                    for( int i = 0; i < segmentTree.length; i++)
                    {
                        sb.append( segmentTree[i]);
                        sb.append( " ");
                    }

                    System.out.println( "YES");
                    System.out.println( sb.toString());
                }
            }
        }
    }
}