package view;
/**
 * The MainMenu View
 * @author Boyan Siromahov and Simon Krol
 * @version Dec 7, 2018
 */

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import controller.Main;
import controller.MainMenuController;

import java.awt.Button;

public class MainMenu
{

	public JFrame frame;
	public LevelBuilder lvlB;

	/**
	 * Create a new main menu
	 */
	public MainMenu()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		Button loadGame = new Button("Load Game");
		loadGame.setBounds(226, 286, 132, 53);
		frame.getContentPane().add(loadGame);
		loadGame.addActionListener(new MainMenuController(this));

		Button buildGame = new Button("Build Level");
		buildGame.setBounds(392, 286, 132, 53);
		frame.getContentPane().add(buildGame);
		buildGame.addActionListener(new MainMenuController(this));

		ImageIcon logo = new ImageIcon(Main.class.getResource("/assets/PvZ/logo.png"));
		JLabel titleLogo = new JLabel(logo);
		titleLogo.setBounds(63, 27, 461, 129);
		frame.getContentPane().add(titleLogo);

		ImageIcon backgroundGif = new ImageIcon(Main.class.getResource("/assets/PvZ_G/background.gif"));

		Button newGame = new Button("New Game");
		newGame.addActionListener(new MainMenuController(this));
		newGame.setBounds(63, 286, 132, 53);
		frame.getContentPane().add(newGame);

		JLabel background = new JLabel(backgroundGif);
		background.setBounds(0, 0, 624, 441);
		frame.getContentPane().add(background);

		frame.setVisible(true);
	}

	/**
	 * Create the level builder
	 */
	public void buildLevel()
	{
		frame.dispose();
		lvlB = new LevelBuilder(this);
	}
}
