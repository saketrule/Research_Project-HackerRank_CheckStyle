/* Enter your code here. Read input from STDIN. Print output to STDOUT */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kyle
 */

 import java.io.*;
 import java.lang.Math;


public class Solution {
 //variables used by main method and recursive method
 private static int N;
 private static int M;
 private static int[][][] connex;
 private static int[][] numConnexPerNode;
 private static int modConstant = 1000000000; //10^9
 
 public static void main (String[] args){
  //variables to read input
     BufferedReader br;
     String input;
     int pos1 = 0;   
     int pos2 = 0; 
     String curWord;
     
     int[][] inputConnex = new int[0][0];
     int numConnex = 0;
     int[] nodesVisited = new int[0];
     
 
     
  try {
   br = new BufferedReader(new InputStreamReader(System.in));
   

         input = br.readLine();
         //get position of blank space
         pos1 = input.indexOf(" ");
         //now store value of N and M
         curWord = input.substring(0,pos1);
         N = Integer.parseInt(curWord);
         curWord = input.substring(pos1+1);
         M = Integer.parseInt(curWord);
         
         inputConnex = new int[M][2];
      
   nodesVisited = new int[N];
                  
         //loop to get M connections
         for(int i = 0; i < M; i++){
          input = br.readLine();
          //get position of blank space
          pos1 = input.indexOf(" ");
          //now store connection information(from what node and to what node)
          curWord = input.substring(0,pos1);
          inputConnex[i][0] = Integer.parseInt(curWord) - 1;
          curWord = input.substring(pos1+1);
          inputConnex[i][1] = Integer.parseInt(curWord) - 1;
         }

     } catch (Exception e) {
   //System.out.println(e);
     }
     
     
     //initialize array that will store in the first column a 1 if we have determined the number of connections to city N starting at this current city.
     //0 otherwise. In the second column will be the number of connections after they have been found(value is 0 before that)
     numConnexPerNode = new int[N][2];
     for(int i = 0; i < N; i++){
      numConnexPerNode[i][0] = 0;
      numConnexPerNode[i][1] = 0;
     }
     
     
     
     //New idea for storing connections - sort inputConnex first, then loop through again and construct connex array
     //initialize array
  connex = new int[N][][];
     for(int i = 0; i < N; i++){
      connex[i] = new int[1][];
      connex[i][0] = new int[2];
      connex[i][0][0] = 0;
      connex[i][0][1] = 0;
     }

     
  //call method to sort array of connections (Merge sort is used)
  inputConnex = mergeSort(inputConnex);
  
  //calc the number of total connections coming out of each node
  int fromNode;
  for(int i = 0; i < M; i++){
   fromNode = inputConnex[i][0];
   connex[fromNode][0][0] = connex[fromNode][0][0] + 1;
  }
  
  
  //construct N x k x 2 matrix (where k = number of connections coming out of a node) to hold connections for recursive method. Uses sorted inputConnex
  int lastIndex = -1;  //keeps track of position amongst M connections
  int numUnique = 0;  //first used to calc number of unique connections exiting a node, then used to keep track of which unique connection we are adding to connex array
  int totalConnex = 0; //stores total number of connections leaving a node(not just non-unique connections)
  for(int i = 0; i < N; i++){
   //figure out number of unique connections
   numUnique = 0;
   totalConnex = connex[i][0][0];
   for(int j = 0; j < totalConnex; j++){
    //first connection is always treated as unique
    if(j == 0){
     numUnique = 1;
    }else{
     //for every connection beyond first, compare to previous element, if same then not unique(works because list is sorted)
     if(inputConnex[lastIndex + 1 + j][1] == inputConnex[lastIndex + j][1]){
      //not a unique connection
     }else{
      numUnique = numUnique + 1; //this is a unique connection
     }
    }
   }
   
   //construct new array of size numUnique+1. first row, first column stores num of unique connections. Additional rows have which node connection
   //is going to in first column and the total number of these connections in second column
   connex[i] = new int[numUnique+1][]; 
   for(int j = 0; j < numUnique+1; j++){
    connex[i][j] = new int[2];
    connex[i][j][0] = 0;
    connex[i][j][1] = 0;
   }
   //store size of array
   connex[i][0][0] = numUnique;
   
   //reuse numUnique. This time it will keep track of the number of unique connections we have stored. This way if there is a duplicate
   //connection we know the index of connex[][][] to store it in
   numUnique = 0;
   
   for(int j = 0; j < totalConnex; j++){
    //again first connection is always treated as unique
    if(j == 0){
     connex[i][numUnique+1][0] = inputConnex[lastIndex + j + 1][1];
     connex[i][numUnique+1][1] = connex[i][numUnique+1][1] + 1;
     numUnique = numUnique + 1;
    }else{
     //if we have non-unique entry
     if(inputConnex[lastIndex + 1 + j][1] == inputConnex[lastIndex + j][1]){
      connex[i][numUnique][1] = connex[i][numUnique][1] + 1;
     //otherwise we have a unique entry
     }else{
      connex[i][numUnique+1][0] = inputConnex[lastIndex + j + 1][1];
      connex[i][numUnique+1][1] = connex[i][numUnique+1][1] + 1;
      numUnique = numUnique + 1;
     }
    
    }
   }
   
   //adjust lastIndex (this is index over the M connections)
   lastIndex = lastIndex + totalConnex;
  
  }
    
  
     
     //construct nodesVisited array for recursive method. An N element array, where element refers to number of times we have visited that node
     for(int i = 0; i < N; i++){
      nodesVisited[i] = 0;
     }
     
     //initialize
     nodesVisited[0] = 0;
 
     
  //initial call to recursive algorithm
  numConnex = recursiveMethod(0,nodesVisited);
     
  numConnex = numConnex % modConstant;
    
     
  
     //print result
     if(numConnex < 0){
      //print infinite message
      System.out.println("INFINITE PATHS");
     }else{
      //print number of connex
      System.out.println(numConnex); 
     }
    
   

    }
    
    //note that -1 is used as a flag to signal a loop has occured in a path that leads from 1 to N. Therefore there are an infinite number of paths and 
    //we can drop out of recursive method
    
    //For the modulo operator, the fact that if A = B + C, then A % n = (B % n + C % n) % n was used throughout. 
    private static int recursiveMethod(int curNode, int[] nodesVisited){
     int numConnex = 0;   //stores the total number of connections from curNode to node N
     //two variables are needed here so that we do not get a number of connections greater than the size of an integer
     int numConnexTemp = 0;  //used to get the number of connections from current node to another node for only one connection between those 2 nodes
     int numConnexTemp1 = 0;  //used to get the total number of connections from current node to another node considering all connections     
     int toNode;

     //check base case
     if(curNode == N-1){
      for(int i = 0; i < N; i++){
       //check that there have not been any loops on our way here
    if(nodesVisited[i] > 1){
     numConnex = -1;
    }   
     
      }
      //check that the Nth node does not connect to a node we have already visited
   for(int i = 0; i < connex[N-1][0][0]; i++){
    toNode = connex[N-1][i+1][0];
    if(nodesVisited[toNode] > 0){
     numConnex = -1;
    }
   }
      
   //if we have not gone through any loops and last node does not create a loop, then we have found one unique path from city 1 to city N
      if(numConnex == 0){
       numConnex = 1;
      }
      return numConnex;
  }
  
  
  
     
     //make next calls to recursive algorithm
  for(int i = 0; i < connex[curNode][0][0]; i++){    
      numConnexTemp = 0;
      numConnexTemp1 = 0;
      toNode = connex[curNode][i+1][0];

      //if we have only visited next node once
      if(nodesVisited[toNode] < 2){
       //check if we know the number of connection to city N starting at toNode. If so use this value, otherwise call recursive method
       if(numConnexPerNode[toNode][0] == 1){
     numConnexTemp = numConnexPerNode[toNode][1];    
     numConnexTemp = numConnexTemp % modConstant;
     
    //if we have not already solved this node, need to call recursive method
    }else{
     //increment number of times we have visited toNode       
        nodesVisited[toNode] = nodesVisited[toNode] + 1;
        
        //get number of connections from toNode to city N and use modulo operator
        numConnexTemp = recursiveMethod(toNode,nodesVisited);
        numConnexTemp = numConnexTemp % modConstant;

     //decrement number of times we have visited toNode so that array can be used for next call
        nodesVisited[toNode] = nodesVisited[toNode] - 1;
       }
       
       //this loop is done so that we dont obtain an integer greater than the maximum value for a Java int
       for(int j = 0; j < connex[curNode][i+1][1]; j++){
        numConnexTemp1 = numConnexTemp1 + numConnexTemp;
        numConnexTemp1 = numConnexTemp1 % modConstant;
       }
       
    numConnexTemp = numConnexTemp1 % modConstant;
       
      }

      //if we have reached Nth city by loop(flag of -1 being used), break out of recursive method
      if(numConnexTemp < 0){
       return numConnexTemp;
   //otherwise we have reached Nth city without loop, increment number of connections
      }else{
       numConnex = numConnex + numConnexTemp;
       numConnex = numConnex % modConstant;
      }
     }
     
     //now that we have exhausted search at curNode, store that value for later use
     numConnexPerNode[curNode][0] = 1;
     numConnexPerNode[curNode][1] = numConnex;
     
     
     
     
     //return answer
     return numConnex;
     
    }

    
    
    //mergeSort used to sort list of M connections
    public static int[][] mergeSort(int[][] list){
     //if we get down to a list of 1 element, list is sorted so return
     if(list.length == 1){
      return list;
     }
     
     //otherwise we split list into 2 roughly even sides
     int[][] left;
     int[][] right;
     
     int middle = Math.round(list.length / 2);
     
     left = new int[middle][];
     for(int i = 0; i < middle; i++){
      left[i] = new int[2];
      left[i][0] = list[i][0];
      left[i][1] = list[i][1];
     }
    
     right = new int[list.length - middle][];
     for(int i = middle; i < list.length; i++){
      right[i-middle] = new int[2];
      right[i-middle][0] = list[i][0];
      right[i-middle][1] = list[i][1];
     }
     
     //recusrively call mergeSort to sort 2 respective halfs
     left = mergeSort(left);
     right = mergeSort(right);
     
     int[][] result = new int[left.length + right.length][2];
     int leftIndex = 0;
     int rightIndex = 0;
     int resultIndex = 0;
     
     //now that both halfs are sorted, we merge them together
     
     //while there are elements left in either array(left or right)
     while(leftIndex < left.length || rightIndex < right.length){
      //if there are elements in both we need to compare and find smallest
      if(leftIndex < left.length && rightIndex < right.length){
       if(left[leftIndex][0] < right[rightIndex][0]){
        //append from left, increment left index
        result[resultIndex][0] = left[leftIndex][0];
        result[resultIndex][1] = left[leftIndex][1];
        resultIndex++;
        leftIndex++;
        
       }else if(left[leftIndex][0] > right[rightIndex][0]){
        //append from right, increment right index
        result[resultIndex][0] = right[rightIndex][0];
        result[resultIndex][1] = right[rightIndex][1];
        resultIndex++;
        rightIndex++;
        
       }else{
        if(left[leftIndex][1] < right[rightIndex][1]){
         //append from left, increment left index
         result[resultIndex][0] = left[leftIndex][0];
         result[resultIndex][1] = left[leftIndex][1];
         resultIndex++;
         leftIndex++;
        
        }else if(left[leftIndex][1] > right[rightIndex][1]){
         //append from right, increment right index
         result[resultIndex][0] = right[rightIndex][0];
         result[resultIndex][1] = right[rightIndex][1];
         resultIndex++;
         rightIndex++;
        
        }else{
         //append from left, increment left index
         result[resultIndex][0] = left[leftIndex][0];
         result[resultIndex][1] = left[leftIndex][1];
         resultIndex++;
         leftIndex++;
        }
       }
      //if there are only elements in left, we take that one 
   }else if(leftIndex < left.length){
       //append from left, increment left index
       result[resultIndex][0] = left[leftIndex][0];
       result[resultIndex][1] = left[leftIndex][1];
       resultIndex++;
       leftIndex++;
       
      }else if(rightIndex < right.length){
       //append from right, increment right index
       result[resultIndex][0] = right[rightIndex][0];
       result[resultIndex][1] = right[rightIndex][1];
       resultIndex++;
       rightIndex++;
      }
  }
  
  return result;
 }
       
    
}