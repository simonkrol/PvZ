
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
	protected void turn(Level curLevel)
	{
		this.move();
		super.turn(curLevel);
	}
	public void move() {
		//Need to add move function
		this.position += this.moveSpeed;
		
	}
	public void attack(Level curLevel)
	{
		//Should only attack if right next to a plant
	}
	public boolean takeDamage(int damage)
	{
		this.currentHP -= damage;
		if(this.currentHP<=0)return true;
		return false;
	}
	
}
