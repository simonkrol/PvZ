package model;

import java.awt.Image;

import javax.swing.ImageIcon;

import controller.Main;

/**
 * The Sunflower class, contains information about the Peashooter plant
 * @author Simon Krol
 * @version Nov 25, 2018
 */
public class Sunflower extends Plant
{
	private static final int DEFAULT_HP = 4;
	private static final int DEFAULT_ATTACK = 13;
	private static final double DEFAULT_ATTACKSPEED = 1; // Attacks per turn
	private static final int DEFAULT_VALUE = 50;
	private static final int DEFAULT_DELAY = 5;
	private static Image sprite = new ImageIcon(Main.class.getResource("/assets/PvZ_G/Sunflower.gif")).getImage();
	private static boolean resized = false;

	/**
	 * Creates a sunflower with default values
	 */
	public Sunflower()
	{
		super(DEFAULT_HP, DEFAULT_ATTACK, DEFAULT_ATTACKSPEED, DEFAULT_VALUE, DEFAULT_DELAY);
	}

	/**
	 * Add sun to the players balance
	 * @param curLevel The current level
	 */
	protected void attack(Object curLevel)
	{
		if (curLevel instanceof Level)
		{
			((Level) curLevel).addToBalance(this.attack);
		}
	}

	/**
	 * Cause the sunflower to perform any required actions
	 * @param curLevel The current level
	 */
	protected void turn(Level curLevel)
	{
		attackState += this.attackSpeed;
		while (attackState >= 1)
		{
			this.attack(curLevel);
			attackState--;
		}
	}

	@Override
	/**
	 * Get the classes sprite
	 */
	public Image getSprite()
	{
		return sprite;

	}

	@Override
	/**
	 * Get if the sprite has been resized already
	 */
	public boolean getResized()
	{
		return resized;
	}

	@Override
	/**
	 * Set the sprite size
	 */
	public void setSpriteSize(int blockWidth, int blockHeight)
	{
		sprite = sprite.getScaledInstance(blockWidth / 2, blockHeight, Image.SCALE_DEFAULT);
		resized = true;
	}
}
