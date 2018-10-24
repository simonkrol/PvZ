import java.util.ArrayList;

public class levels {
	ArrayList<spot> lane;
	ArrayList<ArrayList<spot>> grid = new ArrayList<>();
	
	public levels(int width, int height){
		for(int i = 0; i < height; i++)
		{
			lane = new ArrayList<spot>();
			for(int u = 0; u < width; u++)
			{
				lane.add(new spot());
			}
			grid.add(lane);
		}
	}
}
