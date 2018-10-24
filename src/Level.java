import java.util.ArrayList;

public class Level {
	ArrayList<Lane> grid = new ArrayList<>();
	
	public Level(int width, int height){
		for(int i = 0; i < height; i++)
		{
			grid.add(new Lane());
		}
	}
	
	private Spot navigate(int height, int width)
	{
		return grid.get(height).getZombie(width);
	}
	
	public void addEntity(Object ent,int height,int width)
	{
		Spot spot = navigate(height, width);
		spot.setEntity(ent);
	}
	
	public void removeEntity(int height,int width)
	{
		Spot spot = navigate(height, width);
		spot.setEntity(null);
	}
	
	public void addToQ(Object zmb, int height)
	{
		grid.get(height).addToQueue(zmb);
	}
}