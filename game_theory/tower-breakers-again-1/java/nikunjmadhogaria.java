import java.util.Scanner;

public class Solution{
 
 static Scanner sc = new Scanner(System.in);
 
 public static void main (String args[]){
  
  int testCaseCount, testCaseWinner[], i;
  
  testCaseCount = sc.nextInt();
  
  testCaseWinner = new int[testCaseCount];
  
  for(i = 0; i < testCaseCount; i++)
   testCaseWinner[i] = findWinner();

  for(i = 0; i < testCaseCount; i++)
   System.out.println(testCaseWinner[i]);
 
 }
 
 public static int findWinner (){
  
  int towerCount, towerHeight[], k, towerPrimeFactorCount[];
  
  //System.out.print("Enter the no.of towers: ");
  towerCount = sc.nextInt();
  
  towerHeight = new int[towerCount];
  
  //System.out.print("Enter heights of towers: ");
  for(k = 0; k < towerCount; k++)
   towerHeight[k]  = sc.nextInt();
  
  //using nim logic
  int stackCount,stackValue[],maxStackValue,maxBinaryPosition,temp,sumOfBinaryPositions[],binaryPosition;
  boolean isBalanced;
  
  stackCount = towerCount;
  
        stackValue = new int[stackCount];
        maxStackValue = 0;
        isBalanced = true;
        
       
        for (k = 0; k < stackCount; k++) {
            stackValue[k] = countPrimary(towerHeight[k]);
            if(maxStackValue < stackValue[k])
                maxStackValue = stackValue[k];
        }
  
  //System.out.println("Max stack value: "+maxStackValue);
        
        maxBinaryPosition = 0;
        temp = 1;
        while(temp <= maxStackValue){
            temp *= 2;
            maxBinaryPosition++;
        }
  
  //System.out.println("Max binary position: "+maxBinaryPosition);
        
        sumOfBinaryPositions = new int[maxBinaryPosition];
        for (k = 0; k < maxBinaryPosition; k++)
            sumOfBinaryPositions[k] = 0;
        
        for (k = 0; k < stackCount; k++) {
             
             temp = stackValue[k];
             binaryPosition = 0;
             
             while(temp != 0){
                 sumOfBinaryPositions[binaryPosition] += temp % 2;
                 binaryPosition++;
                 temp = temp / 2;
             }
        
        }
        
        
        for(k = 0; k < maxBinaryPosition; k++){
            
            if(sumOfBinaryPositions[k] % 2 == 1){                
                isBalanced = false;
                break;                
            }
            
        }
        
        if(isBalanced)
            return (2);
        else
            return (1);
  
 }
 
 public static int countPrimary(int num){
  
  int divisor, primaryCount;
  
  divisor = 2;
  primaryCount = 0;
  
  while(num != 1){
   
   if(num % divisor == 0){
    primaryCount++;
    num = num / divisor;
   }
   else
    divisor++;
   
  }
  
  return primaryCount;
 }
}