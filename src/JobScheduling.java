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

		// sort the jobs in descending order of profits
	
		System.out.println("Job id :  Deadline :  Profit ");
		
		
		// Print out jobs details
		for (Job job: jobs)
		{
			System.out.println(job.jobid + " : " + job.deadline + " : " + job.profit);
		}

		System.out.println("\nWorker id   :  Available Time ");
		

		// Print out worker id and available time
		for(Worker w:workers)
		{
			System.out.println(w.workerid + "  " + (w.availableTime));
		}
		
		
		//jobScheduling.printSchedule(workers, workers.size());
		int total_profit = jobScheduling.scheduleJobs(jobs,workers);

		// print the workers 'assigned jobs
		jobScheduling.printScheduleandTotalProfit(workers,total_profit);


	}


	private int scheduleJobs (LinkedList<Job> jobs, LinkedList<Worker> workers)
	{

		Collections.sort(jobs);
		int total_profit = 0;
		for(int i=0;i<jobs.size();i++){
			for(int j=0;j<workers.size();j++){
				boolean jobAssinged = false;
				for(int k=Math.min(workers.get(j).getAssignedJob().length - 1, jobs.get(i).deadline - 1); k>=0;k--){
					
					if(workers.get(j).getAssignedJob()[k] == null){
						workers.get(j).assignJob(k, jobs.get(i));
						jobAssinged = true;
						total_profit += jobs.get(i).profit;
						break;
					}
				}
				if(jobAssinged)
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
			
			// Check if the all the elements are null or not
			if(assignedJob[0] == null) {
				System.out.print("This worker is not assigned with any job.\n");
			}
			//
			else {
			for(Job j:assignedJob){
				if(j != null){
					System.out.print(j.jobid + "  ");
				}
			   
			}
			System.out.println();
			}
			
		}
		
		System.out.println("Total Profit : " + total_profit);
	}


}
