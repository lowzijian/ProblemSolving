import java.util.*;

public class JobScheduling {

	public static void main (String[] args)
	{
		JobScheduling jobScheduling = new JobScheduling();

		LinkedList<Job> jobs = new LinkedList<Job>();
		LinkedList<Job> jobs2 = new LinkedList<Job>();
		LinkedList <Worker> workers = new LinkedList <Worker>();
		LinkedList <Worker> workers2 = new LinkedList <Worker>();
		
		
		
		Random rand = new Random();

		// generate 5 jobs with random deadline and profit
		for(int i = 0; i < 5; i++){
			int deadline = rand.nextInt(5) + 1;
			int profit = rand.nextInt(50) + 1;
			String jobID = "J" + (i+1);
			Job j = new Job(jobID, deadline, profit);
			Job j1 = new Job(jobID, deadline, profit);
			jobs.add(j);
			jobs2.add(j1);
		}

		//generate 2 workers with random available time
		for (int i= 0; i<2; i++)
		{
			int availableTime = rand.nextInt(5)+1;
			String workerID = "W" + (i+1);
			Worker w = new Worker (workerID,availableTime);
			Worker w1 = new Worker (workerID, availableTime);
			workers.add(w);
			workers2.add(w1);
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
		
		int total_profit2 = jobScheduling.scheduleJobs2(jobs2,workers2);
		jobScheduling.printScheduleandTotalProfit(workers2,total_profit2);
		
	}
	
	private int scheduleJobs (LinkedList<Job> jobs, LinkedList<Worker> workers)
	{
		// order the jobs in descending order of profits
		Collections.sort(jobs);
		int total_profit = 0;

		for(int i = 0; i < jobs.size(); i++){
			for(int j = 0; j < workers.size(); j++){
				boolean jobAssigned = false;

				// schedule the jobs as earlier as possible\
				for(int k=Math.min(workers.get(j).getAssignedJob().length - 1, jobs.get(i).deadline - 1); k>=0;k--){
					
					// assign the job if the slot is empty
					if(workers.get(j).getAssignedJob()[k] == null){
						workers.get(j).assignJob(k, jobs.get(i));
						jobAssigned = true;
						total_profit += jobs.get(i).profit;
						break;
					}

				}
				
				// break the loop if the job already assigned
				if(jobAssigned)
					break;
				
			}
		}
		return total_profit;
	}
	
	private int scheduleJobs2 (LinkedList<Job> jobs, LinkedList<Worker> workers)
	{
		// order the jobs in descending order of profits
		Collections.sort(jobs);
		int total_profit = 0;
		Collections.sort(workers);

		for(int i = 0; i < jobs.size(); i++){
			boolean flag = true;
			int count = 0;
			int jobPost;
			
			while(flag) {
				int index = 0;
				
				for(int j = 0; j< workers.size(); j++) {
					index = j;
					int workerAvailableTime = workers.get(index).getAssignedJob().length - 1;
					int deadline = jobs.get(i).deadline - 1;
					int k = Math.min(workerAvailableTime, deadline);
					
					jobPost = k - count;
					
					if(jobPost < 0 && j == workers.size()-1) {
						flag = false;
						break;
					}
					
					if(jobPost >= 0) {
						if(workers.get(j).getAssignedJob()[jobPost] == null){
							workers.get(j).assignJob(jobPost, jobs.get(i));
							flag = false;
							total_profit += jobs.get(i).profit;
							break;
						}
					}
				}
				
				count++;
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
