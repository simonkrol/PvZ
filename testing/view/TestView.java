package view;
/**
 * Tests View class
 * @author Shaun Gordon
 * @version Nov 25, 2018
 */
import org.junit.Before;
import org.junit.Test;

import controller.Controller;
import controller.LevelLoader;
import model.Level;

import static org.junit.Assert.*;
import javax.swing.JLabel;
public class TestView
{
	LevelLoader load;
	View testView;
	Level testLevel;
	Controller testController;
	JLabel testLabel;
	@Before
	public void setUp() throws Exception
	{
		load = new LevelLoader();
		testLevel = load.getLevel("nullLevel.json");
		testView = new View(testLevel);
		testController = new Controller(testLevel, testView);
	}
	@Test
	public void testUpdateMessage()
	{
		testLabel = new JLabel();
		testLabel.setText("SUN: " + 100 + "  Turn: " + 0);
		assertEquals("Labels should be the same", testLabel.getText(), testView.info.getText());
	}
}
