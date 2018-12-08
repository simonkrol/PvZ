package view;

/**
 * The LevelBuilder class, creates the LevelBuilder GUI
 * @author Boyan Siromahov, Gordon MacDonald
 * @version Dec 7, 2018
 */
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.BuildController;

public class LevelBuilder
{

	private JFrame frame;
	public SelectView sel;
	private int height = 0;
	private int width = 0;
	private MainMenu mm;
	private JComboBox<String>[] dropDowns;
	private String[] zombies = { "None", "ImpZombie", "Zombie" };

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
	public void initialize()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		JButton doneBtn = new JButton("Done");
		doneBtn.setBounds(360, 380, 100, 50);
		// doneBtn.addActionListener(ADD BUTTON CONTROLLER);
		frame.getContentPane().add(doneBtn);

		JButton nextBtn = new JButton("Next Wave");
		nextBtn.setBounds(490, 380, 100, 50);
		// nextBtn.addActionListener(ADD BUTTON CONTROLLER);
		frame.getContentPane().add(nextBtn);

		int y = 10;
		for (int i = 0; i < height; i++)
		{
			JLabel label = new JLabel("Lane " + (i + 1));
			label.setBounds(60, y, 50, 20);
			frame.getContentPane().add(label);

			JComboBox<String> cB = new JComboBox<String>(zombies);
			cB.setBounds(150, y, 100, 20);
			dropDowns[i] = cB;
			cB.addActionListener(new BuildController());
			frame.getContentPane().add(cB);

			y += 30;
		}

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

}
