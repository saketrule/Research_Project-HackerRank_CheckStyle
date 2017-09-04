import java.util.Scanner;

/**
 *
 * @author Suraj Singh
 */
class Spiderman {
    public static void main(String ar[])
    {
        Scanner s = new Scanner(System.in);
        int h,n,i,u,people,max[],largest=0;
        n = s.nextInt(); // number of buildings
        h = s.nextInt(); // height of building
        i = s.nextInt(); // jump power lost
        int result[] = new int[n];
        int b[][] = new int[h][n]; // to store the number of people in each building floor wise.
        max = new int[h];
        for(int x=0;x<n;x++)
        {
            people = s.nextInt();
            for(int j=0;j<people;j++)
            {
                u = s.nextInt();
                b[u-1][x]++;
            }
        }
        //Initialize the max array filled bottom up by building height
        for(int x=0;x<h;x++)
            for(int y=0;y<n;y++)
            {
                if(max[x]<b[x][y])
                    max[x] = b[x][y];
            }
        for(int j=0;j<n;j++)
            {
                result[j] = b[h-1][j];
            }
        for(int x=h-2;x>=0;x--)
        {
            for(int j=0;j<n;j++)
            {
                if(x+i >= h)
                    result[j] =  result[j]+b[x][j];
                else
                {
                    if(max[x+i] > result[j])
                    {
                        result[j] = max[x+i]+b[x][j];
                    }
                    else
                        result[j] = result[j]+b[x][j];
                }
                if(max[x] < result[j])
                    max[x] = result[j];
            }
        }
        System.out.println(max[0]);
    }
}