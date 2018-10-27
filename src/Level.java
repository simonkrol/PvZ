
public class Level {
	Lane [] grid;
	Integer balance;
	
	public Level(int width, int height, int balance){
		grid = new Lane[height];
		for(int i = 0; i < height; i++)
		{
			grid[i] = new Lane(width);
		}
		this.balance = balance;
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
			this.addToBalance(-plant.getValue());
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
	public void addToBalance(int toAdd) {
		balance += toAdd;
	}
}
