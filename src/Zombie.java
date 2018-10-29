
public abstract class Zombie extends Entity
{
	protected int moveSpeed;
	protected int position;

	public Zombie(int hp, int att, int def, int mov, double attSp, Lane lane)
	{
		super(hp, lane, att, def, attSp);
		this.moveSpeed = mov;
		this.position = 0; // Distance from the right side
	}

	public int getMoveSpeed()
	{
		return moveSpeed;
	}

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

	public void move()
	{

		if (lane.checkFrontPlant(this.position))
		{
			this.attack(null);
		} else
		{
			position += moveSpeed;
		}
		if (position >= lane.distance)
		{
			lane.hitEnd();
		}

	}

	protected void die()
	{
		lane.liveZombies.remove(this);
	}

	protected void attack(Level curLevel)
	{
		lane.getFrontPlant().takeDamage(attack);

	}

}
