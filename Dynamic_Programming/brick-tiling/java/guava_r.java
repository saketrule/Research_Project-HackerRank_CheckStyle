import java.util.Scanner;


public class Solution 
{
 public static boolean allFilled(int M, int N, String[] board)
 {
  int i,j;
  for (i=0;i<M;i++)
   for (j=0;j<N;j++) 
    if (board[i].charAt(j)=='.') 
     return false;
  return true;    
 }

 public static int calculateWays(int M, int N, String[] board)
 {
  if (allFilled(M,N,board))
   return 1;
  //find all possible combinations
  int i,j,w=0;
  //***
  //*
  for (i=0;i<M-1;i++)
   for (j=0;j<=N-3;j++)
    if (board[i].charAt(j)=='.' && board[i].charAt(j+1)=='.' && board[i].charAt(j+2)=='.' && board[i+1].charAt(j)=='.')
    {
     board[i]=board[i].substring(0,j)+"###"+board[i].substring(j+3);
     board[i+1]=board[i+1].substring(0,j)+"#"+board[i+1].substring(j+1);
     w+=calculateWays(M,N,board);
     //revert 
     board[i]=board[i].substring(0,j)+"..."+board[i].substring(j+3);
     board[i+1]=board[i+1].substring(0,j)+"."+board[i+1].substring(j+1);
    }  
  //**
  //*
  //*
  for (i=0;i<M-2;i++)
  {
   for (j=0;j<=N-2;j++)
    if (board[i].charAt(j)=='.' && board[i].charAt(j+1)=='.' && board[i+1].charAt(j)=='.' && board[i+2].charAt(j)=='.')
    {
     board[i]=board[i].substring(0,j)+"##"+board[i].substring(j+2);
     board[i+1]=board[i+1].substring(0,j)+"#"+board[i+1].substring(j+1);
     board[i+2]=board[i+2].substring(0,j)+"#"+board[i+2].substring(j+1);
     w+=calculateWays(M,N,board);
     //revert 
     board[i]=board[i].substring(0,j)+".."+board[i].substring(j+2);
     board[i+1]=board[i+1].substring(0,j)+"."+board[i+1].substring(j+1);
     board[i+2]=board[i+2].substring(0,j)+"."+board[i+2].substring(j+1);
    } 
  }
  //**
  // *
  // *
  for (i=0;i<M-2;i++)
  {
   for (j=1;j<=N-1;j++)
    if (board[i].charAt(j)=='.' && board[i].charAt(j-1)=='.' && board[i+1].charAt(j)=='.' && board[i+2].charAt(j)=='.')
    {
     board[i]=board[i].substring(0,j-1)+"##"+board[i].substring(j+1);
     board[i+1]=board[i+1].substring(0,j)+"#"+board[i+1].substring(j+1);
     board[i+2]=board[i+2].substring(0,j)+"#"+board[i+2].substring(j+1);
     w+=calculateWays(M,N,board);
     //revert 
     board[i]=board[i].substring(0,j-1)+".."+board[i].substring(j+1);
     board[i+1]=board[i+1].substring(0,j)+"."+board[i+1].substring(j+1);
     board[i+2]=board[i+2].substring(0,j)+"."+board[i+2].substring(j+1);
    } 
  }
  //  *
  //***
  for (i=0;i<M-1;i++)
   for (j=2;j<=N-1;j++)
    if (board[i].charAt(j)=='.' && board[i+1].charAt(j-1)=='.' && board[i+1].charAt(j-2)=='.' && board[i+1].charAt(j)=='.')
    {
     board[i+1]=board[i+1].substring(0,j-2)+"###"+board[i+1].substring(j+1);
     board[i]=board[i].substring(0,j)+"#"+board[i].substring(j+1);
     w+=calculateWays(M,N,board);
     //revert 
     board[i+1]=board[i+1].substring(0,j-2)+"..."+board[i+1].substring(j+1);
     board[i]=board[i].substring(0,j)+"."+board[i].substring(j+1);
    }
  //
  //

  //***
  //  *
  for (i=0;i<M-1;i++)
   for (j=2;j<=N-1;j++)
    if (board[i].charAt(j)=='.' && board[i].charAt(j-1)=='.' && board[i].charAt(j-2)=='.' && board[i+1].charAt(j)=='.')
    {
     board[i]=board[i].substring(0,j-2)+"###"+board[i].substring(j+1);
     board[i+1]=board[i+1].substring(0,j)+"#"+board[i+1].substring(j+1);
     w+=calculateWays(M,N,board);
     //revert 
     board[i]=board[i].substring(0,j-2)+"..."+board[i].substring(j+1);
     board[i+1]=board[i+1].substring(0,j)+"."+board[i+1].substring(j+1);
    }
  //*
  //*
  //**
  for (i=0;i<M-2;i++)
  {
   for (j=1;j<=N-2;j++)
    if (board[i].charAt(j)=='.' && board[i+1].charAt(j)=='.' && board[i+2].charAt(j)=='.' && board[i+2].charAt(j+1)=='.')
    {
     board[i]=board[i].substring(0,j)+"#"+board[i].substring(j+1);
     board[i+1]=board[i+1].substring(0,j)+"#"+board[i+1].substring(j+1);
     board[i+2]=board[i+2].substring(0,j)+"##"+board[i+2].substring(j+2);
     w+=calculateWays(M,N,board);
     //revert 
     board[i]=board[i].substring(0,j)+"."+board[i].substring(j+1);
     board[i+1]=board[i+1].substring(0,j)+"."+board[i+1].substring(j+1);
     board[i+2]=board[i+2].substring(0,j)+".."+board[i+2].substring(j+2);
    } 
  }
  // *
  // *
  //**
  for (i=0;i<M-2;i++)
  {
   for (j=1;j<=N-1;j++)
    if (board[i].charAt(j)=='.' && board[i+1].charAt(j)=='.' && board[i+2].charAt(j)=='.' && board[i+2].charAt(j-1)=='.')
    {
     board[i]=board[i].substring(0,j)+"#"+board[i].substring(j+1);
     board[i+1]=board[i+1].substring(0,j)+"#"+board[i+1].substring(j+1);
     board[i+2]=board[i+2].substring(0,j-1)+"##"+board[i+2].substring(j+1);
     w+=calculateWays(M,N,board);
     //revert 
     board[i]=board[i].substring(0,j)+"."+board[i].substring(j+1);
     board[i+1]=board[i+1].substring(0,j)+"."+board[i+1].substring(j+1);
     board[i+2]=board[i+2].substring(0,j-1)+".."+board[i+2].substring(j+1);
    } 
  }
  //*  
  //***
  for (i=0;i<M-1;i++)
   for (j=2;j<=N-3;j++)
    if (board[i].charAt(j)=='.' && board[i+1].charAt(j+1)=='.' && board[i+1].charAt(j+2)=='.' && board[i+1].charAt(j)=='.')
    {
     board[i+1]=board[i+1].substring(0,j)+"###"+board[i+1].substring(j+3);
     board[i]=board[i].substring(0,j)+"#"+board[i].substring(j+1);
     w+=calculateWays(M,N,board);
     //revert 
     board[i+1]=board[i+1].substring(0,j)+"..."+board[i+1].substring(j+3);
     board[i]=board[i].substring(0,j)+"."+board[i].substring(j+1);
    }
  return w;
 }
 public static void main(String args[])
 {
  Scanner s=new Scanner(System.in);
  int T=s.nextInt(),i,M,N,j;
  String board[];
  for (i=0;i<T;i++)
  {
   M=s.nextInt();
   N=s.nextInt();
   board=new String[M];
   for (j=0;j<M;j++) board[j]=s.next(); 
   System.out.println(calculateWays(M,N,board));
  }
 }
}