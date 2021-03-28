package com.crejo.bo;

import java.util.List;

import com.crejo.dao.MovieDetails;

public interface MovieService {

	public String addUser(String user);

	public String addMovie(String movie);

	public String addReview(String userName, String movieName, int review);

	public List<MovieDetails> getMoviesByGenre(int n, String string);

	public float getReviewScoreByYear(int year);

	public float getReviewScoreByMovie(String movie);

}
