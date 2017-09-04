/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

 public static void main(String[] argv){
  Scanner scn=new Scanner(System.in);
  
  Integer n=scn.nextInt();
  Integer k=scn.nextInt();
  long[] billboards=new long[n+2];
  billboards[0]=0;
  long sum=0;
  for(int i=1;i<=n;i++){
   billboards[i]=scn.nextLong();
   sum+=billboards[i];
  }
  billboards[n+1]=0;
  System.out.println(sum-getShortestDistance(0,n+1,k,billboards));
 }
 
 public static long getShortestDistance(int source,int destination, int k, long[] values){
  MinHeap heap=new MinHeap();
  List<BillBoard> billBoards = new ArrayList<BillBoard>();
  BillBoard billBoard=new BillBoard();
  billBoard.setIndex(0);
  billBoard.setValue(0l);
  billBoards.add(billBoard);
  heap.insert(billBoard);
  
  BillBoard smallest=heap.removemin();
  while(smallest.getIndex()!=values.length-1){
   long value=smallest.getValue();
   int index=smallest.getIndex();
   for(int i=1;i<k+2 && ((index+i)<values.length);i++){
    BillBoard elem;
    try{
      heap.update(billBoards.get(index+i), value+values[index+i]);
    }
    catch(IndexOutOfBoundsException e){
     elem=new BillBoard();
     elem.setIndex(index+i);
     elem.setValue(value+values[index+i]);
     heap.insert(elem);
     billBoards.add(elem);
    }
   }
   smallest=heap.removemin();
  }
  return smallest.getValue();
 }
}
 
 class BillBoard{
  private int index;
  private int heapIndex;
  private Long value;
  public int getIndex() {
   return index;
  }
  public void setIndex(int index) {
   this.index = index;
  }
  public int getHeapIndex() {
   return heapIndex;
  }
  public void setHeapIndex(int heapIndex) {
   this.heapIndex = heapIndex;
  }
  public Long getValue() {
   return value;
  }
  public void setValue(Long value) {
   this.value = value;
  }
  @Override
  public String toString() {
   return "BillBoard [index=" + index + ", heapIndex=" + heapIndex
     + ", value=" + value + "]";
  }
  
  
 }
 
 
 class MinHeap {
     private List<BillBoard> Heap;
     private int size;

     public MinHeap() {
      Heap=new ArrayList<BillBoard>();
  size = 0 ;
     }

     private int leftchild(int pos) {
  return 2*pos;
     }

     private int parent(int pos) {
  return  pos / 2;
     }
     
     private boolean isleaf(int pos) {
  return ((pos > (size-1)/2) && (pos <= size-1));
     }

     private void swap(int pos1, int pos2) {
  BillBoard tmp;

  tmp = Heap.get(pos1);
  Heap.set(pos1, Heap.get(pos2));
  Heap.set(pos2,tmp);
  Heap.get(pos1).setHeapIndex(pos1);
  Heap.get(pos2).setHeapIndex(pos2);
     }

     public void insert(BillBoard elem) {
  size++;
  Heap.add(elem);
  int current = size-1;
  elem.setHeapIndex(size-1);
  
  while (Heap.get(current).getValue() < Heap.get(parent(current)).getValue()) {
      swap(current, parent(current));
      current = parent(current);
  } 
     }
     
     public void update(BillBoard elem,long value){
      if(value<elem.getValue()){
       elem.setValue(value);
       pullUp(elem.getHeapIndex());
      }
     }

     public void print() {
  int i;
  for (i=1; i<=size;i++)
      System.out.print(Heap.get(i) + " ");
  System.out.println();
     }

     public BillBoard removemin() {
  swap(0,size-1);
  BillBoard tmp=Heap.get(size-1);
  Heap.remove(size-1);
  size--;
  if (size != 0)
      pushdown(0);
  return tmp;
     }

     private void pushdown(int position) {
  int smallestchild;
  while (!isleaf(position)) {
      smallestchild = leftchild(position);
      if ((smallestchild+1 < size) && (Heap.get(smallestchild).getValue() > Heap.get(smallestchild+1).getValue()))
       smallestchild = smallestchild + 1;
      if (Heap.get(position).getValue() <= Heap.get(smallestchild).getValue()) return;
      swap(position,smallestchild);
      position = smallestchild;
  }
     }
     
     private void pullUp(int position){
      while(position!=1){
       if(Heap.get(position).getValue()<Heap.get(parent(position)).getValue()){
        swap(position,parent(position));
        position=parent(position);
       }else{
        break;
       }
      }
     }

 }