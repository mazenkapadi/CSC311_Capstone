package com.example.demoproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.TreeMap;

public class SampleController implements Serializable {
	

	static User currentUser;
	static boolean CheckBoxForMainPage = false;

	

	UserStore userMap = new UserStore();
	@FXML
	public TextField SignUpUsernameText;
	@FXML
	public TextField SignUpPasswordText;
	@FXML
	public  TextField SignUpEmailText;
	@FXML
	public TextField LogInUsernameText;
	@FXML
	public TextField LogInPasswordText;
	@FXML
	Button LogInButton = new Button();
	@FXML
	BorderPane LogInSignUpHolder;
	
	
	public void loadUsers(File userFile) {
		
	}
	
	
	public void SignUpEvent(ActionEvent e) {
		
		
		TreeMap<String, User> myList = userMap.getList();

	
		boolean InsertCheck = userMap.insert(new User(SignUpUsernameText.getText(),SignUpPasswordText.getText(),SignUpEmailText.getText()));
		if (InsertCheck==true) {
			JOptionPane.showMessageDialog(null, "Signed up!", "Sign Up Request", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(null, "Username Already Exists!", "Sign Up Request", JOptionPane.INFORMATION_MESSAGE);
		}


	}
	
	
	public void LogInEvent(ActionEvent e) {
		
			
		User myUser = new User (LogInUsernameText.getText(),LogInPasswordText.getText(), null);
		try {
			this.currentUser= userMap.getUser(myUser);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		if (userMap.find(this.currentUser)==true) {
			currentUser = userMap.getUser(currentUser);
			System.out.println (userMap.getUser(currentUser).getPassword());
			System.out.println (LogInPasswordText.getText());
			if (userMap.getUser(currentUser).getPassword().compareTo(LogInPasswordText.getText())==0) {
				JOptionPane.showMessageDialog(null, "Logged In Successfully!", "Log In Request", JOptionPane.INFORMATION_MESSAGE);	
				

				
				//Main newmain = new Main();
				//newmain.login=true;
				
				//newmain.start(newmain.primaryStage);
		
				
				try {
					ScrollPane root2 = (ScrollPane)FXMLLoader.load(getClass().getResource("TwittlrPage.fxml"));
					LogInUsernameText.getScene().setRoot(root2);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

			}
			else
				JOptionPane.showMessageDialog(null, "Incorrect Password", "Log In Request", JOptionPane.INFORMATION_MESSAGE);
			
		}
		else
			JOptionPane.showMessageDialog(null, "Account Not Found", "Log In Request", JOptionPane.INFORMATION_MESSAGE);	
			
			
			
	}
	
	public User getCurrentUser() {
		return currentUser;
	}
	
	
		
		
		

	

	
	
}
