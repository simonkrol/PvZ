import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Lane {
	ArrayList<Spot> spots;
	Queue<Object> zombieQ = new LinkedList<>();
	
	
	public Lane() 
	{
		for(int i = 0; i < 6; i ++) {
			spots.add(new Spot());
		}
	}
	
	public Lane(int length)
	{
		for(int i = 0; i < length; i ++) {
			spots.add(new Spot());
		}
	}
	
	public void addToQueue(Object zmb)
	{
		zombieQ.add(zmb);
	}
	
	public Spot getZombie(int index)
	{
		return spots.get(index);
	}
	
	public Object getZombieQ()
	{
		return zombieQ.poll();
		
	}
}