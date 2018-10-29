import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Level
{
	public Lane[] grid;
	public Integer balance;
	public int width;
	public int height;
	public int turn;
	public BufferedReader levelData;
	public String curInstruction;

	public Level(int width, int height, int balance, String fileName) throws IOException
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

	public void spawnZombies() throws IOException
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

	public Lane getLane(int laneIndex)
	{
		return grid[laneIndex];
	}

	private Spot getSpot(int laneIndex, int spotIndex)
	{
		return grid[laneIndex].getSpot(spotIndex);
	}

	public void placePlant(Plant plant, int laneI, int spotI)
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
			grid[laneI].numPlants++;
		}
	}

	public void addToBalance(int toAdd)
	{
		balance += toAdd;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public void allTurn() throws IOException
	{
		for (Lane lane : grid)
		{
			for (Spot spot : lane.spots)
			{
				if (spot.getOccupied())
					spot.getPlant().turn(this);

			}
			for (Zombie zombie : lane.liveZombies)
			{
				zombie.turn(this);
			}
			if (lane.triggered)
			{
				lane.liveZombies.clear();
				lane.triggered = false;
			}
		}
		turn++;
		spawnZombies();
	}
}
