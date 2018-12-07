package controller;

/**
 * The GameCanvas classed, used to display images to the gui
 * @author Boyan Siromahov, Gordon MacDonald and Simon Krol
 * @version Nov 16, 2018
 */
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.*;

public class GameCanvas extends JPanel
{

	private static final long serialVersionUID = 1L;
	private final int blockWidth, blockHeight;
	private Level level;
	@SuppressWarnings("unused")
	private Graphics g;
	private Image lawnMower;
	private Image grass[];
	private boolean highlight = false;
	private int hLX = 0, hLY = 0;
	private int x, y;

	/**
	 * Create a gamecanvas with given level
	 * 
	 * @param lvl The current level
	 * @param blockWidth The width of each block in pixels
	 * @param blockHeight The heigh of each block in pixels
	 */
	public GameCanvas(Level lvl, int blockWidth, int blockHeight)
	{
		level = lvl;
		this.blockWidth = blockWidth;
		this.blockHeight = blockHeight;
		grass = new Image[4];

		lawnMower = getScaledImage(new ImageIcon(Main.class.getResource("/assets/Pvz_G/LawnMower.png")), (int) (blockWidth / 1.5),
				(int) (blockHeight / 1.5));
		grass[0] = getScaledImage(new ImageIcon(Main.class.getResource("/assets/PvZ_G/Grass0.png")), blockWidth, blockHeight);
		grass[1] = getScaledImage(new ImageIcon(Main.class.getResource("/assets/PvZ_G/Grass1.png")), blockWidth, blockHeight);
		grass[2] = getScaledImage(new ImageIcon(Main.class.getResource("/assets/PvZ_G/Grass0H.png")), blockWidth, blockHeight);
		grass[3] = getScaledImage(new ImageIcon(Main.class.getResource("/assets/PvZ_G/Grass1H.png")), blockWidth, blockHeight);

	}

	@Override
	/**
	 * Paint the spots, plants and zombies
	 */
	public void paintComponent(Graphics g)
	{
		this.g = g;

		x = 0;
		y = 0;
		Image sprite;
		Plant curPlant;

		for (Lane lane : level.getLanes())
		{
			for (Spot spot : lane.getSpots()) // draw plants and spots
			{
				int grassIndex = 0;
				if (x == hLX && y == hLY && highlight)
					grassIndex += 2;
				if ((x + y) % 2 != 0)
					grassIndex++;
				g.drawImage(grass[grassIndex], x * blockWidth, y * blockHeight, this);
				if (spot.getOccupied())
				{
					curPlant = spot.getPlant();
					if (!curPlant.getResized())
						curPlant.setSpriteSize(blockWidth, blockHeight);
					sprite = curPlant.getSprite();
					g.drawImage(sprite, x * blockWidth + (blockWidth / 2) - (sprite.getWidth(this) / 2),
							y * blockHeight, this);

				}
				if (x == 0 && lane.getEndState() == 0)
				{
					g.drawImage(lawnMower, x * blockWidth - blockWidth / 3,
							y * blockHeight + (blockHeight / 2) - lawnMower.getHeight(this) / 2, this);
				}
				x++;
			}
			/*
			 * Paint all the zombies on the lane
			 */
			for (Zombie zmb : lane.getLiveZombies())
			{
				if (!zmb.getResized())
					zmb.setSpriteSize(blockWidth, blockHeight);
				sprite = zmb.getSprite();

				double pos = zmb.getPosition();
				pos = (level.getWidth() - pos) * blockWidth;
				if (pos < 0)
					pos = 0;
				pos += (blockWidth / 2) - sprite.getWidth(this) / 2;
				g.drawImage(sprite, (int) pos, y * blockHeight, this);
			}
			/*
			 * Paint all the projectiles on the lane
			 */
			for (Projectile proj : lane.getProjectiles())
			{
				if (!proj.getResized())
					proj.setSpriteSize(blockWidth, blockHeight);
				sprite = proj.getSprite();
				g.drawImage(sprite, (int) (proj.getPosition() * blockWidth),
						(int) ((y + proj.getOffset()) * blockHeight) + blockHeight / 2 - sprite.getHeight(this) / 2,
						this);
			}
			x = 0;
			y++;
		}

	}

	/**
	 * Highlight a block
	 * @param x The x coordinate of the highlighted block
	 * @param y The y coordinate of the highlighted block
	 */
	protected void highLight(int x, int y)
	{
		highlight = true;
		hLX = x;
		hLY = y;
	}

	/**
	 * Set whether the current block should be highlighted
	 * @param highlight The new boolean representing whether the block is highlighted
	 */
	protected void setHighlight(boolean highlight)
	{
		this.highlight = highlight;
	}

	/**
	 * Get whether the current block is highlight
	 * @return True if highlighted, false otherwise
	 */
	public Boolean getHighlight()
	{
		return highlight;
	}

	/**
	 * Get the x coordinate of the highlighted block
	 * @return Highlighted x coordinate
	 */
	public int getHLX()
	{
		return hLX;
	}

	/**
	 * Get the y coordinate of the highlighted block
	 * @return Highlighted y coordinate 
	 */
	public int getHLY()
	{
		return hLY;
	}

	/**
	 * Get a scale Image of an ImageIcon with the given size values
	 * @param srcImg The src ImageIcon
	 * @param w The new Width
	 * @param h The new Height
	 * @return Scaled Image
	 */
	protected Image getScaledImage(ImageIcon srcImg, int w, int h)
	{
		return srcImg.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT); // scale it the default way to preserve
																				// gifs
	}

}
