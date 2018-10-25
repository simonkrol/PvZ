
public class Plant extends Entity{
	protected Spot location;
	public Plant(int maxHP, int att, int def, double attSp, Lane lane, Spot location) {
		super(maxHP, lane, att, def, attSp);
		this.location = location;
	}

}
