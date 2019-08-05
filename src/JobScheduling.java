import java.util.*;


public class JobScheduling {

	public static void main (String[] args)
	{
		JobScheduling jobScheduling = new JobScheduling();

		LinkedList<Job> jobs = new LinkedList<Job>();
		LinkedList<Job> scheduledJobs = new LinkedList<Job>();
		Random rand = new Random();

		// generate 5 jobs with random deadline and profit
		for(int i = 0; i < 5; i++){
			int deadline = rand.nextInt(5) + 1;
			int profit = rand.nextInt(50) + 1;
			String id = "J" + (i+1);
			Job j = new Job(id, deadline, profit);
			jobs.add(j);
		}

		// sort the jobs in descending order of profits
		Collections.sort(jobs);
		System.out.println(" Job id   /  Deadline  /  Profit ");
		for (Job job: jobs)
		{
			System.out.println(job.jobid + " / " + job.deadline + " / " + job.profit);
		}

		// schedule the jobs
		scheduledJobs= jobScheduling.scheduleJobs(jobs, jobs.size());
		
		// print the scheduled jobs
		jobScheduling.printSchedule(scheduledJobs, scheduledJobs.size());
		
		// print the maximum profit obtained
		jobScheduling.printMaximumProfit(scheduledJobs, scheduledJobs.size());
	}




	private LinkedList<Job> scheduleJobs (LinkedList<Job> jobs, int size)
	{
		LinkedList<Job> scheduledJobs = new LinkedList<Job>();
		
		// array to indicate if a particular slot is filled
		Boolean[] slots = new Boolean[size];
		Arrays.fill(slots, false);

		// array to indicate the position of the scheduled job
		int[] result = new int[size];

		for (int i = 0; i < size; i++) {
			for (int j = jobs.get(i).deadline - 1; j >= 0; j--) {
				if (!slots[j]) {
					result[j] = i;
					slots[j] = true;
					break;
				}
			}
		}

		// add the scheduled jobs into an scheduledJobs array and return it
		for (int i = 0; i < jobs.size(); i++) {
			if (slots[i])
				scheduledJobs.add(new Job(jobs.get(result[i]).jobid, jobs.get(result[i]).deadline, jobs.get(result[i]).profit));
		}

		return scheduledJobs;
	}

	private void printSchedule (LinkedList<Job> scheduledJobs, int size)
	{
		// print the scheduled jobs
		System.out.println("\nJob Schedule :");
		for (int i = 0; i < size; i++)
		{
			System.out.print(scheduledJobs.get(i).jobid);
			if (i != size - 1)
			{
				System.out.print(" -> ");
			}
		}
	}

	private void printMaximumProfit (LinkedList<Job> scheduledJobs, int size)
	{
		// calculate and display the max profit
		int maximumProfit = 0;
		for (int i=0; i< size;i++)
		{
			maximumProfit += scheduledJobs.get(i).profit;
		}
		System.out.println("\n\nMaximum Profits gained : " + maximumProfit);
	}
}
