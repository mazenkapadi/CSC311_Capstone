package com.example.demoproject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class SampleCommentController {
    @FXML
    private VBox Comment;
    @FXML
    private Label UsernameOfPoster;
    @FXML
    private TextArea PostText;
    @FXML
    private ImageView PostImage;

    // Event Listener on Label[#UsernameOfPoster].onMouseClicked
    @FXML
    public void usernameClick(MouseEvent event) {

    }
}
