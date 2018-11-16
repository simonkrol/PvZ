import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;

public class Controller implements ActionListener, MouseListener {
	Level level;
	View view;
	JButton button;
	protected int x, y;
	private int posY;
	private int posX;

	public Controller(Level lvl, View view) {
		level = lvl;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		button = (JButton) e.getSource();
		if (button.getText().equals("Add plant")) {
			level.add = true;

		} else if (button.getText().equals("End Turn")) {
			try {
				level.allTurn();
				level.spawnZombies();
			} catch (IOException e1) {
			}
			if (level.checkWin()) {
				view.update();
				System.out.println("You Killed all the zombies! \n Congratulations you won!");
				return;
			}
			if (level.checkFail()) {
				view.update();
				System.out.println("Zombies have gotten past! \nGame over! ");
				return;
			} else {
				view.update();
			}
		} else if (button.getText().equals("Quit Game")) {
			view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
			System.exit(0);
		} else if (button.getText().equals("Sunflower")) {
			if (level.add) {
				level.placePlant(new Sunflower(level.getLane(view.canvas.hLY)), view.canvas.hLY, view.canvas.hLX);
				level.add = false;
				view.canvas.highlight = false;
				view.update();
			}
		}

		else if (button.getText().equals("Peashooter")) {
			if (level.add) {
				level.placePlant(new Peashooter(level.getLane(view.canvas.hLY)), view.canvas.hLY, view.canvas.hLX);
				level.add = false;
				view.canvas.highlight = false;
				view.update();
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		Point m = MouseInfo.getPointerInfo().getLocation();
		Point s = view.getLocationOnScreen();
		if (level.add) {
			x = m.x - s.x;
			y = m.y - s.y - 50;
			posY = (int) Math.floor(y / 125);
			posX = (int) Math.floor(x / 125);
			view.canvas.highLight(posX, posY);
			view.update();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}
