package com.example.demoproject;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.TreeMap;



public class TwittlrPageController implements Initializable {
	@FXML
	VBox PostObject;
	@FXML
	TextArea PostText;
	@FXML
	Label UsernameOfPoster;
	@FXML
	ImageView PostImage;
	@FXML
	Button CommentButton;
	@FXML
	Label CommentCount;
	@FXML
	Button LikeButton;
	@FXML
	Label LikeCount;
	@FXML
	VBox PostHolder;
	@FXML
	Label TwittlrLabel;
	@FXML 
	CheckBox FollowingOnly;
	 VBox rootUse;
	static VBox root;
	
	 
	 static boolean notMyAccount=false;
	static String usernameClicked;
	
	public void TwittlerPageController() {
		
	

		
	}
	
	public void CreatePostButton() {
		try {
			AnchorPane root3 = (AnchorPane)FXMLLoader.load(getClass().getResource("PostCreator.fxml"));
			PostHolder.getScene().setRoot(root3);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void LogInEvent() {
		try {
		
		//username
			rootUse = (VBox)FXMLLoader.load(getClass().getResource("SampleOfAPost.fxml"));
		PostStore posts = new PostStore();
		LinkedList<Post> PostList = posts.getList();
		
		UserStore Users = new UserStore();
		TreeMap<String, User> myMap = Users.getList();
		//All Posts
		if (SampleController.CheckBoxForMainPage==false ) {
		//
		for (int x=PostList.size()-1; x>=0; x--) {
			root = (VBox)FXMLLoader.load(getClass().getResource("SampleOfAPost.fxml"));
			
			
			
			
			
			
			HBox usernameAndProfPic = (HBox)root.getChildren().get(0);
			
			Label Newlabel = (Label) usernameAndProfPic.getChildren().get(1);
			Newlabel.setText(PostList.get(x).getAuthor().getUsername());
			usernameAndProfPic.getChildren().set(1, Newlabel);
			
			ImageView newPic = (ImageView)usernameAndProfPic.getChildren().get(0);
			if (Users.getUser(PostList.get(x).getAuthor()).profilePic!=null) {
			Image profPic = new Image(Users.getUser(PostList.get(x).getAuthor()).profilePic.getAbsolutePath());
			newPic.setImage(profPic);
			usernameAndProfPic.getChildren().set(0, newPic);
			}
			root.getChildren().set(0, usernameAndProfPic);
			
			TextArea newTextArea = (TextArea)root.getChildren().get(1);
			newTextArea.setText(PostList.get(x).getText());
			root.getChildren().set(1, newTextArea);
			
			ImageView newImage = (ImageView)root.getChildren().get(2);
			
			if (PostList.get(x).image != null) {
			Image image = new Image(PostList.get(x).getImageName());
			
			newImage.setImage(image);
			root.getChildren().set(2, newImage);
			
			HBox HboxArea = (HBox)root.getChildren().get(3);
			Label indexLabel = (Label)HboxArea.getChildren().get(5);
			indexLabel.setText(Integer.toString(x));
			
			Label Time = (Label)HboxArea.getChildren().get(4);
			Time.setText(PostList.get(x).dateString);
			
			Label CommentCount = (Label)HboxArea.getChildren().get(1);
			CommentCount.setText(String.valueOf(PostList.get(x).CommentCount)+" Comments");
			
			Label LikeCount = (Label)HboxArea.getChildren().get(3);
			LikeCount.setText(String.valueOf(PostList.get(x).Likes)+" Likes");
			HboxArea.getChildren().set(3, LikeCount);
			
			root.getChildren().set(3, HboxArea);
			
			
			}
			else {

				root.getChildren().remove(2);
				
				HBox HboxArea = (HBox)root.getChildren().get(2);
				Label indexLabel = (Label)HboxArea.getChildren().get(5);
				indexLabel.setText(Integer.toString(x));
				
				Label Time = (Label)HboxArea.getChildren().get(4);
				Time.setText(PostList.get(x).dateString);
				
				Label CommentCount = (Label)HboxArea.getChildren().get(1);
				CommentCount.setText(String.valueOf(PostList.get(x).CommentCount)+" Comments");
				
				Label LikeCount = (Label)HboxArea.getChildren().get(3);
				LikeCount.setText(String.valueOf(PostList.get(x).Likes)+" Likes");
				HboxArea.getChildren().set(3, LikeCount);
				
				
				this.CommentCount=CommentCount;
				root.getChildren().set(2, HboxArea);
			}
			
			
			
			

			root.setPadding(new Insets(30, 30, 30, 30));
			PostHolder.getChildren().add(root);

			
		}
		// Followers Only
		}
		else {
			//
			for (int x=PostList.size()-1; x>=0; x--) {
				myMap = Users.getList();
				
				LinkedList<User> Following = myMap.get(SampleController.currentUser).UsersIFollow;
				if (Following.contains(PostList.get(x).Author)) {
				
				root = (VBox)FXMLLoader.load(getClass().getResource("SampleOfAPost.fxml"));
				HBox usernameAndProfPic = (HBox)root.getChildren().get(0);
				
				Label Newlabel = (Label) usernameAndProfPic.getChildren().get(1);
				Newlabel.setText(PostList.get(x).getAuthor().getUsername());
				usernameAndProfPic.getChildren().set(1, Newlabel);
				
				ImageView newPic = (ImageView)usernameAndProfPic.getChildren().get(0);
				if (Users.getUser(PostList.get(x).getAuthor()).profilePic!=null) {
					Image profPic = new Image(Users.getUser(PostList.get(x).getAuthor()).profilePic.getAbsolutePath());
					newPic.setImage(profPic);
					usernameAndProfPic.getChildren().set(0, newPic);
					}
				
				root.getChildren().set(0, usernameAndProfPic);
				
				TextArea newTextArea = (TextArea)root.getChildren().get(1);
				newTextArea.setText(PostList.get(x).getText());
				root.getChildren().set(1, newTextArea);
				
				ImageView newImage = (ImageView)root.getChildren().get(2);
				
				if (PostList.get(x).image != null) {
				Image image = new Image(PostList.get(x).getImageName());
				
				newImage.setImage(image);
				root.getChildren().set(2, newImage);
				
				HBox HboxArea = (HBox)root.getChildren().get(3);
				Label indexLabel = (Label)HboxArea.getChildren().get(5);
				indexLabel.setText(Integer.toString(x));
				
				Label Time = (Label)HboxArea.getChildren().get(4);
				Time.setText(PostList.get(x).dateString);
				
				Label CommentCount = (Label)HboxArea.getChildren().get(1);
				CommentCount.setText(String.valueOf(PostList.get(x).CommentCount)+" Comments");
				
				Label LikeCount = (Label)HboxArea.getChildren().get(3);
				LikeCount.setText(String.valueOf(PostList.get(x).Likes)+" Likes");
				HboxArea.getChildren().set(3, LikeCount);
				
				root.getChildren().set(3, HboxArea);
				
				
				
				}
				else {

					root.getChildren().remove(2);
					
					HBox HboxArea = (HBox)root.getChildren().get(2);
					Label indexLabel = (Label)HboxArea.getChildren().get(5);
					indexLabel.setText(Integer.toString(x));
					
					Label Time = (Label)HboxArea.getChildren().get(4);
					Time.setText(PostList.get(x).dateString);
					
					Label CommentCount = (Label)HboxArea.getChildren().get(1);
					CommentCount.setText(String.valueOf(PostList.get(x).CommentCount)+" Comments");
					
					Label LikeCount = (Label)HboxArea.getChildren().get(3);
					LikeCount.setText(String.valueOf(PostList.get(x).Likes)+" Likes");
					
					
					
					this.CommentCount=CommentCount;
					root.getChildren().set(2, HboxArea);
				}
				
				
				
				

				root.setPadding(new Insets(30, 30, 30, 30));
				PostHolder.getChildren().add(root);

				}	
			}
			//
			
			
			
			
		}
		}catch (Exception e) {
			e.printStackTrace();
		}


	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		LogInEvent();
		
	}
	
	public void usernameClick(String username) {
		usernameClicked=username;
		
		try {
			ScrollPane root4 = (ScrollPane)FXMLLoader.load(getClass().getResource("AccountPageSample.fxml"));
			root.getScene().setRoot(root4);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void MyAccountClick() {
		usernameClicked = SampleController.currentUser.getUsername();
		
		try {
			ScrollPane root4 = (ScrollPane)FXMLLoader.load(getClass().getResource("AccountPageSample.fxml"));
			PostHolder.getScene().setRoot(root4);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void postClick() {
		try {
			VBox root4 = (VBox)FXMLLoader.load(getClass().getResource("PostAndComments.fxml"));
			PostHolder.getScene().setRoot(root4);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void likeClick() {
		PostStore store = new PostStore();
		try {
			Post newpost = store.getList().get(SampleOfAPostController.PostClicked);
			newpost.Likes++;
			store.replacePost(SampleOfAPostController.PostClicked, newpost);
			PostStore store2 = new PostStore();
		} catch(Exception e) {
			e.printStackTrace();
		}
		try {
			ScrollPane root4 = (ScrollPane)FXMLLoader.load(getClass().getResource("PostAndComments.fxml"));
			LikeCount.getScene().setRoot(root4);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void CheckBoxClicked() {
		UserStore Users = new UserStore();
		if (SampleController.CheckBoxForMainPage==false) {
			this.FollowingOnly.setSelected(true);
			SampleController.CheckBoxForMainPage=true;
		}
		else {
			this.FollowingOnly.setSelected(false);
			SampleController.CheckBoxForMainPage=false;	
		}
		
		try {
			ScrollPane root2 = (ScrollPane)FXMLLoader.load(getClass().getResource("TwittlrPage.fxml"));
			FollowingOnly.getScene().setRoot(root2);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
	

}
