import java.io.*;
import java.util.*;

class Solution
{

        private int nodeCount;
        private int[][] adjMatrix;

        public static String padLeft(String s, int n)
        {
                return String.format("%1$" + n + "s", s);
        }

        public void printArray()
        {
                for (int i = 0; i < nodeCount; i++)
                {

                        for (int j = 0; j < nodeCount; j++)
                        {
                                if (adjMatrix[i][j] == 1000000)
                                {
                                        System.out.print(padLeft("X", 4));
                                }
                                else
                                {
                                        System.out.print(padLeft(String.valueOf(adjMatrix[i][j]), 4));
                                }
                        }
                        System.out.println("");
                }

        }

        public Solution()
        {

                Scanner in = new Scanner(System.in);

                int edgeCount;
                nodeCount = in.nextInt();
                edgeCount = in.nextInt();

                adjMatrix = new int[nodeCount][];

//                System.out.println(nodeCount);

                for (int i = 0; i < nodeCount; i++)
                {
                        adjMatrix[i] = new int[nodeCount];

                        for (int j = 0; j < nodeCount; j++)
                        {
//                                System.out.println(i + " " + j);
                                adjMatrix[i][j] = 1000000;
                        }

                        adjMatrix[i][i] = 0;
                }

//                printArray();

                int u, v, weight;

                for (int i = 0; i < edgeCount; i++)
                {
                        u = in.nextInt() - 1;
                        v = in.nextInt() - 1;
                        weight = in.nextInt();

//                        System.out.println(weight);

                        adjMatrix[u][v] = weight;
                }

//                System.out.println(nodeCount);
//                printArray();

                for (int pivot = 0; pivot < nodeCount; pivot++)
                {

                        for (int a = 0; a < nodeCount; a++)
                        {

                                for (int b = 0; b < nodeCount; b++)
                                {
                                        if ((adjMatrix[a][pivot] + adjMatrix[pivot][b]) < adjMatrix[a][b])
                                        {
                                                adjMatrix[a][b] = adjMatrix[a][pivot] + adjMatrix[pivot][b];
                                        }
                                }

                        }

//                        printArray();

                }

                int checkCount;
                checkCount = in.nextInt();
                for (int pivot = 0; pivot < checkCount; pivot++)
                {
                        u = in.nextInt() - 1;
                        v = in.nextInt() - 1;

                        if (adjMatrix[u][v] == 1000000)
                        {
                                System.out.println(-1);
                        }
                        else
                        {
                                System.out.println(adjMatrix[u][v]);
                        }
                }

        }

        public static void main(String[] args)
        {
                Solution Sol = new Solution();
        }
}