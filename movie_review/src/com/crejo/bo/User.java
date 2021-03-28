package com.crejo.bo;

import java.util.HashSet;
import java.util.Set;

public abstract class User {
	public String name = "user";
	public User next;
	public int numberOftimesMultiplied = 1;
	public int level = 1;
	public int numberOfReviewsNeeded = 1;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void nextUser(User user) {
		this.next = user;
	}

	public User getPromatedUser(int reviews) {
		User user = null;

		
		 if (reviews == numberOfReviewsNeeded||reviews==0)
			return this;
		else if (reviews < numberOfReviewsNeeded)
			return null;
		if (reviews > numberOfReviewsNeeded) {
			if (next == null)
				return this;
			user = next.getPromatedUser(reviews);
			if (user == null)
				user = this;
		}

		return user;

	}

	public int getLevel() {
		// TODO Auto-generated method stub
		return level;
	}

	public int getNumberOftimesMultiplied() {
		return numberOftimesMultiplied;
	}

	public void setNumberOftimesMultiplied(int numberOftimesMultiplied) {
		this.numberOftimesMultiplied = numberOftimesMultiplied;
	}

	public int getNumberOfReviewsNeeded() {
		return numberOfReviewsNeeded;
	}

	public void setNumberOfReviewsNeeded(int numberOfReviewsNeeded) {
		this.numberOfReviewsNeeded = numberOfReviewsNeeded;
	}

}
