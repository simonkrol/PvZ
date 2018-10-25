
public class Plant extends Entity{
	protected Spot location;
	protected int value;
	
	public Plant(int maxHP, int att, int def, double attSp, Lane lane) {
		super(maxHP, lane, att, def, attSp);
	}
	public Spot getLocation()
	{
		return location;
	}
	public void setLocation(Spot location)
	{
		this.location = location;
	}
	public int getValue()
	{
		return value;
	}

}
