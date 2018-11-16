import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

/**
 * The Text based GUI being used to play Milestone 1
 * 
 * @author Boyan Siromahov
 * @version Oct 29, 2019
 */
public class View extends JFrame {
	private GameCanvas canvas;
	private Level level;
	JLabel info;
	JPanel information;
	protected int x, y;
	protected boolean add;

	public View(Level lvl) {
		level = lvl;
		canvas = new GameCanvas(lvl);
		setLayout(new BorderLayout());
		setSize(lvl.getWidth() * 127, lvl.getHeight() * 125 + 300);
		setTitle("Plants Vs Zombies - Ghetto Edition");
		add("Center", canvas);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		information = new JPanel();
		information.setLayout(new FlowLayout());
		info = new JLabel();
		info.setText("SUN: " + level.getBalance() + "  Turn: " + level.turn);
		information.add(info);
		add(information, BorderLayout.PAGE_START);
		
		
		
		
		
		JPanel selections = new JPanel();
		selections.setLayout(new BoxLayout(selections, BoxLayout.PAGE_AXIS));
		
		JPanel plants = new JPanel();
		plants.setLayout(new FlowLayout());
		
		JButton sunflowerBtn = new JButton();
		sunflowerBtn.setSize(125,125);
		sunflowerBtn.setIcon(new ImageIcon("bin/rsz_unknown.png"));
		plants.add(sunflowerBtn);
		
		JButton peashooterBtn = new JButton();
		peashooterBtn.setSize(125,125);
		peashooterBtn.setIcon(new ImageIcon("bin/peaShooter.png"));
		plants.add(peashooterBtn);
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		
		JButton add = new JButton("Add plant");
		add.setSize(50,100);
		buttons.add(add);
		
		JButton end = new JButton("End Turn");
		end.setSize(50,100);
		buttons.add(end);
		
		selections.add(buttons);
		selections.add(plants);
		add(selections, BorderLayout.PAGE_END);
		
		revalidate();
		
		
		
		
		//action listeners
		canvas.addMouseListener(new ClickListerner(this));
		add.addActionListener(new ButtonListener());
		end.addActionListener(new ButtonListener());
		sunflowerBtn.addActionListener(new ButtonListener());
		peashooterBtn.addActionListener(new ButtonListener());
	}
	
	public void update() {
		canvas.repaint();
		info.setText("SUN: " + level.getBalance() + "  Turn: " + level.turn);
		System.out.println(x + "  " + y);
	}

	
	private void addPlant(Plant plant) {
		if(add) {
			//add plant to canvasn
		}
	}
}

class GameCanvas extends Canvas {
	private Level level;
	
	public GameCanvas(Level lvl) {
		level = lvl;
	}
	
	@Override
	public void paint(Graphics g) {
		Image grass = new ImageIcon("bin/grass.jpg").getImage();
		Image zombie = new ImageIcon("bin/zombie.png").getImage();
		Image sunflower = new ImageIcon("bin/rsz_unknown.png").getImage();
		Image peashooter = new ImageIcon("bin/peaShooter.png").getImage();
		
		int x = 0, y = 0;
		for (Lane lane : level.grid) {
			for (Spot spot : lane.spots) //draw plants
			{ 
				g.drawImage(grass, x, y, this);
				
				if(spot.getPlant() instanceof Sunflower) 
				{
					g.drawImage(sunflower, x, y, this);
				}
				else if(spot.getPlant() instanceof Peashooter) 
				{
					g.drawImage(peashooter, x, y, this);
				}
				
				x += 125;
			}
			
			for(Zombie zmb : lane.liveZombies) {
				int pos = zmb.getPosition();
				if(level.getWidth()*125 - zmb.getPosition() < 0) {
					pos = 0;
				}
				else {
					pos = level.getWidth()*125 - zmb.getPosition();
				}
				g.drawImage(zombie, pos, y, this);
			}
			x = 0;
			y += 125;
		}
		

	}

}
