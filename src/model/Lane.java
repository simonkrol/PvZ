package model;

/**
 * The lane class, seperates the game board into each lane.
 * @author Boyan Siromahov and Simon Krol
 * @version Nov 16, 2018
 */
import java.util.ArrayList;

public class Lane
{
	private int endState = 0;
	private int length;
	private boolean triggered = false;
	private Spot[] spots;
	private ArrayList<Zombie> liveZombies = new ArrayList<Zombie>();
	private ArrayList<Projectile> projectiles  = new ArrayList<Projectile>();

	/**
	 * Construct a default lane of length 8, where the last 2 spots can not host plants
	 */
	public Lane()
	{
		this(8, 2);
	}

	/**
	 * Construct a lane
	 * @param length The number of spots in the lane
	 * @param unplaceable The number of spots at the end of the lane that plants may not live on
	 */
	public Lane(int length, int unplaceable)
	{
		spots = new Spot[length];
		for (int i = 0; i < length; i++)
		{
			spots[i] = new Spot(length - i > unplaceable);
		}
		this.length = length;
	}

	/**
	 * Return the spot at the given index
	 * @param index The index of a given spot
	 * @return Spot at given index
	 */
	protected Spot getSpot(int index)
	{
		return getSpots()[index];
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
	 * Check if a plant exists directly in front of the zombie
	 * @param position The position of the zombie
	 * @return The plant in front of the zombie or null
	 */
	protected Plant checkFrontPlant(double position)
	{
		int index =(int) (length - Math.round(position)) - 1;
		if (index < 0 || index >= getSpots().length)
			return null;
		return getSpots()[index].getPlant();
	}


	/**
	 * Iterates through all plants and zombies in the lane and causes them to update
	 * @param curLevel The current level
	 */
	protected void allTurn(Level curLevel)
	{
		for (Spot spot : getSpots())
		{
			if (spot.getOccupied())
				spot.getPlant().turn(curLevel);

		}
		for (Projectile proj: projectiles)
		{
			proj.turn(curLevel);
		}
		clearUsedProj();
		for (Zombie zombie : liveZombies)
		{
			zombie.turn(curLevel);
		}
		if (triggered) // check if lawnmower has been triggered
		{
			liveZombies.clear();
			triggered = false;
		}
	}

	private void clearUsedProj()
	{
		for(int i = 0; i < projectiles.size(); i++)
		{
			if(projectiles.get(i).getExploded())
			{
				projectiles.remove(i);
				i--;
			}
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
	 * Return if zombies exist in the lane on the right of the given index
	 * @return True if zombies, false otherwise
	 */
	protected boolean attackableZombies(int distance)
	{
		for (Zombie z: liveZombies)
		{
			if((length - distance - 1)>z.getPosition())
			{
				return true;
			}
		}
		return false;
	}
	protected boolean noZombies()
	{
		return liveZombies.size()==0;
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
		for (Spot spot : getSpots())
		{
			if (spot.getOccupied()) // check all spots
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
				for (Zombie zmb : liveZombies) // Check all zombies
				{
					if (zmb != null && zmb.position == length - curSpot) // Currently using a coordinate system,
																					// Later will be replaced with pixel
																					// range
					{
						zombieAdded = true;
						zombieStack++;
					}
				}
				if (zombieStack > 0) // Check for zombies stacking up in one spot
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

	/**
	 * Return the lane's end state
	 * @return End state
	 */
	public int getEndState()
	{
		return endState;
	}

	/**
	 * Return the number of zombies alive in the lane
	 * @return Number of live zombies
	 */
	protected int getNumZombies()
	{
		return liveZombies.size();
	}

	/**
	 * Add a zombie to the lane
	 * @param toAdd The zombie to add
	 */
	public void addZombie(Zombie toAdd)
	{
		liveZombies.add(toAdd);
		toAdd.setLane(this);
	}

	/**
	 * Get the lane's spots
	 * @return The spots in the lane
	 */
	public Spot[] getSpots()
	{
		return spots;
	}

	/**
	 * Get the live zombies in the lane
	 * @return The lane's live zombies
	 */
	public ArrayList<Zombie> getLiveZombies()
	{
		return liveZombies;
	}
	public ArrayList<Projectile> getProjectiles()
	{
		return projectiles;
	}

	/**
	 * Place a given plant at the given index
	 * @param toPlace The plant to place
	 * @param spotIndex The spot's index where to place the plant
	 * @return True if successful, false otherwise
	 */
	protected boolean placePlant(Plant toPlace, int spotIndex)
	{
		Spot spot = spots[spotIndex];
		if (spot.addPlant(toPlace))
		{
			toPlace.setDistance(spotIndex);
			toPlace.setLane(this);
			toPlace.setLocation(spot);
			return true;
		}
		return false;
	}
	/**
	 * Get the number of spots in the lane
	 * @return Length of lane
	 */
	protected int getLength()
	{
		return length;
	}
	
	protected Zombie getProjZombie(double projMS, double projPos)
	{
		Zombie closest = null;
		double cDistance = projMS/2;
		double distance;
		for (Zombie z: liveZombies)
		{
			distance = (length - projPos) - z.getPosition();
			if(distance < cDistance +z.getMoveSpeed() && distance >= -projMS/2 && distance <= projMS/2 + z.getMoveSpeed())
			{
				cDistance = distance - z.getMoveSpeed();
				closest = z;
			}
		}
		return closest;
	}
	protected void createProjectile(Projectile toCreate)
	{
		projectiles.add(toCreate);
	}

}