package com.example.demoproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;

public class CommentCreatorController {
    static LinkedList<String> wordsTypedWrong;
    File image;
    @FXML
    private TextArea NewPostText;
    @FXML
    private Button ChooseImageButton;
    @FXML
    private Button PostCommentButton;

    // Event Listener on Button.onAction
    @FXML
    public void BackButton(ActionEvent event) {
        try {
            ScrollPane root2 = (ScrollPane) FXMLLoader.load(getClass().getResource("PostAndComments.fxml"));
            NewPostText.getScene().setRoot(root2);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // Event Listener on Button[#ChooseImageButton].onAction
    @FXML
    public void ChooseImage(ActionEvent event) {
        JFrame frame = new JFrame();
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(frame);
        image = fc.getSelectedFile();
    }


    public void CreateNewComment() {

        if (wordsTypedWrong != null) {
            int result = 10;
            String wrongwords = "";
            for (int x = 0; x < wordsTypedWrong.size(); x++) {
                if (x < wordsTypedWrong.size() - 1)
                    wrongwords = wrongwords + wordsTypedWrong.get(x) + ", ";
                else
                    wrongwords = wrongwords + wordsTypedWrong.get(x) + " ";
            }
            if (wrongwords != "")
                result = JOptionPane.showConfirmDialog(null, "Are you sure these words are correct? \n" + wrongwords, "Spell Check", JOptionPane.OK_CANCEL_OPTION);
            if (result != 2) {

                if (image == null) {
                    Comment newComment = new Comment(NewPostText.getText(), SampleController.currentUser);
                    PostStore store = new PostStore();
                    try {
                        Post newpost = store.getList().get(SampleOfAPostController.PostClicked);
                        newpost.addComment(newComment);
                        store.replacePost(SampleOfAPostController.PostClicked, newpost);

                        //store.getPost(SampleOfAPostController.PostClicked).addComment(newComment);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        ScrollPane root4 = (ScrollPane) FXMLLoader.load(getClass().getResource("PostAndComments.fxml"));
                        NewPostText.getScene().setRoot(root4);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    PostStore store2 = new PostStore();

                    //	store2.getPost(SampleOfAPostController.PostClicked).addComment(newComment);

                } else {
                    Comment newComment = new Comment(NewPostText.getText(), SampleController.currentUser, image);
                    PostStore store2 = new PostStore();
                    //store2.getPost(SampleOfAPostController.PostClicked).addComment(newComment);

                    Post newpost = store2.getList().get(SampleOfAPostController.PostClicked);
                    newpost.addComment(newComment);
                    store2.replacePost(SampleOfAPostController.PostClicked, newpost);


                    //store.getPost(SampleOfAPostController.PostClicked).addComment(newComment);
                    System.out.println("Comment added");
                    try {
                        ScrollPane root4 = (ScrollPane) FXMLLoader.load(getClass().getResource("PostAndComments.fxml"));
                        NewPostText.getScene().setRoot(root4);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    public void KeyTypedSpellCheck() {


        wordsTypedWrong = new LinkedList<String>();
        String mywordsTyped = NewPostText.getText().toLowerCase();
        mywordsTyped = mywordsTyped.replace("!", " ");
        mywordsTyped = mywordsTyped.replace("?", " ");
        mywordsTyped = mywordsTyped.replace(".", " ");
        mywordsTyped = mywordsTyped.replace(",", " ");
        mywordsTyped = mywordsTyped.replace("...", " ");
        mywordsTyped = mywordsTyped.replace("(", " ");
        mywordsTyped = mywordsTyped.replace(")", " ");
        mywordsTyped = mywordsTyped.replace("!!", " ");
        mywordsTyped = mywordsTyped.replace(":", " ");
        mywordsTyped = mywordsTyped.replace(";", " ");
        String[] wordsTypedList = mywordsTyped.split(" ");
        DictionaryHashing myHash = new DictionaryHashing();
        HashSet<String> hashlist = myHash.getHashSet();
        for (int x = 0; x < wordsTypedList.length; x++) {
            boolean check = hashlist.contains(wordsTypedList[x]);
            if (check == false) {
                wordsTypedWrong.add(wordsTypedList[x]);
            }
        }
    }
}
