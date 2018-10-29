
public class Level {
	public Lane [] grid;
	public Integer balance;
	public int width;
	public int height;
	
	public Level(int width, int height, int balance){
		grid = new Lane[height];
		for(int i = 0; i < height; i++)
		{
			grid[i] = new Lane(width);
		}
		this.balance = balance;
		this.width = width;
		this.height = height;
	}
	
	public Lane getLane(int laneIndex)
	{
		return grid[laneIndex];
	}
	private Spot getSpot(int laneIndex, int spotIndex)
	{
		return grid[laneIndex].getSpot(spotIndex);
	}
	
	public void placePlant(Plant plant,int laneI,int spotI)
	{
		if(plant.getValue() > balance)
		{
			System.out.println("Insufficient funds");
			return;
		}
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
	public int getWidth()
	{
		return width;
	}
	public int getHeight()
	{
		return height;
	}
}
