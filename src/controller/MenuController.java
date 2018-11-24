package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
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
			
		case "Undo" :
			//add undo function
			
		case "Redo" :
			//add redo function
			
		}
			
	}
}
