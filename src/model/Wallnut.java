package model;

import java.awt.Image;

import javax.swing.ImageIcon;

import controller.Main;

/**
 * The Sunflower class, contains information about the Wallnut plant
 * @author Simon Krol
 * @version Nov 16, 2018
 */
public class Wallnut extends Plant
{
	private static final int DEFAULT_HP = 12;
	private static final int DEFAULT_ATTACK = 0;
	private static final double DEFAULT_ATTACKSPEED = 0; // Attacks per turn
	private static final int DEFAULT_VALUE = 50;
	private static final int DEFAULT_DELAY = 5;
	private static Image sprite = new ImageIcon(Main.class.getResource("/assets/PvZ_G/Wallnut.gif")).getImage();
	private static boolean resized = false;

	/**
	 * Creates a sunflower with default values
	 */
	public Wallnut()
	{
		super(DEFAULT_HP, DEFAULT_ATTACK, DEFAULT_ATTACKSPEED, DEFAULT_VALUE, DEFAULT_DELAY);
	}

	/**
	 * It's a wallnut...it doesnt attack
	 * @param curLevel The current level
	 */
	protected void attack(Object curLevel)
	{

	}

	/**
	 * Cause the wallnut to perform any required action
	 * @param curLevel The current level
	 */
	protected void turn(Level curLevel)
	{
		// Once again, its a wallnut
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
