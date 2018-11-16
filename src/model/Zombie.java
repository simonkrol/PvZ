package model;

/**
 * Abstract class that contains all Zombies within the game (BasicZombie)
 * @author Simon Krol
 * @version Oct 29, 2018
 */
public abstract class Zombie extends Entity
{
	protected int moveSpeed;
	protected int position;

	/**
	 * Create a zombie
	 * @param hp Zombies current hp
	 * @param att Attack damage
	 * @param def Defence (Not implemented)
	 * @param mov Movement speed
	 * @param attSp Attack speed
	 * @param lane Lane the zombie is in
	 */
	protected Zombie(int hp, int att, int def, int mov, double attSp, Lane lane)
	{
		super(hp, att, def, attSp, lane);
		this.moveSpeed = mov;
		this.position = 0; // Distance from the right side
	}

	/**
	 * Return zombies movement speed
	 * @return Movement speed
	 */
	protected int getMoveSpeed()
	{
		return moveSpeed;
	}

	/**
	 * Cause the zombie to perform any of its actions
	 * @param curLevel The current level
	 */
	protected void turn(Level curLevel)
	{
		this.move();
		if (lane.checkFrontPlant(position))
		{
			attackState += this.attackSpeed;
			while (attackState >= 1)
			{
				this.attack(curLevel);
				attackState--;
			}
		}
	}

	/**
	 * Try to move the zombie forward in the lane or attack the target in front of it
	 */
	protected void move()
	{

		if (lane.checkFrontPlant(this.position))
		{
			this.attack(null);
		} else
		{
			position += moveSpeed;
		}
		if (position >= lane.getDistance())
		{
			System.out.println("Hit end");
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
	 * Attack the front plant in the lane
	 */
	protected void attack(Level curLevel)
	{
		lane.getFrontPlant().takeDamage(attack);

	}
	
	/**
	 * returns the current position of the zombie
	 * @return returns the position of the zombie
	 */
	public int getPosition(){
		return position;
	}
}
