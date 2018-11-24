package model;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * The Basic Zombie class
 * @author Simon Krol
 * @version Nov 16, 2018
 */
public class BasicZombie extends Zombie
{
	private static final int DEFAULT_HP = 5;
	private static final int DEFAULT_ATTACK = 1;
	private static final double DEFAULT_MOVESPEED = 1; // Units per turn
	private static final double DEFAULT_ATTACKSPEED = 0.8; // Attacks per turn
	private static Image sprite = new ImageIcon("res/assets/PvZ_G/BasicZombie.gif").getImage();
	private static boolean resized = false;

	/**
	 * Create a basicZombie without a starting lane
	 */
	public BasicZombie()
	{
		this(null);
	}

	/**
	 * Constructor to create a basicZombie
	 * @param lane The lane the zombie will exist in
	 */
	public BasicZombie(Lane lane)
	{
		super(DEFAULT_HP, DEFAULT_ATTACK, DEFAULT_MOVESPEED, DEFAULT_ATTACKSPEED, lane);
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
		sprite = sprite.getScaledInstance(blockWidth/2, blockHeight, Image.SCALE_DEFAULT);
		resized = true;
	}
}
