/**
 * The Basic Zombie class
 * @author Simon Krol
 * @version Oct 29, 2018
 */
public class BasicZombie extends Zombie
{
	private static final int DEFAULT_HP = 5;
	private static final int DEFAULT_ATTACK = 1;
	private static final int DEFAULT_DEFENCE = 0;
	private static final int DEFAULT_MOVESPEED = 250; // Units per turn
	private static final double DEFAULT_ATTACKSPEED = 0.8; // Attacks per turn

	/**
	 * Constructor to create a basicZombie
	 * @param lane The lane the zombie will exist in
	 */
	public BasicZombie(Lane lane)
	{
		super(DEFAULT_HP, DEFAULT_ATTACK, DEFAULT_DEFENCE, DEFAULT_MOVESPEED, DEFAULT_ATTACKSPEED, lane);
	}
}
