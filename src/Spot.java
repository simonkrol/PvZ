
public class Spot
{
	private Plant plant;
	private boolean placeable;

	protected Spot(boolean place)
	{
		plant = null;
		placeable = place;
	}

	protected boolean getOccupied()
	{
		return plant != null;
	}

	protected Plant getPlant()
	{
		return plant;
	}

	protected boolean addPlant(Plant toAdd)
	{
		if (!placeable)
		{
			System.out.println("You may not place a plant in this column.");
			return false;
		}
		if (!this.getOccupied())
		{
			this.plant = toAdd;
			return true;
		}
		System.out.println("This Spot already has a plant in it.");
		return false;

	}

	protected boolean killPlant()
	{
		if (this.getOccupied())
		{
			this.plant = null;
			return true;
		}
		return false;
	}
}