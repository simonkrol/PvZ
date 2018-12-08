package view;

/**
 * The LevelBuilder class, creates the LevelBuilder GUI
 * @author Boyan Siromahov, Gordon MacDonald
 * @version Dec 7, 2018
 */

import java.awt.FlowLayout;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import controller.BuildController;


public class LevelBuilder
{


	private JTextField balance,passive, name;
	private JRadioButton sunflower, peashooter, wallnut, torchwood, chomper;
	public JFrame frame;

	public SelectView sel;
	private int height = 0;
	private int width = 0;
	private MainMenu mm;
	@SuppressWarnings("rawtypes")
	private String[] zombies = {"None", "BasicZombie", "ImpZombie", "BucketZombie"};
	private JLabel turnLabel;
	private JComboBox<String>[] dropDowns;

	/**
	 * Create the application.
	 */
	public LevelBuilder(MainMenu mm)
	{
		this.mm = mm;
		select();
	}

	/**
	 * Create a new select view
	 */
	private void select()
	{
		sel = new SelectView(mm);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		JPanel turnPanel = new JPanel();
		JPanel infoPanel = new JPanel();
		turnPanel.setLayout(new BoxLayout(turnPanel, BoxLayout.PAGE_AXIS));
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
		
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout());
		frame.setVisible(true);

		
		turnLabel = new JLabel("Turn 0");
		turnPanel.add(turnLabel);
		JPanel buttons = new JPanel();
		JButton doneBtn = new JButton("Done");
		doneBtn.setBounds(360, 380, 100, 50);
		doneBtn.addActionListener(new BuildController(this));
		doneBtn.setActionCommand("Done");
		
		JButton nextBtn = new JButton("Add Wave");
		nextBtn.setBounds(490, 380, 100, 50);
		
		nextBtn.addActionListener(new BuildController(dropDowns, turnLabel));
		nextBtn.setActionCommand("Add");

		int y = 10;
		for (int i = 0; i < height; i++) {
			JPanel tempPanel = new JPanel();
			
			JLabel label = new JLabel("Lane " + (i + 1));
			label.setBounds(60, y, 50, 20);
			tempPanel.add(label);

			JComboBox<String> cB = new JComboBox<String>(zombies);
			cB.setBounds(150, y, 100, 20);
			dropDowns[i] = cB;
			cB.addActionListener(new BuildController());
			tempPanel.add(cB);
			turnPanel.add(tempPanel);
			y += 30;
		}
		buttons.add(nextBtn);
		buttons.add(doneBtn);
		turnPanel.add(buttons);
		JLabel nameLabel = new JLabel("Level Name:");
		name = new JTextField(10);
		
		JLabel balanceLabel = new JLabel("Starting Balance:");
		balance = new JTextField(10);
		
		JLabel passiveLabel = new JLabel("Passive Gen/Turn:");
		passive = new JTextField(10);
		
		JLabel sunflowerLabel = new JLabel("Allowed Sunflower:");
		sunflower = new JRadioButton();
		sunflower.setSelected(true);

		JLabel peashooterLabel = new JLabel("Allowed Peashooter:");
		peashooter = new JRadioButton();
		peashooter.setSelected(true);
		
		JLabel chomperLabel = new JLabel("Allowed Chomper:");
		chomper = new JRadioButton();
		chomper.setSelected(true);
		
		JLabel wallnutLabel = new JLabel("Allowed Wallnut:");
		wallnut = new JRadioButton();
		wallnut.setSelected(true);
		
		JLabel torchwoodLabel = new JLabel("Allowed Torchwood:");
		torchwood = new JRadioButton();
		torchwood.setSelected(true);
		
		infoPanel.add(nameLabel);
		infoPanel.add(name);
		infoPanel.add(balanceLabel);
		infoPanel.add(balance);
		infoPanel.add(passiveLabel);
		infoPanel.add(passive);
		infoPanel.add(sunflowerLabel);
		infoPanel.add(sunflower);
		infoPanel.add(peashooterLabel);
		infoPanel.add(peashooter);
		infoPanel.add(chomperLabel);
		infoPanel.add(chomper);
		infoPanel.add(wallnutLabel);
		infoPanel.add(wallnut);
		infoPanel.add(torchwoodLabel);
		infoPanel.add(torchwood);
		
		
		
		
		
		frame.add(turnPanel);
		frame.add(infoPanel);

	}

	@SuppressWarnings("unchecked")
	/**
	 * Set the dimensions of the level builder
	 * @return
	 */
	public boolean setDim()
	{
		height = sel.getHeight();
		width = sel.getWidth();
		if (height != 0 && width != 0)
		{
			sel.frame.dispose();
			dropDowns = new JComboBox[height];
			return true;
		}
		return false;
	}
	
	public void writeLevel()
	{
		JsonObject fullLevel = new JsonObject();
		fullLevel.addProperty("balance", Integer.parseInt(balance.getText()));
		fullLevel.addProperty("passiveGen", Integer.parseInt(passive.getText()));
		fullLevel.addProperty("name", name.getText());
		fullLevel.addProperty("numturns", BuildController.getTurn()+1 );
		fullLevel.add("turns", BuildController.getWaves());
		fullLevel.addProperty("height", height);
		fullLevel.addProperty("width", width);
		JsonArray allowedPlants = new JsonArray();
		if(sunflower.isSelected())allowedPlants.add("Sunflower");
		if(peashooter.isSelected())allowedPlants.add("Peashooter");
		if(wallnut.isSelected())allowedPlants.add("Wallnut");
		if(torchwood.isSelected())allowedPlants.add("Torchwood");
		if(chomper.isSelected())allowedPlants.add("Chomper");
		fullLevel.add("plants", allowedPlants);
		
		FileWriter fileW;
		try
		{
			fileW = new FileWriter("res/levels/" + name.getText()+".json");
			fileW.write(fullLevel.toString());
			fileW.close();
		} catch (IOException e)
		{
			System.out.println("error on update LL - 120");
		}

	}

}
