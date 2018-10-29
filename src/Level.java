import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Level
{
	private Lane[] grid;
	private Integer balance;
	private int width;
	private int height;
	private int turn;
	private BufferedReader levelData;
	private String curInstruction;

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

	protected void spawnZombies() throws IOException
	{
		if (curInstruction == null)
			return;
		if (curInstruction.split("-")[0].equals(Integer.toString(turn)))
		{
			String[] lanes = curInstruction.split("-");
			for (int i = 1; i < lanes.length; i++)
			{
				System.out.println(curInstruction);
				System.out.println(lanes[i]);
				grid[Integer.parseInt(lanes[i]) - 1].spawnZombie();
			}
			curInstruction = levelData.readLine();
		}
	}

	protected Lane getLane(int laneIndex)
	{
		return grid[laneIndex];
	}

	protected Spot getSpot(int laneIndex, int spotIndex)
	{
		return grid[laneIndex].getSpot(spotIndex);
	}

	protected void placePlant(Plant plant, int laneI, int spotI)
	{
		if (plant.getValue() > balance)
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

	protected void addToBalance(int toAdd)
	{
		balance += toAdd;
	}

	protected int getWidth()
	{
		return width;
	}

	protected int getHeight()
	{
		return height;
	}

	protected void allTurn() throws IOException
	{
		for (Lane lane : grid)
		{
			lane.allTurn(this);
		}
		turn++;
		spawnZombies();
	}

	protected int getBalance()
	{
		return balance;
	}

	protected boolean checkFail()
	{
		for (int i = 0; i < grid.length; i++)
		{
			if (grid[i].checkFail())
				return true;
		}
		return false;
	}

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
