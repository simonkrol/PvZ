package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.MainMenuController;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SelectView {

	JFrame frame;
	private JTextField heightField;
	private JTextField widthField;
	private MainMenu mm;


	/**
	 * Create the application.
	 */
	public SelectView(MainMenu mm) {
		this.mm = mm;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 339, 218);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLabel lblGameWidth = new JLabel("Game Width: ");
		lblGameWidth.setBounds(10, 51, 86, 23);
		frame.getContentPane().add(lblGameWidth);
		
		JLabel lblGameHeight = new JLabel("Game Height:");
		lblGameHeight.setBounds(10, 11, 86, 29);
		frame.getContentPane().add(lblGameHeight);
		
		heightField = new JTextField();
		heightField.setBounds(106, 15, 86, 20);
		frame.getContentPane().add(heightField);
		heightField.setColumns(10);
		
		widthField = new JTextField();
		widthField.setColumns(10);
		widthField.setBounds(106, 52, 86, 20);
		frame.getContentPane().add(widthField);
		
		JButton btnDone = new JButton("Done");
		btnDone.setActionCommand("Done");
		btnDone.setBounds(202, 132, 89, 23);
		btnDone.addActionListener(new MainMenuController(mm));
		frame.getContentPane().add(btnDone);
		
		JLabel lblWidthsAndHeights = new JLabel("<html>Enter the # height and width of the game above.<br/>Widths and heights over 10 will cause<br/> the game to look weird</html>");
		lblWidthsAndHeights.setBounds(10, 83, 303, 66);
		frame.getContentPane().add(lblWidthsAndHeights);
	}
	
	public int getWidth() {
		try {
			return Integer.parseInt(widthField.getText());
		}catch (Exception e) {}
		return 0;
	}
	
	public int getHeight() {
		try {
			return Integer.parseInt(heightField.getText());
		}catch (Exception e){}		
		return 0;
	}
}
