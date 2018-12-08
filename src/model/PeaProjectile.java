package model;

/**
 * The PeaProjectile class, the projectile created by the Peashooter
 * @author Simon Krol
 * @version Nov 25th, 2018
 */
import java.awt.Image;

import javax.swing.ImageIcon;

import controller.Main;

public class PeaProjectile extends Projectile
{
	private static final double DEFAULT_MOVESPEED = 1;
	private static Image[] sprite = { new ImageIcon(Main.class.getResource("/assets/PvZ_G/Pea.png")).getImage(),
			new ImageIcon(Main.class.getResource("/assets/PvZ_G/PeaFire.png")).getImage() };
	private static boolean resized = false;

	/**
	 * Create a peaProjectile
	 * @param att The projectiles attack
	 * @param position Where the projectile should start in the lane
	 * @param lane Which lane the projectile should start in
	 */
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
		if (status.equals(Status.FIRE))
			return sprite[1];
		return sprite[0];

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
		for (int i = 0; i < sprite.length; i++)
		{
			sprite[i] = sprite[i].getScaledInstance(blockWidth / 4, blockWidth / 4, Image.SCALE_DEFAULT);
		}
		resized = true;
	}
	
	public boolean equals(Object toCheck)
	{
		if(!(toCheck instanceof PeaProjectile))return false;
		PeaProjectile toCompare = (PeaProjectile)toCheck;
		if(!((Projectile)this).equals(toCompare))return false;
		return true;
	}

}
