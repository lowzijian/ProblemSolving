import java.util.*;

public class JobScheduling {

	public static void main (String[] args)
	{
		JobScheduling jobScheduling = new JobScheduling();

		LinkedList<Job> jobs = new LinkedList<Job>();
		LinkedList <Worker> workers = new LinkedList <Worker>();

		Random rand = new Random();

		// generate 5 jobs with random deadline and profit
		for(int i = 0; i < 5; i++){
			int deadline = rand.nextInt(5) + 1;
			int profit = rand.nextInt(50) + 1;
			String jobID = "J" + (i+1);
			Job j = new Job(jobID, deadline, profit);
			jobs.add(j);
		}

		//generate 2 workers with random available time
		for (int i= 0; i<2; i++)
		{
			int availableTime = rand.nextInt(5)+1;
			String workerID = "W" + (i+1);
			Worker w = new Worker (workerID,availableTime);
			workers.add(w);
		}

		// Print out jobs details
		System.out.println("Job id :  Deadline :  Profit ");
		for (Job job: jobs)
		{
			System.out.println(job.jobid + " : " + job.deadline + " : " + job.profit);
		}

		// Print out worker id and available time
		System.out.println("\nWorker id   :  Available Time ");
		for(Worker w:workers)
		{
			System.out.println(w.workerid + "  " + (w.availableTime));
		}

		// schedule and assign the jobs
		int total_profit = jobScheduling.scheduleJobs(jobs,workers);

		// print the workers' assigned jobs
		jobScheduling.printScheduleandTotalProfit(workers,total_profit);
	}


	private int scheduleJobs (LinkedList<Job> jobs, LinkedList<Worker> workers)
	{
		// order the jobs in descending order of profits
		Collections.sort(jobs);
		int total_profit = 0;
		
		for(int i = 0; i < jobs.size(); i++){
			boolean jobAssigned = false;
			
			for(int j=jobs.get(i).deadline - 1; j >= 0; j--){
				// assign the job if the slot is empty
				for (int k = 0; k < workers.size(); k++) {
					if (workers.get(k).getAssignedJob().length > j) {
						if(workers.get(k).getAssignedJob()[j] == null) {
							workers.get(k).assignJob(j, jobs.get(i));
							total_profit += jobs.get(i).profit;
							jobAssigned = true;
							break;
						}
					}
				}
				
				// break the loop if the job already assigned
				if(jobAssigned)
					break;
			}		
		}

		return total_profit;
	}

	private void printScheduleandTotalProfit (LinkedList<Worker> workers , int total_profit)
	{
		System.out.println("--- Job Schedule --- ");
		for(Worker w:workers){
			System.out.print(w.workerid + ": ");
			Job[] assignedJob = w.getAssignedJob();

			for(Job j : assignedJob){
				if(j != null){
					System.out.print(j.jobid + "  ");
				}
			}
			System.out.println();
		}

		System.out.println("Total Profit : " + total_profit);
	}
}
