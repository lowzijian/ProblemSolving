import java.util.*;


public class jobScheduling {

	public static void main (String[] args)
	{
		jobScheduling jobScheduling = new jobScheduling();
		
		LinkedList<Job> jobs = new LinkedList <Job>();
		LinkedList<Job> scheduledJobs = new LinkedList <Job>();
		Random rand = new Random();
		
		for(int i=0;i<5;i++){
			int deadline = rand.nextInt(5) + 1;
			int profit = rand.nextInt(50) + 1;
			String id = "J" + (i+1);
			Job j = new Job(id, deadline, profit);
			jobs.add(j);
		}
        
        Collections.sort(jobs);
        System.out.println(" Job id   /  Deadline  /  Profit ");
        for (Job job: jobs)
        {
        	System.out.println(job.jobid + " / " + job.deadline + " / " + job.profit);
        }
        
        scheduledJobs= jobScheduling.jobSchedule(jobs,jobs.size());
        jobScheduling.printSchedule(scheduledJobs,scheduledJobs.size());
        jobScheduling.printMaximumProfit(scheduledJobs,scheduledJobs.size());
	}
	
	

	
	private LinkedList<Job> jobSchedule (LinkedList<Job> jobs, int size)
	{
		LinkedList<Job> scheduledJobs = new LinkedList <Job>();
		Boolean[] slots = new Boolean[size];
        Arrays.fill(slots, false);
        
        int result[] = new int[size];

        for (int i = 0; i < size; i++) {
            for (int j = jobs.get(i).deadline - 1; j >= 0; j--) {
                if (!slots[j]) {
                    result[j] = i;
                    slots[j] = true;
                    break;
                }
            }
        }

        for (int i = 0; i < jobs.size(); i++) {
            if (slots[i])
            	scheduledJobs.add(new Job(jobs.get(result[i]).jobid,jobs.get(result[i]).deadline,jobs.get(result[i]).profit));
        }
		
		return scheduledJobs;
	}
	
	private void printSchedule (LinkedList<Job> scheduledJobs, int size)
	{
		System.out.println("\njob schedule : ");
		for (int i=0; i< size;i++)
		{
			System.out.print(scheduledJobs.get(i).jobid );
			if (i != size-1)
			{
				System.out.print(" -> ");
			}
		}
	}
	
	private void printMaximumProfit (LinkedList<Job> scheduledJobs, int size)
	{
		int maximumProfit = 0;
		for (int i=0; i< size;i++)
		{
			maximumProfit += scheduledJobs.get(i).profit;
		}
		System.out.println("\n\nMaximum Profits gained : " + maximumProfit);
	}
}
