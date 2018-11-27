package view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controller.LevelLoader;
import controller.MainMenuController;
import model.Level;

import java.awt.Label;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Choice;

public class MainMenu {

	private JFrame frame;
	private Level level;
	private static MainMenu window;
	public Choice saveChoice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new MainMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainMenu() {
		initialize();
	}

	public void loadGame(String levelSave) {
		LevelLoader levels = new LevelLoader();
		level = levels.getLevel(levelSave);
	}

	public void startGame() {

		@SuppressWarnings("unused")
		LevelLoader levels = new LevelLoader();
		level = levels.getLevel("Level1.json");
		View levelGui = new View(level);
		JOptionPane.showMessageDialog(null,
				"To place a plant: click the tile you wish to play on, then the type of plant\n. Undo/Redo can be found in the menu",
				"Instructions", JOptionPane.PLAIN_MESSAGE);
		window.frame.setVisible(false);
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

		
		Button newGame = new Button("New Game");
		newGame.addActionListener(new MainMenuController(this));
		newGame.setBounds(63, 286, 132, 53);
		frame.getContentPane().add(newGame);

		Button loadGame = new Button("Load Game");
		loadGame.setBounds(392, 286, 132, 53);
		frame.getContentPane().add(loadGame);
		loadGame.addActionListener(new MainMenuController(this));
		
		
		
		//Need to populate the list 
		saveChoice = new Choice();
		saveChoice.setBounds(392, 345, 132, 20);
		frame.getContentPane().add(saveChoice);
		
		
		ImageIcon logo = new ImageIcon("res/assets/PvZ/logo.png");
		JLabel titleLogo = new JLabel(logo);
		titleLogo.setBounds(63, 27, 461, 129);
		frame.getContentPane().add(titleLogo);
		
		ImageIcon backgroundGif = new ImageIcon("res/assets/PvZ_G/background.gif");
		JLabel background = new JLabel(backgroundGif);
		background.setBounds(0, 0, 624, 441);
		frame.getContentPane().add(background);
	}
}
