package com.example.demoproject;

import java.io.File;
import java.io.Serializable;
import java.util.LinkedList;

public class User implements Comparable<User>, Serializable {

	String username;
	String password;
	String email;
	LinkedList<User> Followers;
	LinkedList<User> UsersIFollow;
	File profilePic;
	String biography;
	
	User rightNode;
	User leftNode;
	
	public User(String user, String passwd, String mail) {
		
		Followers = new LinkedList<User>();
		UsersIFollow = new LinkedList<User>();
		
		username = user;
		password = passwd;
		email = mail;

	}
	
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		
		if (!(obj instanceof User))
			return false;
		
		User user = (User)obj;
		return username.equals(user.username) && password.equals(user.password);
	}
	
	public String getUserInfo() {
		return (username+" "+password+" "+email);
	}
	
	public String getUsername() {
		return (username);
	}
	
	public String getPassword() {
		return (password);
	}

	@Override
	public int compareTo(User o) {
		String u1 =this.username;
		String u2 = o.getUsername();
		if (u1.equals(u2)) {
			return 0;
		}
		
		if (u1.compareTo(u2)>1)
			return 1;


		return -1;
	}
	
	public void setRightNode(User RightUser) {
		this.rightNode=RightUser;
	}
	
	public void setLeftNode(User LeftUser) {
		this.leftNode=LeftUser;
	}
	
	public User getRightNode() {
		return rightNode;
	}
	
	public User getLeftNode() {
		return leftNode;
	}

	public String getStartInterval() {
		
		return null;
	}
	
	public void followAUser(User newUserToFollow) {
		if (UsersIFollow.contains(newUserToFollow)==false)
		UsersIFollow.add(newUserToFollow);
	}
	
	public void AddFollower(User newFollower) {
		if (Followers.contains(newFollower)==false)
		Followers.add(newFollower);

	}
	
	public void setProfilePic(File newPic) {
		this.profilePic=newPic;
	}
	
	public File getProfilePic() {
		return profilePic;
	}
	


	
}
