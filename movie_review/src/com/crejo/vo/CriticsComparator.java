package com.crejo.vo;

import java.util.Comparator;
import java.util.List;

import com.crejo.bo.Critic;
import com.crejo.dao.MovieDetails;

public class CriticsComparator implements Comparator<MovieDetails> {
	int level = 2;// new Critic().getLevel();

	@Override
	public int compare(MovieDetails o1, MovieDetails o2) {

		int[] usersCount = o1.getUsersCount();
		int[] usersReviewsCount = o1.getReviewsCount();

		float reviewPoint1 = 0;
		if (usersCount[level - 1] != 0)
			reviewPoint1 = usersReviewsCount[level - 1] / usersCount[level - 1];

		int[] usersCount2 = o2.getUsersCount();
		int[] usersReviewsCount2 = o2.getReviewsCount();
		float reviewPoint2 = 0;
		if (usersCount2[level - 1] != 0)
			reviewPoint2 = usersReviewsCount2[level - 1] / usersCount2[level - 1];

		if (reviewPoint1 > reviewPoint2)
			return -1;
		else if (reviewPoint1 == reviewPoint2)
			return 0;
		return 1;
	}

}
