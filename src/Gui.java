public class Gui
{
	private String laneLine = "";
	private Level level;

	protected Gui(Level lvl)
	{
		this.level = lvl;
		for (int i = 0; i < lvl.getWidth(); i++)
		{
			laneLine += "---";
		}

	}

	protected void update()
	{
		System.out.println("Current Sunshine: " + level.getBalance());
		System.out.println(laneLine);
		for (int i = 0; i < level.getHeight(); i++)
		{
			System.out.println(level.getLane(i).getInfo());// contains the important info
			System.out.println(laneLine);
		}
	}

}
