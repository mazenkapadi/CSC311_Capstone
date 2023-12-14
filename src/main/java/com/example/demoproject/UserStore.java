package com.example.demoproject;

import java.io.*;
import java.util.TreeMap;

public class UserStore implements Serializable {
    TreeMap<String, User> theMap;
    //private static final long serialVersionUID = 4949855942917852949L;

    public UserStore() {


        theMap = new TreeMap<String, User>();

        try {
            File myObj = new File("USERFILE.ser");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File found.");
                FileInputStream file = new FileInputStream("USERFILE.ser");

                ObjectInputStream in = new ObjectInputStream(new FileInputStream("USERFILE.ser"));

                TreeMap<String, User> theMapList = (TreeMap<String, User>) in.readObject();

                theMap = theMapList;

                in.close();
                file.close();


            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean find(User user) {
        if (user == null)
            return false;
        String username = user.getUsername();
        return theMap.containsKey(username);
    }

    public User getUser(User user) {
        String username = user.getUsername();
        return theMap.get(username);
    }


    public boolean insert(User user) {
        if (theMap.containsValue(user) == false) {
            theMap.put(user.getUsername(), user);
            addToFile();
            return true;
        }
        return false;
    }

    public void delete(User user) {
        theMap.remove(user);
    }


    public void addToFile() {
        try {
            FileOutputStream file = new FileOutputStream("USERFILE.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);


            out.writeObject(theMap);
            out.close();


        } catch (Exception e) {
            System.out.println("Writing error");
        }
    }

    public TreeMap<String, User> getList() {
        return theMap;
    }


}