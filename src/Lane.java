import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Lane {
	Spot[] spots;
	Queue<Zombie> zombieQ = new LinkedList<>();
	ArrayList<Zombie> liveZombies = new ArrayList<Zombie>();
	protected int distance;
	
	
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
		distance = length * 250;//The distance from side to side;
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
	public int getDistance()
	{
		return distance;
	}
	public void damageZombie(int damage)
	{
		//Deal damage to the first zombie in the lane
	}
}