package model;

/**
 * Abstract class that contains all Zombies within the game (BasicZombie)
 * @author Simon Krol
 * @version Nov 16, 2018
 */
public abstract class Zombie extends Entity
{
	private double moveSpeed;
	private double position;

	/**
	 * Create a zombie
	 * @param hp Zombies current hp
	 * @param att Attack damage
	 * @param mov Movement speed
	 * @param attSp Attack speed
	 * @param lane Lane the zombie is in
	 */
	protected Zombie(int hp, int att, double mov, double attSp, Lane lane)
	{
		super(hp, att, attSp, lane);
		this.moveSpeed = mov;
		this.position = 0; // Distance from the right side
	}

	/**
	 * Return zombies movement speed
	 * @return Movement speed
	 */
	protected double getMoveSpeed()
	{
		return moveSpeed;
	}

	/**
	 * Cause the zombie to perform any of its actions
	 * @param curLevel The current level
	 */
	protected void turn(Level curLevel)
	{
		Plant toAttack = lane.getFrontPlant(position);
		if (toAttack == null)
		{
			this.move();
		} else
		{
			attackState += this.attackSpeed;
			while (attackState >= 1)
			{
				this.attack(toAttack);
				attackState--;
			}
		}
	}

	/**
	 * Try to move the zombie forward in the lane or attack the target in front of it
	 */
	protected void move()
	{
		position += moveSpeed;
		if (position > lane.getLength())
		{
			lane.hitEnd();
		}

	}

	/**
	 * Remove the zombie from the lane
	 */
	protected void die()
	{
		lane.killZombie(this);
	}

	/**
	 * Attack the given plant
	 * @param toAttack The object being attacked
	 */
	protected void attack(Object toAttack)
	{
		if (toAttack instanceof Plant)
		{
			((Plant) toAttack).takeDamage(attack);
		} else
			throw new IllegalArgumentException();
	}

	/**
	 * returns the current position of the zombie
	 * @return returns the position of the zombie
	 */
	public double getPosition()
	{
		return position;
	}

	/**
	 * Set the position of the zombie
	 * @param newPosition The zombies new position
	 */
	protected void setPosition(double newPosition)
	{
		position = newPosition;
	}

}
