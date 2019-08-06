import java.util.*;


public class JobScheduling {

	public static void main (String[] args)
	{
		JobScheduling jobScheduling = new JobScheduling();

		LinkedList<Job> jobs = new LinkedList<Job>();
		LinkedList<Job> scheduledJobs = new LinkedList<Job>();
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
		Collections.sort(jobs);
		System.out.println("Job id   /  Deadline  /  Profit ");

		for (Job job: jobs)
		{
			System.out.println(job.jobid + " / " + job.deadline + " / " + job.profit);
		}

		System.out.println("\nWorker id   /  Available Time ");

		for (Worker worker: workers)
		{
			System.out.println(worker.workerid + " / " + worker.availableTime );
		}

		// schedule the jobs
		jobScheduling.scheduleJobs(jobs,workers);

		// print the scheduled jobs
		jobScheduling.printSchedule(workers, workers.size());

	}


	private void scheduleJobs (LinkedList<Job> jobs, LinkedList<Worker> workers)
	{


		for (int h=0; h< workers.size();h++)
		{
			// Scheduled jobs for one worker 
			LinkedList<Job> scheduledJobs = new LinkedList<Job>();

			// array to indicate if a particular slot is filled
			int availableTime = workers.get(h).availableTime;

			Boolean[] slots = new Boolean[availableTime];
			Arrays.fill(slots, false);

			// array to indicate the position of the scheduled job
			int[] result = new int[jobs.size()];


			//algorithm
			for (int k = 0; k < availableTime;k++)
			{
				for(int i=0; i< jobs.size(); i++)
				{
						if (!slots[k]) {
							result[k] = i;
							slots[k] = true;
							break;
						}
				}
				
			}


			//if jobs are all taken by the first worker, break the loop

			if (jobs.size()>0)
			{
				// add all scheduled jobs into an scheduledJobs list
				for (int i = 0; i < jobs.size(); i++) {
					if (slots[i])
						scheduledJobs.add(new Job(jobs.get(result[i]).jobid, jobs.get(result[i]).deadline, jobs.get(result[i]).profit));
				}
				// assigned the scheduledJobs list to the worker
				workers.get(h).setScheduledJob(scheduledJobs);

				//the original lists of jobs will remove the taken jobs of one of the worker, before scheduling for the next worker
				jobs.removeAll(scheduledJobs);
				System.out.print(jobs);
			}
			else
				break;	
		}


	}

	private void printSchedule (LinkedList<Worker> workers, int size)
	{
		LinkedList <Job> assignedJobs = new LinkedList <Job>();
		// print the scheduled jobs
		System.out.println("\n --- Job Schedule --- \n\n");

		for (int i = 0; i < size; i++)
		{
			//get the assignedJobs for worker
			assignedJobs=workers.get(i).getAssignedJob();
			System.out.println("Job Schedule for worker " + (i+1) + "\n");

			if (assignedJobs.size() > 0 )
			{
				for (int h=0; h < assignedJobs.size();h++)
				{
					System.out.print(assignedJobs.get(h).jobid);

					//print out when it is the not the last job item to be print out
					if (h != assignedJobs.size() - 1)
						System.out.print(" -> ");
				}
				printMaximumProfit(assignedJobs,assignedJobs.size());
			}
			else 
				System.out.print("No jobs is assigned to this worker");
		}
	}

	private void printMaximumProfit (LinkedList<Job> assignedJobs, int size)
	{
		// calculate and display the max profit
		int maximumProfit = 0;

		for (int i=0; i< size;i++)
		{
			maximumProfit += assignedJobs.get(i).profit;
		}
		System.out.println("\nMaximum Profits gained : " + maximumProfit);
	}
}
