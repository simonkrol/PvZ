
public class Sunflower extends Plant {
	private static final int DEFAULT_HP = 4;
	private static final int DEFAULT_ATTACK = 0;
	private static final int DEFAULT_DEFENCE = 0;
	private static final double DEFAULT_ATTACKSPEED = 0.3; //Attacks per turn
	
	public Sunflower(Lane lane)
	{
		super(DEFAULT_HP, DEFAULT_ATTACK, DEFAULT_DEFENCE, DEFAULT_ATTACKSPEED, lane);
	}
	protected void attack()
	{
		System.out.println("Sunflower has given you some sun");
		//Add sun to the player balance
	}
}
