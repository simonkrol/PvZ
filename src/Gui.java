/**
 * The Text based GUI being used to play Milestone 1
 * @author Boyan Siromahov
 * @version Oct 29, 2019
 */
public class Gui
{
	private String laneLine = ""; //String containing the horizontal line lengths
	private Level level;

	/**
	 * Constructor for the gui class
	 * @param lvl The current level being played
	 */
	protected Gui(Level lvl)
	{
		this.level = lvl;
		for (int i = 0; i < lvl.getWidth(); i++)
		{
			laneLine += "---";
		}

	}

	/**
	 * Update the board with any new information about plants, your balance or zombies
	 */
	protected void update()
	{
		System.out.println("Current Sunshine: " + level.getBalance());
		System.out.println(laneLine);
		for (int i = 0; i < level.getHeight(); i++)
		{
			System.out.println(level.getLane(i).getInfo()); // contains the info about how many zombies and plants there are in a lane
			System.out.println(laneLine);
		}
	}

}
