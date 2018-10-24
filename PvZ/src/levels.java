import java.util.ArrayList;

public class levels {
	ArrayList<lane> grid = new ArrayList<>();
	
	public levels(int width, int height){
		for(int i = 0; i < height; i++)
		{
			grid.add(new lane());
		}
	}
	
	private spot navigate(int height, int width)
	{
		return grid.get(height).getZombie(width);
	}
	
	public void addEntity(Object ent,int height,int width)
	{
		spot spot = navigate(height, width);
		spot.setEntity(ent);
	}
	
	public void removeEntity(int height,int width)
	{
		spot spot = navigate(height, width);
		spot.setEntity(null);
	}
	
	public void addToQ(Object zmb, int height)
	{
		grid.get(height).addToQueue(zmb);
	}
}
