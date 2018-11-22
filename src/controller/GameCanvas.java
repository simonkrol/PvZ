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
import javax.swing.JPanel;

import model.Lane;
import model.Level;
import model.Peashooter;
import model.Projectile;
import model.Spot;
import model.Sunflower;
import model.Zombie;

public class GameCanvas extends JPanel
{

	private static final long serialVersionUID = 1L;
	private final int blockWidth, blockHeight;
	private Level level;
	private Graphics g;
	Image basicZombie, sunflower, peashooter, lawnMower, pea;
	Image grass[];
	protected boolean highlight = false;
	protected int hLX = 0, hLY = 0;
	private int x, y;

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
		grass = new Image[4];
		lawnMower = getScaledImage(new ImageIcon("res/assets/Pvz_G/LawnMower.png"), (int)(blockWidth/1.5), (int)(blockHeight/1.5));
		pea = getScaledImage(new ImageIcon("res/assets/PvZ_G/Pea.png"), blockWidth/4, blockWidth/4);
		
		grass[0] = getScaledImage(new ImageIcon("res/assets/PvZ_G/Grass0.png"), blockWidth, blockHeight);
		grass[1] = getScaledImage(new ImageIcon("res/assets/PvZ_G/Grass1.png"), blockWidth, blockHeight);
		grass[2] = getScaledImage(new ImageIcon("res/assets/PvZ_G/Grass0H.png"), blockWidth, blockHeight);
		grass[3] = getScaledImage(new ImageIcon("res/assets/PvZ_G/Grass1H.png"), blockWidth, blockHeight);
		basicZombie = getScaledImage(new ImageIcon("res/assets/PvZ_G/BasicZombie.gif"), blockWidth/2, blockHeight);
		sunflower = getScaledImage(new ImageIcon("res/assets/PvZ_G/Sunflower.gif"), blockWidth/2, blockHeight);
		peashooter = getScaledImage(new ImageIcon("res/assets/PvZ_G/Peashooter.gif"), blockWidth/2, blockHeight);

//		grass[0] = getScaledImage(new ImageIcon("res/assets/PvZ/grass.jpg"), blockWidth, blockHeight);
//		grass[1] = getScaledImage(new ImageIcon("res/assets/PvZ/grass.jpg"), blockWidth, blockHeight);
//		grass[2] = getScaledImage(new ImageIcon("res/assets/PvZ/HLgrass.jpg"), blockWidth, blockHeight);
//		grass[3] = getScaledImage(new ImageIcon("res/assets/PvZ/HLgrass.jpg"), blockWidth, blockHeight);
//		basicZombie = getScaledImage(new ImageIcon("res/assets/PvZ/BasicZombie.png"), blockWidth/2, blockHeight);
//		sunflower = getScaledImage(new ImageIcon("res/assets/PvZ/Sunflower.png"), blockWidth/2, blockHeight);
//		peashooter = getScaledImage(new ImageIcon("res/assets/PvZ/Peashooter.png"), blockWidth/2, blockHeight);

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

		for (Lane lane : level.grid)
		{
			for (Spot spot : lane.getSpots()) // draw plants and spots
			{
				int grassIndex=0;
				if (x == hLX  && y == hLY && highlight) grassIndex+=2;
				if((x+y)%2!=0)grassIndex++;
				g.drawImage(grass[grassIndex], x*blockWidth, y*blockHeight, this);


				if (spot.getPlant() instanceof Sunflower)
				{
					g.drawImage(sunflower, x*blockWidth+(blockWidth/2)-(sunflower.getWidth(this)/2), y*blockHeight, this);
				} else if (spot.getPlant() instanceof Peashooter)
				{
					g.drawImage(peashooter, x*blockWidth+(blockWidth/2)-(peashooter.getWidth(this)/2), y*blockHeight, this);
				}
				if(x==0 && lane.getEndState()==0)
				{
					g.drawImage(lawnMower, x*blockWidth-blockWidth/3, y*blockHeight+(blockHeight/2)-lawnMower.getHeight(this)/2, this);
				}

				x++;
			}

			for (Zombie zmb : lane.getLiveZombies())
			{
				double pos = zmb.getPosition();
				if ((level.getWidth() - pos) * blockWidth < 0)
				{
					pos = 0;
				} else
				{
					pos = (level.getWidth() - pos) * blockWidth;
				}
				pos += (blockWidth/2) - basicZombie.getWidth(this)/2;
				g.drawImage(basicZombie, (int)pos, y*blockHeight, this);
			}
			for (Projectile proj : lane.getProjectiles())
			{
				g.drawImage(pea,(int)(proj.getPosition()*blockWidth), (int)((y+proj.getOffset())*blockHeight)+blockHeight/2 - pea.getHeight(this)/2 , this);
			}
			x = 0;
			y ++;
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

	/**
	 * Get a scale Image of an ImageIcon with the given size values
	 * @param srcImg The src ImageIcon
	 * @param w The new Width
	 * @param h The new Height
	 * @return Scaled Image
	 */
	private Image getScaledImage(ImageIcon srcImg, int w, int h)
	{
		return srcImg.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT); // scale it the default way to preserve gifs
	}

}
