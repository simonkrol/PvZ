package controller;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainMenu;

public class MainMenuController extends Controller implements ActionListener{

	private MainMenu mm;
	
	public MainMenuController(MainMenu mm) {
		super(null, null);
		this.mm = mm;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("New Game")) {
			mm.startGame();
		}
		else if (e.getActionCommand().equals("Load Game")){
			FileDialog fd = new FileDialog(mm.frame, "Choose a file", FileDialog.LOAD);
			fd.setDirectory("res/saves");
			fd.setFile("*.json");
			fd.setVisible(true);
			String filename = fd.getFile();
			if (filename != null)
				loadGame(filename);
		}
		
	}

}
