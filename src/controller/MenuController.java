package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JMenuItem;

import model.Level;
import view.View;

public class MenuController implements ActionListener{
	JMenuItem menuButton;
	private Level level;
	private View view;
	
	
	public MenuController(Level lvl, View view)
	{
		level = lvl;
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		menuButton = (JMenuItem) e.getSource();
		switch(menuButton.getActionCommand()) {
		case "Quit" :
			view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
			System.exit(0);
			break;
			
		case "Undo" :
			level.undo();
			view.update();
			break;
			
		case "Redo" :
			level.redo();
			view.update();
			break;
		}
			
	}
}
