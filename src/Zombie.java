
public class Zombie extends Entity {
	protected int moveSpeed;
	
	public Zombie(int hp, int att, int def, int mov, double attSp, Lane lane) {
		super(hp, lane, att, def, attSp);
		this.moveSpeed = mov;
	}
	public int getMoveSpeed() {
		return moveSpeed;
	}
	
}
