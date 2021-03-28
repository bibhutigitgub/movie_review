package com.crejo.dao;

import java.util.ArrayList;
import java.util.List;

public class MovieDetails {

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	String movieName;
	String genre;
	int year;
	int[] usersCount;
	int[] reviewsCount;

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int[] getUsersCount() {
		return usersCount;
	}

	public int[] getReviewsCount() {
		return reviewsCount;
	}

	public void setUsersCount(int[] usersCount) {
		this.usersCount = usersCount;
	}

	public void setReviewsCount(int[] reviewsCount) {
		this.reviewsCount = reviewsCount;
	}

}
