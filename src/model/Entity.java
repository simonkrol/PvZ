package model;

/**
 * Abstract class that contains all entities within the game (Plants and Zombies)
 * @author Simon Krol
 * @version Nov 16, 2018
 */
public abstract class Entity
{
	private int currentHP, maxHP;
	protected int attack;
	private int defence;
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
	protected Entity(int maxHP, int att, int def, double attSp, Lane lane)
	{
		this.maxHP = maxHP;
		this.currentHP = maxHP;
		this.attack = att;
		this.defence = def;
		this.attackSpeed = attSp;
		this.lane = lane;
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

	/**
	 * Return the Entities current Health
	 * @return Current Health
	 */
	protected int getCurrentHP()
	{
		return currentHP;
	}

	/**
	 * Return the Entities attack value
	 * @return Attack value
	 */
	protected int getAttack()
	{
		return attack;
	}

	/**
	 * Return the Entities attack speed
	 * @return Attack speed
	 */
	protected double getAttackSpeed()
	{
		return attackSpeed;
	}

	/**
	 * Change the entities lane
	 * @param newLane The entities new lane
	 */
	protected void setLane(Lane newLane)
	{
		this.lane = newLane;
	}
}
