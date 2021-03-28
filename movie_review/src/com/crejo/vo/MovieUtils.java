package com.crejo.vo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.Year;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import com.crejo.bo.Critic;
import com.crejo.bo.User;
import com.crejo.bo.Viewer;
import com.crejo.dao.MovieDetails;
import com.crejo.dao.UserDetails;

public class MovieUtils {
	public int noUsers = 1;
	User user = new Viewer();
	public static MovieUtils utils = new MovieUtils();

	private void init() {
		try {
			FileReader reader = new FileReader("files/plugin.properties");

			Properties p = new Properties();
			p.load(reader);
			Set<Entry<Object, Object>> users = p.entrySet();
			User[] sortedUsers = new User[users.size()];

			User temp = user;
			for (Entry<Object, Object> entry : users) {

				String value = (String) entry.getValue();
				if (value == "" || value == null)
					continue;
				User u = (User) Class.forName(value).newInstance();
				sortedUsers[u.getLevel() - 2] = u;
			}
			for (User u1 : sortedUsers) {
				if (u1 == null)
					continue;
				temp.next = u1;
				temp = temp.next;
				noUsers += 1;
			}
			//

		} catch (Exception e) {
			throw new InsufficientDetailsException(" Plugin loading failed");
		}

	}

	private MovieUtils() {
		init();
		// TODO Auto-generated constructor stub
	}

	public static MovieUtils getMovieUtils() {
		return utils;
	}

	public User getPromotedUser(int reviews) {
		return user.getPromatedUser(reviews);

	}

	public UserDetails createUserDetails(String name) {
		if (name == null || name.trim() == "")
			throw new InsufficientDetailsException("User can't be empty : " + name);
		UserDetails details = new UserDetails();
		details.setUserName(name.trim());
		return details;

	}

	public MovieDetails createMovieDetails(String movie) {
		boolean movieBoolean, genreBoolean, yearBoolean, yearMatched;
		movieBoolean = genreBoolean = yearBoolean = true;
		// yearMatched=false;
		String movieName, genre, year;
		movieName = genre = year = "";
		movie = utils.removeSpaces(movie);
		String strings[] = movie.split(" ");
		String s1 = "";

		for (int i = 0; i < strings.length; i++) {
			if (movieBoolean) {
				if (strings[i].equalsIgnoreCase("released")) {
					movieName = s1;
					movieBoolean = false;
					continue;
				}
				s1 = (s1 + " " + strings[i]).trim();
			} else if (yearBoolean) {
				if (strings[i].equalsIgnoreCase("Year")/* ||yearMatched */) {
					// yearMatched=true;
					if (i + 1 < strings.length)// &&strings[i+1]!="")
						year = strings[++i];
					yearBoolean = false;
					continue;
				}

			} else if (genreBoolean) {
				if (s1.equalsIgnoreCase("Genre")) {
					genre = (genre + " " + strings[i]).trim();
				} else {
					s1 = strings[i];
				}
			}

		}
		if (movieName.trim() == "" || genre.trim() == "" || year.trim() == "")
			throw new InsufficientDetailsException("Insufficient Movie Details");
		try {
			int year2 = Integer.parseInt(year);
			MovieDetails moviedtails = new MovieDetails();
			moviedtails.setGenre(genre.trim());
			moviedtails.setMovieName(movieName.trim());
			moviedtails.setYear(year2);

			moviedtails.setReviewsCount(new int[noUsers]);
			moviedtails.setUsersCount(new int[noUsers]);

			return moviedtails;

		} catch (Exception e) {
			throw new InsufficientDetailsException("Incorrected Data Format: " + year);
		}

	}

	public String removeSpaces(String string) {
		String s1[] = string.split(" ");
		String temp = s1[0];
		for (int i = 1; i < s1.length; i++) {
			temp = (temp + " " + s1[i]).trim();
		}
		return temp.trim();
	}
}
