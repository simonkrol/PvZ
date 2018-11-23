package model;

import java.awt.Image;

import javax.swing.ImageIcon;

public class PeaProjectile extends Projectile
{
	private static final double DEFAULT_MOVESPEED = 1;
	private static Image sprite = new ImageIcon("res/assets/PvZ_G/Pea.png").getImage();
	private static boolean resized = false;

	protected PeaProjectile(int att, double position, Lane lane)
	{
		super(att, lane, DEFAULT_MOVESPEED, position);
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
		sprite = sprite.getScaledInstance(blockWidth/4, blockWidth/4, Image.SCALE_DEFAULT);
		resized = true;
	}

}
