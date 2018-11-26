package view;
import org.junit.Before;
import java.awt.*;
import org.junit.Test;

import controller.Controller;
import model.Level;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
public class TestView
{
	View testView;
	Level testLevel;
	Controller testController;
	JLabel testLabel;
	@Before
	public void setUp() throws Exception
	{
		testLevel = new Level(5,6,100, "res/levels/nullLevel.txt");
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
//	@Test
//	public void testCalcBlockSize()
//	{
//		Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		int blockHeight = (int)(screenSize.getHeight()- scnMax.bottom - scnMax.top - 2*testView.information.getHeight() - testView.selections.getHeight()) / testLevel.getHeight();
//		int blockWidth = (int)(screenSize.getWidth() / testLevel.getWidth());
//	}
}
