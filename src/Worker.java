
public class Worker implements Comparable <Worker>{

	String workerid;
	int availableTime;
	Job[] assignedJob;

	public Worker (String workerid , int availableTime)
	{
		if(availableTime < 0) {
			throw new IllegalArgumentException("Only Positive Numbers is available for availableTime."); 
		}
		else {
			this.availableTime = availableTime;
		}
		
		this.assignedJob = new Job[availableTime];
		this.workerid = workerid;
		
	}
	
	public Job[] getAssignedJob(){
		return assignedJob;
	}
	
	public void assignJob(int pos, Job job){
		assignedJob[pos] = job;
	}
	
	@Override
	public int compareTo(Worker otherWorker) {
		
		if (this.availableTime > otherWorker.availableTime)
			return 1;
		
		else if(this.availableTime < otherWorker.availableTime)
			return -1;
		
		else
			return 0;
	}
}
