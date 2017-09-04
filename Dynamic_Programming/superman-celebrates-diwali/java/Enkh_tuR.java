import java.util.Scanner;
public class Solution{
 public static void main(String[] argsv){
  Scanner in = new Scanner(System.in);
  int N = in.nextInt();
  int H = in.nextInt();
  int I = in.nextInt();
  int[][] people = new int[N][H];
  for(int i=0;i<N;i++){
   int p = in.nextInt();
   for(int j=0;j<p;j++){
    people[i][in.nextInt()-1]++;
   }
  }
  int[] the_best = new int[H];
  int[][] endSpecified_best = new int[N][H];
  for(int i=0;i<H;i++){
            the_best[i] = -1;
   for(int j=0;j<N;j++){
    endSpecified_best[j][i] = -1;
   }
  }
 
  System.out.println(superman(H-1,people,the_best,endSpecified_best,I));
  in.close();
 }
 public static int superman(int floor, int[][] people, int[] the_best, int[][] endSpecified_best, int I){
  if(the_best[floor] > -1)
   return the_best[floor];
  else{
  int max = -1;
  for(int i=0;i<people.length;i++)
   if(max < superishman(i,floor,people,the_best,endSpecified_best,I))
    max = superishman(i,floor,people,the_best,endSpecified_best,I);
  the_best[floor] = max;
  return the_best[floor];
  }
 }
 public static int superishman(int end_building, int floor, int[][] people, int[] the_best, int[][] endSpecified_best, int I){
  if(floor == 0)
   return people[end_building][0];
  if(endSpecified_best[end_building][floor] > -1)
   return endSpecified_best[end_building][floor];
  else {  int a;
    if(floor >= I)
     a =  superman(floor-I,people,the_best,endSpecified_best,I) + people[end_building][floor];
    else
     a = -1;
    int b = superishman(end_building,floor-1,people,the_best,endSpecified_best,I) + people[end_building][floor];
    if(a > b)
     endSpecified_best[end_building][floor] = a;
    else 
     endSpecified_best[end_building][floor] = b;
  
  return endSpecified_best[end_building][floor];
   }
 }
}