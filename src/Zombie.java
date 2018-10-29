
public abstract class Zombie extends Entity {
	protected int moveSpeed;
	protected int position;
	
	public Zombie(int hp, int att, int def, int mov, double attSp, Lane lane) {
		super(hp, lane, att, def, attSp);
		this.moveSpeed = mov;
		this.position = -250; //Distance from the right side
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
		if(lane.getPlantPos() >= position/250) 
		{
			this.position += this.moveSpeed;
		}
		else
		{
			super.attack();		
		}
		if(position >= lane.distance) {
			lane.hitEnd();
		}
		
	}
	public boolean takeDamage(int damage)
	{
		this.currentHP -= damage;
		if(this.currentHP<=0)return true;
		return false;
	}
	
}
