package model;

import java.awt.Image;

import javax.swing.ImageIcon;

import controller.Main;

/**
 * The Peashooter class, contains information about the Peashooter plant
 * @author Simon Krol
 * @version Nov 16, 2018
 */
public class Torchwood extends Plant
{
	private static final int DEFAULT_HP = 5;
	private static final int DEFAULT_ATTACK = 3;
	private static final double DEFAULT_ATTACKSPEED = 1; // Attacks per turn
	private static final int DEFAULT_VALUE = 60;
	private static final int DEFAULT_DELAY = 5;
	private static Image sprite = new ImageIcon(Main.class.getResource("/assets/PvZ_G/Torchwood.gif")).getImage();
	private static boolean resized = false;

	/**
	 * Creates a Torchwood with default values
	 */
	public Torchwood()
	{
		super(DEFAULT_HP, DEFAULT_ATTACK, DEFAULT_ATTACKSPEED, DEFAULT_VALUE, DEFAULT_DELAY);
	}

	/**
	 * Its a stump...
	 */
	protected void attack(Object toAttack)
	{
		// Do nothing
	}

	protected void turn(Level curLevel)
	{
		// Do nothing
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
