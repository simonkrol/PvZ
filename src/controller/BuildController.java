package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JComboBox;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Level;
import view.View;

public class BuildController implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		

		if(e.getActionCommand() == "Done"){
			FileWriter fw;
			System.out.println("Done");
		}
		else{
			JComboBox cB = (JComboBox) e.getSource();
			
			String turnJson = "\"turns\": {\n ";
			// String tNumJson = levelbuilder.TurnNumtextfield.gettext() + ": {\n "; **FIX**
			switch ((String) cB.getSelectedItem()) {
			case "None":
				System.out.println("None");
				
				break;
			case "BasicZombie":
				System.out.println("bz");
				break;
			case "ImpZombie":
				System.out.println("iz");
				break;
		
			case "BucketZombie":
				System.out.println("bz");
				break;
			}
		
		}
	}

}
