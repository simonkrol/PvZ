package model;
/**
 * The Spot class, used to split lanes
 * @author Boyan Siromahov and Simon Krol
 * @version Oct 29, 2018
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
	protected boolean getOccupied()
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

	/**
	 * Try to Kill the plant in the spot
	 * @return true if killed, false otherwise
	 */
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