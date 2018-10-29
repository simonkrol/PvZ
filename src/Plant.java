
public abstract class Plant extends Entity
{
	protected Spot location;
	protected int value;
	protected int delay;

	public Plant(int maxHP, int att, int def, double attSp, Lane lane, int value, int delay)
	{
		super(maxHP, lane, att, def, attSp);
		this.value = value;
		this.delay = delay;
	}

	public Spot getLocation()
	{
		return location;
	}

	public void setLocation(Spot location)
	{
		this.location = location;
	}

	public int getValue()
	{
		return value;
	}

	protected void die()
	{
		location.killPlant();
	}

	protected void turn(Level curLevel)
	{
		if (lane.liveZombies.size() == 0)
			return;

		attackState += this.attackSpeed;
		while (attackState >= 1)
		{
			this.attack(curLevel);
			attackState--;
		}
	}

}
