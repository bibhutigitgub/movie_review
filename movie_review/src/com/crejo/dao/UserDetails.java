package com.crejo.dao;

import java.util.HashSet;
import java.util.Set;

public class UserDetails {
	public String userName = "";
	public Set<String> movieList = new HashSet<String>();
	int level = 1;
	int reviews;
	int numberOftimesMultiplied = 1;

	public int getNumberOftimesMultiplied() {
		return numberOftimesMultiplied;
	}

	public void setNumberOftimesMultiplied(int numberOftimesMultiplied) {
		this.numberOftimesMultiplied = numberOftimesMultiplied;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getReviews() {
		return reviews;
	}

	public void setReviews(int reviews) {
		this.reviews = reviews;
	}

	public int getLevel() {
		// TODO Auto-generated method stub
		return level;
	}

	public Set<String> getMovieList() {
		// TODO Auto-generated method stub
		return movieList;
	}

	public void setLevel(int level) {
		// TODO Auto-generated method stub
		this.level = level;
	}

	public void setMovieList(Set<String> movieList) {
		// TODO Auto-generated method stub
		this.movieList = movieList;
	}

}
