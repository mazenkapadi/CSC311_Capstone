package com.example.demoproject;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class Post implements Serializable {
    int Likes;
    int CommentCount;
    String Text;
    User Author;
    LinkedList<Comment> Comments;
    File image;
    SimpleDateFormat formatter;
    Date date;
    String dateString;

    public Post() {
        this.Likes = 0;
        this.CommentCount = 0;
        this.Text = null;
        this.Author = null;
        this.Comments = new LinkedList<Comment>();
        dateString = null;


    }

    public Post(String text, User author) {
        this.Text = text;
        this.Author = author;
        formatter = new SimpleDateFormat();
        date = new Date();
        dateString = formatter.format(date);

    }

    public Post(String text, User author, File myimage) {
        this.Text = text;
        this.Author = author;
        this.image = myimage;
        date = new Date();
        formatter = new SimpleDateFormat();
        dateString = formatter.format(date);
    }

    public void addLike() {
        ++Likes;

        PostStore newp = new PostStore();
        newp.addToFile();
    }

    public void addComment(Comment comment) {

        if (Comments == null) {
            this.Comments = new LinkedList<Comment>();
            Comments.add(0, comment);
            ++CommentCount;

        } else {
            Comments.add(comment);
            ++CommentCount;

        }

        PostStore newp = new PostStore();
        newp.addToFile();
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        this.Text = text;
    }

    public User getAuthor() {
        return this.Author;
    }

    public void setAuthor(User author) {
        this.Author = author;
    }

    public String getImageName() {
        return image.getAbsolutePath();

    }

}
