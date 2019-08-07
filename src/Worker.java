
public class Worker {

	String workerid;
	int availableTime;
	Job[] assignedJob;

	public Worker (String workerid , int availableTime)
	{
		this.assignedJob = new Job[availableTime];
		this.workerid = workerid;
		this.availableTime = availableTime;
		
	}
	
	public Job[] getAssignedJob(){
		return assignedJob;
	}
	
	public void assignJob(int pos, Job job){
		assignedJob[pos] = job;
	}
}
