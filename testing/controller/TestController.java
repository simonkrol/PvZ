package controller;

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
		testLevel = load.getLevel("nullLevel.json");
		testView = new View(testLevel);
		testView.setVisible(false);
		testController = new Controller(testLevel, testView);
	}
	@Test
	public void testHighLight()
	{
		testCanvas = new GameCanvas(testLevel, 5, 5);
		assertFalse("Highlight should be false", testCanvas.getHighLight());
		testCanvas.highLight(3,4);
		assertTrue("Highlight should be true", testCanvas.getHighLight());
		testCanvas.setHighLight(false);
		assertFalse("Highlight should be false", testCanvas.getHighLight());
	}
	@Test
	public void testSunflowerButton()
	{
		flower = new Sunflower();
		testButton = testView.getSunflowerBtn();
		testView.canvas.highLight(2, 2);
		testButton.doClick();
		assertFalse("Space is occupied", testLevel.placePlant(flower, 2, 2));
		assertEquals("Balance should be 50", 50, testLevel.getBalance());
		
	}
	@Test
	public void testWallnutButton()
	{
		flower = new Sunflower();
		testButton = testView.getWallnutBtn();
		testView.canvas.highLight(2, 2);
		testButton.doClick();
		assertFalse("Space is occupied", testLevel.placePlant(flower, 2, 2));
		int balance = testLevel.getBalance();
		assertEquals("Balance should be 50", 50, balance);
		
	}
	@Test
	public void testPeashooterButton()
	{
		flower = new Sunflower();
		testButton = testView.getPeashooterBtn();
		testView.canvas.highLight(2, 2);
		testButton.doClick();
		assertFalse("Space is occupied", testLevel.placePlant(flower, 2, 2));
		int balance = testLevel.getBalance();
		assertEquals("Balance should be 60", 60, balance);
		
	}
	@Test
	public void testEndButton()
	{
		flower = new Sunflower();
		testLevel.placePlant(flower, 2, 2);
		testButton = testView.getEndBtn();
		testButton.doClick();
		int balance = testLevel.getBalance();
		assertEquals("Balance should be 62", 62, balance);
		
	}
	@Test
	public void testQuitButton()
	{
		testButton = testView.getQuitBtn();
		testButton.doClick();
		assertEquals("Controller should be null", null, testController);
		
	}

}
