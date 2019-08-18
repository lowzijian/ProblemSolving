public class Job implements Comparable <Job>{
	int profit;
	int deadline;
	String jobid;
	
	public Job() {
		
	}
	
	public Job(String jobid , int deadline , int profit)
	{
		if(profit < 0) {
			throw new IllegalArgumentException("Only Positive Numbers is available for profit."); 
		}
		else {
			this.profit = profit;
		}
		
		if(deadline < 0) {
			throw new IllegalArgumentException("Only Positive Numbers is available for deadline"); 
		}
		else {
			this.deadline = deadline;
		}
		
		this.jobid = jobid;
	}

	@Override
	public int compareTo(Job otherJob) {
		return otherJob.profit- this.profit;
	}
}