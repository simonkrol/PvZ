import java.util.ArrayList;

public class Entity {
	protected int currentHP, maxHP, attack, defence;
	protected Lane lane;
	protected double attackSpeed;
	protected double attackState;
	
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
	protected void turn(Level curLevel)
	{
		attackState +=this.attackSpeed;
		while(attackState>=1)
		{
			this.attack(curLevel);
			attackState--;
		}
	}
	
	protected void attack(Level curLevel)
	{
		System.out.println(this.getClass().getName()+" attacks");

	}
}
