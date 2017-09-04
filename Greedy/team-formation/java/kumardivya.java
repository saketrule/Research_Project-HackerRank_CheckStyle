import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class team_formation {
 class Group {
  int start;
  int end;
 }
 class MyClass {
  int value;
  int count;
 }
 
 public static void main(String[] arg) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out)); 
   Scanner scan = new Scanner(System.in);
   int T = scan.nextInt();
   while(T-- > 0) {
    int N = scan.nextInt();
    if(N==0) {
     //System.out.println(0);
     writer.write(0+"\n");
     continue;
    }
    int[] inputArray = new int[N];
    for(int i=0;i<N;i++) {
     inputArray[i] = scan.nextInt();
    }
    Arrays.sort(inputArray);
    ArrayList<MyClass> noRept = new ArrayList<MyClass>(N);
    int prev = -1938948844;
    int count = 0;
    for(int i=0;i<N;i++) {
     if(prev == -1938948844) {
      count = 1;
      prev = inputArray[i];
     }else if(inputArray[i] == prev) {
      count++;
     }else {
                     MyClass temp = new team_formation().new MyClass();
      temp.value = prev;
      temp.count = count;
      noRept.add(temp);
      count = 1;
      prev = inputArray[i];
     }
     if(i == N-1){
      MyClass temp = new team_formation().new MyClass();
      temp.value = prev;
      temp.count = count;
      noRept.add(temp);
      count = 0;
      prev = -1938948844;
     }
     
    }
    ArrayList<Group> groupArray = new ArrayList<Group>(N);
    int start=-1938948844,end=-1938948844;
    int prevNum = -1938948844;
    for(int i=0;i<noRept.size();i++) {
                 //System.out.println(noRept.get(i).value+"+++++++++++"+noRept.get(i).count);
     if(prevNum == -1938948844) {
      start = i;
      end = i;
      prevNum = noRept.get(i).value;
     }else if(noRept.get(i).value ==  prevNum+1) {
      end = i;
                     prevNum = noRept.get(i).value;
     }else {
                     //System.out.println("start = "+start + "  end = "+end);
      Group temp = new team_formation().new Group();
      temp.start = start;
      temp.end = end;
      groupArray.add(temp);
                     prevNum = noRept.get(i).value;
      start=i;
      end=i; 
     }
     if(i == noRept.size()-1) {
                   // System.out.println("start = "+start + "  end = "+end);
      
      Group temp = new team_formation().new Group();
      temp.start = start;
      temp.end = end;
      groupArray.add(temp);
     }
    }
             
    ArrayList<Integer> sd = new ArrayList<Integer>(N);
    for(int i=0;i<groupArray.size();i++){
     int s = sd.size() - 1, e = sd.size() - 1;
     int start1 = groupArray.get(i).start;
     int end1 = groupArray.get(i).end;
     int currSize = -12;
     int currCn = -12;
                 //System.out.println(start1+"-----------"+end1);
     for(int j=start1;j<=end1;j++) {
                     if(j==start1) {
                      int curH = noRept.get(j).count;
                      s = sd.size();
                      for(int k=0;k<curH;k++) {
                       sd.add(1);
                      }
                      e = sd.size() - 1;
                      currCn = curH;
                     }else {
                      if(noRept.get(j).count > currCn) {
                       int extra = noRept.get(j).count - currCn;
                       for(int k=s;k<=e;k++) {
                           sd.set(k,sd.get(k)+1);
                          }
                       for(int k=0;k<extra;k++) {
                           sd.add(1);
                          }
                       currCn = noRept.get(j).count;
                       e = sd.size() - 1;
                      }else if(noRept.get(j).count < currCn) {
                       int loosing = currCn - noRept.get(j).count;
                       s = s + loosing;
                       for(int k=s;k<=e;k++) {
                        sd.set(k, sd.get(k)+1);
                       }
                       currCn = noRept.get(j).count;
                      }else if(noRept.get(j).count == currCn) {
                       for(int k=s;k<=e;k++) {
                           sd.set(k,sd.get(k)+1);
                          }
                       
                      }
                     }
                     
     }
     
    }
    
    int min = sd.get(0);
    for(int i=0;i<sd.size();i++) {
                // System.out.println(sd.get(i));
     if(sd.get(i)<min) {
      min = sd.get(i);
     }
    }
    //System.out.println(min);
    writer.write(min+"\n");
   }
   writer.flush();
   writer.close();
   scan.close();
 }

}