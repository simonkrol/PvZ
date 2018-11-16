package model;

/**
 * The Peashooter class, contains information about the Peashooter plant
 * @author Simon Krol
 * @version Oct 29, 2018
 */
public class Peashooter extends Plant
{
	private static final int DEFAULT_HP = 5;
	private static final int DEFAULT_ATTACK = 2;
	private static final int DEFAULT_DEFENCE = 0;
	private static final double DEFAULT_ATTACKSPEED = 1; // Attacks per turn
	public static final int DEFAULT_VALUE = 40;
	private static final int DEFAULT_DELAY = 5;

	/**
	 * Creates a peashooter with default values
	 * @param lane The lane the peashooter is in
	 */
	public Peashooter()
	{
		super(DEFAULT_HP, DEFAULT_ATTACK, DEFAULT_DEFENCE, DEFAULT_ATTACKSPEED, DEFAULT_VALUE, DEFAULT_DELAY);
	}

	/**
	 * Shoot a pea at the closest zombie in the lane
	 */
	protected void attack(Level curLevel)
	{
		this.lane.damageZombie(this.attack);
	}

}
