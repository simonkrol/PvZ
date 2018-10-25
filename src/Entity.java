
public class Entity {
	protected int currentHP, maxHP, attack, defence;
	protected Lane lane;
	protected double attackSpeed;
	
	public int getMaxHP() 
	{
		return maxHP;
	}
	public int getCurrentHP() 
	{
		return currentHP;
	}
	public void changeHP(int change)
	{
		currentHP += change;
		if(currentHP<=0) {
			this.die();
		}
	}
	private void die() {
		;
	}
	public int getAttack() {
		return attack;
	}
	public int getDefence() {
		return defence;
	}
	public double getAttackSpeed() {
		return attackSpeed;
	}
	
	protected Entity(int maxHP, Lane lane, int att, int def, double attSp) {
		this.maxHP = maxHP;
		this.currentHP = maxHP;
		this.lane = lane;
		this.attack = att;
		this.defence = def;
		this.attackSpeed = attSp;
	}
}
