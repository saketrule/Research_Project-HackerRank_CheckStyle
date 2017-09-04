import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

/**
 * Author: Charles Wang
 * Date: 22/02/12
 * Time: 11:39 PM
 */
public class Solution
{
    public static class City
    {
        HashSet<Integer> parents;
        HashMap<Integer, Long> children;
        BigInteger numPathTo= BigInteger.valueOf(0);
        boolean connectedToStart = false;
        boolean connectedToEnd = false;
        boolean visited = false;

        public City()
        {
            parents = new HashSet<Integer>();
            children = new HashMap<Integer, Long>();
        }
    }
    
    private static final int MOD = 1000000000;
    public static void main ( String args[] )
    {
        try
        {
            BufferedReader br =
                    new BufferedReader(new InputStreamReader(System.in));
            String line;
            line = br.readLine();
            String[] splitLine = line.split("\\s+");
            int N = Integer.parseInt( splitLine[0] );
            int M = Integer.parseInt( splitLine[1] );

            City[] cities = new City[N+1];
            for ( int i = 1; i <= N; i++ )
            {
                cities[i] = new City();
            }

            int start;
            int finish;
            
            for ( int m = 0; m< M; m++ )
            {
                line = br.readLine();
                splitLine = line.split("\\s+");
                
                start = Integer.parseInt( splitLine[0] );
                finish = Integer.parseInt( splitLine[1] );

                Long pathToChild = cities[start].children.get( finish );
                if ( pathToChild == null )
                {
                    pathToChild = 0l;
                }
                pathToChild++;
                cities[start].children.put(finish, pathToChild);
                cities[finish].parents.add(start);
            }
            
            cities[1].numPathTo = BigInteger.valueOf(1);
            cities[1].connectedToStart = true;
            cities[cities.length -1].connectedToEnd = true;
            
           // processPaths( cities, 1 );
            processPathsIter ( cities );
            for ( int i = 1; i < N+1; i++ )
            {
                if ( !isConnected( cities, i ) )
                {
                    //remove from parent
                    for ( int parent : cities[i].parents )
                    {
                        cities[parent].children.remove( i );
                    }
                    
                    for ( int child : cities[i].children.keySet() )
                    {
                        cities[child].parents.remove( i );
                    }
                }
            }

            cities[N].children.clear();
            
            if ( !process( cities ) )
            {
                System.out.println( "INFINITE PATHS" );
            }
            else
            {
                System.out.println( cities[cities.length-1].numPathTo.mod( BigInteger.valueOf( MOD ) ).toString() );
            }

        }
        catch (Exception e)
        {
            System.err.println("Error:" + e.getMessage());
        }
    }

    private static void processPaths( City[] cities, int city )
    {        
        if ( city == cities.length - 1 )
        {
            cities[city].connectedToEnd = true;
            return;
        }
        else if ( cities[city].children.isEmpty() )
        {
            cities[city].connectedToEnd = false;
            return;
        }

        for ( int child : cities[city].children.keySet() )
        {
            if ( cities[child].connectedToStart )
            {
                if ( cities[child].connectedToEnd )
                {
                    cities[city].connectedToEnd = true;
                }
                continue;
            }
            cities[child].connectedToStart = true;

            processPaths( cities, child );

            if ( cities[child].connectedToEnd )
            {
                cities[city].connectedToEnd = true;
            }
        }
    }
    
    
    private static void processPathsIter( City[] cities )
    {
        ArrayList<Integer> toVisit = new ArrayList<Integer>();

        if ( cities[1].children.isEmpty() )
        {
            return;
        }

        if ( cities[cities.length - 1].parents.isEmpty() )
        {
            return;
        }


        for ( Integer child : cities[1].children.keySet() )
        {
            cities[child].connectedToStart = true;
            toVisit.add( child );
        }
        
        while ( !toVisit.isEmpty() )
        {
            int city = toVisit.remove( toVisit.size() - 1 );
            for ( Integer child : cities[city].children.keySet() )
            {
                if ( cities[child].connectedToStart )
                {
                    continue;
                }
                cities[child].connectedToStart = true;
                toVisit.add( child );
            }                        
        }


        for ( Integer parent : cities[cities.length - 1].parents )
        {
            cities[parent].connectedToEnd = true;
            toVisit.add( parent );
        }

        while ( !toVisit.isEmpty() )
        {
            int city = toVisit.remove( toVisit.size() - 1 );
            for ( Integer parent : cities[city].parents )
            {
                if ( cities[parent].connectedToEnd )
                {
                    continue;
                }
                cities[parent].connectedToEnd = true;
                toVisit.add( parent );
            }
        }

    }

    private static boolean isConnected( City[] cities, int city )
    {
        return isConnected( cities[city] );
    }

    private static boolean isConnected( City city )
    {
        return city.connectedToEnd && city.connectedToStart;
    }

    private static boolean process( City[] cities )
    {
        ArrayList<Integer> toVisit = new ArrayList<Integer>();
        for ( Integer child : cities[1].children.keySet() )
        {
            City childCity = cities[child];
            childCity.parents.remove( 1 );
            childCity.numPathTo = childCity.numPathTo.add( BigInteger.valueOf( cities[1].children.get( child ) ) );
            //childCity.numPathTo %= MOD;
            if ( childCity.parents.isEmpty() )
            {
                toVisit.add( child );
            }
        }

        if ( toVisit.isEmpty() )
        {
            return false;
        }

        while ( !toVisit.isEmpty() )
        {
            int city = toVisit.remove( 0 );
            City visitingCity = cities[city];

            if ( visitingCity.visited )
            {
                return false;
            }
            visitingCity.visited = true;
            
            for ( int child : visitingCity.children.keySet() )
            {
                City childCity = cities[child];
                childCity.parents.remove( city );
                BigInteger tmp = BigInteger.valueOf( cities[city].children.get( child ) );
                childCity.numPathTo = childCity.numPathTo.add( cities[city].numPathTo.multiply( tmp ) );
                //childCity.numPathTo %= MOD;
                if ( childCity.parents.isEmpty() )
                {
                    toVisit.add( child );
                }
            }
        }

        return true;
    }
   

}