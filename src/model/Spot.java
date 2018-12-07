package model;

/**
 * The Spot class, used to split lanes
 * @author Boyan Siromahov and Simon Krol
 * @version Nov 25, 2018
 */
public class Spot
{
	private Plant plant;
	private boolean placeable;

	/**
	 * Construct a spot
	 * @param place Whether plants may be placed on the spot
	 */
	protected Spot(boolean place)
	{
		plant = null;
		placeable = place;
	}

	/**
	 * Return if a spot is occupied by a plant
	 * @return true if occupied, false otherwise
	 */
	public boolean getOccupied()
	{
		return plant != null;
	}

	/**
	 * Return the current plant in the spot
	 * @return Current plant
	 */
	public Plant getPlant()
	{
		return plant;
	}

	/**
	 * Try to place a plant in the spot
	 * @param toAdd The plant to be placed
	 * @return true if successful, false otherwise
	 */
	protected boolean addPlant(Plant toAdd)
	{
		if (!placeable)
		{
			return false;
		}
		if (!this.getOccupied())
		{
			this.plant = toAdd;
			return true;
		}
		return false;

	}

	/**
	 * Try to Kill the plant in the spot
	 */
	protected void killPlant()
	{
		if (this.getOccupied())
		{
			this.plant = null;
		}
		else throw new NullPointerException();
	}
	
	public void setReferences(Lane lane)
	{
		if(plant instanceof Plant)
		{
			plant.setLocation(this);
			plant.setLane(lane);
		}
	}
}