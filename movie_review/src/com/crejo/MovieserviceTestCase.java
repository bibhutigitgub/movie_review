package com.crejo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.OrderWith;
import org.junit.runners.MethodSorters;

import com.crejo.bo.MovieService;
import com.crejo.bo.MovieServiceImpl;
import com.crejo.dao.DataStore;
import com.crejo.dao.MovieDetails;
import com.crejo.dao.UserDetails;
import com.crejo.vo.ExceedException;
import com.crejo.vo.MovieUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MovieserviceTestCase {
	MovieUtils utils;
	MovieService impl;
	DataStore dataStore;

	@Before
	public void setUp() throws Exception {
		utils = MovieUtils.getMovieUtils();
		impl = new MovieServiceImpl();
		dataStore = DataStore.getDataStore();
//sds main
	}

	@Test
	public void atestAddUser() {

		String result = impl.addUser("SRK");
		// assertSame("user: Salman added successfully",impl.addUser("Salman"));
		impl.addUser("Salman");
		impl.addUser("Deepika");

		Map<String, UserDetails> mapusers = dataStore.getMapOfUsers();
		Collection<UserDetails> values2 = mapusers.values();
		assertEquals(values2.size(), 3);
		List list = new ArrayList<String>();
		list.add("SRK");
		list.add("Salman");
		list.add("Deepika");
		for (UserDetails details1 : values2) {
			assertTrue(list.contains(details1.getUserName()));
			assertEquals(1, details1.getLevel());
			assertEquals(1, details1.getNumberOftimesMultiplied());

		}
		impl.addUser("Hritik");

		try {
			impl.addUser("Hritik");

		} catch (ExceedException e) {
			System.out.println(e.getMessage());
		}

	}
//main
	@Test
	public void btestAddMovie() {
		impl.addMovie("Don  released in Year 2001 for Genre Action & Comedy ");
		impl.addMovie("Tiger released in Year 2008 for Genre Drama");
		impl.addMovie("Padmaavat released in Year 2006 for Genre Comedy");

		Map<String, MovieDetails> mapOfMovies = dataStore.getMapOfmovies();
		Collection<MovieDetails> values = mapOfMovies.values();

		for (MovieDetails details1 : values) {

			String genre = details1.getGenre();
			String moviename = details1.getMovieName();
			int year = details1.getYear();
			if (moviename.equals("Don")) {
				assertTrue(genre.equals("Action & Comedy"));
				assertEquals(year, 2001);
			} else if (moviename.equals("Tiger")) {
				assertTrue(genre.equals("Drama"));
				assertEquals(year, 2008);
			} else if (moviename.equals("Padmaavat")) {
				assertTrue(genre.equals("Comedy"));
				assertEquals(year, 2006);
			}
		}
		impl.addMovie("Lunchbox released in Year 2022 for Genre Drama");
		impl.addMovie("Guru released in Year 2006 for Genre Drama");
		impl.addMovie("Metro released in Year 2006 for Genre Romance");

		try {
			impl.addMovie("Metro released in Year 2006 for Genre Romance");

		} catch (ExceedException e) {
			System.out.println(e.getMessage());
		}

	}

	@Test
	public void ctestAddReview() {

		System.out.println(impl.addReview("SRK", "Don", 2));
		System.out.println(impl.addReview("SRK", "Padmaavat", 8));
		System.out.println(impl.addReview("Salman", "Tiger", 5));
		System.out.println(impl.addReview("Deepika", "Don", 9));
		System.out.println(impl.addReview("Deepika", "Guru", 6));
		try {
			System.out.println(impl.addReview("SRK", "Don", 2));
		} catch (ExceedException e) {
			System.out.println(e.getMessage());
		}

		try {
			impl.addReview("Deepika", "Lunchbox", 9);

		} catch (ExceedException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(impl.addReview("SRK", "Metro", 6));

		Map<String, UserDetails> mapusers = dataStore.getMapOfUsers();
		UserDetails userDetails = mapusers.get("SRK");
		assertEquals(2, userDetails.getLevel());
		assertEquals(2, userDetails.getNumberOftimesMultiplied());

		System.out.println(impl.addReview("SRK", "Tiger", 6));
		assertEquals(4, userDetails.getMovieList().size());

		try {
			System.out.println(impl.addReview("SRK", "Guru", 11));
		} catch (ExceedException e) {
			System.out.println(e.getMessage());
		}

		impl.addReview("Hritik", "Padmaavat", 8);
		impl.addReview("Hritik", "Don", 5);
		System.out.println(impl.addReview("Hritik", "Guru", 6));

		impl.addReview("Hritik", "Metro", 6);
		impl.addReview("Hritik", "Tiger", 6);

		Map<String, MovieDetails> mapOfMovies = dataStore.getMapOfmovies();
		Collection<MovieDetails> values = mapOfMovies.values();

		for (MovieDetails movieDetails : values) {

			int[] reviewCountList = movieDetails.getReviewsCount();
			int[] usersList = movieDetails.getUsersCount();

			int criticreviews = 0, numberOfCritic = 0, viewerReviews = 0, numberOfViewer = 0;

			criticreviews = reviewCountList[1];
			numberOfCritic = usersList[1];
			viewerReviews = reviewCountList[0];
			numberOfViewer = usersList[0];

			String genre = movieDetails.getGenre();
			String moviename = movieDetails.getMovieName();
			int year = movieDetails.getYear();

			if (moviename.equals("Don")) {

				assertEquals(viewerReviews, 16);
				assertEquals(numberOfViewer, 3);
				assertEquals(criticreviews, 0);
				assertEquals(numberOfCritic, 0);
			} else if (moviename.equals("Tiger")) {
				assertEquals(viewerReviews, 5);
				assertEquals(numberOfViewer, 1);
				assertEquals(criticreviews, 24);
				assertEquals(numberOfCritic, 2);
			} else if (moviename.equals("Padmaavat")) {
				assertEquals(viewerReviews, 16);
				assertEquals(numberOfViewer, 2);
				assertEquals(criticreviews, 0);
				assertEquals(numberOfCritic, 0);
			} else if (moviename.equals("Guru")) {
				assertEquals(viewerReviews, 12);
				assertEquals(numberOfViewer, 2);
				assertEquals(criticreviews, 0);
				assertEquals(numberOfCritic, 0);
			} else if (moviename.equals("Metro")) {
				assertEquals(viewerReviews, 6);
				assertEquals(numberOfViewer, 1);
				assertEquals(criticreviews, 12);
				assertEquals(numberOfCritic, 1);
			} else if (moviename.equals("Lunchbox")) {
				assertEquals(viewerReviews, 0);
				assertEquals(numberOfViewer, 0);
				assertEquals(criticreviews, 0);
				assertEquals(numberOfCritic, 0);
			}
		}

	}

	@Test
	public void dtestGetMoviesByGenre() {
		List<MovieDetails> movielIst = impl.getMoviesByGenre(7, "Drama");

		assertEquals("Tiger", movielIst.get(0).getMovieName());
		assertEquals("Guru", movielIst.get(1).getMovieName());
		assertEquals(2, movielIst.size());

		impl.addMovie("Fast & Furious  released in Year 2008 for Genre Drama");
		impl.addMovie("Dhoom released in Year 2008 for Genre Drama");

		impl.addReview("SRK", "Dhoom", 8);
		impl.addReview("Salman", "Dhoom", 5); // (14,2) , (34 ,2)
		impl.addReview("Deepika", "Dhoom", 9);
		impl.addReview("Hritik", "Dhoom", 9);

		impl.addReview("SRK", "Fast & Furious", 8);
		impl.addReview("Salman", "Fast & Furious", 5); // (5,1),(52,3)
		impl.addReview("Deepika", "Fast & Furious", 9);
		impl.addReview("Hritik", "Fast & Furious", 9);

		movielIst = impl.getMoviesByGenre(7, "Drama");

		assertEquals("Fast & Furious", movielIst.get(0).getMovieName());
		assertEquals("Dhoom", movielIst.get(1).getMovieName());
		assertEquals("Tiger", movielIst.get(2).getMovieName());
		assertEquals("Guru", movielIst.get(3).getMovieName());
		assertEquals(4, movielIst.size());

	}

	@Test
	public void etestGetReviewScoreByYear() {

		assertTrue(7.0 == impl.getReviewScoreByYear(2006));
		assertTrue(10.0 == impl.getReviewScoreByYear(2008));
		try {
			assertTrue(10.0 == impl.getReviewScoreByYear(2022));
		} catch (ExceedException e) {
			System.out.println(e.getMessage());
//fail(e.getMessage());
		}
	}

	@Test
	public void ftestGetReviewScoreByMovie() {

		assertTrue(10.0 == impl.getReviewScoreByMovie("Fast & Furious"));
		assertTrue(10.0 == impl.getReviewScoreByMovie("Dhoom"));
		assertTrue(5.0 == impl.getReviewScoreByMovie("Don"));
		assertTrue(9.0 == impl.getReviewScoreByMovie("Tiger"));
		assertTrue(6.0 == impl.getReviewScoreByMovie("Guru"));

	}

}
