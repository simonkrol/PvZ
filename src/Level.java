import java.util.ArrayList;

public class Level {
	Lane [] grid;
	
	public Level(int width, int height){
		grid = new Lane[height];
		for(int i = 0; i < height; i++)
		{
			grid[i] = new Lane();
		}
	}
	
	private Spot getSpot(int laneIndex, int spotIndex)
	{
		return grid[laneIndex].getSpot(spotIndex);
	}
	
	public void placePlant(Plant plant,int laneI,int spotI)
	{
		Spot spot = getSpot(laneI, spotI);
		if(spot.addPlant(plant))
		{
			plant.setLocation(spot);
			;//Decrement their sun counter by plant.getValue();
		}
	}
	
	public void removePlant(int laneI,int spotI)
	{
		Spot spot = getSpot(laneI, spotI);
		spot.killPlant();
	}
	
	public void addToQ(Zombie toAdd, int laneI)
	{
		grid[laneI].addToQueue(toAdd);
	}
}
