public class Gui {
	private String laneLine;
	private Level level;
	
	public Gui(Level lvl)
	{
		this.level = lvl;
		for(int i = 0; i < lvl.getWidth(); i ++)
		{
			laneLine += "----";
		}
		
	}
	
	protected void update()
	{
		System.out.println(laneLine);
		for(int i = 0; i < level.getHeight(); i ++)
		{
			System.out.println(getInfo(i));//contains the important info
			System.out.println(laneLine);
		}
	}
	
	private String getInfo(int i)
	{
		int currSpot = 1;
		String laneInfo = "";
		Lane curr = level.grid[i];
		for(Spot spot: curr.spots)
		{
			if(spot.getOccupied()) 
			{
				if(spot.getPlant() instanceof Sunflower) 
				{
					laneInfo += "|Ps";
				}
				else if(spot.getPlant() instanceof Peashooter)
				{
					laneInfo += "|Pp";
				}
			}
			else {
				int zombieStack = 0;
				for(Zombie zmb: curr.liveZombies)
				{
					if(zmb.position == curr.distance - currSpot*250)
					{
						zombieStack ++;
						laneInfo += "|" + zombieStack + "z";
					}
				}
			}
		}
		return laneInfo + "|";
	}
	
	
	
	
	
}
