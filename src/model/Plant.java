package model;

/**
 * Abstract class that is extended by all Plants within the game
 * @author Simon Krol
 * @version Dec 7, 2018
 */
public abstract class Plant extends Entity
{
	transient protected Spot location;
	protected int distance;
	protected int value;
	protected int delay;

	public Plant()
	{
		super();
	}

	/**
	 * Constructor for plant (Only called from subclasses)
	 * @param maxHP Maximum HP
	 * @param att Attack Damage
	 * @param attSp Attack Speed
	 * @param value Cost to create
	 * @param delay Delay until you can place another (Not implemented)
	 */
	protected Plant(int maxHP, int att, double attSp, int value, int delay)
	{
		super(maxHP, att, attSp, null);
		this.value = value;
		this.delay = delay;
	}

	/**
	 * Return the current spot the plant is on
	 * @return The current spot
	 */
	protected Spot getLocation()
	{
		return location;
	}

	/**
	 * Set the plant's current spot
	 * @param location The spot to be changed to
	 */
	protected void setLocation(Spot location)
	{
		this.location = location;
	}

	/**
	 * Get the price of the plant
	 * @return Plants price
	 */
	public int getValue()
	{
		return value;
	}

	/**
	 * Set the index location of the plant
	 * @param newDistance The new distance value
	 */
	protected void setDistance(int newDistance)
	{
		distance = newDistance;
	}

	/**
	 * Kill the plant and remove it from the board
	 */
	protected void die()
	{
		location.killPlant();
	}

	/**
	 * Cause the plant to perform any of its actions
	 * @param curLevel The current level
	 */
	protected void turn(Level curLevel)
	{
		if (lane.attackableZombies(distance))
		{
			attackState += this.attackSpeed;
			while (attackState >= 1)
			{
				this.attack(curLevel);
				attackState--;
			}
		}
	}

	@Override
	/**
	 * Check if Plants are equivalent
	 */
	public boolean equals(Object toCheck)
	{
		if (toCheck == null)
			return false;
		if (!(toCheck instanceof Plant))
			return false;
		Plant toCompare = (Plant) toCheck;
		if (!super.equals(toCompare))
			return false;
		if (distance != toCompare.distance)
			return false;
		if (value != toCompare.value)
			return false;
		if (delay != toCompare.delay)
			return false;
		return true;
	}

}
