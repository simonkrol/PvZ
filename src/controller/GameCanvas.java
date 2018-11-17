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
	private Level level;
	private Graphics g;
	Image grass = new ImageIcon("Assets/Pictures/grass.jpg").getImage();
	Image zombie = new ImageIcon("Assets/Pictures/zombie.png").getImage();
	Image sunflower = new ImageIcon("Assets/Pictures/rsz_unknown.png").getImage();
	Image peashooter = new ImageIcon("Assets/Pictures/peaShooter.png").getImage();
	Image grassHL = new ImageIcon("Assets/Pictures/HLgrass.jpg").getImage();
	protected boolean highlight = false;
	protected int hLX = 0, hLY = 0;

	/**
	 * Create a gamecanvas with given level
	 * 
	 * @param lvl The current level
	 */
	public GameCanvas(Level lvl)
	{
		level = lvl;
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
				if (x == hLX * 125 && y == hLY * 125 && highlight)
				{
					g.drawImage(grassHL, x, y, this);
					highlight = false;

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

				x += 125;
			}

			for (Zombie zmb : lane.getLiveZombies())
			{
				int pos = zmb.getPosition();
				if (level.getWidth() * 125 - zmb.getPosition() < 0)
				{
					pos = 0;
				} else
				{
					pos = level.getWidth() * 125 - zmb.getPosition();
				}
				g.drawImage(zombie, pos, y, this);
			}
			x = 0;
			y += 125;
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

}
