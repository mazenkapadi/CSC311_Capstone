package com.example.demoproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class PostCreatorController implements Initializable {

    static LinkedList<String> wordsTypedWrong;
    @FXML
    TextArea NewPostText;
    @FXML
    Button ChooseImageButton;
    @FXML
    Button CreatePost;
    File image;

    public void CreateNewPost() {

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
                    Post newpost = new Post(NewPostText.getText(), SampleController.currentUser);
                    PostStore store = new PostStore();
                    store.insertPost(newpost);
                    ScrollPane root2;
                    try {
                        root2 = (ScrollPane) FXMLLoader.load(getClass().getResource("TwittlrPage.fxml"));
                        NewPostText.getScene().setRoot(root2);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }


                } else {
                    Post newpost = new Post(NewPostText.getText(), SampleController.currentUser, image);
                    PostStore store = new PostStore();
                    store.insertPost(newpost);
                    try {
                        ScrollPane root2 = (ScrollPane) FXMLLoader.load(getClass().getResource("TwittlrPage.fxml"));
                        NewPostText.getScene().setRoot(root2);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }

        }

    }

    public void ChooseImage() {
        JFrame frame = new JFrame();
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(frame);
        image = fc.getSelectedFile();
    }

    public void BackButton() {
        try {
            ScrollPane root2 = (ScrollPane) FXMLLoader.load(getClass().getResource("TwittlrPage.fxml"));
            NewPostText.getScene().setRoot(root2);
        } catch (IOException e) {
            e.printStackTrace();
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
                if (wordsTypedList[x] != " " && wordsTypedList[x] != "")
                    wordsTypedWrong.add(wordsTypedList[x]);
            }
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {


    }

}
