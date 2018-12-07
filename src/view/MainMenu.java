package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import controller.LevelLoader;
import controller.MainMenuController;
import model.*;

import java.awt.Button;
import java.awt.Choice;
import java.io.FileReader;

public class MainMenu {

	public JFrame frame;
	private Level level;
	public Choice saveChoice;
	public LevelBuilder lvlB;

	/**
	 * Create the application.
	 */
	public MainMenu() {
		initialize();
	}

	public void startGame() {

		LevelLoader levels = new LevelLoader();
		level = levels.getLevel("Level1.json");
		@SuppressWarnings("unused")
		View levelGui = new View(level);
		JOptionPane.showMessageDialog(null,
				"To place a plant: click the tile you wish to play on, then the type of plant\n. Undo/Redo can be found in the menu",
				"Instructions", JOptionPane.PLAIN_MESSAGE);
		frame.dispose();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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

		// //Need to populate the list
		// saveChoice = new Choice();
		// saveChoice.setBounds(392, 345, 132, 20);
		// frame.getContentPane().add(saveChoice);

		ImageIcon logo = new ImageIcon("res/assets/PvZ/logo.png");
		JLabel titleLogo = new JLabel(logo);
		titleLogo.setBounds(63, 27, 461, 129);
		frame.getContentPane().add(titleLogo);

		ImageIcon backgroundGif = new ImageIcon("res/assets/PvZ_G/background.gif");

		Button newGame = new Button("New Game");
		newGame.addActionListener(new MainMenuController(this));
		newGame.setBounds(63, 286, 132, 53);
		frame.getContentPane().add(newGame);
		JLabel background = new JLabel(backgroundGif);
		background.setBounds(0, 0, 624, 441);
		frame.getContentPane().add(background);

		frame.setVisible(true);
	}
	public void buildLevel() {
		lvlB = new LevelBuilder(this);
	}
}
