package controller;
/**
 * Class tests classes in controller package
 * @author Shaun Gordon
 * @version Nov 25, 2018
 */
import java.awt.Image;
import javax.swing.JButton;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import model.Level;
import model.Sunflower;
import view.View;

public class TestController{
	View testView;
	LevelLoader load;
	Level testLevel;
	Image testImage;
	GameCanvas testCanvas;
	Controller testController;
	JButton testButton;
	Sunflower flower;
	@Before
	public void setUp() throws Exception
	{
		load = new LevelLoader();
		testLevel = load.getLevel("nullLevel2.json");
		testView = new View(testLevel);
		testView.setVisible(false);
		testController = new Controller(testLevel, testView);
	}
	@Test
	public void testHighLight()
	{
		testCanvas = new GameCanvas(testLevel, 5, 5);
		assertFalse("Highlight should be false", testCanvas.getHighlight());
		testCanvas.highLight(3,4);
		assertTrue("Highlight should be true", testCanvas.getHighlight());
		testCanvas.setHighlight(false);
		assertFalse("Highlight should be false", testCanvas.getHighlight());
	}
	@Test
	public void testEndButton()
	{
		flower = new Sunflower();
		testButton = new JButton();
		testButton.setActionCommand("End");
		testButton.addActionListener(new Controller(testLevel, testView));
		testLevel.placePlant(flower, 2, 2);
		testButton.doClick();
		int balance = testLevel.getBalance();
		assertEquals("Balance should be 62", 62, balance);
		
	}
	@Test
	public void testSunflowerButton()
	{
		flower = new Sunflower();
		testButton = new JButton();
		testButton.setActionCommand("Plants/Sunflower");
		testButton.addActionListener(new Controller(testLevel, testView));
		testView.getCanvas().highLight(2, 2);
		testButton.doClick();
		assertFalse("Space is occupied", testLevel.placePlant(flower, 2, 2));
		assertEquals("Balance should be 50", 50, testLevel.getBalance());
		
	}
	@Test
	public void testWallnutButton()
	{
		flower = new Sunflower();
		testButton = new JButton();
		testButton.setActionCommand("Plants/Wallnut");
		testButton.addActionListener(new Controller(testLevel, testView));
		testView.getCanvas().highLight(2, 2);
		testButton.doClick();
		assertFalse("Space is occupied", testLevel.placePlant(flower, 2, 2));
		int balance = testLevel.getBalance();
		assertEquals("Balance should be 50", 50, balance);
		
	}
	@Test
	public void testPeashooterButton()
	{
		flower = new Sunflower();
		testButton = new JButton();
		testButton.setActionCommand("Plants/Peashooter");
		testButton.addActionListener(new Controller(testLevel, testView));
		testView.getCanvas().highLight(2, 2);
		testButton.doClick();
		assertFalse("Space is occupied", testLevel.placePlant(flower, 2, 2));
		int balance = testLevel.getBalance();
		assertEquals("Balance should be 60", 60, balance);
		
	}
	@Test
	public void testChomperButton()
	{
		flower = new Sunflower();
		testButton = new JButton();
		testButton.setActionCommand("Plants/Chomper");
		testButton.addActionListener(new Controller(testLevel, testView));
		testView.getCanvas().highLight(2, 2);
		testButton.doClick();
		assertFalse("Space is occupied", testLevel.placePlant(flower, 2, 2));
		int balance = testLevel.getBalance();
		assertEquals("Balance should be 40", 40, balance);
		
	}
	@Test
	public void testTorchwoodButton()
	{
		flower = new Sunflower();
		testButton = new JButton();
		testButton.setActionCommand("Plants/Torchwood");
		testButton.addActionListener(new Controller(testLevel, testView));
		testView.getCanvas().highLight(2, 2);
		testButton.doClick();
		assertFalse("Space is occupied", testLevel.placePlant(flower, 2, 2));
		int balance = testLevel.getBalance();
		assertEquals("Balance should be 40", 40, balance);
		
	}
	
	@Test
	public void testSaveLoad()
	{
		testController.saveGame("TestSave");
		testController.loadGame("TestSave");
		assertTrue("Levels should be identical", testLevel.equals(testController.level));
	}

}
