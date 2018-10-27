
public class DancerZombie extends Zombie {
	private static final int DEFAULT_HP = 5;
	private static final int DEFAULT_ATTACK = 1;
	private static final int DEFAULT_DEFENCE = 0;
	private static final int DEFAULT_MOVESPEED = 100; //Units per turn
	private static final double DEFAULT_ATTACKSPEED = 0.8; //Attacks per turn
	
	
	public DancerZombie(Lane lane) {
		super(DEFAULT_HP, DEFAULT_ATTACK, DEFAULT_DEFENCE, DEFAULT_MOVESPEED, DEFAULT_ATTACKSPEED, lane);
	}
	public void dance()
	{
		System.out.println("*Dancer Zombie starts to bust a groove*");
	}
	public void move()
	{
		super.move();
		this.dance();
	}
}
