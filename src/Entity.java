public abstract class Entity
{
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
		System.out.print("Current hp is: " + currentHP);
		if (currentHP <= 0)
		{
			this.die();
		}
	}

	protected abstract void die();

	public int getAttack()
	{
		return attack;
	}

	public int getDefence()
	{
		return defence;
	}

	public double getAttackSpeed()
	{
		return attackSpeed;
	}

	protected Entity(int maxHP, Lane lane, int att, int def, double attSp)
	{
		this.maxHP = maxHP;
		this.currentHP = maxHP;
		this.lane = lane;
		this.attack = att;
		this.defence = def;
		this.attackSpeed = attSp;
	}

	protected abstract void turn(Level curLevel);

	protected abstract void attack(Level curLevel);

	public void takeDamage(int damage)
	{
		this.currentHP -= damage;
		if (this.currentHP <= 0)
			this.die();
	}
}
