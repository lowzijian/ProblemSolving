import java.util.*;

public class Worker {

	String workerid;
	int availableTime;
	LinkedList <Job> assignedJob;

	public Worker (String workerid , int availableTime)
	{
		this.availableTime = availableTime;
		this.workerid = workerid;
		
	}
	
	public void setScheduledJob(LinkedList <Job> scheduledJob)
	{
		this.assignedJob = scheduledJob;
	}
	
	public LinkedList <Job> getAssignedJob ()
	{
		return assignedJob;
	}


}
