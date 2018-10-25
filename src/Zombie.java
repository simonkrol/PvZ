
public class Zombie extends Entity {
	protected int moveSpeed;
	protected int position;
	public Zombie(int hp, int att, int def, int mov, double attSp, Lane lane) {
		super(hp, lane, att, def, attSp);
		this.moveSpeed = mov;
		this.position = 0; //Distance from the right side
	}
	public int getMoveSpeed() {
		return moveSpeed;
	}
	public void move() {
		this.position += this.moveSpeed;
	}
	
}
