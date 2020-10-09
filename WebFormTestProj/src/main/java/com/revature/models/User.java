package com.revature.models;

public class User {
	private String username;
	private String password;
	private String favoriteFood;
	private String[] languagesSpoken;
	

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFavoriteFood() {
		return favoriteFood;
	}


	public void setFavoriteFood(String favoriteFood) {
		this.favoriteFood = favoriteFood;
	}


	public String[] getLanguagesSpoken() {
		return languagesSpoken;
	}


	public void setLanguagesSpoken(String[] languagesSpoken) {
		this.languagesSpoken = languagesSpoken;
	}


	public User() {
		
	}
}
