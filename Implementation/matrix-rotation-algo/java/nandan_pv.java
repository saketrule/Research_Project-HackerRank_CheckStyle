import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Listnode {
  //*** fields ***
    private Object data;
    private Listnode next;
    private Listnode prev;

  //*** methods ***
    // 2 constructors
    public Listnode(Object d) {
 this(d,null,null);
    }

    public Listnode(Object d, Listnode n, Listnode p) {
 data = d;
 next = n;
 prev = p;
    }
    
    // access to fields
    public Object getData() {
        return data;
    }
    
    public Listnode getNext() {
        return next;
    }
    
    public Listnode getPrev() {
        return prev;
    }
        
    // modify fields
    public void setData(Object ob) {
        data = ob;
    }
    
    public void setNext(Listnode n) {
        next = n;
    }
    
    public void setPrev(Listnode n) {
        prev = n;
    }
}

public class Rotation {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int rows    = in.nextInt();
        int columns = in.nextInt();
        int rotationCount = in.nextInt();
        int[][] inArr = new int[rows][columns];
        int[][] outArr = new int[rows][columns];
        int counter = 1;
  //System.out.println();
        for(int x =0; x<rows; x++) {
            for(int y=0; y<columns; y++) {
                inArr[x][y] = in.nextInt();
                //inArr[x][y] = counter; counter++;
                //System.out.println(x+" "+y + " - " + inArr[x][y]);
            }
        }
        
     Listnode[] listNode = new Listnode[Math.min(rows,columns)/2];
     Listnode FirstListNode=null;
     Listnode LastListNode=null;
     
     counter = 0;
     int count = 0;
     int xmax = rows-1, ymax = columns-1;
     int xx=0, yy=0;
     while (count < rows*columns){
      FirstListNode=null;
      LastListNode=null;
      
      xx = counter;
      yy = counter;
      while(yy<=ymax){
       //System.out.println(xx+","+yy);
       if(FirstListNode == null){
        FirstListNode = new Listnode(inArr[xx][yy]);
        LastListNode = FirstListNode;
       }
       else{
        Listnode tempListNode = new Listnode(inArr[xx][yy]);
        LastListNode.setNext(tempListNode);
        tempListNode.setPrev(LastListNode);
        LastListNode = tempListNode;
       }
       yy++;
       count++;
      }
      
      xx = counter+1;
      yy = ymax;
      //System.out.println(counter + " - DOWN");
      while(xx<=xmax-1){
       //System.out.println(xx+","+yy);
       Listnode tempListNode = new Listnode(inArr[xx][yy]);
       LastListNode.setNext(tempListNode);
       tempListNode.setPrev(LastListNode);
       LastListNode = tempListNode;
       xx++;
       count++;      
      }

      xx = xmax;
      yy = ymax;
      //System.out.println(counter + " - LEFT");
      while(yy>=counter){
       //System.out.println(xx+","+yy);
       Listnode tempListNode = new Listnode(inArr[xx][yy]);
       LastListNode.setNext(tempListNode);
       tempListNode.setPrev(LastListNode);
       LastListNode = tempListNode;
       yy--;
       count++;      
      }

      xx = xmax-1;
      yy = counter;
      //System.out.println(counter + " - UP");
      while(xx>=counter+1){
       //System.out.println(xx+","+yy);
       Listnode tempListNode = new Listnode(inArr[xx][yy]);
       LastListNode.setNext(tempListNode);
       tempListNode.setPrev(LastListNode);
       LastListNode = tempListNode;
       xx--;
       count++;      
      }

      LastListNode.setNext(FirstListNode);
      FirstListNode.setPrev(LastListNode);
      listNode[counter]=FirstListNode;

      xmax--;
      ymax--;
      counter++;

     }
     //System.out.println("Count " + count);
     
     
     
     int d = rows, e = columns;
        for(int idx = 0; idx <counter; idx++){
      int movement = rotationCount%(2*(d+e-2));
      //System.out.println("Movement - " + movement);
      FirstListNode = listNode[idx];
      while(movement>0){
       FirstListNode = FirstListNode.getNext();
       movement--;
      }
      //System.out.println("FirstListNode - " + FirstListNode.getData());
      listNode[idx] = FirstListNode;
      d=d-2;
      e=e-2;
      //System.out.println("d - " + d);
      //System.out.println("e - " + e);
     }
     
     
     /*for(int idx = 0; idx <counter; idx++){
      System.out.println();
      FirstListNode = listNode[idx];
      System.out.print(FirstListNode.getData());
      Listnode nextListNode = FirstListNode.getNext();
      while(nextListNode != FirstListNode){
       System.out.print(" " + nextListNode.getData());
       nextListNode = nextListNode.getNext();
      }
     }*/
     
     
     count =0;
  counter = 0;
  count = 0;
     xmax = rows-1; ymax = columns-1;
  xx=0; yy=0;
  while (count < rows*columns){
  
      FirstListNode = listNode[counter];

      xx = counter;
      yy = counter;       
      while(yy<=ymax){
    //System.out.println(xx+","+yy);
       outArr[xx][yy] = (int)FirstListNode.getData();
       FirstListNode = FirstListNode.getNext();
       yy++;
       count++;
      }
      
      xx = counter+1;
      yy = ymax;
      while(xx<=xmax-1){
       //System.out.println(xx+","+yy);
       outArr[xx][yy] = (int)FirstListNode.getData();
       FirstListNode = FirstListNode.getNext();
       xx++;
       count++;      
      }

      xx = xmax;
      yy = ymax;
      while(yy>=counter){
       //System.out.println(xx+","+yy);
       outArr[xx][yy] = (int)FirstListNode.getData();
       FirstListNode = FirstListNode.getNext();
       yy--;
       count++;      
      }

      xx = xmax-1;
      yy = counter;
      while(xx>=counter+1){
       //System.out.println(xx+","+yy);
       outArr[xx][yy] = (int)FirstListNode.getData();
       FirstListNode = FirstListNode.getNext();
       xx--;
       count++;      
      }

      xmax--;
      ymax--;
      counter++; 
     }
     for(int x =0; x<rows; x++) {   
            for(int y=0; y<columns; y++) {
                System.out.print(outArr[x][y] + " ");
            }
            System.out.println();
        }
 }
}