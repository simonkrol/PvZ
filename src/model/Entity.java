package model;

import java.awt.Image;

/**
 * Abstract class that contains all entities within the game (Plants and Zombies)
 * @author Simon Krol
 * @version Nov 16, 2018
 */
public abstract class Entity
{
	@SuppressWarnings("unused")
	private int currentHP, maxHP;
	protected int attack;
	transient protected Lane lane;
	protected double attackSpeed;
	protected double attackState;
	protected Status status;
	protected int statusDelay;

	protected Entity()
	{
		
	}
	/**
	 * Entity constructor
	 * @param maxHP Entities maximum health
	 * @param lane The lane the entity is in
	 * @param att The entities attack damage
	 * @param attSp The entities attack speed
	 */
	protected Entity(int maxHP, int att, double attSp, Lane lane)
	{
		this.maxHP = maxHP;
		this.currentHP = maxHP;
		this.attack = att;
		this.attackSpeed = attSp;
		this.lane = lane;
		status = Status.NORMAL;
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
	 * ToAttack would be an entity instead of an object except that we need a way of
	 * adding sun to our balance, once sun entities are added, this can be changed.
	 * @param toAttack The object being attacked
	 */
	protected abstract void attack(Object toAttack);

	/**
	 * Method to be initialized in each subclass that returns an the image representing that class.
	 * This method should only be run after the sprite has been resized
	 * @return The sprite representing the current class
	 */
	public abstract Image getSprite();

	/**
	 * Get whether the sprite has already been resized
	 * @return True if resized, false otherwise
	 */
	public abstract boolean getResized();

	/**
	 * Method to be initialized within each subclass to set the size of the sprite, this should only be run once and 
	 * @param blockWidth The new width of our sprite
	 * @param blockHeight The new height of our sprite
	 */
	public abstract void setSpriteSize(int blockWidth, int blockHeight);

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
