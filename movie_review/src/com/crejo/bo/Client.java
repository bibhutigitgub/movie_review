package com.crejo.bo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.crejo.dao.DataStore;
import com.crejo.dao.MovieDetails;
import com.crejo.dao.UserDetails;
import com.crejo.vo.MovieUtils;

public class Client {

	public static void main(String[] args) {
		
		
		MovieUtils utils=MovieUtils.getMovieUtils();
		
		MovieDetails details=utils.createMovieDetails("  Don    and    don1     released   in   Year    2   001 for    Genre    Action    &  Comedy  ");
		System.out.println(details.getGenre());
		System.out.println(details.getMovieName());
		System.out.println(details.getYear());
	
		/*
		User user=utils.getPromotedUser(1);
		System.out.println(user.getLevel());
		user=utils.getPromotedUser(2);
		System.out.println(user.getLevel());
		user=utils.getPromotedUser(3);
		System.out.println(user.getLevel());
		user=utils.getPromotedUser(4);
		System.out.println(user.getLevel());
		user=utils.getPromotedUser(0);
		System.out.println(user.getLevel());
	
		 */
		 
		
		MovieService impl=new MovieServiceImpl();
		impl.addMovie("Don  released in Year 2001 for Genre Action & Comedy ");
		impl.addMovie("Tiger released in Year 2008 for Genre Drama");
		impl.addMovie("Padmaavat released in Year 2006 for Genre Comedy");
		impl.addMovie("Lunchbox released in Year 2022 for Genre Drama");
		impl.addMovie("Guru released in Year 2006 for Genre Drama");
		impl.addMovie("Metro released in Year 2006 for Genre Romance");

		
		
		impl.addMovie("Tiger1 released in Year 2008 for Genre Drama");
		impl.addMovie("Tiger2 released in Year 2008 for Genre Drama");
		impl.addMovie("Fast & Furious     released in Year 2008 for Genre Drama");
		impl.addMovie("Tiger4 released in Year 2008 for Genre Drama");
		impl.addMovie("Tiger5 released in Year 2008 for Genre Drama");
		
		
		
		DataStore dataStore=DataStore.getDataStore();
		Map<String, MovieDetails> mapOfMovies=dataStore.getMapOfmovies();
		Collection<MovieDetails>  values=    mapOfMovies.values();
		/* 
		  for(MovieDetails details1:values) {
			  
			  System.out.println(details1.getGenre());
				System.out.println(details1.getMovieName());
				System.out.println(details1.getYear());
				int userCount[]=details1.getUsersCount();
				System.out.println(userCount[0] +" " +userCount[1]);
				int[] reviewsCount=details1.getReviewsCount();
				System.out.println(reviewsCount[0]+ " "+reviewsCount[1]);
			}
		  */
		  System.out.println(impl.addUser("SRK"));
		  impl.addUser("Salman");
		  impl.addUser("Deepika");
		  
		  impl.addUser("Bibhuti");
		  impl.addUser("Bhushan");
		  impl.addUser("Pradhan");
		  
		/*  
		  Map<String,UserDetails> mapusers=dataStore.getMapOfUsers();
			Collection<UserDetails>  values2=    mapusers.values();
			 
			  for(UserDetails details1:values2) {
				  System.out.println(details1.userName);
					System.out.println(details1.getLevel());
					System.out.println(details1.getReviews());
					System.out.println(details1.getNumberOftimesMultiplied());
					System.out.println(details1.getMovieList());
				}
			
		  */
		  
		 System.out.println( impl.addReview("SRK", "Don", 2));
		 System.out.println(  impl.addReview("SRK", "Padmaavat", 8));
		 System.out.println( impl.addReview("Salman", "Don", 5));
		 System.out.println( impl.addReview("Deepika", "Don", 9));
		 System.out.println(  impl.addReview("Deepika", "Guru", 6));
		  
		  
		  
		 System.out.println(  impl.addReview("Bibhuti", "Padmaavat", 8));
		 //System.out.println( impl.addReview("Bibhuti", "Don", 5));
		 System.out.println(  impl.addReview("Bibhuti", "Guru", 6));
		  
		 System.out.println(  impl.addReview("Bhushan", "Padmaavat", 8));
		 //System.out.println(  impl.addReview("Bhushan", "Don", 5));
		 System.out.println(  impl.addReview("Bhushan", "Guru", 6));
		  
		 System.out.println( impl.addReview("Pradhan", "Padmaavat", 8));
		// System.out.println(  impl.addReview("Pradhan", "Don", 5));
		 System.out.println( impl.addReview("Pradhan", "Guru", 6));
		 
		 
		  
		 
		  impl.addReview("Bibhuti", "Tiger1", 6);
		  impl.addReview("Bibhuti", "Don", 1);
		  impl.addReview("Bibhuti", "Fast & Furious", 4);
		  impl.addReview("Bibhuti", "Tiger4", 3);
		  
		  
		  impl.addReview("Bhushan", "Tiger1", 1);
		  impl.addReview("Bhushan", "Tiger2", 2);
		  impl.addReview("Bhushan", "Don", 3);
		  impl.addReview("Bhushan", "Tiger4", 4);
		  
		  impl.addReview("Pradhan", "Don", 4);
		  impl.addReview("Pradhan", "Tiger2", 2);
		  impl.addReview("Pradhan", "    Fast   &   Furious  ", 5);
		  impl.addReview("Pradhan", "Tiger4", 4);
		  
		  impl.addReview("Pradhan", "Tiger5", 9);
		  impl.addReview("Salman", "Tiger5", 9);
		  
		  
		  
		  
		 //impl.addReview("SRK", "Don", 10);
		   	
		 // impl.addReview("Deepika", "Lunchbox", 5);
		  System.out.println(impl.addReview("SRK", "Tiger", 5));
		  System.out.println(impl.addReview("SRK", "Metro", 7));
		  
		   dataStore=DataStore.getDataStore();
			 mapOfMovies=dataStore.getMapOfmovies();
			  values=    mapOfMovies.values();
			 
			  for(MovieDetails details1:values) {
				  System.out.println(details1.getGenre());
					System.out.println(details1.getMovieName());
					System.out.println(details1.getYear());
					int userCount[]=details1.getUsersCount();
					System.out.println(userCount[0] +" " +userCount[1]);
					int[] reviewsCount=details1.getReviewsCount();
					System.out.println(reviewsCount[0]+" "+reviewsCount[1]);
				}
		  
			System.out.println(" ---------------------------------------------------------------");
			
			  //List<MovieDetails>  values1= impl.getMoviesByGenre(7, "Drama");
			  List<MovieDetails>  values1= impl.getMoviesByGenre(7, "Action   &   Comedy");
			 
			  for(MovieDetails details1:values1) {
				  System.out.println(details1.getGenre());
					System.out.println(details1.getMovieName());
					System.out.println(details1.getYear());
					
					int userCount[]=details1.getUsersCount();
					System.out.println(userCount[0] +" " +userCount[1]);
					int[] reviewsCount=details1.getReviewsCount();
					System.out.println(reviewsCount[0]+ " "+reviewsCount[1]);
				}
				
			  
		/*	  
		  System.out.println(impl.getReviewScoreByMovie("Tiger4"));
		  System.out.println(impl.getReviewScoreByMovie("Tiger1"));
		  System.out.println(impl.getReviewScoreByMovie("Tiger2"));
		  */
		  System.out.println(impl.getReviewScoreByMovie("   Fast    & Furious   "));
		  System.out.println(impl.getReviewScoreByMovie("Don"));
			  
		  
			  System.out.println(impl.getReviewScoreByYear(2006));
		 System.out.println(impl.getReviewScoreByYear(2008));
		 // Date date=new Date();
		  //date.
		  
	}

}
