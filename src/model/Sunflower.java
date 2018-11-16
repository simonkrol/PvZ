package model;

/**
 * The Sunflower class, contains information about the Peashooter plant
 * @author Simon Krol
 * @version Oct 29, 2018
 */
public class Sunflower extends Plant
{
	private static final int DEFAULT_HP = 4;
	private static final int DEFAULT_ATTACK = 12;
	private static final int DEFAULT_DEFENCE = 0;
	private static final double DEFAULT_ATTACKSPEED = 1; // Attacks per turn
	public static final int DEFAULT_VALUE = 50;
	private static final int DEFAULT_DELAY = 5;

	/**
	 * Creates a sunflower with default values
	 * @param lane The lane the sunflower is in
	 */
	public Sunflower()
	{
		super(DEFAULT_HP, DEFAULT_ATTACK, DEFAULT_DEFENCE, DEFAULT_ATTACKSPEED, DEFAULT_VALUE, DEFAULT_DELAY);
	}

	/**
	 * Add sun to the players balance
	 * @param curLevel The current level
	 */
	protected void attack(Level curLevel)
	{
		curLevel.addToBalance(this.attack);
	}

	/**
	 * Cause the sunflower to perform any required actions
	 * @param curLevel The current level
	 */
	protected void turn(Level curLevel)
	{
		attackState += this.attackSpeed;
		while (attackState >= 1)
		{
			this.attack(curLevel);
			attackState--;
		}
	}
}
