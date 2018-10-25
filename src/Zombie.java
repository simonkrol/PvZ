
public class Zombie extends Entity {

	private static final int DEFAULT_HP = 4;
	private static final int DEFAULT_ATTACK = 1;
	private static final int DEFAULT_DEFENCE = 0;
	private static final int DEFAULT_MOVESPEED = 100; //Units per turn
	private static final double DEFAULT_ATTACKSPEED = 0.3; //Attacks per turn
	
	protected int moveSpeed;
	
	//Creates a default zombie
	protected Zombie(Lane lane, Spot spot) {
		this(DEFAULT_HP, DEFAULT_ATTACK, DEFAULT_DEFENCE, DEFAULT_MOVESPEED, DEFAULT_ATTACKSPEED, lane);
	}
	public Zombie(int hp, int att, int def, int mov, double attSp, Lane lane) {
		super(hp, lane, att, def, attSp);
		this.moveSpeed = mov;
	}
	public int getMoveSpeed() {
		return moveSpeed;
	}
	
}
