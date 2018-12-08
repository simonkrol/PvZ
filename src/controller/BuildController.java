package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

import model.Level;
import view.View;

public class BuildController implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox cB = (JComboBox) e.getSource();
		switch ((String) cB.getSelectedItem()) {
		case "None":
			
			break;
		case "Zombie":
			
			break;
		case "ImpZombie":
			
			break;
		}
	}

}
