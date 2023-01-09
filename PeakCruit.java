import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.IntStream;

public class PeakCruit
{
    int task=0;
    public int jobScheduling(int[] s, int[] e, int[] p) 
    {
        int n = s.length;
        int[][] jobs = new int[n][3];

        for (int i = 0; i < n; ++i)
            jobs[i] = new int[] {s[i], e[i], p[i]};

        Arrays.sort(jobs, (x, y) -> x[0]-y[0] != 0 ? x[0]-y[0] : x[1]-y[1]);
                
        int[] dp = IntStream.range(0, n+1).map(j -> 0).toArray();
        int count =1;
        for (int i = n-1; i >= 0; --i)
        {
            int k = Arrays.binarySearch(jobs, new int[]{jobs[i][1], 0, 0}, 
                                        Comparator.comparingInt(j -> j[0]));

            k = (k>=0) ? k : -k-1;
            dp[i] = Math.max(dp[i+1], dp[k] + jobs[i][2]);

            

            if(dp[i+1]> dp[k] + jobs[i][2]){
                count++;
            }
            // System.out.println(count);
        }
        int length=0;
        for(int i=0;i<p.length;i++){
            length=length+p[i];
       
            //System.out.println(jobs[i][0]+"uuuu"+jobs[i][1]+"yyy"+jobs[i][2]);
        }
        length=length-dp[0];
        System.out.println("The number of tasks and earnings available for others");
        if(length==0){
            count=0;
        }
        System.out.println("Task: "+count);
        return length;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number of jobs");

        int size=sc.nextInt();
        System.out.println("Enter job start time, end time, and earnings");
        int [] start =new int[size];
        int [] end =new int[size];
        int [] earning =new int[size];
        
        for(int i=0;i<size;i++){
             start[i]=sc.nextInt();
            end[i]=sc.nextInt();
             earning[i]=sc.nextInt();
        }

        PeakCruit p=new PeakCruit();
        int store=p.jobScheduling(start,end,earning);
        System.out.println("earnings: "+store);
    }
}
