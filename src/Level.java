/**
 * The level class, contains all info about the current game being played
 * @author Boyan Siromahov and Simon Krol
 * @version Oct 29, 2018
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Level
{
	protected Lane[] grid;
	private Integer balance;
	private int width;
	private int height;
	protected int turn;
	private BufferedReader levelData;
	private String curInstruction;
	protected boolean add = false;

	/**
	 * Construct a level
	 * @param width The width in spots, of the level
	 * @param height The height in spots, of the level
	 * @param balance The player's starting sun balance
	 * @param fileName The file storing the level's zombie data
	 * @throws IOException If readline fails
	 */
	protected Level(int width, int height, int balance, String fileName) throws IOException
	{
		grid = new Lane[height];
		for (int i = 0; i < height; i++)
		{
			grid[i] = new Lane(width, 2);
		}
		this.balance = balance;
		this.width = width;
		this.height = height;
		levelData = new BufferedReader(new FileReader(fileName));
		curInstruction = levelData.readLine();
	}

	/**
	 * Check the levelData and spawn any zombies intended for the given turn
	 * @throws IOException If readline fails
	 */
	protected void spawnZombies() throws IOException
	{
		if (curInstruction == null)
			return;
		if (curInstruction.split("-")[0].equals(Integer.toString(turn)))
		{
			String[] lanes = curInstruction.split("-");
			for (int i = 1; i < lanes.length; i++)
			{
				grid[Integer.parseInt(lanes[i]) - 1].spawnZombie();
			}
			curInstruction = levelData.readLine();
		}
	}

	/**
	 * Return a given lane
	 * @param laneIndex The index of the lane to be returned
	 * @return a given Lane
	 */
	protected Lane getLane(int laneIndex)
	{
		return grid[laneIndex];
	}

	/**
	 * Return a given spot
	 * @param laneIndex The index of the lane the spot is contained within
	 * @param spotIndex The index of the spot to be returned
	 * @return a given Spot
	 */
	protected Spot getSpot(int laneIndex, int spotIndex)
	{
		return grid[laneIndex].getSpot(spotIndex);
	}

	/**
	 * Try to place a plant at the given indices.
	 * @param plant The plant being placed
	 * @param laneI The lane index of the plants location
	 * @param spotI The spot index of the plants location
	 */
	protected void placePlant(Plant plant, int laneI, int spotI)
	{
		if (plant.getValue() > balance) //check if player has enough to purchase the plant
		{
			System.out.println("Insufficient funds");
			return;
		}
		Spot spot = getSpot(laneI, spotI);
		if (spot.addPlant(plant))
		{
			plant.setLocation(spot);
			this.addToBalance(-plant.getValue());
		}
	}

	/**
	 * Add sun to the player's balance
	 * @param toAdd The amount fo be added
	 */
	protected void addToBalance(int toAdd)
	{
		balance += toAdd;
	}

	/**
	 * Return the width in spots, of the level
	 * @return Width of the level
	 */
	protected int getWidth()
	{
		return width;
	}

	/**
	 * Return the height in spots, of the level
	 * @return Height of the level
	 */
	protected int getHeight()
	{
		return height;
	}

	/**
	 * Iterate through all lanes in the level and run all their turns, also spawn any new zombies
	 * @throws IOException  If readline fails
	 */
	protected void allTurn() throws IOException
	{
		for (Lane lane : grid)
		{
			lane.allTurn(this);
		}
		turn++;
		spawnZombies();
	}

	/**
	 * Return the user's current balance
	 * @return Users balance
	 */
	protected int getBalance()
	{
		return balance;
	}

	/**
	 * Check if the level has been failed. The level has been failed if any lane has failed.
	 * @return true if failed, false otherwise
	 */
	protected boolean checkFail()
	{
		for (int i = 0; i < grid.length; i++)
		{
			if (grid[i].checkFail())
				return true;
		}
		return false;
	}

	/**
	 * Check if the player has won
	 * @return true if won, false otherwise
	 */
	protected boolean checkWin()
	{
		if (curInstruction != null)
			return false;
		for (int i = 0; i < grid.length; i++)
		{
			if (grid[i].checkNoWin())
				return false;

		}
		return true;
	}
}
