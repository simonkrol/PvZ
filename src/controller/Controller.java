package controller;

/**
 * The Controller Class
 * @author Boyan Siromahov and Gordon MacDonald
 * @version Nov 16, 2018
 */
import model.*;
import view.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.JButton;

public class Controller implements ActionListener, MouseListener
{
	Level level;
	View view;
	JButton button;
	protected int x, y;
	private int posY;
	private int posX;

	/**
	 * Create a controller for the current level
	 * @param lvl The current level
	 * @param view The GUI associated with the level
	 */
	public Controller(Level lvl, View view)
	{
		level = lvl;
		this.view = view;
	}

	@Override
	/**
	 * The Action Performed method, deals with user inputs
	 */
	public void actionPerformed(ActionEvent e)
	{
		button = (JButton) e.getSource();
		if (button.getText().equals("Add plant"))
		{
			level.setAdd(true);

		} else if (button.getText().equals("End Turn"))
		{
			try
			{
				level.allTurn();
				level.spawnZombies();
			} catch (IOException e1)
			{
			}
			if (level.checkWin())
			{
				view.update();
				System.out.println("You Killed all the zombies! \n Congratulations you won!");
				return;
			}
			if (level.checkFail())
			{
				view.update();
				System.out.println("Zombies have gotten past! \nGame over! ");
				return;
			} else
			{
				view.update();
			}
		} else if (button.getText().equals("Quit Game"))
		{
			view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
			System.exit(0);
		} else if (button.getText().equals("Sunflower"))
		{
			if (level.getAdd())
			{
				level.placePlant(new Sunflower(), view.canvas.hLY, view.canvas.hLX);
				level.setAdd(false);
				view.canvas.highlight = false;
				view.update();
			}
		}

		else if (button.getText().equals("Peashooter"))
		{
			if (level.getAdd())
			{
				level.placePlant(new Peashooter(), view.canvas.hLY, view.canvas.hLX);
				level.setAdd(false);
				view.canvas.highlight = false;
				view.update();
			}
		}

	}

	@Override
	/**
	 * Listens for mouseclicks
	 */
	public void mouseClicked(MouseEvent arg0)
	{
		Point m = MouseInfo.getPointerInfo().getLocation();
		Point s = view.getLocationOnScreen();
		if (level.getAdd())
		{
			x = m.x - s.x;
			y = m.y - s.y - 50;
			posY = (int) Math.floor(y / 125);
			posX = (int) Math.floor(x / 125);
			view.canvas.highLight(posX, posY);
			view.update();
		}
	}

	@Override
	/**
	 * Overridden event method, not being used
	 */
	public void mouseEntered(MouseEvent arg0)
	{
	}

	@Override
	/**
	 * Overridden event method, not being used
	 */
	public void mouseExited(MouseEvent arg0)
	{
	}

	@Override
	/**
	 * Overridden event method, not being used
	 */
	public void mousePressed(MouseEvent arg0)
	{
	}

	@Override
	/**
	 * Overridden event method, not being used
	 */
	public void mouseReleased(MouseEvent arg0)
	{
	}

}
