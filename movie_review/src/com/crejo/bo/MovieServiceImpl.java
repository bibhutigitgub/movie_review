package com.crejo.bo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.crejo.dao.DataStore;
import com.crejo.dao.MovieDetails;
import com.crejo.dao.UserDetails;
import com.crejo.vo.CriticsComparator;
import com.crejo.vo.ExceedException;
import com.crejo.vo.InsufficientDetailsException;
import com.crejo.vo.MovieUtils;

public class MovieServiceImpl implements MovieService {

	DataStore dataStore;
	MovieUtils utils = MovieUtils.getMovieUtils();

	public MovieServiceImpl() {
		dataStore = DataStore.getDataStore();
	}

	@Override
	public String addMovie(String movie) {

		MovieDetails movieDetails = utils.createMovieDetails(movie);
		Map<String, MovieDetails> mapOfMovies = dataStore.getMapOfmovies();
		if (mapOfMovies.containsKey(movieDetails.getMovieName()))
			throw new ExceedException("Movie already exist :" + movieDetails.getMovieName());
		mapOfMovies.put(movieDetails.getMovieName(), movieDetails);
		return "movie :" + movieDetails.getMovieName() + " added successfully";

	}

	@Override
	public String addUser(String user) {
		user = utils.removeSpaces(user);
		Map<String, UserDetails> mapOfUsers = dataStore.getMapOfUsers();
		if (mapOfUsers.containsKey(user))
			throw new ExceedException("User already exist :" + user);
		UserDetails details = utils.createUserDetails(user);
		mapOfUsers.put(user, details);
		return "user: " + user + " added successfully";

	}

	@Override
	public String addReview(String userName, String movieName, int review) {
		userName = utils.removeSpaces(userName);

		movieName = utils.removeSpaces(movieName);

		validateReview(userName, movieName, review);

		String status = "review is added successfully";
		Map<String, UserDetails> mapOfUsers = dataStore.getMapOfUsers();
		UserDetails userDetails = mapOfUsers.get(userName);

		Map<String, MovieDetails> mapOfMovies = dataStore.getMapOfmovies();
		MovieDetails movieDetails = mapOfMovies.get(movieName);

		userDetails.getMovieList().add(movieName);

		int prevlevel = userDetails.getLevel();
		int preReviews = userDetails.getReviews();
		userDetails.setReviews(preReviews + userDetails.getNumberOftimesMultiplied() * review);

		int[] usersCount = movieDetails.getUsersCount();
		int[] usersReviewsCount = movieDetails.getReviewsCount();
		usersCount[userDetails.getLevel() - 1] += 1;
		usersReviewsCount[userDetails.getLevel() - 1] += userDetails.getNumberOftimesMultiplied() * review;

		User user = utils.getPromotedUser(userDetails.getMovieList().size());
		if (user.getLevel() > prevlevel) {
			userDetails.setLevel(user.getLevel());
			userDetails.setNumberOftimesMultiplied(user.getNumberOftimesMultiplied());

			status = "Since" + "  " + userName + " has published 3 reviews, he is promoted to ‘" + user.getName()
					+ "’ now";
		}

		return status;
	}

	public boolean isValidGenre(MovieDetails details, String genre) {

		boolean flag = false;
		if (genre.equalsIgnoreCase(details.getGenre()))
			flag = true;
		else {
			String s[] = details.getGenre().split(" ");
			for (int i = 0; i < s.length; i++) {
				if (genre.equalsIgnoreCase(s[i])) {
					flag = true;
					break;
				}
			}
		}
		if (flag) {
			Calendar calendar = Calendar.getInstance();
			if (details.getYear() > calendar.get(Calendar.YEAR))
				flag = false;
			// list.add(details);

		}
		return flag;

	}

	@Override
	public List<MovieDetails> getMoviesByGenre(int n, String genre) {
		if (genre == null || genre.trim() == "")
			throw new InsufficientDetailsException("genre can't be empty : ");

		genre = utils.removeSpaces(genre);

		Map<String, MovieDetails> mapOfMovies = dataStore.getMapOfmovies();
		Collection<MovieDetails> values = mapOfMovies.values();

		List<MovieDetails> list = new ArrayList<MovieDetails>();

		for (MovieDetails details : values) {

			if (isValidGenre(details, genre)) {
				list.add(details);

			}
		}
		Collections.sort(list, new CriticsComparator());

		if (list.size() > n)
			return list.subList(0, n);

		return list;
	}

	@Override
	public float getReviewScoreByMovie(String movie) {

		Map<String, MovieDetails> mapOfMovies = dataStore.getMapOfmovies();
		String s1[] = movie.split(" ");

		movie = utils.removeSpaces(movie);
		if (!mapOfMovies.containsKey(movie))
			throw new InsufficientDetailsException("Movie  doesn't exist ");

		MovieDetails movieDetails = mapOfMovies.get(movie);

		Calendar calendar = Calendar.getInstance();
		if (movieDetails.getYear() > calendar.get(Calendar.YEAR))
			throw new ExceedException("movie yet to be released ");

		int[] countList = movieDetails.getReviewsCount();
		int[] usersList = movieDetails.getUsersCount();

		int reviewsCount = 0;
		for (int c1 : countList)
			reviewsCount += c1;
		int usersCount = 0;
		for (int c2 : usersList)
			usersCount += c2;
		if (usersCount == 0)
			return 0;
		float averageScore = reviewsCount / usersCount;
		if (averageScore > 10)
			return 10;
		else

			return averageScore;

	}

	@Override
	public float getReviewScoreByYear(int year) {

		Calendar calendar = Calendar.getInstance();

		if (year > calendar.get(Calendar.YEAR) || year < 1)
			throw new ExceedException("year should be positive number upto :" + calendar.get(Calendar.YEAR));
		Map<String, MovieDetails> mapOfMovies = dataStore.getMapOfmovies();
		Collection<MovieDetails> values = mapOfMovies.values();

		List<MovieDetails> list = new ArrayList<MovieDetails>();

		int numberUsers, reviewsCount;
		numberUsers = reviewsCount = 0;
		for (MovieDetails details : values) {

			if (details.getYear() == year) {

				int[] reviewcountList = details.getReviewsCount();
				int[] userCountList = details.getUsersCount();

				for (int c1 : userCountList)
					numberUsers += c1;

				for (int c2 : reviewcountList)
					reviewsCount += c2;

			}

		}

		if (numberUsers > 0) {
			float f = reviewsCount / numberUsers;
			if (f > 10)
				return 10;
			return f;

		}

		return 0;
	}

	public void validateReview(String userName, String movieName, int review) {

		if ((userName == null) || (userName.trim() == "") || (movieName == null) || (movieName.trim() == ""))
			throw new InsufficientDetailsException("User and Movie can't be empty  ");
		userName = userName.trim();
		movieName = movieName.trim();

		Map<String, UserDetails> mapOfUsers = dataStore.getMapOfUsers();

		if (review > 10 || review < 1)
			throw new ExceedException("Review score should be between 1 to 10 ");

		if (!mapOfUsers.containsKey(userName))
			throw new InsufficientDetailsException("User does't exist :" + userName);
		UserDetails userDetails = mapOfUsers.get(userName);
		if (userDetails.getMovieList().contains(movieName))
			throw new ExceedException("multiple reviews not allowed");

		Map<String, MovieDetails> mapOfMovies = dataStore.getMapOfmovies();

		if (!mapOfMovies.containsKey(movieName))
			throw new InsufficientDetailsException("Movie  doesn't exist ");
		MovieDetails movieDetails = mapOfMovies.get(movieName);

		Calendar calendar = Calendar.getInstance();
		calendar.get(calendar.YEAR);

		if (movieDetails.getYear() > calendar.get(Calendar.YEAR))
			throw new ExceedException("movie yet to be released ");
	}

}
