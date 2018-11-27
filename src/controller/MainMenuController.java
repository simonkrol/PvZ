package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainMenu;

public class MainMenuController implements ActionListener{

	private MainMenu mm;
	
	public MainMenuController(MainMenu mm) {
		this.mm = mm;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("New Game")) {
			mm.startGame();
		}
		else if (e.getActionCommand().equals("Load Game")){
			//mm.loadGame(levelSave); TO DO
		}
		
	}

}
