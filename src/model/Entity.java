package model;

/**
 * Abstract class that contains all entities within the game (Plants and Zombies)
 * @author Simon Krol
 * @version Oct 29, 2018
 */
public abstract class Entity
{
	protected int currentHP, maxHP, attack, defence;
	protected Lane lane;
	protected double attackSpeed;
	protected double attackState;

	/**
	 * Entity constructor
	 * @param maxHP Entities maximum health
	 * @param lane The lane the entity is in
	 * @param att The entities attack damage
	 * @param def The entities defence, currently unused
	 * @param attSp The entities attack speed
	 */
	protected Entity(int maxHP, Lane lane, int att, int def, double attSp)
	{
		this.maxHP = maxHP;
		this.currentHP = maxHP;
		this.lane = lane;
		this.attack = att;
		this.defence = def;
		this.attackSpeed = attSp;
	}

	/**
	 * Method to be initialized in each subclass that causes the entity to die
	 */
	protected abstract void die();

	/**
	 * Method to be initialized in each subclass that updates the entity
	 * @param curLevel The current level being played
	 */
	protected abstract void turn(Level curLevel);

	/**
	 * Method to be initialized in each subclass that causes the entity to attack
	 * @param curLevel The current level being played
	 */
	protected abstract void attack(Level curLevel);

	/**
	 * Reduce the entities HP, if at 0 or below, the entity will die
	 * @param damage The amount of damage to take
	 */
	protected void takeDamage(int damage)
	{
		this.currentHP -= damage;
		if (this.currentHP <= 0)
			this.die();
	}
	public int getCurrentHP()
	{
		return currentHP;
	}
	public int getAttack()
	{
		return attack;
	}
	public double getAttackSpeed()
	{
		return attackSpeed;
	}
}
