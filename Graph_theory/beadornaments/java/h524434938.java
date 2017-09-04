import java.math.BigInteger;
import java.util.Scanner;

public class Solution {

 public static void main(String[] args) {
  /*
   * Enter your code here. Read input from STDIN. Print output to STDOUT.
   * Your class should be named Solution.
   */
  Scanner scanner = new Scanner(System.in);
  int testCases = scanner.nextInt();
  for (int i = 0; i < testCases; i++) {
   int colors = scanner.nextInt();
   BigInteger[] beads = new BigInteger[colors];
   BigInteger sum = new BigInteger("0");
   for (int j = 0; j < colors; j++) {
    BigInteger bead = BigInteger.valueOf((long) scanner.nextInt());
    beads[j] = bead;
    sum = sum.add(bead);
   }
   if (colors == 1) {
    System.out.println(sum.pow(sum.intValue() - 2).mod(
      new BigInteger("1000000007")));
   } else {
    BigInteger product = new BigInteger("1");
    for (int k = 0; k < colors; k++) {
     product = product
       .multiply(beads[k].pow(beads[k].intValue() - 1));
    }
    product = product.multiply(sum.pow(colors - 2));
    System.out.println(product.mod(new BigInteger("1000000007")));
   }
  }
 }

}