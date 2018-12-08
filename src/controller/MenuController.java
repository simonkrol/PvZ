package controller;

/**
 * The menu controller, controls the menu.
 * @author Boyan Siromahov and Simon Krol
 * @version Dec 7, 2018
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import model.Level;

import view.View;

public class MenuController extends Controller implements ActionListener
{
	JMenuItem menuButton;

	public MenuController(Level lvl, View view)
	{
		super(lvl, view);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		menuButton = (JMenuItem) e.getSource();
		switch (menuButton.getActionCommand())
		{
			case "Quit":
				view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
				System.exit(0);
				break;

			case "Undo":
				level.undo();
				view.update();
				break;

			case "Redo":
				level.redo();
				view.update();
				break;

			case "Save":
				String s = (String) JOptionPane.showInputDialog(view, "Save as:", lastSave);
				saveGame(s);
				break;
			case "Load":
				loadGame(getName(view));
				break;
		}

	}

}
