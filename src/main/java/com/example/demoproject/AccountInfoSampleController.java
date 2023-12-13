package com.example.demoproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import javax.swing.*;
import java.io.IOException;

public class AccountInfoSampleController {
	
	@FXML
	Label UsernameLabel;
	
	@FXML
	Label EmailLabel;
	
	@FXML
	TextArea Biography;
	
	@FXML
	ImageView ProfilePicture;
	
	@FXML
	Button ChangeProfPicButton;
	
	public void ChangeProfPic() {
		
		
		JFrame frame = new JFrame();
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(frame);
        UserStore map = new UserStore();
		map.getUser(SampleController.currentUser).profilePic=fc.getSelectedFile(); 
		map.addToFile();
		
		try {
			ScrollPane root4 = (ScrollPane)FXMLLoader.load(getClass().getResource("AccountPageSample.fxml"));
			ChangeProfPicButton.getScene().setRoot(root4);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void SetNewBio() {
		String Bio = Biography.getText();
		
		
		UserStore map = new UserStore();
		map.getUser(SampleController.currentUser).biography=Bio;
		map.addToFile();

	}
	
	public void Follow() {
		
		System.out.println("Follow");
		
		UserStore Users= new UserStore();
		User myUser = new User (TwittlrPageController.usernameClicked ,null, null);
		
		User AccountUser = Users.getUser(myUser);
		
		Users.getUser(AccountUser).Followers.add(SampleController.currentUser);
		//Users.replaceUser(AccountUser, AccountUser);
		
		Users.getUser(SampleController.currentUser).UsersIFollow.add(AccountUser);
		//Users.replaceUser(SampleController.currentUser, SampleController.currentUser);
		
		Users.addToFile();
		
		
	}
	
	
	
}





