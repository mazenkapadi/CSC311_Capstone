package com.example.demoproject;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment implements Serializable {
    String text;
    File image;
    User author;
    SimpleDateFormat formatter;
    Date date;
    String dateString;


    public Comment() {

    }

    public Comment(String text, User author) {
        this.text = text;
        this.author = author;
        date = new Date();
        formatter = new SimpleDateFormat();
        dateString = formatter.format(date);
    }

    public Comment(String text, User author, File myimage) {
        this.text = text;
        this.author = author;
        this.image = myimage;
        date = new Date();
        formatter = new SimpleDateFormat();
        dateString = formatter.format(date);
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
