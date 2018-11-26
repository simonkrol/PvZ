package model;

/**
 * Abstract class that is extended by all Projectiles within the game
 * @author Simon Krol
 * @version Nov 25, 2018
 */
import java.util.Random;

public abstract class Projectile extends Entity
{
	private static Random rand = new Random();
	private double moveSpeed;
	private double position;
	private double offset;
	private boolean exploded;

	/**
	 * Create a projectile
	 * @param att How much damage it will do
	 * @param lane The lane it belongs in
	 * @param moveSpeed How quickly it moves
	 * @param position Where it starts
	 */
	protected Projectile(int att, Lane lane, double moveSpeed, double position)
	{
		super(1, att, 0, lane);
		this.moveSpeed = moveSpeed;
		this.offset = (rand.nextDouble() * 0.6) - 0.33;
		this.position = position;
		exploded = false;
	}

	@Override
	/**
	 * Kill the projectile
	 */
	protected void die()
	{
		exploded = true;

	}

	@Override
	/**
	 * Progress through a turn
	 */
	protected void turn(Level curLevel)
	{
		move();
		Zombie toAttack = lane.getProjZombie(this);
		if (toAttack != null)
		{
			attack(toAttack);
		}

	}

	@Override
	/**
	 * Attack the given object
	 */
	protected void attack(Object toAttack)
	{
		if (toAttack instanceof Zombie)
		{
			((Zombie) toAttack).takeDamage(attack);
			this.die();
		}

	}

	/**
	 * Move along the lane
	 */
	protected void move()
	{
		position += moveSpeed;
		Plant onPlant = lane.getLocationPlant(position);
		if (lane.getLocationPlant(position) instanceof Torchwood)
		{
			status = Status.FIRE;
			attack += onPlant.getAttack();
		}
	}

	/**
	 * Get the double amount for how much the projectile should be shifted on the y axis
	 * @return The offset
	 */
	public double getOffset()
	{
		return offset;
	}

	/**
	 * Get the position of the projectile along the lane
	 * @return The projectile's position
	 */
	public double getPosition()
	{
		return position;
	}

	/**
	 * Get if the projectile has exploded
	 * @return True if used, false otherwise
	 */
	protected boolean getExploded()
	{
		return exploded;
	}

	/**
	 * Get the projectiles movement speed
	 * @return A double representing the number of blocks per turn
	 */
	protected double getMoveSpeed()
	{
		return moveSpeed;
	}

}
