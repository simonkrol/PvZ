
public class Sunflower extends Plant {
	private static final int DEFAULT_HP = 4;
	private static final int DEFAULT_ATTACK = 25;
	private static final int DEFAULT_DEFENCE = 0;
	private static final double DEFAULT_ATTACKSPEED = 0.3; //Attacks per turn
	protected static final int DEFAULT_VALUE = 50;
	private static final int DEFAULT_DELAY = 5;
	
	public Sunflower(Lane lane)
	{
		super(DEFAULT_HP, DEFAULT_ATTACK, DEFAULT_DEFENCE, DEFAULT_ATTACKSPEED, lane, DEFAULT_VALUE, DEFAULT_DELAY);
	}
	protected void attack(Level curLevel)
	{
		System.out.println("Sunflower has given you some sun");
		curLevel.addToBalance(this.attack);
	}
	protected void turn(Level curLevel)
	{
		attackState +=this.attackSpeed;
		while(attackState>=1)
		{
			this.attack(curLevel);
			attackState--;
		}
	}
}
