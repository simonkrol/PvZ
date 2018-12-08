package controller;

/**
 * The MainMenuController Class
 * @author Boyan Siromahov and Simon Krol
 * @version Dec 7, 2018
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.MainMenu;
import view.View;

public class MainMenuController extends Controller implements ActionListener
{

	private MainMenu mm;

	/**
	 * Create a main menu controller
	 * @param mm The main menu this reacts to
	 */
	public MainMenuController(MainMenu mm)
	{
		super(null, null);
		this.mm = mm;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch (e.getActionCommand())
		{
			case "New Game":
				startGame();
				break;
			case "Load Game":
				loadGame(getName(mm.frame));
				break;
			case "Build Level":
				mm.buildLevel();
				break;
			case "Done":
				if (mm.lvlB.setDim())
					mm.lvlB.initialize();
				break;
		}

	}

	/**
	 * Start a game using our default level
	 */
	private void startGame()
	{
		LevelLoader levels = new LevelLoader();
		level = levels.getLevel("Level1.json");
		@SuppressWarnings("unused")
		View levelGui = new View(level);
		JOptionPane.showMessageDialog(null,
				"To place a plant: click the tile you wish to play on, then the type of plant\n. Undo/Redo/Save/Load can be found in the menu",
				"Instructions", JOptionPane.PLAIN_MESSAGE);
		mm.frame.dispose();
	}


}
