import java.util.*;
class GameOFStone{
  public static void main(String[] args)
  {
    int T=0,n=0,m=0;
    Scanner sc=new Scanner(System.in);
    T=sc.nextInt();
    while(T--!=0)
    {
        n=sc.nextInt();
        m=sc.nextInt();
        if(m==1||n%2==0)
            System.out.println(""+2);
        else
          System.out.println(""+1);

    }
  }
}