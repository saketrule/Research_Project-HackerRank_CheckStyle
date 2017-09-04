import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
public class TSPNearestNeighbour
{
    private int numberOfNodes;
    private Stack<Integer> stack;
 
    public TSPNearestNeighbour()
    {
        stack = new Stack<Integer>();
    }
 
    public int tsp(int start,int adjacencyMatrix[][],List<Integer> lettersCountries)
    {
        int minDest=0;
        numberOfNodes = adjacencyMatrix[1].length - 1;
        int[] visited = new int[numberOfNodes + 1];
        for(int letterCountry:lettersCountries)
            visited[letterCountry]=-1;
        
        visited[start] = 1;
        
                            lettersCountries.remove(lettersCountries.indexOf(start));
        stack.push(start);
        int element, dst = 0, i;
        int min = Integer.MAX_VALUE;
        boolean minFlag = false;
    //    System.out.print(start + "\t");
 
        while (!stack.isEmpty()&&lettersCountries.size()!=0)
        {
           // System.out.println("stack:"+stack);
            element = stack.peek();
            i = 1;
            min = Integer.MAX_VALUE;
            while (i <= numberOfNodes)
            {
                if (adjacencyMatrix[element][i] >= 1 && visited[i] != 1)
                {
                    if (min > adjacencyMatrix[element][i])
                    {
                        min = adjacencyMatrix[element][i];
                        dst = i;
                        
                     //   System.out.println("dst:"+dst);
                        
                       // System.out.println("distance:"+element+"to:"+i+"-->"+adjacencyMatrix[element][dst]);
                        minFlag = true;
                        if(visited[dst]==-1){
                         //   System.out.println("removing");
                            lettersCountries.remove(lettersCountries.indexOf(dst));
                        }
                    }
                }
                i++;
            }
            if (minFlag)
            {
                
                minDest+=adjacencyMatrix[element][dst];
                visited[dst] = 1;
                stack.push(dst);
            //    System.out.print(dst + "\t");
                minFlag = false;
                
            }else{
               int from= stack.pop();
                
                if(!stack.isEmpty()&&!lettersCountries.isEmpty()){
                    int to=stack.peek();
                        
              //  System.out.print( to+ "\t");
                minDest+=adjacencyMatrix[from][to];
                }
               
            
            }
        }
        return minDest;
    }
 
    public static void main(String... arg)
    {
        int number_of_nodes;
        Scanner scanner = null;
        try
        {
            scanner = new Scanner(System.in);
            number_of_nodes = scanner.nextInt();
            //3 letters
           int count= scanner.nextInt();
            List<Integer> lettersCountries=new ArrayList<>(count);
            for(int i=0;i<count;i++)
            lettersCountries.add(scanner.nextInt());
            int adjacency_matrix[][] = new int[number_of_nodes + 1][number_of_nodes + 1];
            
            while(scanner.hasNext())
            {
                int i=scanner.nextInt();
                int j=scanner.nextInt();
                adjacency_matrix[i][j] = scanner.nextInt();
                
            }
            for (int i = 1; i <= number_of_nodes; i++)
            {
                for (int j = 1; j <= number_of_nodes; j++)
                {
                    if (adjacency_matrix[j][i] == 0&&adjacency_matrix[i][j]!=0)
                    {
                        adjacency_matrix[j][i] =adjacency_matrix[i][j];
                    }
                }
            }
          //  println(adjacency_matrix);
           // System.out.println("the citys are visited as follows");
            int min=Integer.MAX_VALUE;
            for(int country:lettersCountries){
                
            TSPNearestNeighbour tspNearestNeighbour = new TSPNearestNeighbour();
            List<Integer> letters=new ArrayList<>(lettersCountries);
            int minDest=tspNearestNeighbour.tsp(country,adjacency_matrix,letters);
            
                if(minDest<min){
                    min=minDest;
                }
            }
            System.out.println(min);
        } catch (InputMismatchException inputMismatch)
         {
             System.out.println("Wrong Input format");
         }
        scanner.close();
    }
    
    public static void println(int matrix[][]){
        for (int i = 0; i < matrix.length; i++) {
    for (int j = 0; j < matrix[0].length; j++) {
        System.out.print(matrix[i][j] + " ");
    }
    System.out.print("\n");
}
    }
}