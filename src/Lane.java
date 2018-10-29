/**
 * The lane class, seperates the game board into each lane.
 * @author Boyan Siromahov and Simon Krol
 * @version Oct 29, 2018
 */
import java.util.ArrayList;

public class Lane
{
	private int endState = 0;
	private boolean triggered = false;
	private Spot[] spots;
	private ArrayList<Zombie> liveZombies = new ArrayList<Zombie>();
	private int distance;
	
	/**
	 * Construct a default lane of length 8, where the last 2 spots can not host plants
	 */
	protected Lane()
	{
		this(8, 2);
	}
	
	/**
	 * Construct a lane
	 * @param length The number of spots in the lane
	 * @param unplaceable The number of spots at the end of the lane that plants may not live on
	 */
	protected Lane(int length, int unplaceable)
	{
		spots = new Spot[length];
		for (int i = 0; i < length; i++)
		{
			spots[i] = new Spot(length - i > unplaceable);
		}
		distance = length * 250;// The distance from side to side;
	}

	/**
	 * Return the spot at the given index
	 * @param index The index of a given spot
	 * @return Spot at given index
	 */
	protected Spot getSpot(int index)
	{
		return spots[index];
	}
	/**
	 * Returns the length of the lane in distance, where each spot is 250 units wide
	 * @return The length of the lane
	 */
	protected int getDistance()
	{
		return distance;
	}

	/**
	 * Damages the first zombie in the lane
	 * @param damage The amount of damage the zombie should take
	 */
	protected void damageZombie(int damage)
	{
		if (liveZombies.size() == 0)
			return;
		Zombie closest = liveZombies.get(0);
		for (Zombie zmb : liveZombies)
		{
			if (zmb.position > closest.position)
			{
				closest = zmb;
			}
		}
		closest.takeDamage(damage);
	}
	
	/**
	 * Spawn a zombie in the lane
	 */
	protected void spawnZombie()
	{
		liveZombies.add(new BasicZombie(this));
	}

	/**
	 * Triggered when a zombie hits the end, causes the lawnmower to trigger, then causes the game to end
	 */
	protected void hitEnd()
	{
		if (triggered)
			return;
		endState += 1;
		triggered = true;

	}
	
	/**
	 * Check if a zombie is standing directly in front of a plant
	 * @param position The position of the zombie
	 * @return True if in front of plant, false otherwise
	 */
	protected Boolean checkFrontPlant(int position)
	{
		int index = spots.length - 2 - (int) Math.floor(position / 250.0);
		if (index < 0 || index >= spots.length)
			return false;
		return spots[index].getOccupied();
	}

	/**
	 * Returns the plant in the spot closest to the zombies
	 * @return The most upfront plant
	 */
	protected Plant getFrontPlant()
	{
		for (int i = spots.length - 1; i >= 0; i--)
		{
			if (spots[i].getOccupied())
				return spots[i].getPlant();
		}
		return null;
	}

	/**
	 * Iterates through all plants and zombies in the lane and causes them to update
	 * @param curLevel The current level
	 */
	protected void allTurn(Level curLevel)
	{
		for (Spot spot : spots)
		{
			if (spot.getOccupied())
				spot.getPlant().turn(curLevel);

		}
		for (Zombie zombie : liveZombies)
		{
			zombie.turn(curLevel);
		}
		if (triggered) //check if lawnmower has been triggered
		{
			liveZombies.clear();
			triggered = false;
		}
	}

	/**
	 * Check to see if the player has lost
	 * @return True if lost, false otherwise
	 */
	protected boolean checkFail()
	{
		return endState >= 2;
	}

	/**
	 * Check to see if the game is still ongoing
	 * @return True if ongoing, false otherwise
	 */
	protected boolean checkNoWin()
	{
		if (liveZombies.size() != 0)
			return true;
		return false;
	}
	
	/**
	 * Return if no zombies exist in the lane
	 * @return True if no zombies, false otherwise
	 */
	protected boolean noZombies()
	{
		return liveZombies.size() == 0;
	}

	/**
	 * Kill a given zombie in the lane
	 * @param toKill The zombie being killed
	 */
	protected void killZombie(Zombie toKill)
	{
		liveZombies.remove(toKill);
	}

	/**
	 * Return string based info about the lane, used to build the GUI
	 * @return A string representing the lane
	 */
	protected String getInfo()
	{
		int curSpot = 1;
		String laneInfo = "";
		for (Spot spot : spots)
		{
			if (spot.getOccupied()) //check all spots
			{
				if (spot.getPlant() instanceof Sunflower)
				{
					laneInfo += "|Ps";
				} else if (spot.getPlant() instanceof Peashooter)
				{
					laneInfo += "|Pp";
				}
			} else
			{
				boolean zombieAdded = false;
				int zombieStack = 0;
				for (Zombie zmb : liveZombies) //Check all zombies
				{
					if (zmb != null && zmb.position == distance - curSpot * 250) //Currently using a coordinate system, Later will be replaced with pixel range
					{
						zombieAdded = true;
						zombieStack++;
					}
				}
				if (zombieStack > 0) //Check for zombies stacking up in one spot
				{
					laneInfo += "|" + zombieStack + "z";
				}

				if (!zombieAdded)
				{
					laneInfo += "|  ";
				}
			}
			curSpot++;
		}
		return laneInfo + "|";
	}

}