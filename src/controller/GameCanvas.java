package controller;

/**
 * The GameCanvas classed, used to display images to the gui
 * @author Boyan Siromahov and Gordon MacDonald
 * @version Nov 16, 2018
 */
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import model.Lane;
import model.Level;
import model.Peashooter;
import model.Spot;
import model.Sunflower;
import model.Zombie;

public class GameCanvas extends Canvas
{

	private static final long serialVersionUID = 1L;
	private final int blockWidth, blockHeight;
	private Level level;
	private Graphics g;
	Image grass, zombie, sunflower, peashooter, grassHL;
	protected boolean highlight = false;
	protected int hLX = 0, hLY = 0;

	/**
	 * Create a gamecanvas with given level
	 * 
	 * @param lvl The current level
	 */
	public GameCanvas(Level lvl, int blockWidth, int blockHeight)
	{
		level = lvl;
		this.blockWidth = blockWidth;
		this.blockHeight = blockHeight;
		grass = getScaledImage(new ImageIcon("Assets/Pictures/grass.jpg"), blockWidth, blockHeight);
		zombie = getScaledImage(new ImageIcon("Assets/Pictures/zombie.png"), blockWidth, blockHeight);
		sunflower = getScaledImage(new ImageIcon("Assets/Pictures/rsz_unknown.png"), blockWidth, blockHeight);
		peashooter = getScaledImage(new ImageIcon("Assets/Pictures/peaShooter.png"), blockWidth, blockHeight);
		grassHL = getScaledImage(new ImageIcon("Assets/Pictures/HLgrass.jpg"), blockWidth, blockHeight);
	}

	@Override
	/**
	 * Paint the spots, plants and zombies
	 */
	public void paint(Graphics g)
	{
		this.g = g;

		int x = 0, y = 0;
		for (Lane lane : level.grid)
		{
			for (Spot spot : lane.getSpots()) // draw plants and spots
			{
				if (x == hLX * blockWidth && y == hLY * blockHeight && highlight)
				{
					g.drawImage(grassHL, x, y, this);

				} else
				{
					g.drawImage(grass, x, y, this);
				}

				if (spot.getPlant() instanceof Sunflower)
				{
					g.drawImage(sunflower, x, y, this);
				} else if (spot.getPlant() instanceof Peashooter)
				{
					g.drawImage(peashooter, x, y, this);
				}

				x += blockWidth;
			}

			for (Zombie zmb : lane.getLiveZombies())
			{
				int pos = zmb.getPosition();
				if (level.getWidth() * blockWidth - zmb.getPosition() < 0)
				{
					pos = 0;
				} else
				{
					pos = level.getWidth() * blockWidth - zmb.getPosition();
				}
				g.drawImage(zombie, pos, y, this);
			}
			x = 0;
			y += blockHeight;
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
	protected void setHighLight(boolean highlight)
	{
		this.highlight = highlight;
	}
	
	private Image getScaledImage(ImageIcon srcImg, int w, int h){
		Image image = srcImg.getImage(); // transform it 
		return image.getScaledInstance(w, h,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
	}

}
