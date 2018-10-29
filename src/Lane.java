import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Lane {
	protected boolean end = false;
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
		if(liveZombies.size()==0)return;
		Zombie closest = liveZombies.get(1);
		for(Zombie zmb: liveZombies)
		{
			if(zmb.position < closest.position)
			{
				closest = zmb;
			}
		}
		closest.takeDamage(damage);
	}
	protected void spawnZombieWave() 
	{
		liveZombies.add(zombieQ.poll());
	}
	protected void hitEnd()
	{
		if(end) {
			System.out.println("Zombies have gotten past! \n Game over! ");
		}
		liveZombies.clear();
		end = true;
		
	}
}