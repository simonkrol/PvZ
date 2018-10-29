public abstract class Entity
{
	protected int currentHP, maxHP, attack, defence;
	protected Lane lane;
	protected double attackSpeed;
	protected double attackState;

	protected Entity(int maxHP, Lane lane, int att, int def, double attSp)
	{
		this.maxHP = maxHP;
		this.currentHP = maxHP;
		this.lane = lane;
		this.attack = att;
		this.defence = def;
		this.attackSpeed = attSp;
	}

	protected abstract void die();

	protected abstract void turn(Level curLevel);

	protected abstract void attack(Level curLevel);

	protected void takeDamage(int damage)
	{
		this.currentHP -= damage;
		if (this.currentHP <= 0)
			this.die();
	}
}
