package controller;

/**
 * The BuilderController class, controllers the LevelBuilder GUI
 * @author Boyan Siromahov, Simon Krol
 * @version Dec 7, 2018
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import view.LevelBuilder;

public class BuildController implements ActionListener
{

	private JComboBox<String>[] dropDowns;
	private static int turn;
	private static JsonObject waves;
	private JLabel turnLabel;
	private LevelBuilder builder;

	public BuildController()
	{
		this(null, null);
	}

	public BuildController(LevelBuilder builder)
	{
		this(null, null);
		this.builder = builder;
	}

	public BuildController(JComboBox<String>[] dropDowns, JLabel turnLabel)
	{
		turn = 0;
		this.dropDowns = dropDowns;
		this.turnLabel = turnLabel;
		waves = new JsonObject();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{

		String actionCommand = e.getActionCommand();
		if (actionCommand == "Add")
		{
			JsonObject wave;
			JsonArray zombies;
			wave = new JsonObject();
			for (int i = 0; i < dropDowns.length; i++)
			{

				String selected = dropDowns[i].getSelectedItem().toString();
				if (selected != "None")
				{
					zombies = new JsonArray();
					zombies.add(selected);
					wave.add(Integer.toString(i), zombies);
				}
			}
			turn++;
			turnLabel.setText("Turn " + Integer.toString(turn));

			waves.add(Integer.toString(turn), wave);

		}
		if (actionCommand == "Done")
		{
			builder.writeLevel();
			builder.frame.dispose();
		}
	}

	public static int getTurn()
	{
		return turn;
	}

	public static JsonObject getWaves()
	{
		return waves;
	}

}
