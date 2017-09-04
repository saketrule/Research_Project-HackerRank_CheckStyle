import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Solution {

 House [] homes;
 Road [] roads;

 public static void main(String [] args){

  Solution rob = new Solution();
  rob.getInput();

  int maxMoney = 0;

  int numRobbed = 1;

  /* RATCHET DON'T DO THIS */
  ArrayList <ArrayList <House>> previousPaths = new ArrayList <ArrayList <House>>();

  ArrayList <House> lucrativelyRobbed = new ArrayList <House> (rob.homes.length);


  for(int i=0;i<rob.homes.length*500;i++){

   int currentMoney = 0;
   ArrayList <House> currentRobbed = new ArrayList <House> (rob.homes.length);

   while(!rob.allClosed()){

    int toRob = (int)Math.floor(new Random().nextFloat()*rob.homes.length);
    /* (SUPER HACKY) This is a horrible way to do this in reality */

    if(rob.homes[toRob].canAccess){
     currentRobbed.add(rob.homes[toRob]);
     currentMoney += rob.homes[toRob].rob();
    }

    //System.out.println(currentMoney);
   }

   boolean contained = false;
   for(ArrayList<House> robbed: previousPaths){
    if(robbed.containsAll(currentRobbed) && currentRobbed.containsAll(robbed))
     contained = true;
   }

   if(!contained){

    previousPaths.add(currentRobbed);

    if(currentMoney>maxMoney){
     maxMoney = currentMoney;
     numRobbed = 1;
    }
    else if(currentMoney==maxMoney){
     numRobbed++;
    }
   }

   else{
    //System.out.println(currentRobbed);
   }

   rob.allGood();
  }

  System.out.println(maxMoney + " " + numRobbed);


 }

 public void getInput(){

  Scanner in = new Scanner(System.in);

  homes = new House[in.nextInt()];
  roads = new Road[in.nextInt()];

  for(int i=0;i<homes.length;i++){
   homes[i] = new House();
   homes[i].money = in.nextInt();
  }

  for(int i=0;i<roads.length;i++){
   roads[i] = new Road();

   int houseOne = in.nextInt()-1;
   int houseTwo = in.nextInt()-1;

   roads[i].one = homes[houseOne];
   roads[i].two = homes[houseTwo];

   homes[houseOne].label = houseOne;
   homes[houseTwo].label = houseTwo;

   homes[houseOne].addNeighbor(homes[houseTwo]);
   homes[houseTwo].addNeighbor(homes[houseOne]);
  }

  in.close();
 }

 private boolean allClosed(){

  for(int i=0;i<homes.length;i++){
   if(homes[i].canAccess){
    return false;
   }
  }

  return true;
 }

 private void allGood(){

  for(House home:homes)
   home.canAccess = true;
 }

 class House{
  int money;
  int label;
  boolean robbed=false;
  boolean canAccess=true;
  ArrayList <House> neighbors = new ArrayList <House>();

  private void addNeighbor(House toAdd){
   neighbors.add(toAdd);
  }

  private int rob(){
   canAccess=false;
   robbed=true;
   for(House neighbor:neighbors)
    neighbor.canAccess=false;

   return money;
  }

  public String toString(){
   return "I am number " + label + ".";
  }

  public int compareTo(House other){
   return this.label - other.label;
  }
 }

 class Road{
  House one;
  House two;
 }

}