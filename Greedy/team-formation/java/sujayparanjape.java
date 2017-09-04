import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
    
    String line = scanner.nextLine();
    
    int T = Integer.parseInt(line);
  
    for(int t = 0  ; t< T;t++)
    {
       String  inp_str[] =  scanner.nextLine().split(" ");
       int N= Integer.parseInt(inp_str[0]);
       
       if(N == 0 )
       {
        System.out.println("0");
        continue;
       }
       
       
       int num[] = new int[N] ;
       for(int i =0; i<N;i++) 
       num[i] = Integer.parseInt(inp_str[i+1]);
       
       //sort num 
        Arrays.sort(num);
        
       
       //get unique element and counts into new array 
        int currentElement = num[0];
        int currentCount = 0;
        List<Integer> elements = new ArrayList<Integer>();
        List<Integer> elementCounts = new ArrayList<Integer>();
        for(int i=0;i<N;i++)
            {
            if(currentElement != num[i])
                {
                //insert count and element into new list 
                elements.add(currentElement);
                elementCounts.add(currentCount);
                //set currentElement into new element 
                currentElement = num[i];
                currentCount = 1;
            }
            else
                {
                currentCount++;
            }
        }
        
        elements.add(currentElement);
        elementCounts.add(currentCount);
        
        /**********************************************/
        
        class listComparator implements Comparator<ArrayList<Integer>>
        {
   @Override
   public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
    // TODO Auto-generated method stub
    if(o1.get(o1.size()-1)< o2.get(o2.size()-1))
     return -1;
    else if(o1.get(o1.size()-1)> o2.get(o2.size()-1))
     return 1; 
    else 
    return o1.size() - o2.size();
   }
        
        }
        
        PriorityQueue<ArrayList<Integer>> queue = new PriorityQueue<>(10, new listComparator());
        int elementsLeft = N;
        int currentIndx = 0; 
        int minSize = N;
        while(elementsLeft > 0)
        {
         
         for(int l = 0;l <elementCounts.get(currentIndx) ;l++ )
         {
          while(true)
          {
           if(queue.peek() != null )
           {
            if(queue.peek().get(queue.peek().size()-1) == elements.get(currentIndx)-1)
            {
             //pop array list 
             ArrayList<Integer> temp = queue.poll();
                          
             //add element to the array list
             temp.add(elements.get(currentIndx));
             
             //reinsert the arraylist into queue
             queue.add(temp);
             
             break; // break while 
            }
            else if(queue.peek().get(queue.peek().size()-1) < elements.get(currentIndx)-1)
            {
             //arraylist can no longer be extended.
             //just pop it
             ArrayList<Integer> temp = queue.poll();
             
             /*for(int x = 0; x< temp.size() ;x++ )
              System.out.print(String.valueOf(temp.get(x)) + " ");
            
             System.out.println( elements.get(currentIndx) ); */
             
             //check size and update if less than min 
             minSize = minSize > temp.size() ? temp.size() : minSize;
            }
            else
            {
            //make new array list  
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(elements.get(currentIndx));
                
                // add it to queue
                queue.add(temp);
                
                //break the while;
                break;
            }
            
           }
           else
           {
            //make new array list  
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(elements.get(currentIndx));
            
            // add it to queue
            queue.add(temp);
            
            //break the while;
            break;
            
           }
           
          }
          
          elementsLeft--;
         }
         currentIndx++;
        }
        
        while(queue.peek() != null )
        {
          ArrayList<Integer> temp = queue.poll();
    
    //check size and update if less than min 
    minSize = minSize > temp.size() ? temp.size() : minSize;
        }
        
        /**********************************************/
        
        System.out.println(String.valueOf(minSize));
        
    }
        
        
    }
}