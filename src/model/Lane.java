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
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

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
	protected Plant getFrontPlant(double position)
	{
		int index = (int) (length - Math.round(position)) - 1;
		if (index < 0 || index >= spots.length)
			return null;
		return spots[index].getPlant();
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
		for (Projectile proj : projectiles)
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

	/**
	 * Clear all projectiles that have exploded
	 */
	protected void clearUsedProj()
	{
		for (int i = 0; i < projectiles.size(); i++)
		{
			if (projectiles.get(i).getExploded())
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
	 * @param distance The given index
	 * @return True if zombies, false otherwise
	 */
	protected boolean attackableZombies(int distance)
	{
		for (Zombie z : liveZombies)
		{
			if ((length - distance) > z.getPosition())
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Return the closest zombie to the right of distance and within within blocks
	 * @param distance The index we search to the right of
	 * @param within How far we search
	 * @return The closest zombie, null if none exist close enough
	 */
	protected Zombie closeZombies(double distance, double within)
	{

		double dist;
		Zombie closest = null;
		for (Zombie z : liveZombies)
		{
			dist = (int) ((length - distance) - z.getPosition());
			if (dist > 0 && dist < within)
			{
				closest = z;
				within = dist;
			}
		}
		return closest;
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

	/**
	 * Return the list of projectiles in the lane
	 * @return The lane's projectiles
	 */
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

	/**
	 * Get the zombie that a projectile should collide with
	 * @param toProject The projectile in question
	 * @return The zombie collided with, null if none selected
	 */
	protected Zombie getProjZombie(Projectile toProject)
	{
		Zombie closest = null;
		double cDistance = toProject.getMoveSpeed() / 2;
		double distance;
		for (Zombie z : liveZombies)
		{
			distance = (length - toProject.getPosition()) - z.getPosition();
			if (distance < cDistance + z.getMoveSpeed() && distance >= -toProject.getMoveSpeed() / 2
					&& distance <= toProject.getMoveSpeed() / 2 + z.getMoveSpeed())
			{
				cDistance = distance - z.getMoveSpeed();
				closest = z;
			}
		}
		return closest;
	}

	/**
	 * Add a projectile to the lane
	 * @param toCreate The projectile being added
	 */
	protected void createProjectile(Projectile toCreate)
	{
		projectiles.add(toCreate);
	}

	/**
	 * Get the plant directly underneath the given distance
	 * @param distance The location being searched
	 * @return The given plant, null if invalid input or no plant exists on location
	 */
	protected Plant getLocationPlant(double distance)
	{
		if (distance > 0 && distance < length)
		{
			return spots[(int) Math.floor(distance)].getPlant();
		}
		return null;
	}
	
	public void setReferences()
	{
		for (Zombie zombie: liveZombies)
		{
			zombie.setLane(this);
		}
		for(Spot spot: spots)
		{
			spot.setReferences(this);
		}
	}
	
	public boolean equals(Object toCheck)
	{
		if(!(toCheck instanceof Lane))return false;
		Lane toCompare = (Lane)toCheck;
		if(endState != toCompare.endState)return false;
		if(length != toCompare.length)return false;
		if(triggered != toCompare.triggered)return false;
		if(spots.length != toCompare.spots.length)return false;
		for(int i=0; i<spots.length; i++)
		{
			if(!spots[i].equals(toCompare.spots[i]))return false;
		}
		if(liveZombies.size() != toCompare.liveZombies.size())return false;
		for(int i=0; i<liveZombies.size(); i++)
		{
			if(!liveZombies.get(i).equals(toCompare.liveZombies.get(i)))return false;
		}
		if(projectiles.size() != toCompare.projectiles.size())return false;
		for(int i=0; i<projectiles.size(); i++)
		{
			if(!projectiles.get(i).equals(toCompare.projectiles.get(i)))return false;
		}
		return true;
			
	}

}