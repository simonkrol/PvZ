import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Lane {
	protected boolean end = false;
	protected boolean triggered = false;
	Spot[] spots;
	Queue<Zombie> zombieQ = new LinkedList<>();
	ArrayList<Zombie> liveZombies = new ArrayList<Zombie>();
	protected int distance;
	protected int numPlants = 0;
	
	
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
	public int getDistance()
	{
		return distance;
	}
	public void damageZombie(int damage)
	{
		if(liveZombies.size()==0)return;
		Zombie closest = liveZombies.get(0);
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
		if(zombieQ.size()>0) {
			liveZombies.add(zombieQ.poll());
		}
	}
	protected void hitEnd()
	{
		if(triggered)return;
		end = true;
		triggered = true;
		
	}

	protected int getPlantPos() {
		for(int i = distance/250 -1; i >= 0; i-- )
		{
			if(spots[i].getOccupied()) {
				return i; //in this case it will return the index of the spot. Later this will be the pixels of the plant
			}
		}
		return distance;
	}
	
	protected boolean hasPlants() {
		if(numPlants != 0)
		{
			return true;
		}
		return false;
	}
}