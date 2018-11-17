package model;

/**
 * Abstract class that contains all Plants within the game (Peashooter and Sunflower)
 * @author Simon Krol
 * @version Nov 16, 2018
 */
public abstract class Plant extends Entity
{
	protected Spot location;
	protected int value;
	protected int delay;

	/**
	 * Constructor for plant (Only called from subclasses)
	 * @param maxHP Maximum HP
	 * @param att Attack Damage
	 * @param def Defence(Not implemented)
	 * @param attSp Attack Speed
	 * @param value Cost to create
	 * @param delay Delay until you can place another (Not implemented)
	 */
	protected Plant(int maxHP, int att, int def, double attSp, int value, int delay)
	{
		super(maxHP, att, def, attSp, null);
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
		if (lane.noZombies())
			return;

		attackState += this.attackSpeed;
		while (attackState >= 1)
		{
			this.attack(curLevel);
			attackState--;
		}
	}

}
