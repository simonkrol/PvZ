
public class Peashooter extends Plant{
	private static final int DEFAULT_HP = 4;
	private static final int DEFAULT_ATTACK = 2;
	private static final int DEFAULT_DEFENCE = 0;
	private static final double DEFAULT_ATTACKSPEED = 0.3; //Attacks per turn
	private static final int DEFAULT_VALUE = 40;
	private static final int DEFAULT_DELAY = 5;
	
	public Peashooter(Lane lane)
	{
		super(DEFAULT_HP, DEFAULT_ATTACK, DEFAULT_DEFENCE, DEFAULT_ATTACKSPEED, lane, DEFAULT_VALUE, DEFAULT_DELAY);
	}
	public Peashooter(int maxHP, int att, int def, double attSp, Lane lane, int value, int delay) {
		super(maxHP, att, def, attSp, lane, value, delay);
		// TODO Auto-generated constructor stub
	}
	
	
	protected void attack(Level curLevel)
	{
		this.lane.damageZombie(this.attack);
	}

}
