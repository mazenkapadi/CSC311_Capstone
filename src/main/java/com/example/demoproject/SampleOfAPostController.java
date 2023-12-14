package com.example.demoproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class SampleOfAPostController {
    static int PostClicked;
    @FXML
    VBox PostObject;
    @FXML
    Label UsernameOfPoster;
    @FXML
    TextArea PostText;
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
    Label indexLabel;
    int x = 0;

    // Event Listener on Button[#CommentButton].onAction
    @FXML
    public void TwittlerPageController(ActionEvent event) {

    }

    public void usernameClick() {
        TwittlrPageController con = new TwittlrPageController();
        con.usernameClick(this.UsernameOfPoster.getText());
    }

    public void postClick() {
        PostClicked = Integer.parseInt(indexLabel.getText());
        try {
            ScrollPane root4 = (ScrollPane) FXMLLoader.load(getClass().getResource("PostAndComments.fxml"));
            PostObject.getScene().setRoot(root4);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void LikeClick() {
        PostClicked = Integer.parseInt(indexLabel.getText());
        PostStore store = new PostStore();
        try {
            Post newpost = store.getList().get(SampleOfAPostController.PostClicked);
            newpost.addLike();
            store.replacePost(SampleOfAPostController.PostClicked, newpost);
            PostStore store2 = new PostStore();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ScrollPane root2 = (ScrollPane) FXMLLoader.load(getClass().getResource("TwittlrPage.fxml"));
            LikeCount.getScene().setRoot(root2);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }


}
