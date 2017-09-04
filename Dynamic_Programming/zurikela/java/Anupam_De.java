import java.util.*;
class Solution
    {
    public class node
        {
            long value;
            int active=1;
            int id;
            long taken,ntaken;
        }
    public static HashMap<Integer,node> map=new HashMap();
    public static ArrayList<Integer> L[]=new ArrayList[100005];
    public static void create(int index,long id)
        {
            Solution.node temp=new Solution().new node();
            temp.value=id;
            temp.taken=id;
            temp.id=index;
            map.put(index,temp);
        }
    public static void add_edge(int x,int y)
        {
            if(L[x]==null)
            {ArrayList<Integer> l=new ArrayList();
             L[x]=l;}
            if(L[y]==null)
            {
            ArrayList<Integer> l_=new ArrayList();
            L[y]=l_;
            }
            L[x].add(y);
            L[y].add(x);
        }
    public static void merge(int x,int k)
        {
            boolean marked[]=new boolean[100005];
            marked[x]=true;
            dfs(x,marked,0);
            Solution.node temp=new Solution().new node();
            temp.value=Math.max(map.get(x).taken,map.get(x).ntaken);
            temp.taken=temp.value;
            temp.id=k;
            map.put(x,null);
            map.put(k,temp);
        }
    public static void solve()
        {
            boolean marked[]=new boolean[100005];
            long f_ans=0;
            for(int x:map.keySet())
                {
                    if(map.get(x)!=null && marked[x]==false)
                        {
                            marked[x]=true;
                            dfs(x,marked,0);
                            long ans=Math.max(map.get(x).taken,map.get(x).ntaken); 
                            f_ans+=ans;
                            //System.out.println(x+" "+f_ans);
                        }
                }
            System.out.println(f_ans);
        }
    public static void print()
        {
            for(int i:map.keySet())
                {
                 if(map.get(i)!=null)System.out.println(map.get(i).id+" "+map.get(i).value);
                }
        }
    public static void dfs(int source,boolean marked[],int parent)
        {
            if(L[source]!=null)
            {
            long taken=map.get(source).taken,nottaken=0,tnodes=map.get(source).value;
            for(int j=0;j<L[source].size();j++)
                {
                    if(!marked[L[source].get(j)])
                        {
                            marked[L[source].get(j)]=true;
                            dfs(L[source].get(j),marked,source);
                        }
                }
            for(int j=0;j<L[source].size();j++)
                {
                    if(L[source].get(j)!=parent)
                        {
                        taken+=map.get(L[source].get(j)).ntaken;
                        tnodes+=map.get(L[source].get(j)).value;
                        }
                }
            for(int j=0;j<L[source].size();j++)
                {
                    if(L[source].get(j)!=parent)
                        nottaken+=Math.max(map.get(L[source].get(j)).taken,map.get(L[source].get(j)).ntaken);
                }
            for(int j=0;j<L[source].size();j++)
                {
                    if(L[source].get(j)!=parent)map.put(map.get(L[source].get(j)).id,null);
                }
            node root=map.get(source);
            root.taken=taken;
            root.ntaken=nottaken;
            root.active=0;
            map.put(source,root);
            L[source]=null;
            }
        }
    public static void main(String args[])
    {
        Scanner input=new Scanner(System.in);
        int q=input.nextInt();
        int k=1;
        while(q>0)
        {
            q--;
            String s=input.next();
            if(s.equals("A"))
            {
                create(k,input.nextLong());
                k++;
            }
            else if(s.equals("B"))
            {
                int x=input.nextInt();
                int y=input.nextInt();
                add_edge(x,y);
            }
            else if(s.equals("C"))
            {
                int x=input.nextInt();
                merge(x,k);
                k++;
            }
        }
     //System.out.println(map);
    //print();
    solve();
    }
    }