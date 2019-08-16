public class Job implements Comparable <Job>{
	int profit;
	int deadline;
	String jobid;
	
	public Job() {
		
	}
	
	public Job(String jobid , int deadline , int profit)
	{
		this.profit = profit;
		this.deadline = deadline;
		this.jobid = jobid;
	}

	@Override
	public int compareTo(Job otherJob) {
		return otherJob.profit- this.profit;
	}
}