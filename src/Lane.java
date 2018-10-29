import java.util.ArrayList;

public class Lane
{
	private int endState = 0;
	private boolean triggered = false;
	private Spot[] spots;
	private ArrayList<Zombie> liveZombies = new ArrayList<Zombie>();
	private int distance;

	protected Lane()
	{
		this(8, 2);
	}

	protected Lane(int length, int unplaceable)
	{
		spots = new Spot[length];
		for (int i = 0; i < length; i++)
		{
			spots[i] = new Spot(length - i > unplaceable);
		}
		distance = length * 250;// The distance from side to side;
	}

	protected Spot getSpot(int index)
	{
		return spots[index];
	}

	protected int getDistance()
	{
		return distance;
	}

	protected void damageZombie(int damage)
	{
		if (liveZombies.size() == 0)
			return;
		Zombie closest = liveZombies.get(0);
		for (Zombie zmb : liveZombies)
		{
			if (zmb.position > closest.position)
			{
				closest = zmb;
			}
		}
		closest.takeDamage(damage);
	}

	protected void spawnZombie()
	{
		liveZombies.add(new BasicZombie(this));
	}

	protected void hitEnd()
	{
		if (triggered)
			return;
		endState += 1;
		triggered = true;

	}

	protected Boolean checkFrontPlant(int position)
	{
		int index = spots.length - 2 - (int) Math.floor(position / 250.0);
		if (index < 0 || index >= spots.length)
			return false;
		return spots[index].getOccupied();
	}

	protected Plant getFrontPlant()
	{
		for (int i = spots.length - 1; i >= 0; i--)
		{
			if (spots[i].getOccupied())
				return spots[i].getPlant();
		}
		return null;
	}

	protected void allTurn(Level curLevel)
	{
		for (Spot spot : spots)
		{
			if (spot.getOccupied())
				spot.getPlant().turn(curLevel);

		}
		for (Zombie zombie : liveZombies)
		{
			zombie.turn(curLevel);
		}
		if (triggered)
		{
			liveZombies.clear();
			triggered = false;
		}
	}

	protected boolean checkFail()
	{
		return endState >= 2;
	}

	protected boolean checkNoWin()
	{
		if (liveZombies.size() != 0)
			return true;
		return false;
	}

	protected boolean noZombies()
	{
		return liveZombies.size() == 0;
	}

	protected void killZombie(Zombie toKill)
	{
		liveZombies.remove(toKill);
	}

	protected String getInfo()
	{
		int curSpot = 1;
		String laneInfo = "";
		for (Spot spot : spots)
		{
			if (spot.getOccupied())
			{
				if (spot.getPlant() instanceof Sunflower)
				{
					laneInfo += "|Ps";
				} else if (spot.getPlant() instanceof Peashooter)
				{
					laneInfo += "|Pp";
				}
			} else
			{
				boolean zombieAdded = false;
				int zombieStack = 0;
				for (Zombie zmb : liveZombies)
				{
					if (zmb != null && zmb.position == distance - curSpot * 250)
					{
						zombieAdded = true;
						zombieStack++;
					}
				}
				if (zombieStack > 0)
				{
					laneInfo += "|" + zombieStack + "z";
				}

				if (!zombieAdded)
				{
					laneInfo += "|  ";
				}
			}
			curSpot++;
		}
		return laneInfo + "|";
	}

}