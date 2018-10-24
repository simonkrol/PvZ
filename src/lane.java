import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class lane {
	ArrayList<spot> spots;
	Queue<Object> monsterQ = new LinkedList<>();
	
	
	public lane() 
	{
		for(int i = 0; i < 6; i ++) {
			spots.add(new spot());
		}
	}
	
	public lane(int lenght)
	{
		for(int i = 0; i < lenght; i ++) {
			spots.add(new spot());
		}
	}
	
	public void addToQueue(Object zmb)
	{
		monsterQ.add(zmb);
	}
	
	public spot getZombie(int index)
	{
		return spots.get(index);
	}
	
	public Object getZombieQ()
	{
		return monsterQ.poll();
		
	}
}
