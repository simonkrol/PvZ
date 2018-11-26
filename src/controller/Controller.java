package controller;

/**
 * The Controller Class
 * @author Boyan Siromahov and Gordon MacDonald
 * @version Nov 24, 2018
 */
import model.*;
import view.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Controller implements ActionListener, MouseListener
{
	private Level level;
	private View view;
	private JButton button;
	private int x, y;
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
		if (button.getActionCommand().equals("End"))
		{
			level.allTurn();
			level.wipeTurnHist(); // clear the previous recorded actions at the end of each turn
			if (level.checkWin())
			{
				view.update();
				JOptionPane.showMessageDialog(null, "You killed them all!", "WIN", JOptionPane.PLAIN_MESSAGE);
				button.setEnabled(false);
				return;
			}
			if (level.checkFail())
			{
				view.update();
				JOptionPane.showMessageDialog(null, "Zombies have gotten past!  Game over", "LOSS",
						JOptionPane.PLAIN_MESSAGE);
				button.setEnabled(false);
				return;
			} else
			{
				view.update();
			}
		} else if (button.getActionCommand().equals("Quit"))
		{
			view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
			System.exit(0);
		} else if (button.getActionCommand().startsWith("Plants"))
		{
			if (view.getCanvas().getHighlight())
			{
				String plantType = button.getActionCommand().split("/")[1];
				try
				{
					Class<?> cls = Class.forName("model." + plantType);
					Plant newPlant = (Plant) cls.newInstance();
					level.placePlant(newPlant, view.getCanvas().getHLY(), view.getCanvas().getHLX());
					view.getCanvas().setHighlight(false);
					view.update();
				} catch (Exception e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

		view.getCanvas().setHighlight(false);
	}

	@Override
	/**
	 * Listens for mouseclicks
	 */
	public void mouseClicked(MouseEvent arg0)
	{
		Point m = MouseInfo.getPointerInfo().getLocation();
		Point s = view.getLocationOnScreen();
		x = m.x - s.x;
		y = m.y - s.y - 50;
		posY = (int) Math.floor(y / view.getBlockHeight());
		posX = (int) Math.floor(x / view.getBlockWidth());
		view.getCanvas().highLight(posX, posY);
		view.update();
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
	public void mouseReleased(MouseEvent arg0)
	{

	}
}
