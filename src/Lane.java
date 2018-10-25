import java.util.LinkedList;
import java.util.Queue;

public class Lane {
	Spot[] spots;
	Queue<Zombie> zombieQ = new LinkedList<>();
	
	
	public Lane() 
	{
		this(6);
	}
	
	public Lane(int length)
	{
		spots = new Spot[length];
		for(int i = 0; i < length; i ++) {
			spots[i] = new Spot();
		}
	}
	
	public void addToQueue(Zombie zombie)
	{
		zombieQ.add(zombie);
	}
	
	public Spot getSpot(int index)
	{
		return spots[index];
	}
	
	public Zombie getZombie()
	{
		return zombieQ.poll();
		
	}
}